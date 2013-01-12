package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import services.Service1;
import services.Service2;
import services.Service3;

public class MainIOC4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("spring-with-import.xml");
		
		
		
		Service1 service1 = (Service1) context.getBean("service1");
		System.out.println("service1 says : "+service1.getName());
		
		Service2 service2 = (Service2) context.getBean("service2");
		System.out.println("service2 says : "+service2.getName());
		
		Service3 service3 = (Service3) context.getBean("service3");
		System.out.println("service3 says : "+service3.getName());
	}

}
