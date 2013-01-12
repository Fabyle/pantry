package consumer;

import org.springframework.beans.factory.annotation.Autowired;

import services.HelloWorld;

public class Consumer {
	
	@Autowired
	public HelloWorld helloWorld;
	
	public String sayHelloWorld(){
		return helloWorld.sayHelloWorld();
	}

}
