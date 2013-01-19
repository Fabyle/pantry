package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoaderListener;

import services.Service1;
import tools.ToolBean;

/**
 * 
 * Servlet d'écoute des requetes Http de pilotage de l'ESIC les paramètres de la
 * requete sont fournit au listeners pour déclencher le traitement
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Service1 service1;
	
	
	// initialisation de la servlet
    public void init(ServletConfig c) throws ServletException {
//        String jdbc = c.getInitParameter("jdbc.url");
//        bs = new Business(jdbc);
    	System.out.println("init servlet");
    	service1 = ContextLoaderListener.getCurrentWebApplicationContext().getBean("service1",Service1.class);
    	
    	ToolBean toolbean = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ToolBean.class);
    	System.out.println("toolbeab contient :"+toolbean.service1.getName());
    	
    	
    	
    }
    
 // destruction la servlet
    public void destroy() {
    	System.out.println("destroy servlet");
    }
    
    
   
	

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		
		Map<String, String[]> parameter = request.getParameterMap();
		Map<String, String> contexteMap = new HashMap<String, String>();

		Set<Entry<String, String[]>> listEntrySet = parameter.entrySet();
		for (Entry<String, String[]> entry : listEntrySet) {
			contexteMap.put(entry.getKey(), entry.getValue()[0]);
		}

		response.setContentType("text/plain");
		response.getWriter().write("le service s'appelle :"+service1.getName());
	}
	
	

}
