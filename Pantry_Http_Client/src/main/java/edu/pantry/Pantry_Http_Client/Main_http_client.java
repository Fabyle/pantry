package edu.pantry.Pantry_Http_Client;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class Main_http_client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create an instance of HttpClient.
					HttpClient client = new HttpClient();
					
					//String uri = "http://localhost:9999/ESIC?loginUtilisateur=lemoinef&idZoneAppelante=zoneApplicationExterne&idTechniqueClient=00117466949&typeClient=P&libelleClient=LEMOINE%20ADELE&idFonction=F0012";
					String uri = "http://www.google.fr";
					GetMethod method = new GetMethod(uri);

					// Provide custom retry handler is necessary
					method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
							new DefaultHttpMethodRetryHandler(3, false));
					
					try {

						// Execute the method.
						int statusCode = client.executeMethod(method);

						if (statusCode != HttpStatus.SC_OK) {
							
						}

						// Read the response body.
						byte[] responseBody = method.getResponseBody();
						
						
						// Deal with the response.
						// Use caution: ensure correct character encoding and is not
						// binary data
						 System.out.println(new String(responseBody));

					} catch (HttpException eb) {
						
						// eb.printStackTrace();
					} catch (IOException ec) {
						
						// ec.printStackTrace();
					} finally {
						// Release the connection.
						method.releaseConnection();
					}


					

	}

}
