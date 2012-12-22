package edu.pantry.google.guice.moduleGuice;


import com.google.inject.AbstractModule;

import edu.pantry.google.guice.implementationPackage.ImplementationA;
import edu.pantry.google.guice.interfacePackage.InterfaceA;

public class moduleGuice extends AbstractModule {
	protected void configure() {
		bind(InterfaceA.class).to(ImplementationA.class);
		
	}
}