package factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import services.HelloWorld;
import services.imp.HelloWorldFrenchImp;
import consumer.Consumer;

@Configuration
public class ServicesFactory {
	
	@Bean
	public HelloWorld helloWorld(){
		return new HelloWorldFrenchImp();
	}
	
	@Bean 
	public Consumer consommateur(){
		return new Consumer();
	}
	

}
