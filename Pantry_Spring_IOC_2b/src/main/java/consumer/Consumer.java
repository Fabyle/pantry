package consumer;

import javax.inject.Inject;

import services.HelloWorld;

public class Consumer {

	@Inject
	public HelloWorld helloWorld;

	public String sayHelloWorld() {
		return helloWorld.sayHelloWorld();
	}

}
