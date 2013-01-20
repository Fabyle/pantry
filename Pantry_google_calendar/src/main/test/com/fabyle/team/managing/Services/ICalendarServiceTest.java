/**
 * 
 */
package com.fabyle.team.managing.Services;

import java.io.IOException;

import com.fabyle.team.managing.Services.imp.CalendarServicesImp;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.util.ServiceException;

import junit.framework.TestCase;

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
		service.init("bte.pilotage@gmail.com","xxx", "testService");
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
			service.addDaysOfWork("Xavier", "travail2","commentaires2", "2013-01-30", "2013-02-10");
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
