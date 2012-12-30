
package edu.pantry.Pantry_WebServices_JAX_WS_Server_2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloWorldImplTest {

    @Test
	public void testSayHi() {
        HelloWorldImpl helloWorldImpl = new HelloWorldImpl();            	
        String response = helloWorldImpl.sayHi("Sam");
        assertEquals("HelloWorldImpl not properly saying hi", "Hello Sam", response);
    }
}
