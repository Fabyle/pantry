package edu.pantry.Pantry_Http_Jetty;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * Servlet d'�coute des requetes Http de pilotage de l'ESIC
 * les param�tres de la requete sont fournit au listeners pour d�clencher le traitement
 * 
 * Requete d'exemple : 
 * 
 * http://localhost:9876/ESIC?loginUtilisateur=lemoinef&environnement=DEV&
 * idFonction=F0001&idZoneAppelante=zoneRechercheClient&
 * idFonctionAppelante=F1001&espaceCourant=Clients&
 * listeClients=P*00117466949*LEMOINE+ADELE
 *  
 * @author lemoinef
 *
 */
public class MyServlet extends HttpServlet {

	private List<MyServletListener> listEsicListener = new ArrayList<MyServletListener>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * http://localhost:9876/ESIC?loginUtilisateur=lemoinef&environnement=DEV&
	 * idFonction=F0001&idZoneAppelante=zoneRechercheClient&
	 * idFonctionAppelante=F1001&espaceCourant=Clients&
	 * listeClients=P*00117466949*LEMOINE+ADELE
	 */

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		
		boolean doitContinuer = true;
		Map<String, String[]> parameter = request.getParameterMap();
		Map<String, String> contexteMap = new HashMap<String, String>();

		Set<Entry<String, String[]>> listEntrySet = parameter.entrySet();
		for (Entry<String, String[]> entry : listEntrySet) {
			contexteMap.put(entry.getKey(), entry.getValue()[0]);
		}

		

		if (doitContinuer) {
			for (MyServletListener listener : listEsicListener) {
				listener.traiterRequete(contexteMap);
			}
		}

		response.setContentType("text/plain");
		response.getWriter().write("OK-ESIC");
	}

	/**
	 * construction du path
	 * @return le path de l'url pour la servlet
	 */
	public String getPath() {
		return "/ESIC";
	}

	/**
	 * permet d'ajouter des listeners sur la servlet. 
	 * @param listener
	 */
	public void addMyServletListener(MyServletListener listener) {
		listEsicListener.add(listener);

	}

}

