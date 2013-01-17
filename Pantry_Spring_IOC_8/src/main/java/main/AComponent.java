package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import services.Client;

@Component
public class AComponent {

	
	@Autowired
	public Client client;
		

	}


