package com.fabyle.team.managing.Services.imp;

import com.fabyle.managing.domain.EntreeAgenda;
import com.fabyle.team.managing.Services.ISerialisationServices;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class SerialisationServicesImp implements ISerialisationServices {

	@Override
	public String serialise(EntreeAgenda entree) {

		XStream xstream = new XStream(new StaxDriver()); // does not require
															// XPP3 library
															// starting with
															// Java 6
		String xml = xstream.toXML(entree);
		return xml;
	}

}
