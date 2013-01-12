package services;

public class ClientService {
	private static ClientService clientService = new ClientService();
	
	private String name;

	public String getName() {
		return name;
	}

	private ClientService() {
	}

	public static ClientService createInstance() {
		clientService.name = "the client service";
		return clientService;
	}
}