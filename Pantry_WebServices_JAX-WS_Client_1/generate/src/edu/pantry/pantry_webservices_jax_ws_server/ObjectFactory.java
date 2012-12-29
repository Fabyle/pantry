
package edu.pantry.pantry_webservices_jax_ws_server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.pantry.pantry_webservices_jax_ws_server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SimpleHelloWorld_QNAME = new QName("http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", "simpleHelloWorld");
    private final static QName _MakeHelloWorld_QNAME = new QName("http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", "makeHelloWorld");
    private final static QName _MakeHelloWorldResponse_QNAME = new QName("http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", "makeHelloWorldResponse");
    private final static QName _SimpleHelloWorldResponse_QNAME = new QName("http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", "simpleHelloWorldResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.pantry.pantry_webservices_jax_ws_server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SimpleHelloWorldResponse }
     * 
     */
    public SimpleHelloWorldResponse createSimpleHelloWorldResponse() {
        return new SimpleHelloWorldResponse();
    }

    /**
     * Create an instance of {@link MakeHelloWorldResponse }
     * 
     */
    public MakeHelloWorldResponse createMakeHelloWorldResponse() {
        return new MakeHelloWorldResponse();
    }

    /**
     * Create an instance of {@link MakeHelloWorld }
     * 
     */
    public MakeHelloWorld createMakeHelloWorld() {
        return new MakeHelloWorld();
    }

    /**
     * Create an instance of {@link SimpleHelloWorld }
     * 
     */
    public SimpleHelloWorld createSimpleHelloWorld() {
        return new SimpleHelloWorld();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleHelloWorld }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", name = "simpleHelloWorld")
    public JAXBElement<SimpleHelloWorld> createSimpleHelloWorld(SimpleHelloWorld value) {
        return new JAXBElement<SimpleHelloWorld>(_SimpleHelloWorld_QNAME, SimpleHelloWorld.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeHelloWorld }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", name = "makeHelloWorld")
    public JAXBElement<MakeHelloWorld> createMakeHelloWorld(MakeHelloWorld value) {
        return new JAXBElement<MakeHelloWorld>(_MakeHelloWorld_QNAME, MakeHelloWorld.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeHelloWorldResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", name = "makeHelloWorldResponse")
    public JAXBElement<MakeHelloWorldResponse> createMakeHelloWorldResponse(MakeHelloWorldResponse value) {
        return new JAXBElement<MakeHelloWorldResponse>(_MakeHelloWorldResponse_QNAME, MakeHelloWorldResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleHelloWorldResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Pantry_WebServices_JAX_WS_Server.pantry.edu/", name = "simpleHelloWorldResponse")
    public JAXBElement<SimpleHelloWorldResponse> createSimpleHelloWorldResponse(SimpleHelloWorldResponse value) {
        return new JAXBElement<SimpleHelloWorldResponse>(_SimpleHelloWorldResponse_QNAME, SimpleHelloWorldResponse.class, null, value);
    }

}
