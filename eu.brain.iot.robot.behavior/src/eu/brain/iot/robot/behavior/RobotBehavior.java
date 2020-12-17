package eu.brain.iot.robot.behavior;

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
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import java.util.function.Predicate;

import org.osgi.util.promise.Deferred;
import org.osgi.util.promise.Promise;

import com.paremus.brain.iot.example.door.api.DoorStatus;
import com.paremus.brain.iot.example.door.api.DoorStatus.State;
import com.paremus.brain.iot.example.door.api.DoorStatusResponse;
import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.BrainIoTEvent;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.api.Command;
import eu.brain.iot.robot.api.Coordinate;
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

@Component(configurationPid = "eu.brain.iot.example.robot.RobotBehavior", 
configurationPolicy = ConfigurationPolicy.REQUIRE, 
service = {
		SmartBehaviour.class })
@SmartBehaviourDefinition(consumed = { NewPickPointResponse.class, NewStoragePointResponse.class, DockingResponse.class, CartNoticeResponse.class, MarkerReturn.class, QueryStateValueReturn.class, RobotReadyBroadcast.class,
		DoorStatusResponse.class, AvailabilityReturn.class}, 
		author = "LINKS", name = "Robot Behavior", 
		description = "Implements a Robot Behavior.")

public class RobotBehavior implements SmartBehaviour<BrainIoTEvent> {

	private int robotID;
	private static volatile String robotIP;
	private boolean robotReady = false;
	private static volatile QueryStateValueReturn queryReturn;
	private static volatile int markerID = 0;
	private static volatile int newMarkerCounter = 0;
	private static volatile int currentMarkerCounter = 0;
	private boolean isDoorOpen = false;
	private static  volatile NewPickPointResponse pickResponse = null;
	private static  NewStoragePointResponse storageResponse = null;
	private static  DockingResponse dockingResponse = null;
	private static CartNoticeResponse cartNoticeResponse = null;
	
	private List<PendingRequest> pendingRequests = new CopyOnWriteArrayList<>();

	@ObjectClassDefinition
	public static @interface Config {

		@AttributeDefinition(description = "The identifier for the robot behaviour")
		int id();

	}

	private Config config;
	private ExecutorService worker;
	private ServiceRegistration<?> reg;

	@Reference
	private EventBus eventBus;

	@Activate
	void activate(BundleContext context, Config config, Map<String, Object> props) {
		this.config = config;
		this.robotID = config.id();
		
		System.out.println("\nHello!  I am robotBehavior : " + config.id());

		worker = Executors.newFixedThreadPool(10);

		Dictionary<String, Object> serviceProps = new Hashtable<>(props.entrySet().stream()
				.filter(e -> !e.getKey().startsWith(".")).collect(Collectors.toMap(Entry::getKey, Entry::getValue)));

		serviceProps.put(SmartBehaviourDefinition.PREFIX_ + "filter",
				String.format("(|(robotID=%s)(robotID=%s))", robotID, RobotCommand.ALL_ROBOTS));

		System.out.println("+++++++++ Robot Behaviour filter = " + serviceProps.get(SmartBehaviourDefinition.PREFIX_ + "filter"));
		reg = context.registerService(SmartBehaviour.class, this, serviceProps);

		

		 onStart();
	}
	
	private static class PendingRequest {
		public Predicate<? super BrainIoTEvent> consumer;
		public BrainIoTEvent toResend;
		public Deferred<? super BrainIoTEvent> latch;
		
		public int processed = -1;
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
					Coordinate pickPoint = null;
					pickCounter = 1;
					storageCounter = 1;

					// --------------------------- Query Pick point --------------------------------------

					while (query) {

						NewPickPointRequest pickRequest = new NewPickPointRequest();
						pickRequest.robotID = this.robotID;
				//		this.pickResponse = null;
				//		setPickResponse(null);
						RobotBehavior.pickResponse = null;
						eventBus.deliver(pickRequest);

						waitPickResponse();

				//		if (getPickResponse().hasNewPoint) {
						if (RobotBehavior.pickResponse.hasNewPoint) {	
							System.out.println("-----------new Pick Point-------------");
							pickPoint = getPickResponse().pickPoint;
							if(pickPoint == null) {
								System.out.println("no pick point, RB exit!");
								stop();
							}
							System.out.println("RB" + robotID + " get new Pick Point: " + pickPoint.getX() + ", "
									+ pickPoint.getY() + ", " + pickPoint.getZ());
							System.out.println("------------------------");
							break;
						} else {

							if (pickCounter > 0) { // just ask for 2 times
								System.out.println(
										"RB" + robotID + " doesn't get any Pick Point, continue to query after 10s");
								// TODO continue to query new pick point
								try {
									TimeUnit.SECONDS.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								pickCounter--;
								continue;
							} else {
								nextIteration = false;
								System.out.println("RB" + robotID
										+ " doesn't get any Pick Point, all carts have been moved, exit! ");
								break;
							}

						}
					} // while

					if (nextIteration) { // it means a new pick point is found, the break is not because no pick point
											// is found
						// --------------------------- Go to Picking point --------------------------------------

						if (!executeGoTo(pickPoint, "Picking point")) {
							break; // execution failed
						}
						// --------------------------- check Cart Marker --------------------------------------

						CheckMarker checkMarker = createCheckMarker(); // CheckMarker
						eventBus.deliver(checkMarker);
						System.out.println("-->RB" + robotID + " sending CheckMarker");

						int newMarkerID = waitMarker();
						System.out.println("-->RB" + robotID + " got new MarkerID = " + newMarkerID);

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

						PickCart pickCart = createPickCart(newMarkerID); // PickCart
						queryReturn = null;
						eventBus.deliver(pickCart);
						System.out.println("-->RB" + robotID + " sending pick");

						if (waitQueryReturn(pickCart.command)) { // always true.
							CurrentState currentState = queryReturn.currentState;

							if (currentState.equals(CurrentState.unknown)) {
								robotReady = false;
								System.out.println("-->RB" + robotID
										+ " execute PickCart action failed, Robot Behavior stops !!!!");
								break;
							} else { // FINISHED
								System.out.println("-->RB " + robotID + " Pick Cart successfully");
							}
						}

						// --------------------------- Query Storage point --------------------------------------

						while (query) {

							NewStoragePointRequest storageRequest = new NewStoragePointRequest();
							storageRequest.robotID = this.robotID;
							storageRequest.markerID = markerID;
							RobotBehavior.storageResponse = null;
							eventBus.deliver(storageRequest);

							waitStorageResponse();

							if (RobotBehavior.storageResponse.hasNewPoint) {
								System.out.println("-----------new Storage Point-------------");
								System.out.println("------------------------");
								break;
							} else {
								if (storageCounter > 0) { // just ask for 2 times
									System.out.println("RB" + robotID
											+ " doesn't get any Storage Point, continue to query after 10s");
									try {
										TimeUnit.SECONDS.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									storageCounter--;
									continue;
								} else {
									nextIteration = false;
									System.out.println("RB" + robotID
											+ " doesn't get any Storage Point for this cart after querying for 2 times, exit! ");
									break;
								}
							}
						} // while
						if (nextIteration) {

							// --------------------------- Go to Storage AUX -------------------------------------

							if (!executeGoTo(RobotBehavior.storageResponse.storageAuxliaryPoint, "storage AUX")) {
								break; // execution failed
							}
							// --------------------------- check Door Marker  --------------------------------------

							CheckMarker checkDoorMarker = createCheckMarker(); // CheckMarker
							eventBus.deliver(checkDoorMarker);
							System.out.println("-->RB" + robotID + " sending check Door Marker");

							int DoorID = waitMarker();
							System.out.println("-->RB" + robotID + " got DoorID = " + DoorID);

							// --------------------------- Go to Storage Point --------------------------------------

							if (!executeGoTo(RobotBehavior.storageResponse.storagePoint, "storage Point")) {
								break; // execution failed
							}

							// --------------------------- Place Cart --------------------------------------

							PlaceCart placeCart = createPlaceCart(); // PickCart
							queryReturn = null;
							eventBus.deliver(placeCart);
							System.out.println("-->RB" + robotID + " sending placeCart");

							if (waitQueryReturn(placeCart.command)) { // always true. otherwise it always query
								CurrentState currentState = queryReturn.currentState;

								if (currentState.equals(CurrentState.unknown)) {
									robotReady = false;
									System.out.println("-->RB" + robotID
											+ " execute PickCart action failed, Robot Behavior stops !!!!");
									break;
								} else { // FINISHED
									System.out.println("-->RB " + robotID + " Place Cart successfully");
								}
							}

							// --------------------------- Cart Moved Notice --------------------------------------
							CartMovedNotice cartMovedNotice = createCartMovedNotice();
							cartNoticeResponse = null;
							eventBus.deliver(cartMovedNotice);
							System.out.println("-->RB" + robotID + " sending cartMovedNotice");

							waitCartNoticeResponse(); // noticeStatus = "OK"

							System.out.println("-->RB" + robotID + " got CartNoticeResponse");

							// --------------------------- Docking Request--------------------------------------
							DockingRequest dockingRequest = createDockingRequest();
							dockingResponse = null;
							eventBus.deliver(dockingRequest);
							System.out.println("-->RB" + robotID + " sending dockingRequest");

							if (waitDockingResponse()) {
								if (dockingResponse.hasNewPoint) {

									// --------------------------- Go to Docking AUX -------------------------------------

									if (!executeGoTo(dockingResponse.dockAuxliaryPoint, "dock AUX")) {
										break; // execution failed
									}
									// --------------------------- check Door Marker --------------------------------------

									CheckMarker checkDoorMarker2 = createCheckMarker(); // CheckMarker
									eventBus.deliver(checkDoorMarker2);
									System.out.println("-->RB" + robotID
											+ " sending check Door Marker on the way to Docking area");

									int DoorID2 = waitMarker();
									System.out.println("-->RB" + robotID + " got DoorID = " + DoorID2);

									// --------------------------- Go to Docking Point --------------------------------------

									if (!executeGoTo(dockingResponse.dockingPoint, "dock Point")) {
										break; // execution failed
									}
								} else {
									System.out.println("-->RB" + robotID + " exit because NO Docking point found ");
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
						e.printStackTrace();
					}
				}

			} // end of ==> while (nextIteration)

			if (nextIteration == false) { // only when normal exit(no pick, no storage after querying for a long time)
				System.out.println("-->Tasks are done. Robot Behavior " + robotID + " exit !!!");
			} else {
				System.out.println("-->RB " + robotID + "  exit because of failure in robot!!!");
			}
		}

		);

	}

	@Override
	public void notify(BrainIoTEvent event) {

		if (event instanceof RobotReadyBroadcast) {
			RobotReadyBroadcast rbc = (RobotReadyBroadcast) event;
			worker.execute(() -> {
				System.out.println("-->RB" + robotID + " received RobotReady event");
				robotIP = rbc.robotIP;
				robotReady = rbc.isReady;
				System.out.println("robotReady--"+robotReady);
			});

		} else if (event instanceof NewPickPointResponse) {
		//	this.pickResponse = (NewPickPointResponse) event;
		//	NewPickPointResponse rs = (NewPickPointResponse) event;
			System.out.println("\n--1--"+(NewPickPointResponse) event);
	
		//	setPickResponse((NewPickPointResponse) event);
			RobotBehavior.pickResponse = (NewPickPointResponse) event;
			
			// System.out.println("-->RB" + robotID + " receive NewPickPointResponse ");
			worker.execute(() -> {
			//	RobotBehavior.pickResponse = (NewPickPointResponse) event;
				
				
				System.out.println("-->RB" + robotID + " receive  "+ getPickResponse());
		//		System.out.println("-->RB" + robotID + " receive NewPickPointResponse "+ getPickResponse().pickPoint.toString());
			});

		} else if (event instanceof NewStoragePointResponse) {
			RobotBehavior.storageResponse = (NewStoragePointResponse) event;
			worker.execute(() -> {
				System.out.println("-->RB" + robotID + " receive NewStoragePointResponse ");
			});

		} else if (event instanceof DockingResponse) {
			this.dockingResponse = (DockingResponse) event;
			worker.execute(() -> {
				System.out.println("-->RB" + robotID + " receive DockingResponse ");
			});

		} else if (event instanceof CartNoticeResponse) {
			this.cartNoticeResponse = (CartNoticeResponse) event;
			worker.execute(() -> {
				System.out.println("-->RB" + robotID + " receive CartNoticeResponse ");
			});

		} else if (event instanceof QueryStateValueReturn) {
			QueryStateValueReturn qs = (QueryStateValueReturn) event;
			worker.execute(() -> {
				System.out.println("-->RB" + robotID + " receive query return value");
				this.queryReturn = qs;
			});

		} else if (event instanceof MarkerReturn) {
			MarkerReturn cvr = (MarkerReturn) event;
			worker.execute(() -> {
				System.out.println("-->RB" + robotID + " receive Check Marker return, marker ID = " + cvr.markerID);
				this.markerID = cvr.markerID;
				this.newMarkerCounter += 1;
			});

		} else if (event instanceof DoorStatusResponse) {
			worker.execute(() -> {
				DoorStatusResponse response = (DoorStatusResponse) event;
				if (response.state == State.OPEN) {
					isDoorOpen = true;
					System.out.println("-->RB" + robotID + " door is opened successfully!!!!");
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
	
	
/*	@Override
	public void notify(BrainIoTEvent event) {

		System.out.println("-->RB" + robotID + " received an event type " + event.getClass().getSimpleName());

		for(PendingRequest pr : pendingRequests) {
			try {
				if(pr.consumer.test(event)) {
					pr.latch.resolve(event);
				}
			} catch (Exception e) {
				pr.latch.fail(e);
			}
		}
	}*/
	
	private Promise<Integer> queryStateAsync(int RobotId, int mission) {
		QueryState qs =new QueryState();
		
    	
		System.out.println("ORCHESTRATOR: SEND QUERY STATE");
			return awaitResponse(qs, e -> {
				if(e instanceof QueryStateValueReturn) {
					QueryStateValueReturn qsvr = (QueryStateValueReturn) e;
				//	return RobotId == qsvr.robotId && qsvr.mission == mission;
				}
				return false;
			})
			.map(e -> (QueryStateValueReturn)e)
			.thenAccept(e -> System.out.println("Orchestrator Recieved a Query response from Robot " + e.robotID))
			.map(e -> e.robotID);   // QueryStateValueReturn.value
	}
	
	
	private Promise<BrainIoTEvent> awaitResponse(BrainIoTEvent event, 
			Predicate<BrainIoTEvent> acceptableResponse) {
		PendingRequest pr = new PendingRequest();
		pr.toResend = event;
		pr.consumer = acceptableResponse;
		Deferred<BrainIoTEvent> deferred = new Deferred<>();
		pr.latch = deferred;
		pendingRequests.add(pr);
		eventBus.deliver(event);   // for each await, a new command will be sent
		return deferred.getPromise().timeout(10000).onResolve(() -> pendingRequests.remove(pr));
	}

	public boolean executeGoTo(Coordinate coordinate, String targetPoint) {
		WriteGoTo writeGoTo = createWriteGoTo(coordinate); // writeGOTO
		queryReturn = null;
		eventBus.deliver(writeGoTo);
		System.out.println("-->RB" + robotID + " sending goto cmd");

		if (waitQueryReturn(writeGoTo.command)) { // always true.
			CurrentState currentState = queryReturn.currentState;

			if (currentState.equals(CurrentState.unknown)) {
				robotReady = false;
				System.out.println("-->RB" + robotID + " execute GoTo " + targetPoint
						+ " action failed, Robot Behavior stops !!!!");
				// break;
				return false;
			} else { // FINISHED
				System.out.println("-->RB " + robotID + " GoTo " + targetPoint + " successfully");
			}
		}
		return true;
	}

	public boolean waitQueryReturn(Command command) {
		System.out.println("-->RB" + robotID + " in waiting queryReturn");
		while (true) {
			if (queryReturn != null) {
				if (queryReturn.command.equals(command)/* && queryReturn.currentState */) {
					return true;
				} else {
					System.out.println(
							"-->RB" + robotID + " get queryReturn, but the command is not the same, wait......");
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean waitPickResponse() {
		System.out.println("-->RB" + robotID + " in waiting PickResponse");
		while (true) {
		//	if (getPickResponse() != null) {
			if (RobotBehavior.pickResponse != null) {
				return true;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean waitStorageResponse() {
		System.out.println("-->RB" + robotID + " in waiting storageResponse");
		while (true) {
			if (RobotBehavior.storageResponse != null) {
				return true;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean waitCartNoticeResponse() {
		System.out.println("-->RB" + robotID + " in waiting CartNoticeResponse");
		while (true) {
			if (this.cartNoticeResponse != null) {
				return true;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean waitDockingResponse() {
		System.out.println("-->RB" + robotID + " in waiting dockingResponse");
		while (true) {
			if (this.dockingResponse != null) {
				return true;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * public int waitMarker() { System.out.println("-->RB" + robotId +
	 * " is waiting for pose Marker"); while (true) { if (markerID!=0) { //
	 * this.currentMarkerCounter = this.newMarkerCounter; return markerID; } else {
	 * try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {
	 * e.printStackTrace(); } } } }
	 */

	public int waitMarker() {
		System.out.println("-->RB" + robotID + " is waiting for pose Marker");
		while (true) {
			if (this.currentMarkerCounter != this.newMarkerCounter) {
				this.currentMarkerCounter = this.newMarkerCounter;
				return markerID;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private WriteGoTo createWriteGoTo(Coordinate coordinate) {
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
		CartMovedNotice cartMovedNotice = new CartMovedNotice();
		cartMovedNotice.robotID = robotID;
		cartMovedNotice.pickPoint = pickResponse.pickPoint;
		return cartMovedNotice;
	}

	private NoCartNotice createNoCartNotice() {
		NoCartNotice noCartNotice = new NoCartNotice();
		noCartNotice.robotID = robotID;
		noCartNotice.pickPoint = pickResponse.pickPoint;
		return noCartNotice;
	}

	private DockingRequest createDockingRequest() {
		DockingRequest dockingRequest = new DockingRequest();
		dockingRequest.robotID = robotID;
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
		}
	}

}
