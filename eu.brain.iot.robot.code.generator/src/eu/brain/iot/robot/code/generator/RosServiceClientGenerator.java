/*******************************************************************************
 * *  Copyright (C) 2021 LINKS Foundation
 * 
 * This file is based on the ROSOSGi open-source project which is a part of DIANNE  -  Framework for distributed artificial neural networks
 * 
 * DIANNE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package eu.brain.iot.robot.code.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JCatchBlock;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JConditional;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JDirectClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JTryBlock;
import com.helger.jcodemodel.JVar;
import com.helger.jcodemodel.JWhileLoop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(

		immediate=true,
		properties = "config.properties"
	)
public class RosServiceClientGenerator {

	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RosServiceClientGenerator.class.getSimpleName());
	
	@Activate
	public void loadConfig(ComponentContext context) throws Exception {
		Dictionary<String,Object>  properties=context.getProperties();
			
			String content = read_TD_file("/TD_for_ROS_Edge_Node.json");
			JSONObject TDobject = new JSONObject(content);

			JSONObject actions = TDobject.getJSONObject("actions");
			
			//   className        clientName,   serviceProp, values
			Map<String, HashMap<String, HashMap<String, String>>> classMap = new HashMap<String,HashMap<String,HashMap<String, String>>>();

			
			HashMap<String, HashMap<String, String>> serviceMap = null;
			HashMap<String, String> clientInfo;
			
			for(String clientName : actions.keySet()) {
				clientInfo = new HashMap<>();
				JSONObject service = (JSONObject)actions.get(clientName);
				String className = service.getString("ros:codeGenerator:class");

				if(classMap.containsKey(className)) {
					serviceMap = classMap.get(className);
				} else {
					serviceMap = new HashMap<String,HashMap<String,String>>();
				}
				JSONArray forms = service.getJSONArray("forms");

					JSONObject serviceProp = forms.getJSONObject(0);
					clientInfo.put("serviceName", serviceProp.getString("ros:serviceName"));
					clientInfo.put("serviceType", serviceProp.getString("ros:serviceType"));
					clientInfo.put("serviceRequestType", serviceProp.getString("ros:serviceRequestType"));
					clientInfo.put("serviceResponseType", serviceProp.getString("ros:serviceResponseType"));
					
					serviceMap.put(clientName, clientInfo);
					
					if(!classMap.containsKey(className)) {
						classMap.put(className, serviceMap);
					}
			}
			System.out.println("\nclassMap = "+classMap.keySet()+"\n");
			for(String cn : classMap.keySet()) {
				System.out.println("className = "+cn);
				System.out.println("clients = "+ classMap.get(cn).keySet()+"\n");
			}
			
			for(String className : classMap.keySet()) {
				generateServiceClass(className, classMap.get(className), properties);
			}
			
			
	//---------------------------------PROPERTIES--------------------------------------------
			
			JSONObject property = TDobject.getJSONObject("properties");
			
			Map<String, HashMap<String, HashMap<String, String>>> topicClassMap = new HashMap<String,HashMap<String,HashMap<String, String>>>();
			HashMap<String, HashMap<String, String>> topicMap = null;
			HashMap<String, String> topicInfo;
			
			for(String clientName : property.keySet()) {
				topicInfo = new HashMap<>();
				JSONObject service = (JSONObject)property.get(clientName);
				String className = service.getString("ros:codeGenerator:class");

				if(topicClassMap.containsKey(className)) {
					topicMap = topicClassMap.get(className);
				} else {
					topicMap = new HashMap<String,HashMap<String,String>>();
				}
				JSONArray forms = service.getJSONArray("forms");

					JSONObject serviceProp = forms.getJSONObject(0);
					topicInfo.put("topicRole", serviceProp.getString("ros:Role"));
					topicInfo.put("topicName", serviceProp.getString("ros:TopicName"));
					topicInfo.put("topicType", serviceProp.getString("ros:TopicType"));
					topicInfo.put("messageType", serviceProp.getString("ros:MessageType"));
					
					System.out.println(clientName+" messageType = "+topicInfo.get("messageType"));
					
					topicMap.put(clientName, topicInfo);
					
					System.out.println(" topicMap = "+topicMap);
					
					if(!topicClassMap.containsKey(className)) {
						topicClassMap.put(className, topicMap);
					}

					System.out.println(" topicClassMap 22 = "+topicClassMap);
			}
			
			System.out.println("\ntopicClassMap = "+topicClassMap.keySet()+"\n");
			for(String cn : topicClassMap.keySet()) {
				System.out.println("topicClassName = "+cn);
				System.out.println("topic clients = "+ topicClassMap.get(cn).keySet()+"\n");
			}
			
			for(String topicClassName : topicClassMap.keySet()) {
				generateTopicClass(topicClassName, topicClassMap.get(topicClassName), properties);
			}
			
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void generateTopicClass(String topicClassName, HashMap<String, HashMap<String, String>> topicMap, Dictionary<String,Object> properties) throws Exception {
		
		JCodeModel codeModel = new JCodeModel();
		
		JPackage jpackage = codeModel._package((String)properties.get("topicGeneratedClassPackage"));
		JDefinedClass jclass = jpackage._class(JMod.PUBLIC|JMod.ABSTRACT, topicClassName);

		//Define the attributes
		AbstractJType nodeType=codeModel.ref("org.ros.node.ConnectedNode");
		JFieldVar node = jclass.field(JMod.PRIVATE, nodeType, "node");

		
		//Create the constructor
		JMethod jConstructor = jclass.constructor(JMod.PUBLIC);
		jConstructor.param(nodeType,"node");
		JBlock jConstructorBlock = jConstructor.body();
		jConstructorBlock.assign(JExpr._this().ref("node"), JExpr.ref("node"));
		
		//register implementation
		JMethod jRegister=jclass.method(JMod.PUBLIC, void.class, "register");
		JBlock jRegisterBlock=jRegister.body();
		
		int flag = 0;
		for(String clientName : topicMap.keySet()) {
			HashMap<String, String> clientInfo = topicMap.get(clientName);
			
			System.out.println("clientInfo = "+clientInfo);
			
			if(clientInfo.get("topicRole").compareTo("Subscriber")==0) {
				
				//add new attribute related to topic
				JDirectClass genericSubscriber=codeModel.directClass("eu.brain.iot.robot.service.GenericSubscriber");
				AbstractJType topicType=codeModel.ref(clientInfo.get("messageType"));
				JFieldVar subscriber = jclass.field(JMod.PRIVATE, genericSubscriber.narrow(topicType), clientName);
		
				JFieldVar topicMsg = jclass.field(JMod.PRIVATE, topicType, topicType.name().toLowerCase(), JExpr._null()); 
				
				//register() context
				jRegisterBlock.assign(subscriber, JExpr._new(genericSubscriber.narrow(topicType)).arg(node) );
				if(clientInfo.get("topicName").replaceFirst("/#robotName(.*)", "").compareTo("")==0) {
					IJExpression tempExpr=JExpr.lit("/").plus(JExpr.ref("robotName")).plus(JExpr.lit(clientInfo.get("topicName").replaceFirst("/#robotName", "")));
					jRegisterBlock.add(subscriber.invoke("register").arg(tempExpr).arg(clientInfo.get("topicType")));
					try {
						if (flag == 0) {
							JFieldVar name = jclass.field(JMod.PRIVATE, String.class, "robotName");
							jConstructor.param(String.class, "robotName");
							jConstructorBlock.assign(JExpr._this().ref("robotName"), JExpr.ref("robotName"));
							flag = 1;
						}
					}catch(java.lang.IllegalArgumentException e) {
						System.out.println("in subscriber robotName already defined");
					}
				}else {
					jRegisterBlock.add(subscriber.invoke("register").arg(clientInfo.get("topicName")).arg(clientInfo.get("topicType")));
				}
				
				//return value	
				JMethod jgetter=jclass.method(JMod.PUBLIC, topicType, "get_"+clientName+"_value");
				JBlock getterbody=jgetter.body();
				
				 getterbody.assign(topicMsg, subscriber.invoke("getCurrentValue"));
				 
				 
				JWhileLoop getWhile = getterbody._while(topicMsg.eqNull());
				JBlock whileBody = getWhile.body();
				whileBody.directStatement("System.out.println(\" "+ clientName +" get empty value, read again.......\");");
				
				JTryBlock tryBlock = whileBody._try();
				
				JBlock tryb = tryBlock.body();

				tryb.directStatement("sleep(2);");
				
				JCatchBlock catchBlock = tryBlock._catch(codeModel.ref(InterruptedException.class));
				final JVar exceptionVar = catchBlock.param("e");
				
				catchBlock.body()._throw(exceptionVar);
				
				whileBody.assign(topicMsg, subscriber.invoke("getCurrentValue"));
				
				getterbody._return(topicMsg);
				
			}else if(clientInfo.get("topicRole").compareTo("Publisher")==0) {

				//add new attribute related to topic
				JDirectClass publisherType=codeModel.directClass("org.ros.node.topic.Publisher");
				AbstractJType topicType=codeModel.ref(clientInfo.get("messageType"));
				JFieldVar publisher = jclass.field(JMod.PUBLIC,publisherType.narrow(topicType), clientName);
				
				//register() context
				if(clientInfo.get("topicName").replaceFirst("/#robotName(.*)", "").compareTo("")==0) {
					IJExpression tempExpr=JExpr.lit("/").plus(JExpr.ref("robotName")).plus(JExpr.lit(clientInfo.get("topicName").replaceFirst("/#robotName", "")));
					jRegisterBlock.add(publisher.invoke("register").arg(tempExpr).arg(clientInfo.get("topicType")));
					try {
						if (flag == 0) {
							JFieldVar name = jclass.field(JMod.PRIVATE, String.class, "robotName");
							jConstructor.param(String.class, "robotName");
							jConstructorBlock.assign(JExpr._this().ref("robotName"), JExpr.ref("robotName"));
							flag = 1;
						}
					} catch (java.lang.IllegalArgumentException e) {
						System.out.println("in publisher robotName already defined");
					}
				}else {
					jRegisterBlock.assign(publisher, node.invoke("newPublisher").arg(clientInfo.get("topicName")).arg(clientInfo.get("topicType")));
				}
				jRegisterBlock.add(publisher.invoke("setLatchMode").arg(true));
				//Message sender
				JMethod pubMsg =jclass.method(JMod.PUBLIC, void.class, "publish_"+clientName+"_Msg");
				pubMsg.param(topicType, "msg");
				pubMsg.body().add(publisher.invoke("publish").arg(JExpr.ref("msg")));
				//Message constructor
				if(clientInfo.get("messageType").compareTo("std_msgs/Float64")==0)
				{
					JMethod constructMsg =jclass.method(JMod.PUBLIC, topicType, "construct_"+clientName+"_Msg");
					constructMsg.javadoc().add("Default message constructor for door,need to be modified when usage chaged");
					constructMsg.param(String.class, "doorAction");
					JBlock msgBody=constructMsg.body();
					msgBody.decl(topicType, "msg");
					msgBody.assign(JExpr.ref("msg"), publisher.invoke("newMessage"));
					JConditional msgIf=msgBody._if(JExpr.ref("doorAction").invoke("compareTo").arg("open").eq0()); 
					msgIf._then().add(JExpr.ref("msg").invoke("setData").arg(JExpr.lit(1.0)));
					msgIf._else().add(JExpr.ref("msg").invoke("setData").arg(JExpr.lit(0.0)));
					msgBody._return(JExpr.ref("msg"));
				}
				else
				{
					JMethod constructMsg =jclass.method(JMod.PUBLIC|JMod.ABSTRACT, topicType, "construct_"+clientName+"_Msg");
				}
				
				
			}else {
				System.out.println("The role is neither Publisher nor Subscriber");
			}
			
		}
		
		
		codeModel.build(new File((String)properties.get("topicGeneratedClassLocation")));
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void generateServiceClass(String className, HashMap<String, HashMap<String, String>> serviceMap, Dictionary<String,Object> properties) throws Exception {
		JCodeModel codeModel = new JCodeModel();
		
		JPackage jpackage = codeModel._package((String)properties.get("serviceGeneratedClassPackage"));
		
		JDefinedClass jclass = jpackage._class(JMod.PUBLIC|JMod.ABSTRACT, className);
		jclass.javadoc().add("When there has no abstract methods in the class, this abstract class can be instantiated by:\n");
		jclass.javadoc().add(className+" object = new "+className+" (){};\n");
		jclass.javadoc().add("Otherwise, the abstract method need to be overrided when instantiation");
		//Define the attributes
		AbstractJType nodeType=codeModel.ref("org.ros.node.ConnectedNode");
		AbstractJType factoryType=codeModel.ref("org.ros.message.MessageFactory");
		JFieldVar node = jclass.field(JMod.PRIVATE,nodeType,"node");
		JFieldVar Factory = jclass.field(JMod.PRIVATE,factoryType,"Factory");
		
		//Create the constructor
		JMethod jConstructor = jclass.constructor(JMod.PUBLIC);
		jConstructor.param(nodeType,"node");
		jConstructor.param(factoryType,"Factory");
		JBlock jConstructorBlock = jConstructor.body();
		jConstructorBlock.assign(JExpr._this().ref("node"), JExpr.ref("node"));
		jConstructorBlock.assign(JExpr._this().ref("Factory"), JExpr.ref("Factory"));
		
		//register implementation
		JMethod jRegister=jclass.method(JMod.PUBLIC, void.class, "register");
		JBlock jRegisterBlock=jRegister.body();	
		
		//function implementation
		int flag = 0;
		for(String clientName : serviceMap.keySet()) {
			HashMap<String, String> clientInfo = serviceMap.get(clientName);
			
			//Define useful types
			AbstractJType serviceClientType=codeModel.parseType("eu.brain.iot.robot.service.GenericService<"+clientInfo.get("serviceRequestType")+","+clientInfo.get("serviceResponseType")+">");
			AbstractJType serviceRequestType=codeModel.ref(clientInfo.get("serviceRequestType"));
			AbstractJType serviceResponseType=codeModel.ref(clientInfo.get("serviceResponseType"));
			AbstractJType stringType=codeModel.ref("java.lang.String");
			AbstractJType callResponseType=codeModel.ref("eu.brain.iot.ros.edge.node.CallResponse");
			AbstractJType stateType=codeModel.ref("java.lang.String");
			AbstractJType callReturnType=codeModel.ref("int[]");
			JDirectClass headerType=codeModel.directClass("procedures_msgs.ProcedureHeader");
			JDirectClass pResultType=codeModel.directClass("procedures_msgs.ProcedureResult");
			JDirectClass pStateType=codeModel.directClass("procedures_msgs.ProcedureState");
			JDirectClass timeType=codeModel.directClass("org.ros.message.Time");
			//Create the serviceClient Type and reference
			JFieldVar serviceClientReference = jclass.field(JMod.PUBLIC ,serviceClientType, clientName);
			
			//Generate the instance of serviceClient
			jRegisterBlock.assign(serviceClientReference, JExpr._new(serviceClientType).arg(node));
			
			//Add the register method of serviceClient to register() of the class
			if(clientInfo.get("serviceName").replaceFirst("/#robotName(.*)", "").compareTo("")==0) {
				IJExpression tempExpr=JExpr.lit("/").plus(JExpr.ref("robotName")).plus(JExpr.lit(clientInfo.get("serviceName").replaceFirst("/#robotName", "")));
				jRegisterBlock.add(JExpr.invoke(serviceClientReference,"register").arg(tempExpr).arg(clientInfo.get("serviceType")));
			
				try {
					if (flag == 0) {
						JFieldVar name = jclass.field(JMod.PRIVATE, String.class, "robotName");
						jConstructor.param(String.class, "robotName");
						jConstructorBlock.assign(JExpr._this().ref("robotName"), JExpr.ref("robotName"));
						flag = 1;
					}
				}catch(java.lang.IllegalArgumentException e) {
					System.out.println("robotName already defined");
				}
			}
			else {
				jRegisterBlock.add(JExpr.invoke(serviceClientReference,"register").arg(clientInfo.get("serviceName")).arg(clientInfo.get("serviceType")));
			}
			
			//create the call_xx method for each serviceClient ,  call_gotoRun()
			
			if (!clientName.contains("Query")) {
				JMethod jCall = jclass.method(JMod.PUBLIC, String.class, "call_" + clientName);
				jCall.javadoc().addReturn().add("check if the request is successfully sent to the ROS service, the possible result are: ok/error");
				jCall.param(serviceRequestType, "request");
				JBlock jCallBlock = jCall.body();

				// insided call_xx method
				JVar result = jCallBlock.decl(stringType, "result", JExpr.ref("null"));

				JVar responseMsg = jCallBlock.decl(serviceResponseType, "responseVal", serviceClientReference.invoke("call").arg(JExpr.ref("request")));
				
				JConditional responseIf = jCallBlock._if(responseMsg.ne(JExpr._null()));
				responseIf._then().assign(result, responseMsg.invoke("getResult").invoke("getResult"));

				// TODO check
				responseIf._else().directStatement("System.out.println(robotName+\" "+ className+" "+clientName +" Response timeout! return null\");");

				jCallBlock._return(result);
				
			} else { // CallResponse call_gotoQuery()
				
				JMethod jCall = jclass.method(JMod.PUBLIC, callResponseType, "call_" + clientName);  //CallResponse.class
				jCall.javadoc().addReturn().add("check if the request is successfully sent to the ROS service, the possible result are: ok/error");
				jCall.param(serviceRequestType, "request");
				JBlock jCallBlock = jCall.body();

				// insided call_xx method
				JVar callResp = jCallBlock.decl(callResponseType, "callResp", JExpr.ref("null"));

				JVar pqr = jCallBlock.decl(serviceResponseType, "responseVal", serviceClientReference.invoke("call").arg(JExpr.ref("request")));
				
				JConditional responseIf = jCallBlock._if(pqr.ne(JExpr._null()));
				
				JVar pState = responseIf._then().decl(pStateType, "pState", pqr.invoke("getState")); 
				JVar pResult = responseIf._then().decl(pResultType, "pResult", pqr.invoke("getResult")); 

				responseIf._then().assign(callResp, callResponseType._new());
				
				responseIf._then().assign(callResp.ref("result"), pResult.invoke("getResult"));
				responseIf._then().assign(callResp.ref("current_state"), pState.invoke("getCurrentState"));
				responseIf._then().assign(callResp.ref("last_event"), pResult.invoke("getLastEvent"));
				responseIf._then().assign(callResp.ref("message"), pState.invoke("getMessage"));

				responseIf._else().directStatement("System.out.println(robotName+\" "+ className+" "+clientName +" Response timeout! return null\");");

				jCallBlock._return(callResp);
			}
			
			
			
				
			//create the defalut construct msg method for each serviceClient
			
			if(clientInfo.get("serviceRequestType").compareTo("procedures_msgs.ProcedureQueryRequest")==0)
			{
				JMethod jConstruct = jclass.method(JMod.PUBLIC, serviceRequestType, "constructMsg_"+ clientName);
				JBlock jConstructBlock =jConstruct.body();
				jConstruct.javadoc().add("Default message constructor, need override according to usage");
				JVar request = jConstructBlock.decl(serviceRequestType, "request");
				jConstructBlock.assign(request, serviceClientReference.ref("serviceClient").invoke("newMessage"));
				JVar header = jConstructBlock.decl(headerType, "header",Factory.invoke("newFromType").arg(headerType.staticRef("_TYPE")));
				jConstructBlock.add(header.invoke("setId").arg(JExpr.lit(-1)));
				jConstructBlock.add(header.invoke("setPriority").arg(JExpr.lit(0)));
				JVar time=jConstructBlock.decl(timeType, "time",JExpr._new(timeType));
				jConstructBlock.add(timeType.staticInvoke("fromMillis").arg(JExpr.lit(0)));
				jConstructBlock.add(timeType.staticInvoke("fromNano").arg(JExpr.lit(0)));
				jConstructBlock.add(header.invoke("setStamp").arg(time));
				jConstructBlock.add(header.invoke("setName").arg(""));
				jConstructBlock.add(request.invoke("setHeader").arg(header));
				jConstructBlock._return(request);
			}
			else
			{
				JMethod jConstruct = jclass.method(JMod.PUBLIC |JMod.ABSTRACT, serviceRequestType, "constructMsg_"+clientName);
				jConstruct.javadoc().add("This abstract method need to be overrided when instantiate the class");
			}
				
		}	

		codeModel.build(new File((String)properties.get("serviceGeneratedClassLocation")));

	}
	
	
	public String read_TD_file(String path) {

		StringBuilder builder = new StringBuilder();
		
		BufferedReader br = null;
		InputStream is = null;
		try {
		is = this.getClass().getClassLoader().getResourceAsStream(path);
		br = new BufferedReader(new InputStreamReader(is));
		String s = "";
		
		
			while ((s = br.readLine()) != null) {
				builder = builder.append(s);
			}
			
		} catch (IOException e) {
			logger.error("\n Exception:", e);
		} finally {
		    if (is != null) {
		        try {
		        	br.close();
					is.close();
		        } catch (IOException e) {
		        	logger.error("\n Exception:", e);
		        }
		    }
		}
		return builder.toString();
	}
	
}
