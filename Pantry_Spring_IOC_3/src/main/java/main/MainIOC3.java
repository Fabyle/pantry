package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import services.Service1;
import services.Service2;

public class MainIOC3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context =
			    new ClassPathXmlApplicationContext(new String[] {"spring-service-1.xml", "spring-service-2.xml"});
		
		Service1 service1 = (Service1) context.getBean("service1");
		System.out.println("service1 says : "+service1.getName());
		
		Service2 service2 = (Service2) context.getBean("service2");
		System.out.println("service2 says : "+service2.getName());
	}

}
