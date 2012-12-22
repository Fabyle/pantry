package edu.pantry.google.guice.consumerPackage;


import com.google.inject.Inject;

import edu.pantry.google.guice.interfacePackage.InterfaceA;

public class ConsumerForA {
	
	@Inject
	InterfaceA theAtoInject;
	
	// can be use with a constructor too
	@Inject
	public ConsumerForA(InterfaceA theAtoInject) {
		super();
		this.theAtoInject = theAtoInject;
	}
	

	public InterfaceA getTheAtoInject() {
		return theAtoInject;
	}

	public void setTheAtoInject(InterfaceA theAtoInject) {
		this.theAtoInject = theAtoInject;
	}
	
	

}
