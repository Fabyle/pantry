
package edu.pantry.Pantry_WebServices_JAX_WS_Server_2;

import javax.jws.WebService;

@WebService(endpointInterface = "edu.pantry.Pantry_WebServices_JAX_WS_Server_2.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

