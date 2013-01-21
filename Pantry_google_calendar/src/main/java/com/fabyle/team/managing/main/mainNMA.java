package com.fabyle.team.managing.main;

import java.io.IOException;

import com.google.gdata.util.ServiceException;

public class mainNMA extends Init{
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			init();
			service.addDaysOfWorkNumber("Nicolas", "Validation batch GPEC","Validation du batch GPEC pour MFE", "2013-01-21", 3);
			service.addDaysOfWorkNumber("Nicolas", "Esimation charge - Validation batch DN pour BCN","Validation batch DN pour BCN", "2013-01-24", 2);
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


}
