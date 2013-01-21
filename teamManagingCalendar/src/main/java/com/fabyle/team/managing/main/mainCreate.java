package com.fabyle.team.managing.main;

import java.io.IOException;

import com.fabyle.team.managing.Services.ICalendarServices;
import com.google.gdata.util.ServiceException;

public class mainCreate extends Init{
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			init();
			service.createCalendar("Xavier", "Calendrier de Xavier",ICalendarServices.GREEN);
			service.createCalendar("Yohan", "Calendrier de Yohan",ICalendarServices.BLUE);
			service.createCalendar("Nicolas", "Calendrier de Nicolas",ICalendarServices.RED);
			service.createCalendar("Mohamed", "Calendrier de Mohamed",ICalendarServices.BLUE);
			service.createCalendar("Fabien", "Calendrier de Fabien",ICalendarServices.RED);
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
