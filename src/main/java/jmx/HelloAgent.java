package jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.modelmbean.RequiredModelMBean;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import dynamic.HelloDynamic;
import model.ModelMBeanUtils;
import notification.HelloListener;
import notification.Jack;

public class HelloAgent {

	public static void main(String[] args) throws Exception {
		// MBeanServer server = MBeanServerFactory.createMBeanServer();
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		ObjectName helloName = new ObjectName("chengang:name=HelloWorld");
		
		/**standard-mbean begin**/
		//Hello hello=new Hello();
		/**standard-mbean end**/
		
		/**model-mbean begin**/
		RequiredModelMBean hello = ModelMBeanUtils.createModlerMBean();
		/**model-mbean end**/
		
		/**dynamic-mbean begin**/
		//HelloDynamic hello = new HelloDynamic();
		/**dynamic-mbean end**/
		
		server.registerMBean(hello, helloName);
		
		/**以下内容可通过浏览器请求 http://localhost:8082 来访问
		 * 实际上, 一些jmx client比如jconsole等不需要如下功能 **/
		ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		server.registerMBean(adapter, adapterName);
		
		/**notification begin**/
//		 Jack jack = new Jack(); 
//		 server.registerMBean(jack, new ObjectName("HelloAgent:name=jack")); 
//		 jack.addNotificationListener(new HelloListener(), null, hello); 
		/**notification end**/
		 
		adapter.start();
		System.out.println("start.....");
	}

}
