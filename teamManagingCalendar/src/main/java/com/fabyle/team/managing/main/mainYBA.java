package com.fabyle.team.managing.main;

import java.io.IOException;

import com.google.gdata.util.ServiceException;

public class mainYBA extends Init{
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			init();
			service.addDaysOfWorkNumber("Yohan", "Sp�cification pour le batch portefeuille","Sp�cification pour le batch portefeuille", "2013-01-21", 5);
			} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


}
