package com.fabyle.team.managing.main;

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

import com.fabyle.team.managing.Services.ICalendarServices;
import com.fabyle.team.managing.Services.imp.CalendarServicesImp;

public class Init {
	
	static ICalendarServices service = new CalendarServicesImp();

	protected static void init() {
		System.setProperty("http.proxyHost", "truc");
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
	
		
		service.init("bte.truc@gmail.com","truc", "testService");
	}

}
