package com.fabyle.team.managing.Services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fabyle.team.Services.exception.EventCreationException;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.util.ServiceException;

public interface ICalendarServices {

	// The HEX representation of red, blue and green
	public static final String RED = "#A32929";
	public static final String BLUE = "#2952A3";
	public static final String GREEN = "#0D7813";
	public static final String GREENLIGHT = "#25e7cc";
	public static final String YELLOWBRAUN = "#e5e791";

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
	public void deleteCalendar(String Tittle) throws IOException, ServiceException;
	
	/**
	 * initialisation du service
	 * @param userName
	 * @param password
	 * @param application
	 */
	public abstract void init(String userName, String password, String application);

	
	/**
	 * ajoute un évenement dans un calendrier 
	 * 
	 * @param calendarTitle
	 * @param eventTitle
	 * @param commentaries
	 * @param startTimeS
	 * @param endTimeS
	 * @throws IOException
	 * @throws ServiceException
	 */
	public abstract void addAnEvent(String calendarTitle,String eventTitle,String commentaries,String startTimeS,String endTimeS) throws IOException, ServiceException;

	public abstract void addDaysOfWork(String calendarTitle, String eventTitle,
			String commentaries, String startdateS, String endDateS) throws IOException,
			ServiceException, EventCreationException;

	public abstract void addDaysOfWorkNumber(String calendarTitle, String eventTitle,
			String commentaries, String startDateS, int numberOfDays) throws IOException,
			ServiceException, EventCreationException;

	public abstract void addDaysOfWorkNumber(String calendarTitle, String eventTitle,
			String commentaries, String startDateS, int numberOfDays, int rate)
			throws IOException, ServiceException, EventCreationException;

	


}
