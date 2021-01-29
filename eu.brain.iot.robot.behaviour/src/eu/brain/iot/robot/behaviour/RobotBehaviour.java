package eu.brain.iot.robot.behaviour;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.component.annotations.Reference;
import java.util.function.Predicate;
import org.osgi.util.promise.Deferred;
import org.osgi.util.promise.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import eu.brain.iot.service.robotic.door.api.DoorStatusResponse;
import eu.brain.iot.service.robotic.door.api.DoorStatusRequest.State;
import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.BrainIoTEvent;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.api.Command;
import eu.brain.iot.robot.events.*;
import eu.brain.iot.robot.events.QueryStateValueReturn.CurrentState;
import eu.brain.iot.robot.api.RobotCommand;
import eu.brain.iot.warehouse.events.CartMovedNotice;
import eu.brain.iot.warehouse.events.CartNoticeResponse;
import eu.brain.iot.warehouse.events.DockingRequest;
import eu.brain.iot.warehouse.events.DockingResponse;
import eu.brain.iot.warehouse.events.NewPickPointRequest;
import eu.brain.iot.warehouse.events.NewPickPointResponse;
import eu.brain.iot.warehouse.events.NewStoragePointRequest;
import eu.brain.iot.warehouse.events.NewStoragePointResponse;
import eu.brain.iot.warehouse.events.NoCartNotice;

@Component(
		immediate=true,
		configurationPid = "eu.brain.iot.example.robot.RobotBehavior", 
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		scope=ServiceScope.SINGLETON,
		service = {SmartBehaviour.class})

@SmartBehaviourDefinition(consumed = { NewPickPointResponse.class, NewStoragePointResponse.class, DockingResponse.class, CartNoticeResponse.class, MarkerReturn.class, QueryStateValueReturn.class, RobotReadyBroadcast.class,
		DoorStatusResponse.class, AvailabilityReturn.class}, 
		author = "LINKS", name = "Robot Behavior", 
		description = "Implements a Robot Behavior.")

public class RobotBehaviour implements SmartBehaviour<BrainIoTEvent> {

	private int robotID;
	private String robotIP;
	private boolean robotReady = false;
	private static volatile QueryStateValueReturn queryReturn;
	private static volatile int markerID = 0;
	private static volatile int newMarkerCounter = 0;
	private static volatile int currentMarkerCounter = 0;
	private boolean isDoorOpen = false;
	private static volatile NewPickPointResponse pickResponse = null;
	private static NewStoragePointResponse storageResponse = null;
	private static DockingResponse dockingResponse = null;
	private static CartNoticeResponse cartNoticeResponse = null;
	private ConfigurationAdmin cm;
	private BundleContext context;
	

/*	@ObjectClassDefinition
	public static @interface Config {

		@AttributeDefinition(description = "The identifier for the robot behaviour")
		int id();

	}*/
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RobotBehaviour.class.getSimpleName());

//	private Config config;
	private ExecutorService worker;
	private ServiceRegistration<?> reg;

	@Reference
	private EventBus eventBus;
	
	@Reference
    void setConfigurationAdmin(ConfigurationAdmin cm) {
        this.cm = cm;
    }

	@Activate
	void activate(BundleContext context, /*Config config,*/ Map<String, Object> props) {
	/*	this.config = config;
		this.robotID = config.id();*/
		this.context = context;
		
		String UUID = context.getProperty("org.osgi.framework.uuid");
		
		logger.info("\nHello!  I am robotBehavior : " + robotID + ",  UUID = "+UUID);

		worker = Executors.newFixedThreadPool(10);

		Dictionary<String, Object> serviceProps = new Hashtable<>(props.entrySet().stream()
				.filter(e -> !e.getKey().startsWith(".")).collect(Collectors.toMap(Entry::getKey, Entry::getValue)));

	//	serviceProps.put(SmartBehaviourDefinition.PREFIX_ + "filter",  // only receive some sepecific events with robotID
	//			String.format("(|(robotID=%s)(robotID=%s))", robotID, RobotCommand.ALL_ROBOTS));

		logger.info("+++++++++ Robot Behaviour filter = " + serviceProps.get(SmartBehaviourDefinition.PREFIX_ + "filter"));
		reg = context.registerService(SmartBehaviour.class, this, serviceProps);

		 onStart();
	}

	

	public void onStart() {

		worker.execute(() -> {

			boolean nextIteration = true;
			int pickCounter = 1;
			int storageCounter = 1;
			// worker.execute(() -> {

			while (nextIteration) {

				if (robotReady) {

					boolean query = true;
					String pickPoint = null;
					pickCounter = 1;
					storageCounter = 1;

					// --------------------------- Query Pick point --------------------------------------
					logger.info("--------------------------- Query Pick point --------------------------------------");
					while (query) {

						NewPickPointRequest pickRequest = new NewPickPointRequest();
						pickRequest.robotID = this.robotID;
						RobotBehaviour.pickResponse = null;
						eventBus.deliver(pickRequest);

						waitPickResponse();

				//		if (getPickResponse().hasNewPoint) {
						if (RobotBehaviour.pickResponse.hasNewPoint) {	
							logger.info("----------- has new Pick Point = true-------------");
							pickPoint = getPickResponse().pickPoint;
							if(pickPoint == null) {
								logger.info("-->no pick point, RB exit!");
								stop();
							}
							logger.info("-->RB" + robotID + " get new Pick Point: " + pickPoint);
							break;
							
						} else {

							if (pickCounter > 0) { // just ask for 2 times
								logger.info("-->RB" + robotID + " doesn't get any Pick Point, continue to query after 10s");
								// TODO continue to query new pick point
								try {
									TimeUnit.SECONDS.sleep(10);
								} catch (InterruptedException e) {
									logger.error("\n Exception:", e);
								}
								pickCounter--;
								continue;
							} else {
								nextIteration = false;
								logger.info("-->RB" + robotID + " doesn't get any Pick Point, all carts have been moved, exit! ");
								break;
							}

						}
					} // while

					if (nextIteration) { // it means a new pick point is found, the break is not because no pick point is found
						
						// --------------------------- Go to Picking point --------------------------------------
						logger.info("--------------------------- Go to Picking point --------------------------------------");

						if (!executeGoTo(pickPoint, "Picking point")) {
							break; // execution failed
						}
						
						// --------------------------- check Cart Marker --------------------------------------
						logger.info("--------------------------- check Cart Marker --------------------------------------");

						CheckMarker checkMarker = createCheckMarker(); // CheckMarker
						eventBus.deliver(checkMarker);
						logger.info("-->RB" + robotID + " sending CheckMarker");

						int newMarkerID = waitMarker();
						logger.info("-->RB" + robotID + " got new MarkerID = " + newMarkerID);

						// ---------------------------TODO: No Cart Notice----------THEN Cancel current
						// mission? how?--how to handle no marker found on topic in ros node.---can't
						// always wait.-----------------------

						// 1. how to know no cart here, then run:

						/*
						 * NoCartNotice noCartNotice = createNoCartNotice(); cartNoticeResponse = null;
						 * eventBus.deliver(noCartNotice); System.out.println("-->RB" + robotID +
						 * " sending NoCartNotice");
						 * 
						 * waitCartNoticeResponse(); // noticeStatus = "OK" continue;
						 */
						// ---------------------------TODO: Cancel current action after detecting  Anomaly--------------------------------------

						// --------------------------- Pick Cart --------------------------------------
						logger.info("--------------------------- Pick Cart --------------------------------------");
						
						PickCart pickCart = createPickCart(newMarkerID); // PickCart
						queryReturn = null;
						eventBus.deliver(pickCart);
						logger.info("-->RB" + robotID + " is sending PickCart with robotID = "+pickCart.robotID);

						if (waitQueryReturn(pickCart.command)) { // always true.
							CurrentState currentState = queryReturn.currentState;

							if (currentState.equals(CurrentState.unknown)) {
								robotReady = false;
								logger.info("-->RB" + robotID + " execute PickCart action failed, Robot Behavior stops !!!!");
								break;
							} else { // FINISHED
								logger.info("-->RB " + robotID + " Pick Cart successfully");
							}
						}
						

						// --------------------------- Query Storage point --------------------------------------
						logger.info("--------------------------- Query Storage point --------------------------------------");
						while (query) {

							NewStoragePointRequest storageRequest = new NewStoragePointRequest();
							storageRequest.robotID = this.robotID;
							storageRequest.markerID = markerID;
							RobotBehaviour.storageResponse = null;
							eventBus.deliver(storageRequest);

							waitStorageResponse();

							if (RobotBehaviour.storageResponse.hasNewPoint) {
								logger.info("-----------has new Storage Point-------------");
								break;
							} else {
								if (storageCounter > 0) { // just ask for 2 times
									logger.info("-->RB" + robotID + " doesn't get any Storage Point, continue to query after 10s");
									try {
										TimeUnit.SECONDS.sleep(10);
									} catch (InterruptedException e) {
										logger.error("\n Exception:", e);
									}
									storageCounter--;
									continue;
								} else {
									nextIteration = false;
									logger.info("-->RB" + robotID + " doesn't get any Storage Point for this cart after querying for 2 times, exit! ");
									break;
								}
							}
						} // while
						if (nextIteration) {

							// --------------------------- Go to Storage AUX -------------------------------------
							logger.info("--------------------------- Go to Storage AUX --------------------------------------");
							
							if (!executeGoTo(RobotBehaviour.storageResponse.storageAuxliaryPoint, "storage AUX")) {
								break; // execution failed
							}
							
							// --------------------------- check Door Marker  --------------------------------------
							logger.info("--------------------------- Check Door Marker --------------------------------------");
							
							CheckMarker checkDoorMarker = createCheckMarker(); // CheckMarker
							eventBus.deliver(checkDoorMarker);
							logger.info("-->RB" + robotID + " is sending cmd to check Door Marker");

							int DoorID = waitMarker();
							logger.info("-->RB" + robotID + " got DoorID = " + DoorID);

							
							// --------------------------- Go to Storage Point --------------------------------------
							logger.info("--------------------------- Go to Storage Point --------------------------------------");
							
							if (!executeGoTo(RobotBehaviour.storageResponse.storagePoint, "storage Point")) {
								break; // execution failed
							}

							// --------------------------- Place Cart --------------------------------------
							logger.info("--------------------------- Place Cart --------------------------------------");
							
							PlaceCart placeCart = createPlaceCart(); // PickCart
							queryReturn = null;
							eventBus.deliver(placeCart);
							logger.info("-->RB" + robotID + " sending placeCart");

							if (waitQueryReturn(placeCart.command)) { // always true. otherwise it always query
								CurrentState currentState = queryReturn.currentState;

								if (currentState.equals(CurrentState.unknown)) {
									robotReady = false;
									logger.info("-->RB" + robotID + " execute PickCart action failed, Robot Behavior stops !!!!");
									break;
								} else { // FINISHED
									logger.info("-->RB " + robotID + " Place Cart successfully");
								}
							}

							// --------------------------- Cart Moved Notice --------------------------------------
							logger.info("--------------------------- Cart Moved Notice --------------------------------------");
							
							CartMovedNotice cartMovedNotice = createCartMovedNotice();
							cartNoticeResponse = null;
							eventBus.deliver(cartMovedNotice);
							logger.info("-->RB" + robotID + " is sending CartMovedNotice");

							waitCartNoticeResponse(); // noticeStatus = "OK"

							logger.info("-->RB" + robotID + " got CartNoticeResponse");

							// --------------------------- Docking Request--------------------------------------
							logger.info("--------------------------- Docking Request --------------------------------------");
							
							DockingRequest dockingRequest = createDockingRequest();
							dockingResponse = null;
							eventBus.deliver(dockingRequest);
							logger.info("-->RB" + robotID + " is sending DockingRequest with robotIP = "+ robotID); // TODO change to robotIP

							if (waitDockingResponse()) {
								if (dockingResponse.hasNewPoint) {

									// --------------------------- Go to Docking AUX -------------------------------------
									logger.info("--------------------------- Go to Docking AUX --------------------------------------");

									
									if (!executeGoTo(dockingResponse.dockAuxliaryPoint, "docking AUX")) {
										break; // execution failed
									}
									// --------------------------- check Door Marker --------------------------------------
									logger.info("--------------------------- Check Door Marker --------------------------------------");

									
									CheckMarker checkDoorMarker2 = createCheckMarker(); // CheckMarker
									eventBus.deliver(checkDoorMarker2);
									logger.info("-->RB" + robotID + " sending check Door Marker on the way to Docking area");

									int DoorID2 = waitMarker();
									logger.info("-->RB" + robotID + " got DoorID = " + DoorID2);

									
									// --------------------------- Go to Docking Point --------------------------------------
									logger.info("--------------------------- Go to Docking Point --------------------------------------");

									if (!executeGoTo(dockingResponse.dockingPoint, "dock Point")) {
										break; // execution failed
									}
								} else {
									logger.info("-->RB" + robotID + " exit because NO Docking point found ");
									nextIteration = false;
									break;
								}
							}
						} // end of if(nextIteration), new storage point is found
						else { // nextIteration = false
							break; // no storage are found for this cart
						}

					} // end of if(nextIteration), new pick point is found
					else {
						break; // Tasks are done
					}

				} else { // robotReady = false
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						logger.error("\n Exception:", e);
					}
				}

			} // end of ==> while (nextIteration)

			if (nextIteration == false) { // only when normal exit(no pick, no storage after querying for a long time)
				logger.info("-->Tasks are done. Robot Behavior " + robotID + " exit !!!");
			} else {
				logger.info("-->RB " + robotID + "  exit because of failure in robot!!!");
			}
		}

		);

	}
	
	@Modified
    void modified(Map<String, Object> properties) {
		logger.info("\n\n --> RB " + robotID + "  has osgi service properties :" + properties);

    }

	@Override
	public void notify(BrainIoTEvent event) {
		logger.info("-->RB " + robotID + " received an event: "+event.getClass().getSimpleName());

		if (event instanceof RobotReadyBroadcast) {
			RobotReadyBroadcast rbc = (RobotReadyBroadcast) event;
			worker.execute(() -> {
				robotIP = rbc.robotIP;
				robotID = rbc.robotID;
			//	robotReady = rbc.isReady;
				
				Bundle adminBundle = FrameworkUtil.getBundle(RobotBehaviour.class);
				String location = adminBundle.getLocation();
				
				Configuration config;
				try {
					config = cm.getConfiguration("eu.brain.iot.example.robot.RobotBehavior", location);

					Hashtable<String, Object> props = new Hashtable<>();
					props.put(SmartBehaviourDefinition.PREFIX_ + "filter", // only receive some sepecific events with robotID
							String.format("(|(robotID=%s)(robotID=%s))", robotID, RobotCommand.ALL_ROBOTS));
					config.update(props);
					logger.info("-->RB " + robotID + " update properties = "+props);
					
				} catch (IOException e) {
					logger.error("\n Exception:", e);
				}
				
				robotReady = rbc.isReady;
				logger.info("-->RB " + robotID + " robotReady -- "+robotReady);
				
			});

		} else if (event instanceof NewPickPointResponse) {
		//	this.pickResponse = (NewPickPointResponse) event;
		//	NewPickPointResponse rs = (NewPickPointResponse) event;
		//	System.out.println("\n--1--"+(NewPickPointResponse) event);
	
		//	setPickResponse((NewPickPointResponse) event);
			RobotBehaviour.pickResponse = (NewPickPointResponse) event;

		} else if (event instanceof NewStoragePointResponse) {
			RobotBehaviour.storageResponse = (NewStoragePointResponse) event;

		} else if (event instanceof DockingResponse) {
			RobotBehaviour.dockingResponse = (DockingResponse) event;

		} else if (event instanceof CartNoticeResponse) {
			RobotBehaviour.cartNoticeResponse = (CartNoticeResponse) event;

		} else if (event instanceof QueryStateValueReturn) {
			QueryStateValueReturn qs = (QueryStateValueReturn) event;
			worker.execute(() -> {
				logger.info("-->RB" + robotID + " receive QueryStateValueReturn = " + qs.currentState);
				RobotBehaviour.queryReturn = qs;
			});

		} else if (event instanceof MarkerReturn) {
			MarkerReturn cvr = (MarkerReturn) event;
			worker.execute(() -> {
				logger.info("-->RB" + robotID + " receive Check Marker return, marker ID = " + cvr.markerID);
				RobotBehaviour.markerID = cvr.markerID;
				RobotBehaviour.newMarkerCounter += 1;
			});

		} else if (event instanceof DoorStatusResponse) {
			worker.execute(() -> {
				DoorStatusResponse response = (DoorStatusResponse) event;
				if (response.currentState == State.OPEN) {
					isDoorOpen = true;
					logger.info("-->RB" + robotID + " door is opened successfully!!!!");
				}
			});
		}

	}
	
	private static synchronized void setPickResponse(NewPickPointResponse event) {
		pickResponse = event;
	}
	private static synchronized NewPickPointResponse getPickResponse() {
		return pickResponse;
	}

	public boolean executeGoTo(String coordinate, String targetPoint) {
		WriteGoTo writeGoTo = createWriteGoTo(coordinate); // writeGOTO
		queryReturn = null;
		eventBus.deliver(writeGoTo);
		logger.info("-->RB" + robotID + " is sending WriteGoTo: "+ coordinate +"with robotID = "+writeGoTo.robotID);

		if (waitQueryReturn(writeGoTo.command)) { // always true.
			CurrentState currentState = queryReturn.currentState;

			if (currentState.equals(CurrentState.unknown)) {
				robotReady = false;
				logger.info("-->RB" + robotID + " execute GoTo " + targetPoint + " action failed, Robot Behavior stops !!!!");
				// break;
				return false;
			} else { // FINISHED
				logger.info("-->RB " + robotID + " GoTo " + targetPoint + " successfully");
			}
		}
		return true;
	}

	public boolean waitQueryReturn(Command command) {
		logger.info("-->RB" + robotID + " is waiting QueryStateValueReturn");
		while (true) {
			if (queryReturn != null) {
				if (queryReturn.command.equals(command)/* && queryReturn.currentState */) {
					return true;
				} else {
					logger.info(
							"-->RB" + robotID + " get QueryStateValueReturn, but the command is not the same, wait......");
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						logger.error("\n Exception:", e);
					}
				}
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					logger.error("\n Exception:", e);
				}
			}
		}
	}

	public boolean waitPickResponse() {
		logger.info("-->RB" + robotID + " is waiting PickResponse");
		while (true) {
		//	if (getPickResponse() != null) {
			if (RobotBehaviour.pickResponse != null) {
				return true;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					logger.error("\n Exception:", e);
				}
			}
		}
	}

	public boolean waitStorageResponse() {
		logger.info("-->RB" + robotID + " is waiting storageResponse");
		while (true) {
			if (RobotBehaviour.storageResponse != null) {
				return true;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					logger.error("\n Exception:", e);
				}
			}
		}
	}

	public boolean waitCartNoticeResponse() {
		logger.info("-->RB" + robotID + " is waiting CartNoticeResponse");
		while (true) {
			if (this.cartNoticeResponse != null) {
				return true;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					logger.error("\n Exception:", e);
				}
			}
		}
	}

	public boolean waitDockingResponse() {
		logger.info("-->RB" + robotID + " is waiting dockingResponse");
		while (true) {
			if (this.dockingResponse != null) {
				return true;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					logger.error("\n Exception:", e);
				}
			}
		}
	}

	public int waitMarker() {
		logger.info("-->RB" + robotID + " is waiting for pose Marker");
		while (true) {
			if (RobotBehaviour.currentMarkerCounter != RobotBehaviour.newMarkerCounter) {
				RobotBehaviour.currentMarkerCounter = RobotBehaviour.newMarkerCounter;
				return markerID;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					logger.error("\n Exception:", e);
				}
			}
		}
	}

	private WriteGoTo createWriteGoTo(String coordinate) {
		WriteGoTo writeGoTo = new WriteGoTo();
		writeGoTo.robotID = robotID;
		writeGoTo.coordinate = coordinate;
		return writeGoTo;
	}

	private PickCart createPickCart(int markerID) {
		PickCart pc = new PickCart();
		pc.robotID = robotID;
		pc.markerID = markerID;
		return pc;
	}

	private PlaceCart createPlaceCart() {
		PlaceCart placeCart = new PlaceCart();
		placeCart.robotID = robotID;
		return placeCart;
	}

	private CheckMarker createCheckMarker() {
		CheckMarker checkMarker = new CheckMarker();
		checkMarker.robotID = robotID;
		return checkMarker;
	}


	private CartMovedNotice createCartMovedNotice() {
		CartMovedNotice cartMovedNotice=new CartMovedNotice();
		cartMovedNotice.robotID=robotID;
		cartMovedNotice.pickPoint = pickResponse.pickPoint;
		return cartMovedNotice;
	}
	
	private NoCartNotice createNoCartNotice() {
		NoCartNotice noCartNotice=new NoCartNotice();
		noCartNotice.robotID=robotID;
		noCartNotice.pickPoint = pickResponse.pickPoint;
		return noCartNotice;
	}
	
	private DockingRequest createDockingRequest() {
		DockingRequest dockingRequest = new DockingRequest();
		dockingRequest.robotID=robotID;
		dockingRequest.robotIP = robotIP;
		return dockingRequest;
	}

	@Deactivate
	void stop() {
		reg.unregister();
		worker.shutdown();
		try {
			worker.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
			logger.error("\n Exception:", ie);
		}
	}

}
