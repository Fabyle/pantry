package edu.pantry.google.guice.main;


import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.pantry.google.guice.consumerPackage.ConsumerForA;
import edu.pantry.google.guice.moduleGuice.moduleGuice;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Guice IOC");
		Injector injector = Guice.createInjector(new moduleGuice());		
		ConsumerForA consumer = injector.getInstance(ConsumerForA.class);
		System.out.println(consumer.getTheAtoInject().getId());
	}

}
