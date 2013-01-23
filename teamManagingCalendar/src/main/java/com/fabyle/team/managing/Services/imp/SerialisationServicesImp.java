package com.fabyle.team.managing.Services.imp;

import com.fabyle.managing.domain.EntreeAgenda;
import com.fabyle.team.managing.Services.ISerialisationServices;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class SerialisationServicesImp implements ISerialisationServices {

	@Override
	public String serialise(EntreeAgenda entree) {

		XStream xstream = new XStream(new StaxDriver()); 							
		String xml = xstream.toXML(entree);
		return xml;
	}
	
	@Override
	public EntreeAgenda deSerialise(String xml){
		
		XStream xstream = new XStream(new StaxDriver()); 
		EntreeAgenda entre = (EntreeAgenda) xstream.fromXML(xml);
		return entre;
		
	}
	

}
