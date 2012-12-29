
package edu.pantry.Pantry_WebServices_JAX_WS_Client_1.pantry_webservices_jax_ws_server;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "HelloWorldServiceImpService", targetNamespace = "http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", wsdlLocation = "http://localhost:8080/WS/Hello?wsdl")
public class HelloWorldServiceImpService
    extends Service
{

    private final static URL HELLOWORLDSERVICEIMPSERVICE_WSDL_LOCATION;
    private final static WebServiceException HELLOWORLDSERVICEIMPSERVICE_EXCEPTION;
    private final static QName HELLOWORLDSERVICEIMPSERVICE_QNAME = new QName("http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", "HelloWorldServiceImpService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/WS/Hello?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HELLOWORLDSERVICEIMPSERVICE_WSDL_LOCATION = url;
        HELLOWORLDSERVICEIMPSERVICE_EXCEPTION = e;
    }

    public HelloWorldServiceImpService() {
        super(__getWsdlLocation(), HELLOWORLDSERVICEIMPSERVICE_QNAME);
    }

    public HelloWorldServiceImpService(WebServiceFeature... features) {
        super(__getWsdlLocation(), HELLOWORLDSERVICEIMPSERVICE_QNAME, features);
    }

    public HelloWorldServiceImpService(URL wsdlLocation) {
        super(wsdlLocation, HELLOWORLDSERVICEIMPSERVICE_QNAME);
    }

    public HelloWorldServiceImpService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HELLOWORLDSERVICEIMPSERVICE_QNAME, features);
    }

    public HelloWorldServiceImpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelloWorldServiceImpService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns HelloWorldService
     */
    @WebEndpoint(name = "HelloWorldServiceImpPort")
    public HelloWorldService getHelloWorldServiceImpPort() {
        return super.getPort(new QName("http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", "HelloWorldServiceImpPort"), HelloWorldService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelloWorldService
     */
    @WebEndpoint(name = "HelloWorldServiceImpPort")
    public HelloWorldService getHelloWorldServiceImpPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", "HelloWorldServiceImpPort"), HelloWorldService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HELLOWORLDSERVICEIMPSERVICE_EXCEPTION!= null) {
            throw HELLOWORLDSERVICEIMPSERVICE_EXCEPTION;
        }
        return HELLOWORLDSERVICEIMPSERVICE_WSDL_LOCATION;
    }

}
