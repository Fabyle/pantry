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

import com.fabyle.team.managing.Services.imp.CalendarServicesImp;

import com.google.gdata.util.ServiceException;

/**
 * @author fabien
 *
 */
public class ICalendarServiceTest extends TestCase {

	ICalendarServices service = new CalendarServicesImp();
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("http.proxyHost", "truc.si.fr");
		System.setProperty("http.proxyPort", "8080");
		//Override system DNS setting with Google free DNS server
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
	            	// ip de systproxy.si.fr 172.21.248.249
					retour =  Arrays
					    .asList(new Proxy(Proxy.Type.HTTP,
					                      new InetSocketAddress(InetAddress.getByName("truc"),
					                                            8080)));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return retour;
	        }
	    });

		
		service.init("truc@gmail.com","truc", "testService");
	}
	
	public void testCreateCalendar() {
		try {
			service.createCalendar("Xavier", "Calendrier de Xavier",ICalendarServices.GREEN);
			
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testDeleteCalendar() {
		try {
			service.deleteCalendar("Xavier");
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testAddAnEvent() {
		try {
			service.addAnEvent("Xavier", "travail","commentaires", "2013-01-30T10:00:00-08:00", "2013-02-10T10:00:00-08:00");
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testAddADayofWork() {
		try {
			service.addDaysOfWork("XRO", "travail2","commentaires2", "2013-04-28", "2013-05-05");
			service.addDaysOfWork("NMA", "travail2","commentaires2", "2013-04-28", "2013-05-05");
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
