package factory;

import services.ClientService;

public class DefaultServiceLocator {

	public ClientService createClientServiceInstance(){
		
		ClientService unClient = new ClientService();
		unClient.setName("un client ");
		return unClient;
		
	}
	
}
