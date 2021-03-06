/*******************************************************************************
 *  ROSOSGi - Bridging the gap between Robot Operating System (ROS) and OSGi
 *  Copyright (C) 2015, 2016  imec - IDLab - UGent
 *
 *  This file is part of DIANNE  -  Framework for distributed artificial neural networks
 *
 *  DIANNE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *   
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 *  Contributors:
 *      Tim Verbelen, Steven Bohez
 *******************************************************************************/
package be.iminds.iot.ros.core;

import java.io.File;
import java.util.Map.Entry;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.ros.RosCore;
import org.ros.concurrent.DefaultScheduledExecutorService;
import org.ros.master.client.MasterStateClient;
import org.ros.master.client.ServiceSystemState;
import org.ros.master.client.SystemState;
import org.ros.master.client.TopicSystemState;
import org.ros.master.client.TopicType;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.DefaultNodeMainExecutor;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMain;
import org.ros.node.NodeMainExecutor;
import be.iminds.iot.ros.api.Ros;

@Component(service = {Ros.class},
	immediate=true,
	configurationPolicy=ConfigurationPolicy.REQUIRE,
	name= "eu.brain.iot.robotics.roscore.ROS",
	property = {"osgi.command.scope=ros", 
		"osgi.command.function=env",	
		"osgi.command.function=nodes",
		"osgi.command.function=topics",
		"osgi.command.function=publishers",
		"osgi.command.function=subscribers",
		"osgi.command.function=services",
		"osgi.command.function=providers"})
public class RosImpl extends AbstractNodeMain implements Ros {

	// ROS environment variables
	private URI masterURI;
	private String distro;
	private String namespace;
	private String root;
	private String packagePath;
	
	// ROS core instance (native or rosjava)
	private Process nativeCore;
	private RosCore core;
	private String robotIP;
	private int robotId;
	private String robotName;
	
	// rosjava connection to the ROS master
	private MasterStateClient master;

	// construct our own thread pool for rosjava
	private NodeMainExecutor executor;
	private ThreadPoolExecutor pool;
	private int threadCount = 0;

	// also register a node to get access to basic ros info
	private ConnectedNode node;

	
	@Activate
	void activate(BundleContext context, Map<String, Object> properties) throws Exception {
		try {
			String uri = null;

			if (!properties.isEmpty()) {
				for (Entry<String, Object> entry : properties.entrySet()) {
					String key = entry.getKey();
					if (key.equals("ros.master.uri")) {
						uri = (String) entry.getValue();
					} else if (key.equals("robotIP")) {
						this.robotIP = (String) entry.getValue();
					} else if (key.equals("robotId")) {
						this.robotId = Integer.parseInt((String) entry.getValue());
					} else if (key.equals("robotName")) {
						this.robotName = (String) entry.getValue();
					}
				}
			}
			
			if(uri == null) {
				if(robotIP != null) {
					uri = "http://"+robotIP+":11311";
				} else {
					uri = getVariable(context, "ROS_MASTER_URI", "ros.master.uri");
				}
			}

			if(uri == null){
				throw new Exception("No master URI configured!");
			//	deactivate();
			}
			masterURI = new URI(uri);//uri
			System.out.println("\n--> masterURI = "+masterURI);
			
			if(robotIP == null) {
				String[] strs = uri.split(":");
				robotIP = strs[1].substring(2);
				System.out.println("--> robotIP = "+robotIP);
			}

			System.out.println("--> robotName = "+robotName);
			System.out.println("--> robotId = "+robotId);
			
			distro = getVariable(context, "ROS_DISTRO", "ros.distro");
			namespace = getVariable(context, "ROS_NAMESPACE", "ros.namespace");
			root = getVariable(context, "ROS_ROOT", "ros.root");
			packagePath = getVariable(context, "ROS_PACKAGE_PATH", "ros.package.path");
		} catch(Exception e){
			System.err.println("Error setting up the ROS environment: "+e.getMessage());
			throw e;
		} 
		
		// create threadpool for running additional rosjava nodes
		// these parameters are equal as for CachedThreadPool ... change if useful
		pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, 
				new SynchronousQueue<Runnable>(), new ThreadFactory() {	
					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r, "rosjava-pool-"+(threadCount++));
					}
				});
		executor = DefaultNodeMainExecutor.newDefault(new DefaultScheduledExecutorService(pool));

		// start ROS core if required
		boolean start = !rosCoreActive();
		if(start){
			System.out.println("\n start installed ROS system");
			boolean startNative = false;   // -------keep always running native ros core
			String rosCoreNative = getVariable(context, null,"ros.core.native");
			
			if(rosCoreNative != null){
				startNative = Boolean.parseBoolean(rosCoreNative);				
			}
			
			if(startNative){
				System.out.println("\n start Native roscore =  "+ startNative);
				// native ROScore process
				ProcessBuilder builder = new ProcessBuilder("roscore", "-p "+masterURI.getPort());
				builder.environment().put("ROS_MASTER_URI", masterURI.toString());
				/* File log = new File("log");
				 builder.redirectErrorStream(true);
				 builder.redirectOutput(Redirect.appendTo(log));
				//builder.inheritIO();*/
				nativeCore = builder.start();
				
			} else {
				// rosjava ROScore implementation
				core = RosCore.newPublic(masterURI.getHost(), masterURI.getPort());
				core.start();
				System.out.println("ROS core service [/rosout] started on "+core.getUri());
			}
		}
		
		// block until ROS core is initialized
		// TODO add timeout?
		while(!rosCoreActive()){   //= false
			Thread.sleep(100);
		}
		// add this node
		addNode(this);
	}
	
	@Deactivate
	void deactivate() throws Exception {
		removeNode(this);
		
		if(core != null)
			core.shutdown();
		
		if(nativeCore != null){
			nativeCore.destroy();
		}
		if(executor != null){
			executor.shutdown();
		}
		
	}
	@Override
	public String getRobotIP() {
		return robotIP;
	}
	@Override
	public int getRobotId() {
		return robotId;
	}
	@Override
	public String getRobotName() {
		return robotName;
	}
	
	private boolean rosCoreActive(){
		try {
			// try to connect to ROS core
			Socket s = new Socket(masterURI.getHost(), masterURI.getPort());
			s.close();
			return true;
		} catch(Exception e){
			// not active
			return false;
		}
	}


	
	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("rosinfo"+robotId+"/");
	}

	@Override
	public void onStart(final ConnectedNode connectedNode) {
		node = connectedNode;
		master = new MasterStateClient(connectedNode, connectedNode.getMasterUri());
		synchronized(this){
			notifyAll();
		}
	}
	
	
		
	private String getVariable(BundleContext context, String environmentKey, String propertyKey){
		// then try context property
		
		String ctx = context.getProperty(propertyKey);
		if(ctx != null && ctx.length()!=0){
			return ctx;
		} else if(environmentKey != null){
			return System.getenv(environmentKey);
		}
		return null;
	}
            
	
	@Override
	public URI masterURI() {
		return masterURI;
	}

	@Override
	public String masterHost(){
		return masterURI.getHost();
	}
	
	@Override
	public int masterPort(){
		return masterURI.getPort();
	}
	
	@Override
	public String distro() {
		return distro;
	}

	@Override
	public String namespace() {
		return namespace;
	}

	@Override
	public File root() {
		if(root==null){
			return new File(".");
		}
		return new File(root);
	}

	@Override
	public List<File> packagePath() {
		List<File> files = new ArrayList<>();
		if(packagePath!=null){
			for(String f : packagePath.split(":")){
				files.add(new File(f));
			}
		}
		return files;
	}
	
	
	/**
	 * This method ensures that any call to the OSGi service waits until the ROS node
	 * is actually intialized. This is a hacky way of making sure the OSGi and ROS lifecycles
	 * can interwork while using DS 
	 */
	private synchronized void waitForInit() {
		if(master!=null){
			return;
		} else {
			try {
				this.wait();
			} catch(InterruptedException e){}
		}
	}
	
	@Override
	public URI nodeURI(String node) {
		waitForInit();
		return master.lookupNode(node);
	}

	@Override
	public Collection<String> nodes() {
		waitForInit();
		// TODO is there a better way to list all nodes?
		SystemState state = master.getSystemState();
		final Set<String> nodes = new HashSet<>();
		state.getTopics().stream().forEach(t -> {
			nodes.addAll(t.getSubscribers());
			nodes.addAll(t.getPublishers());
		});
		return nodes;
	}
	
	@Override
	public Collection<String> topics() {
		waitForInit();
		SystemState state = master.getSystemState();
		return state.getTopics().stream().map(t -> t.getTopicName()).collect(Collectors.toList());
	}

	@Override
	public Collection<String> publishers(String topic) {
		waitForInit();
		SystemState state = master.getSystemState();
		TopicSystemState tss = state.getTopics().stream().filter(t -> t.getTopicName().equals(topic)).findFirst().get();
		return tss.getPublishers();
	}

	@Override
	public Collection<String> subscribers(String topic) {
		waitForInit();
		SystemState state = master.getSystemState();
		TopicSystemState tss = state.getTopics().stream().filter(t -> t.getTopicName().equals(topic)).findFirst().get();
		return tss.getSubscribers();
	}

	@Override
	public String topicType(String topic) {
		waitForInit();
		TopicType type = master.getTopicTypes().stream().filter(t -> t.getName().equals(topic)).findFirst().get();
		return type.getMessageType();
	}

	@Override
	public Collection<String> services() {
		waitForInit();
		SystemState state = master.getSystemState();
		return state.getServices().stream().map(s -> s.getServiceName()).collect(Collectors.toList());
	}

	@Override
	public Collection<String> providers(String service) {
		waitForInit();
		SystemState state = master.getSystemState();
		ServiceSystemState sss = state.getServices().stream().filter(s -> s.getServiceName().equals(service)).findFirst().get();
		return sss.getProviders();
	}
	
	@Override
	public String env(){
		StringBuilder builder = new StringBuilder();
		builder.append("ROS Environmnent \n");
		builder.append(" masterURI: ").append(masterURI).append("\n");
		builder.append(" distro: ").append(distro).append("\n");
		builder.append(" namespace: ").append(namespace).append("\n");
		builder.append(" root: ").append(root).append("\n");
		builder.append(" packagePath: ").append(packagePath).append("\n");
		return builder.toString();
	}
	
	@Override
	public void setParameter(String key, Object value){
		waitForInit();
		if(value instanceof Integer){
			node.getParameterTree().set(key, (Integer)value);
		} else if(value instanceof Double){
			node.getParameterTree().set(key, (Double)value);
		} else if(value instanceof String){
			node.getParameterTree().set(key, (String)value);
		} else if(value instanceof Boolean){
			node.getParameterTree().set(key, (Boolean)value);
		} else if(value instanceof List){
			node.getParameterTree().set(key, (List)value);
		} else if(value instanceof Map){
			node.getParameterTree().set(key, (Map)value);
		} else {
			node.getParameterTree().set(key, String.valueOf(value));
		}
	}	
	
	@Override
	public <T> T getParameter(String key, Class<T> type){
		waitForInit();
		if(type == Integer.class){
			return (T) new Integer(node.getParameterTree().getInteger(key));
		} else if(type == Double.class){
			return (T) new Double(node.getParameterTree().getDouble(key));
		} else if(type == String.class){
			return (T) node.getParameterTree().getString(key);
		} else if(type == Boolean.class){
			return (T) new Boolean(node.getParameterTree().getBoolean(key));
		} else if(type == List.class){
			return (T) node.getParameterTree().getList(key);
		} else if(type == Map.class){
			return (T) node.getParameterTree().getMap(key);
		} 
		return null;
	}
	
	
	@Reference(cardinality=ReferenceCardinality.MULTIPLE, 
			   policy=ReferencePolicy.DYNAMIC)
	public void addNode(NodeMain node) {
		if(node != this)
			waitForInit();
		try {
			NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(masterHost(), masterURI());
			nodeConfiguration.setRosRoot(root());
			nodeConfiguration.setRosPackagePath(packagePath());
			executor.execute(node, nodeConfiguration);
		} catch(Throwable e){
			e.printStackTrace();
		}
	}
	
	public void removeNode(NodeMain node){
		waitForInit();
		try {
			executor.shutdownNodeMain(node);
		} catch(Throwable t){
			t.printStackTrace();
		}
	}

}
