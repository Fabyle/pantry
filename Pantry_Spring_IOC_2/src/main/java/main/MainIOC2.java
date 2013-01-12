package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import consumer.Consumer;
import factory.ServicesFactory;

public class MainIOC2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		AnnotationConfigApplicationContext context
        = new AnnotationConfigApplicationContext(ServicesFactory.class);
		
		Consumer Consumer = (Consumer) context.getBean("consommateur");
		
		
		System.out.println(Consumer.sayHelloWorld());

	}

}
