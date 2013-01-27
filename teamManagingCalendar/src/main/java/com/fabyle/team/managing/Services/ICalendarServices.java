package com.fabyle.team.managing.Services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fabyle.managing.domain.EntreeAgenda;
import com.fabyle.managing.domain.EntreeAgendaCongee;
import com.fabyle.managing.domain.Planification;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.util.ServiceException;

/**
 * @author lemoinef
 *
 */
public interface ICalendarServices {

	// The HEX representation of red, blue and green
	public static final String RED = "#A32929";
	public static final String BLUE = "#2952A3";
	public static final String GREEN = "#0D7813";
	
	
	public abstract void reset();
	


	
	

	public abstract void addEntreeAgendaTravail(EntreeAgenda entre, String startDateS,
			int numberOfDays, int rate);

	public abstract void addEntreeAgendaConge(EntreeAgendaCongee entre, String startDateS,
			String endDateS);
	
	public abstract void deleteEntreeAgendaConge(EntreeAgenda entre);
	

	/**
	 * Creates a new secondary calendar using the owncalendars feed.
	 * 
	 * @param service
	 *            An authenticated CalendarService object.
	 * @return The newly created calendar entry.
	 * @throws IOException
	 *             If there is a problem communicating with the server.
	 * @throws ServiceException
	 *             If the service is unable to handle the request.
	 */
	public CalendarEntry createCalendar(String Tittle, String summary,
			String color) throws IOException, ServiceException;

	/**
	   * Deletes the given calendar entry.
	   * 
	   * @param calendar The calendar entry to delete.
	   * @throws IOException If there is a problem communicating with the server.
	   * @throws ServiceException If the service is unable to handle the request.
	   */
	public void deleteCalendar(String Title) throws IOException, ServiceException;
	
	
	public  Map<String,List<Planification>> dumpCalendars(List<String> Titles) throws IOException, ServiceException;

	/**
	 * initialisation du service
	 * @param userName
	 * @param password
	 * @param application
	 */
	public abstract void init(String userName, String password, String application); 

	


}
