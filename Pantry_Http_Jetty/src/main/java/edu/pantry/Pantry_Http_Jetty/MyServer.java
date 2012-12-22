package edu.pantry.Pantry_Http_Jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MyServer {
	
	
	private MyServlet myServlet;
	private Server server;

	/**
	 * D�marrage du serveur Jetty ESIC sur le port
	 * 
	 * @param port
	 */
	public void demarrer(int port) {

		server = new Server(port);
		ServletContextHandler handler = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		handler.setContextPath("/"); // technically not required, as "/" is the
										// default
		myServlet = new MyServlet();

		handler.addServlet(new ServletHolder(myServlet),
				myServlet.getPath());
		server.setHandler(handler);

		try {
			server.start();
		} catch (Exception e) {

		}

	}

	/**
	 * permet de transmettre � l'ESICServlet des listeners. Les listeners
	 * recevront en le contexte fournit en param�tre de la requ�te re�u par la
	 * servlet
	 * 
	 * @param listener
	 */
	public void addMyServletListener(MyServletListener listener) {
		myServlet.addMyServletListener(listener);
	}

	/**
	 * Permet d'arr�ter le serveur Jetty
	 */
	public void arreter() {
		try {
			server.stop();
		} catch (Exception e) {

		}

	}

	

}
