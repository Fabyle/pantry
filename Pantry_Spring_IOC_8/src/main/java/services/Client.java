package services;

public class Client {
	
	
	private Identity identity;
	private CarInformations carInformations;
	
	
	public Client(Identity identity, CarInformations carInformations) {
		super();
		this.setIdentity(identity);
		this.setCarInformations(carInformations);
	}


	public Identity getIdentity() {
		return identity;
	}


	public void setIdentity(Identity identity) {
		this.identity = identity;
	}


	public CarInformations getCarInformations() {
		return carInformations;
	}


	public void setCarInformations(CarInformations carInformations) {
		this.carInformations = carInformations;
	}

	
	
}