
package edu.pantry.jaxws;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService(serviceName = "HelloWorld",
portName="HelloWorldPort",
endpointInterface = "edu.pantry.jaxws.HelloWorld",
targetNamespace = "http://edu.pantry.jaxws")
//,
//wsdlLocation = "WEB-INF/wsdl/CalculatorService.wsdl")
public class HelloWorldImpl implements HelloWorld {
	
	 @Resource
	 private WebServiceContext context;
	

    public String sayHi(String text) {
        return "Hello " + text;
    }
}