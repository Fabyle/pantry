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

import static com.fabyle.team.managing.Services.NomenclatureServices.NMA2;
import static com.fabyle.team.managing.Services.NomenclatureServices.NMA;
import static com.fabyle.team.managing.Services.NomenclatureServices.YBA;
import static com.fabyle.team.managing.Services.NomenclatureServices.OBO;
import static com.fabyle.team.managing.Services.NomenclatureServices.XRO;
import org.apache.poi.util.ArrayUtil;

import junit.framework.TestCase;

import com.fabyle.team.managing.Services.imp.CalendarServicesImp;
import com.fabyle.team.managing.Services.imp.GantServicesImp;

public class Test_IGantServices extends TestCase {

	ICalendarServices serviceCalendar = new CalendarServicesImp();
	GantServicesImp serviceGantt = new GantServicesImp();

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

		serviceCalendar.init(props.getProperty("google.login"), props.getProperty("google.password"), "testService");
	}
	
	 public void testGantt() {
		 
		 String[] liste = { XRO, YBA, NMA , NMA2, OBO};
		
		 serviceGantt.constructGantt(serviceCalendar, Arrays.asList(liste));
		
		 }
	
	


}
