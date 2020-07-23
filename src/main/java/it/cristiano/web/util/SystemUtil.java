package it.cristiano.web.util;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;

import it.cristiano.web.controller.rest.MockAbstract;

public class SystemUtil {

	public static ArrayList<String> getServiceList(String servicePackage, String formatttedResourcesPath, String contextPath, String serverPort, Environment env) throws Exception{
		ArrayList<String> serviceList = new ArrayList<String>();
		Object[] classArray = ClassUtil.getClassesInPackage(servicePackage, formatttedResourcesPath);
        for (Object clazz : classArray) {
        	String annotationPropertyValue=  clazz.getClass().getAnnotation(RequestMapping.class).value()[0];
        	String servicePropertyValue = env.getProperty(getPropertyReference2EnvKey(annotationPropertyValue));
        	for (Method method : clazz.getClass().getMethods())
	        	if (method.getName().contains(MockAbstract.SERVICE_NAME_PREFIX)) {
	        		String servicePath= method.getAnnotation(RequestMapping.class).value()[0];
	        		String serviceMethod= method.getAnnotation(RequestMapping.class).method()[0].toString();
	        		serviceList.add(String.format("\n< method ={%s}, path = %s >",serviceMethod, "http://"+InetAddress.getLocalHost().getHostAddress()+":"+serverPort+contextPath+"/"+ servicePropertyValue+servicePath));
	        	}
        }
	    return serviceList;
	}
	
	public static String getPropertyReference2EnvKey(String propRefName) {
		if (propRefName!=null)
			return propRefName.replace("${", "").replace("}", "");
		return "";
	}

}
