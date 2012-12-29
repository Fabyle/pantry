package edu.pantry.Pantry_WebServices_JAX_WS_Client_1;

import edu.pantry.pantry_webservices_jax_ws_server.HelloWorldService;
import edu.pantry.pantry_webservices_jax_ws_server.HelloWorldServiceImpService;

/**
 * Hello world!
 *
 */
public class App_client 
{
    public static void main( String[] args )
    {
    	HelloWorldServiceImpService connecteur = new HelloWorldServiceImpService();
    	HelloWorldService hello = connecteur.getHelloWorldServiceImpPort();
    	System.out.println(hello.makeHelloWorld("toto"));
    }
}
