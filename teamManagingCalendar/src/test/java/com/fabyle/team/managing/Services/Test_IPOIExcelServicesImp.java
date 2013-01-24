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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.fabyle.managing.domain.Planification;
import com.fabyle.team.managing.Services.imp.CalendarServicesImp;
import com.fabyle.team.managing.Services.imp.POIExcelServicesImp;

public class Test_IPOIExcelServicesImp extends TestCase {
	
	ICalendarServices serviceGoogle = new CalendarServicesImp();
	
	
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
		
		serviceGoogle.init(props.getProperty("google.login"), props.getProperty("google.password"), "testService");
		}

	public void testWorkbook() {

		SimpleDateFormat formatFile = new SimpleDateFormat(
				"dd-MM-yyyy-hh-mm-ss");

		POIExcelServicesImp service = new POIExcelServicesImp();

		HSSFWorkbook wkb = service.initWorkbook();
		HSSFSheet worksheetservice = service.createGrilleJours(wkb, "01-01-2013", "31-03-2013");
		Map<String,List<Planification>> listPlan = null;
		
		service.addEntryfromGoogleCalendar(worksheetservice,listPlan,"01-01-2013","31-03-2013");
				
				service.SaveWorkbook("planning-" + (formatFile.format(new Date()))
						+ ".xls", wkb);

	}

}
