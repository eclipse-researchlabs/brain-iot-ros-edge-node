package eu.brain.iot.robot.service.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.IJAssignmentTarget;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JArray;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JConditional;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JDirectClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldRef;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JInvocation;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JTryBlock;
import com.helger.jcodemodel.JTypeVar;
import com.helger.jcodemodel.JVar;
import eu.brain.iot.robot.service.config.*;


@Component(
		immediate=true,
		properties = "OSGI-INF/config.properties"
	)
public class ServiceClassGenerator {
	
	ServiceConfig serviceConfig=null;
	
	
	@Activate
	public void loadConfig(ComponentContext context) {
		Dictionary<String,Object>  properties=context.getProperties();
		Gson gson= new Gson();
		JsonReader reader;
		try {
			System.out.println("running");
			reader=new JsonReader(new FileReader((String)properties.get("serviceConfigFile")));
			System.out.println(reader.toString());
			System.out.println("before gson.fromJson");
			serviceConfig=gson.fromJson(reader, ServiceConfig.class);
			System.out.println("finish load");
			System.out.println(serviceConfig.getRosClasses().get(0).getServceClients().get(1).getServiceClientName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			for (RosClass singleClass : serviceConfig.getRosClasses())
			generateSingleClass(singleClass,properties);
		} catch (IOException | JClassAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	public void generateSingleClass(RosClass rosClass,Dictionary<String,Object> properties) throws IOException, JClassAlreadyExistsException {
		JCodeModel codeModel = new JCodeModel();
		
		JPackage jpackage = codeModel._package((String)properties.get("serviceGeneratedClassPackage"));
		
		JDefinedClass jclass = jpackage._class(JMod.PUBLIC|JMod.ABSTRACT,rosClass.getClassName());
		jclass.javadoc().add("When there has no abstract methods in the class, this abstract class can be instantiated by:\n");
		jclass.javadoc().add(rosClass.getClassName()+" object = new "+rosClass.getClassName()+" (){};\n");
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
		for (ServceClient sc : rosClass.getServceClients()) {
			
			//Define useful types
			AbstractJType serviceClientType=codeModel.parseType("eu.brain.iot.robot.service.GenericService<"+sc.getServiceRequestType()+","+sc.getServiceResponseType()+">");
			AbstractJType serviceRequestType=codeModel.ref(sc.getServiceRequestType());
			AbstractJType serviceResponseType=codeModel.ref(sc.getServiceResponseType());
			AbstractJType resultType=codeModel.ref("java.lang.String");
			AbstractJType stateType=codeModel.ref("java.lang.String");
			AbstractJType callReturnType=codeModel.ref("int[]");
			JDirectClass headerType=codeModel.directClass("procedures_msgs.ProcedureHeader");
			JDirectClass timeType=codeModel.directClass("org.ros.message.Time");
			//Create the serviceClient Type and reference
			JFieldVar serviceClientReference = jclass.field(JMod.PUBLIC ,serviceClientType, sc.getServiceClientName());
			
			//Generate the instance of serviceClient
			jRegisterBlock.assign(serviceClientReference, JExpr._new(serviceClientType).arg(node));
			
			//Add the register method of serviceClient to register() of the class
			if(sc.getServiceName().replaceFirst("/#robotName(.*)", "").compareTo("")==0) {
				IJExpression tempExpr=JExpr.lit("/").plus(JExpr.ref("robotName")).plus(JExpr.lit(sc.getServiceName().replaceFirst("/#robotName", "")));
				jRegisterBlock.add(JExpr.invoke(serviceClientReference,"register").arg(tempExpr).arg(sc.getServiceType()));
			
				try {
					JFieldVar name = jclass.field(JMod.PRIVATE,String.class,"robotName");
					jConstructor.param(String.class,"robotName");
					jConstructorBlock.assign(JExpr._this().ref("robotName"), JExpr.ref("robotName"));
				}catch(java.lang.IllegalArgumentException e) {
					System.out.println("robotName already defined");
				}
			}
			else {
				jRegisterBlock.add(JExpr.invoke(serviceClientReference,"register").arg(sc.getServiceName()).arg(sc.getServiceType()));
			}
			//create the call_xx method for each serviceClient
			JMethod jCall = jclass.method(JMod.PUBLIC, Integer[].class, "call_"+sc.getServiceClientName());
			jCall.javadoc().addReturn().add("returnVal[0] is the check result of response result,returnVal[1] is the check result of response state");
			jCall.param(serviceRequestType,"request");
			JBlock jCallBlock=jCall.body();
				//insided call_xx method
				JVar result = jCallBlock.decl(resultType, "result");
				JVar state = jCallBlock.decl(stateType, "state");
				JVar responseMsg=jCallBlock.decl(serviceResponseType,"responseVal");
//				JVar callReturn= jCallBlock.decl(callReturnType,"returnVal",JExpr.);
				JArray arr = JExpr.newArray(codeModel.ref(Integer.class));
				arr.add(JExpr.lit(0));
				arr.add(JExpr.lit(0));
				JVar callReturn = jCallBlock.decl(codeModel.ref(Integer[].class), "returnVal", arr);
				jCallBlock.assign(responseMsg, serviceClientReference.invoke("call").arg(JExpr.ref("request")));
				JConditional responseIf=jCallBlock._if(responseMsg.ne(JExpr._null()));
				responseIf._then().assign(result,responseMsg.invoke("getResult").invoke("getResult"));
				responseIf._then().assign(state,responseMsg.invoke("getState").invoke("getCurrentState"));
				responseIf._else()._return(callReturn);
				
				//result check
				JConditional resultIf = jCallBlock._if(JExpr.invoke(result, "compareTo").arg("ok").eq(JExpr.lit(0)));
				resultIf._then().assign(callReturn.component(JExpr.lit(0)),JExpr.lit(1));
//				resultIf._else().assign(callReturn.component(JExpr.lit(0)),JExpr.lit(0));
				//state check
				JConditional stateIf = jCallBlock._if(JExpr.invoke(state, "compareTo").arg("finished").eq(JExpr.lit(0)));
				stateIf._then().assign(callReturn.component(JExpr.lit(1)),JExpr.lit(1));
				stateIf=jCallBlock._if(JExpr.invoke(state, "compareTo").arg("queued").eq(JExpr.lit(0)));
				stateIf._then().assign(callReturn.component(JExpr.lit(1)),JExpr.lit(2));
				stateIf=jCallBlock._if(JExpr.invoke(state, "compareTo").arg("running").eq(JExpr.lit(0)));
				stateIf._then().assign(callReturn.component(JExpr.lit(1)),JExpr.lit(3));
				stateIf=jCallBlock._if(JExpr.invoke(state, "compareTo").arg("paused").eq(JExpr.lit(0)));
				stateIf._then().assign(callReturn.component(JExpr.lit(1)),JExpr.lit(4));
				stateIf=jCallBlock._if(JExpr.invoke(state, "compareTo").arg("unknown").eq(JExpr.lit(0)));
				stateIf._then().assign(callReturn.component(JExpr.lit(1)),JExpr.lit(5));
				
				
				jCallBlock._return(callReturn);
			//create the defalut construct msg method for each serviceClient
			
			if(sc.getServiceRequestType().compareTo("procedures_msgs.ProcedureQueryRequest")==0)
			{
				JMethod jConstruct = jclass.method(JMod.PUBLIC, serviceRequestType, "constructMsg_"+sc.getServiceClientName());
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
				JMethod jConstruct = jclass.method(JMod.PUBLIC |JMod.ABSTRACT, serviceRequestType, "constructMsg_"+sc.getServiceClientName());
				jConstruct.javadoc().add("This abstract method need to be overrided when instantiate the class");
			}
				
		}	
		
		

		codeModel.build(new File((String)properties.get("serviceGeneratedClassLocation")));

		
	}
	
}
