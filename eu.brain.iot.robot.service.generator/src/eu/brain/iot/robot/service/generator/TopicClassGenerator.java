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
import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.IJStatement;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JClassAlreadyExistsException;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JConditional;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JDirectClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JLambdaMethodRef;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.JWhileLoop;

import eu.brain.iot.robot.topic.config.RosClass;
import eu.brain.iot.robot.topic.config.Topic;
import eu.brain.iot.robot.topic.config.TopicConfig;

@Component(

		immediate=true,
		properties = "OSGI-INF/config.properties"
	)
public class TopicClassGenerator {
	
	TopicConfig topicConfig=null;
	@Activate
	public void loadConfig(ComponentContext context) {
		Dictionary<String,Object>  properties=context.getProperties();
		Gson gson= new Gson();
		JsonReader reader;
		try {
			System.out.println("running");
			reader=new JsonReader(new FileReader((String)properties.get("topicConfigFile")));
			System.out.println(reader.toString());
			System.out.println("before gson.fromJson");
			topicConfig=gson.fromJson(reader, TopicConfig.class);
			System.out.println("finish load");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			for (RosClass singleClass : topicConfig.getRosClasses())
			generateSingleClass(singleClass,properties);
		} catch (IOException | JClassAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generateSingleClass(RosClass rosClass,Dictionary<String,Object> properties) throws IOException, JClassAlreadyExistsException{
		JCodeModel codeModel = new JCodeModel();
		
		JPackage jpackage = codeModel._package((String)properties.get("topicGeneratedClassPackage"));
		JDefinedClass jclass = jpackage._class(JMod.PUBLIC|JMod.ABSTRACT,rosClass.getClassName());

		//Define the attributes
		AbstractJType nodeType=codeModel.ref("org.ros.node.ConnectedNode");
		JFieldVar node = jclass.field(JMod.PRIVATE,nodeType,"node");
		
		
		//Create the constructor
		JMethod jConstructor = jclass.constructor(JMod.PUBLIC);
		jConstructor.param(nodeType,"node");
		JBlock jConstructorBlock = jConstructor.body();
		jConstructorBlock.assign(JExpr._this().ref("node"), JExpr.ref("node"));
		
		//register implementation
		JMethod jRegister=jclass.method(JMod.PUBLIC, void.class, "register");
		JBlock jRegisterBlock=jRegister.body();
		
		for (Topic topic:rosClass.getTopics()) {
			
			if(topic.getRole().compareTo("Subscriber")==0) {
				
				//add new attribute related to topic
				JDirectClass genericSubscriber=codeModel.directClass("eu.brain.iot.robot.service.GenericSubscriber");
				AbstractJType topicType=codeModel.ref(topic.getMessageType());
				JFieldVar subscriber = jclass.field(JMod.PRIVATE,genericSubscriber.narrow(topicType),topic.getReferenceName());

				
				//register() context
				jRegisterBlock.assign(subscriber,JExpr._new(genericSubscriber.narrow(topicType)).arg(node) );
				if(topic.getTopicName().replaceFirst("/#robotName(.*)", "").compareTo("")==0) {
					IJExpression tempExpr=JExpr.lit("/").plus(JExpr.ref("robotName")).plus(JExpr.lit(topic.getTopicName().replaceFirst("/#robotName", "")));
					jRegisterBlock.add(subscriber.invoke("register").arg(tempExpr).arg(topic.getTopicType()));
					try {
						JFieldVar name = jclass.field(JMod.PRIVATE,String.class,"robotName");
						jConstructor.param(String.class,"robotName");
						jConstructorBlock.assign(JExpr._this().ref("robotName"), JExpr.ref("robotName"));
					}catch(java.lang.IllegalArgumentException e) {
						System.out.println("in subscriber robotName already defined");
					}
				}else {
					jRegisterBlock.add(subscriber.invoke("register").arg(topic.getTopicName()).arg(topic.getTopicType()));
				}
				
				//return value	
				JMethod jgetter=jclass.method(JMod.PUBLIC, topicType, "get_"+topic.getReferenceName()+"_value");
				JBlock getterbody=jgetter.body();
				JWhileLoop getWhile=getterbody._while(subscriber.invoke("getCurrentValue").eqNull());
				getterbody._return(subscriber.invoke("getCurrentValue"));
				
			}else if(topic.getRole().compareTo("Publisher")==0) {

				//add new attribute related to topic
				JDirectClass publisherType=codeModel.directClass("org.ros.node.topic.Publisher");
				AbstractJType topicType=codeModel.ref(topic.getMessageType());
				JFieldVar publisher = jclass.field(JMod.PUBLIC,publisherType.narrow(topicType),topic.getReferenceName());
				
				//register() context
				if(topic.getTopicName().replaceFirst("/#robotName(.*)", "").compareTo("")==0) {
					IJExpression tempExpr=JExpr.lit("/").plus(JExpr.ref("robotName")).plus(JExpr.lit(topic.getTopicName().replaceFirst("/#robotName", "")));
					jRegisterBlock.add(publisher.invoke("register").arg(tempExpr).arg(topic.getTopicType()));
					try {
					JFieldVar name = jclass.field(JMod.PRIVATE,String.class,"robotName");
					jConstructor.param(String.class,"robotName");
					jConstructorBlock.assign(JExpr._this().ref("robotName"), JExpr.ref("robotName"));
					}catch(java.lang.IllegalArgumentException e) {
						System.out.println("in publisher robotName already defined");
					}
				}else {
					jRegisterBlock.assign(publisher, node.invoke("newPublisher").arg(topic.getTopicName()).arg(topic.getTopicType()));
				}
				jRegisterBlock.add(publisher.invoke("setLatchMode").arg(true));
				//Message sender
				JMethod pubMsg =jclass.method(JMod.PUBLIC, void.class, "publish_"+topic.getReferenceName()+"_Msg");
				pubMsg.param(topicType, "msg");
				pubMsg.body().add(publisher.invoke("publish").arg(JExpr.ref("msg")));
				//Message constructor
				if(topic.getMessageType().compareTo("std_msgs/Float64")==0)
				{
					JMethod constructMsg =jclass.method(JMod.PUBLIC, topicType, "construct_"+topic.getReferenceName()+"_Msg");
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
					JMethod constructMsg =jclass.method(JMod.PUBLIC|JMod.ABSTRACT, topicType, "construct_"+topic.getReferenceName()+"_Msg");
				}
				
				
			}else {
				System.out.println("The role is neither Publisher nor Subscriber");
			}
			
			
		}
		
		
		codeModel.build(new File((String)properties.get("topicGeneratedClassLocation")));
		
		
	}
	
	
	
	
	
}
