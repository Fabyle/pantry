package main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import services.Client;



public class MainIOC8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("spring-context.xml");
		
		Client theClient = context.getBean("client",Client.class);
		System.out.println(theClient.getIdentity().getName());
		System.out.println(theClient.getCarInformations().getCarName());
		
		AComponent o = (AComponent) context.getBean(AComponent.class);
		System.out.println(o.client);
	}
}
