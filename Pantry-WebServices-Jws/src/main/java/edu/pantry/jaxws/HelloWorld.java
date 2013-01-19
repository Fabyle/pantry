package edu.pantry.jaxws;

import javax.jws.WebService;


@WebService(name="HelloWorldPortType",
targetNamespace = "http://edu.pantry.jaxws")
public interface HelloWorld {
    String sayHi(String text);
}