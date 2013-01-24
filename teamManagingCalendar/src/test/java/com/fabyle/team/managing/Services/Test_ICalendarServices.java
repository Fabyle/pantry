/**
 * 
 */
package com.fabyle.team.managing.Services;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import com.fabyle.team.managing.Services.imp.CalendarServicesImp;
import com.google.gdata.util.ServiceException;

/**
 * @author fabien
 * 
 */
public class Test_ICalendarServices extends TestCase {

	ICalendarServices service = new CalendarServicesImp();

	protected void setUp() throws Exception {
		
		super.setUp();
		
		final Properties props = new Properties();

		
		InputStream is = ClassLoader.getSystemResourceAsStream("connexion.properties");
		try {
		  props.load(is);
		}
		catch (IOException e) {
		 // Handle exception here
		}
		
		System.setProperty("http.proxyHost", props.getProperty("proxy.url"));
		System.setProperty("http.proxyPort", props.getProperty("proxy.port"));
		// Override system DNS setting with Google free DNS server
		System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
		System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");

		ProxySelector.setDefault(new ProxySelector() {

			@Override
			public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
				throw new RuntimeException("Proxy connect failed", ioe);
			}

			@Override
			public List select(URI uri) {
				List retour = new ArrayList();
				try {
					// ip de du proxy
					retour = Arrays.asList(new Proxy(Proxy.Type.HTTP,
							new InetSocketAddress(InetAddress
									.getByName(props.getProperty("proxy.ip")), 8080)));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return retour;
			}
		});

		service.init(props.getProperty("google.login"), props.getProperty("google.password"), "testService");
	}


	public void testDeleteCalendar() {
		try {
			service.deleteCalendar("Xavier");
			service.deleteCalendar("Nicolas");
			service.deleteCalendar("Yohan");
			service.deleteCalendar("Jonathan");
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testCreateCalendar() {
		try {
			service.createCalendar("Xavier", "Calendrier de Xavier",
					ICalendarServices.GREEN);
			service.createCalendar("Nicolas", "Calendrier de Nicolas",
					ICalendarServices.RED);
			service.createCalendar("Yohan", "Calendrier de Yohan",
					ICalendarServices.BLUE);
			service.createCalendar("Jonathan", "Calendrier de Jonathan",
					ICalendarServices.BLUE);

		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void testAddAnEvent() {
	// try {
	// service.addAnEvent("Xavier", "travail","commentaires",
	// "2013-01-30CET10:00:00-08:00", "2013-02-10CETT10:00:00-08:00");
	// } catch (IOException | ServiceException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// public void testAddADayofWork() {
	// try {
	// service.addDaysOfWork("Xavier", "travail2","commentaires2", "2013-04-28",
	// "2013-05-20");
	// //service.addDaysOfWork("NMA", "travail2","commentaires2", "2013-04-28",
	// "2013-05-05");
	// } catch (IOException | ServiceException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// public void testAddADayofWorkNumber() {
	//
	// ajouteJoursDeTravail("Xavier", "travailNumber", "commentaires2",
	// "2013-05-07", 3);
	//
	// }

//	public void testSemaine4() {
//
//		ajouteJoursDeTravail("Xavier", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_CONC_DEV + " - "
//				+ P_LANCEUR, "Lanceur Excel pour ESIC", "2013-01-21", 2, 60);
//		ajouteJoursDeTravail("Nicolas", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_VALIDATION + " - "
//				+ P_VALID_GEC, "", "2013-01-21", 3, 60);
//		ajouteJoursDeTravail("Nicolas", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_INTERSIS + " - " + TYPE_VALIDATION + " - "
//				+ P_VALID_BCN, "Validation batch DN pour BCN",
//				"2013-01-24", 2, 60);
//		ajouteJoursDeTravail("Yohan", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_SPEC + " - " + P_EXPORT_PTF
//				,
//				"Spécification pour le batch portefeuille", "2013-01-21", 5, 60);
//		
//				
//		
//	}
		
//	public void testAjouterConge() {
//		// attention au libellé identique pour les congés - il ne pourront pas etre inserer
//				ajouteJoursDeTravail("Xavier", "Congés", "Congés", "2013-02-08", 3, 100);
//	}
//	
//	public void testAjouterTestMonteeEnCharge() {
//		ajouteJoursDeTravail("Nicolas", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_VALIDATION + " - "
//				+ P_VALID_EXPLOITP2+" - 1", "Planning des tirs de montée en charge. Tir 1",
//				"2013-02-21", 1, 100);
//		ajouteJoursDeTravail("Nicolas", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_VALIDATION + " - "
//				+ P_VALID_EXPLOITP2+" - 2", "Planning des tirs de montée en charge. Tir 2",
//				"2013-02-25", 1, 100);
//		ajouteJoursDeTravail("Nicolas", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_VALIDATION + " - "
//				+ P_VALID_EXPLOITP2+" - 3", "Planning des tirs de montée en charge. Tir 3",
//				"2013-02-21", 1, 100);
//		ajouteJoursDeTravail("Nicolas", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_VALIDATION + " - "
//				+ P_VALID_EXPLOITP2+" - 4", "Planning des tirs de montée en charge. Tir 4",
//				"2013-02-28", 1, 100);
//		ajouteJoursDeTravail("Nicolas", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_VALIDATION + " - "
//				+ P_VALID_EXPLOITP2+" - 5", "Planning des tirs de montée en charge. Tir 5",
//				"2013-03-04", 1, 100);
//		ajouteJoursDeTravail("Nicolas", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_VALIDATION + " - "
//				+ P_VALID_EXPLOITP2+" - 6", "Planning des tirs de montée en charge. Tir 6",
//				"2013-03-07", 1, 100);
//		ajouteJoursDeTravail("Nicolas", CLIENT_REUNICA + " - "
//				+ CONTRIBUTION_PALIER2 + " - " + TYPE_VALIDATION + " - "
//				+ P_VALID_EXPLOITP2+" - 7", "Planning des tirs de montée en charge. Tir 7",
//				"2013-03-11", 1, 100);
//	}
//	
//	

//	private void ajouteJoursDeTravail(String calendarTitle, String eventTitle,
//			String commentaries, String startDateS, int numberOfDays, int rate) {
//
//		try {
//			service.addDaysOfWorkNumber(calendarTitle, eventTitle,
//					commentaries, startDateS, numberOfDays, rate);
//		} catch (IOException | ServiceException | EventCreationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
