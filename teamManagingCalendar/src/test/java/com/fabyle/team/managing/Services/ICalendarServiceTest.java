/**
 * 
 */
package com.fabyle.team.managing.Services;

import java.io.IOException;
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

import junit.framework.TestCase;

import com.fabyle.team.Services.exception.EventCreationException;
import com.fabyle.team.managing.Services.imp.CalendarServicesImp;
import com.google.gdata.util.ServiceException;

/**
 * @author fabien
 * 
 */
public class ICalendarServiceTest extends TestCase {

	ICalendarServices service = new CalendarServicesImp();

	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("http.proxyHost", "gogoproxy.si.fr");
		System.setProperty("http.proxyPort", "8080");
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
					// ip de gogo.si.fr gogo
					retour = Arrays.asList(new Proxy(Proxy.Type.HTTP,
							new InetSocketAddress(InetAddress
									.getByName("gogo"), 8080)));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return retour;
			}
		});

		service.init("bte.gogo@gmail.com", "gogo6", "testService");
	}

	public void testDeleteCalendar() {
		try {
			service.deleteCalendar("Xavier");
			service.deleteCalendar("Nicolas");
			service.deleteCalendar("Yohan");
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testCreateCalendar() {
		try {
			service.createCalendar("Xavier", "Calendrier de Xavier",
					ICalendarServices.GREEN);
			service.createCalendar("Nicolas", "Calendrier de Nicolas",ICalendarServices.RED);
			service.createCalendar("Yohan", "Calendrier de Yohan",ICalendarServices.BLUE);

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
//	public void testAddADayofWorkNumber() {
//
//		ajouteJoursDeTravail("Xavier", "travailNumber", "commentaires2",
//				"2013-05-07", 3);
//
//	}

	public void testSemaine4() {

		ajouteJoursDeTravail("Xavier", "Lanceur Excel pour ESIC",
				"Lanceur Excel pour ESIC", "2013-01-21", 2);
		ajouteJoursDeTravail("Nicolas", "Validation batch GPEC",
				"Validation du batch GPEC pour MFE", "2013-01-21", 3);
		ajouteJoursDeTravail("Nicolas",
				"Esimation charge - Validation batch DN pour BCN",
				"Validation batch DN pour BCN", "2013-01-24", 2);
		ajouteJoursDeTravail("Yohan",
				"Spécification pour le batch portefeuille",
				"Spécification pour le batch portefeuille", "2013-01-21", 5);

	}

	private void ajouteJoursDeTravail(String calendarTitle, String eventTitle,
			String commentaries, String startDateS, int numberOfDays) {

		try {
			service.addDaysOfWorkNumber(calendarTitle, eventTitle,
					commentaries, startDateS, numberOfDays);
		} catch (IOException | ServiceException | EventCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
