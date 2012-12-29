/*
 * HelloWorldServiceInterface.java créé le 28 déc. 2012
 * Systalians
 */
package edu.pantry.Pantry_WebServices_JAX_WS_Server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * TODO ajouter un commentaire
 * 
 * @author Systalians - lemoinef
 * @author Systalians - $Author:$
 * @version $Name: $
 */
@WebService
public interface HelloWorldService {

	@WebMethod
	public abstract String makeHelloWorld(String value);

	@WebMethod
	public abstract String simpleHelloWorld();

}