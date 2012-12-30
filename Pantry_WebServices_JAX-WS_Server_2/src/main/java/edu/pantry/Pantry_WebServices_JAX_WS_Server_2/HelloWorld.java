package edu.pantry.Pantry_WebServices_JAX_WS_Server_2;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
}

