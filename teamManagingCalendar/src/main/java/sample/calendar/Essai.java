package sample.calendar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Essai {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Set the http proxy to webcache.mydomain.com:8080
		 
		System.setProperty("http.proxyHost", "systproxy.si.fr");
		System.setProperty("http.proxyPort", "8080");
		 
		// Next connection will be through proxy.
		URL url;
		try {
			url = new URL("http://java.sun.com/");
			InputStream in = url.openStream();
			System.out.println(in.read());
			System.out.println(in.read());
			System.out.println(in.read());
			System.out.println(in.read());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		 
		// From now on http connections will be done directly.

	}

}
