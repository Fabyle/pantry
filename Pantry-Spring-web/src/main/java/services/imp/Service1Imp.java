package services.imp;

import org.springframework.stereotype.Component;

import services.Service1;

public class Service1Imp implements Service1 {
	
	
	

	public Service1Imp() {
		super();
		System.out.println("initialisation de service 1");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "I'm service 1";
	}

	

}
