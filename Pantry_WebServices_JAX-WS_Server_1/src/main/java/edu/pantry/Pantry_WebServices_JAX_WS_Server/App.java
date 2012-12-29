package edu.pantry.Pantry_WebServices_JAX_WS_Server;

import javax.xml.ws.Endpoint;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Endpoint.publish("http://localhost:8080/WS/Hello",new HelloWorldServiceImp());
    }
}
