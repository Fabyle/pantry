package edu.pantry.Pantry_Spring_IOC.consumerPackage;

import edu.pantry.Pantry_Spring_IOC.interfacePackage.InterfaceA;

public class ConsumerForA {
	
	InterfaceA theAtoInject;

	public InterfaceA getTheAtoInject() {
		return theAtoInject;
	}

	public void setTheAtoInject(InterfaceA theAtoInject) {
		this.theAtoInject = theAtoInject;
	}
	
	

}
