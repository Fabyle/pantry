package edu.pantry.Pantry_Http_Jetty;

import java.util.Map;



/**
 * Interface des listeners qui veulent recevoir de l'information
 * de la part de la EsicServlet.
 * @author lemoinef
 *
 */
public interface MyServletListener {
	
	
	void traiterRequete(Map contexte);

}
