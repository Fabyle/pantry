package edu.pantry.Pantry_Spring_IOC.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.pantry.Pantry_Spring_IOC.consumerPackage.ConsumerForA;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Spring IOC");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Module.xml");
		ConsumerForA consumer = (ConsumerForA) context.getBean("consumerForA");
		System.out.println(consumer.getTheAtoInject().getId());
	}

}
