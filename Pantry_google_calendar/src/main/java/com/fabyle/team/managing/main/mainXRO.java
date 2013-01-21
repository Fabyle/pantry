package com.fabyle.team.managing.main;

import java.io.IOException;

import com.google.gdata.util.ServiceException;

public class mainXRO extends Init{
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			init();
			service.addDaysOfWorkNumber("Xavier", "Lanceur Excel pour ESIC","Lanceur Excel pour ESIC", "2013-01-21", 2);
			} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


}
