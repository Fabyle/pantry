package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import services.ClientService;

public class MainIOC5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("spring-context.xml");
		
		ClientService theClientService = context.getBean("clientService",ClientService.class);
		System.out.println(theClientService.getName());
		
	}
}
