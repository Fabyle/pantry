package edu.pantry.Pantry_WebServices_JAX_WS_Server;

/*
 * HelloWorldService.java créé le 28 déc. 2012
 * Systalians
 */
import javax.jws.WebService;

/**
 * TODO ajouter un commentaire
 * 
 * @author Systalians - lemoinef
 * @author Systalians - $Author:$
 * @version $Name: $
 */
@WebService(endpointInterface = "edu.pantry.Pantry_WebServices_JAX_WS_Server.HelloWorldService")
public class HelloWorldServiceImp implements HelloWorldService {
	/**
	 * @see edu.pantry.Pantry_WebServices_JAX_WS_Server.HelloWorldService#makeHelloWorld(java.lang.String)
	 */
	@Override
	public String makeHelloWorld(String value) {
		return "Hello World to " + value;
	}

	/**
	 * @see edu.pantry.Pantry_WebServices_JAX_WS_Server.HelloWorldService#simpleHelloWorld()
	 */
	@Override
	public String simpleHelloWorld() {
		return "Hello World to everybody";
	}
}
