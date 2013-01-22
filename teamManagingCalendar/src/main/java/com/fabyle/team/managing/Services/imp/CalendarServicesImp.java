package com.fabyle.team.managing.Services.imp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fabyle.team.Services.exception.EventCreationException;
import com.fabyle.team.managing.Services.ICalendarServices;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.batch.BatchOperationType;
import com.google.gdata.data.batch.BatchStatus;
import com.google.gdata.data.batch.BatchUtils;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.calendar.ColorProperty;
import com.google.gdata.data.calendar.HiddenProperty;
import com.google.gdata.data.calendar.TimeZoneProperty;
import com.google.gdata.data.extensions.When;
import com.google.gdata.data.extensions.Where;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.InvalidEntryException;
import com.google.gdata.util.ServiceException;

public class CalendarServicesImp implements ICalendarServices {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CalendarServicesImp.class);

	// The base URL for a user's calendar metafeed (needs a username appended).
	private static final String METAFEED_URL_BASE = "https://www.google.com/calendar/feeds/";

	// The string to add to the user's metafeedUrl to access the owncalendars
	// feed.
	private static final String OWNCALENDARS_FEED_URL_SUFFIX = "/owncalendars/full";

	// The string to add to the user's metafeedUrl to access the event feed for
	// their primary calendar.
	private static final String EVENT_FEED_URL_SUFFIX = "/private/full";

	// The URL for the owncalendars feed of the specified user.
	// (e.g.
	// http://www.googe.com/feeds/calendar/jdoe@gmail.com/owncalendars/full)
	private URL owncalendarsFeedUrl = null;

	// The URL for the event feed of the specified user's primary calendar.
	// (e.g. http://www.googe.com/feeds/calendar/jdoe@gmail.com/private/full)
	private static URL eventFeedUrl = null;

	CalendarService service;
	private String userName;
	private List<Date> daysoff;

	/**
	 * initialisation du service
	 * 
	 * @param userName
	 * @param password
	 * @param application
	 */
	@Override
	public void init(String userName, String password, String application) {
		this.userName = userName;
		service = new CalendarService(application);
		try {
			service.setUserCredentials(userName, password);
		} catch (AuthenticationException e) {
			LOGGER.debug("Anomalie à l'initialisation du Service Calendar", e);
		}
		try {
			owncalendarsFeedUrl = new URL(METAFEED_URL_BASE + userName
					+ OWNCALENDARS_FEED_URL_SUFFIX);

			eventFeedUrl = new URL(METAFEED_URL_BASE + userName
					+ EVENT_FEED_URL_SUFFIX);
			eventFeedUrl = new URL(
					"https://www.google.com/calendar/feeds/hj0276lr9bv2imehq7toae374k%40group.calendar.google.com/private/full");
		} catch (MalformedURLException e) {
			LOGGER.debug("Anomalie creation de l'URL du service", e);
		}

		try {
			daysoff = this.getDaysOffInFrance();
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fabyle.team.managing.Services.ICalendarServices#createCalendar(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CalendarEntry createCalendar(String Tittle, String summary,
			String color) throws IOException, ServiceException {

		// Create the calendar
		CalendarEntry calendar = new CalendarEntry();
		calendar.setTitle(new PlainTextConstruct(Tittle));
		calendar.setSummary(new PlainTextConstruct(summary));
		calendar.setTimeZone(new TimeZoneProperty("Europe/Paris"));
		calendar.setHidden(HiddenProperty.FALSE);
		calendar.setColor(new ColorProperty(color));
		calendar.addLocation(new Where("", "", "Paris"));

		// Insert the calendar
		return service.insert(owncalendarsFeedUrl, calendar);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fabyle.team.managing.Services.ICalendarServices#deleteCalendar(java
	 * .lang.String)
	 */
	@Override
	public void deleteCalendar(String calendarTitle) throws IOException,
			ServiceException {

		System.setProperty("http.proxyHost", "systproxy.si.fr");
		System.setProperty("http.proxyPort", "8080");

		LOGGER.info("suppression des calendriers de titre {}", calendarTitle);
		List<CalendarEntry> listVerifiantTitle = searchCalendar(calendarTitle);
		for (CalendarEntry calendarEntry : listVerifiantTitle) {
			try {
				calendarEntry.delete();
			} catch (InvalidEntryException e) {
				LOGGER.error("Erreur lors de la suppression du calendrier", e);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fabyle.team.managing.Services.ICalendarServices#addAnEvent(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void addAnEvent(String calendarTitle, String eventTitle,
			String commentaries, String startTimeS, String endTimeS)
			throws IOException, ServiceException {

		List<CalendarEntry> listVerifiantTitle = searchCalendar(calendarTitle);
		for (CalendarEntry calendarEntry : listVerifiantTitle) {
			URL postUrl = new URL(calendarEntry.getLink("alternate",
					"application/atom+xml").getHref());
			CalendarEventEntry myEntry = new CalendarEventEntry();
			myEntry.setTitle(new PlainTextConstruct(eventTitle));
			myEntry.setContent(new PlainTextConstruct(commentaries));

			DateTime startTime = DateTime.parseDateTime(startTimeS);
			DateTime endTime = DateTime.parseDateTime(endTimeS);
			// "2013-01-30T10:00:00-08:00"

			When eventTimes = new When();
			eventTimes.setStartTime(startTime);
			eventTimes.setEndTime(endTime);
			myEntry.addTime(eventTimes);

			// Send the request and receive the response:
			CalendarEventEntry insertedEntry = service.insert(postUrl, myEntry);

		}

	}

	@Override
	public void addDaysOfWorkNumber(String calendarTitle, String eventTitle,
			String commentaries, String startDateS, int numberOfDays)
			throws IOException, ServiceException, EventCreationException {

		URL postUrl = rechercherURL(calendarTitle);

		List<String> list = this.listOfDaysNumber(startDateS, numberOfDays,
				postUrl);
		addDaysOfWork(calendarTitle, eventTitle, commentaries, list.get(0),
				list.get(list.size() - 1), postUrl);

	}

	@Override
	public void addDaysOfWorkNumber(String calendarTitle, String eventTitle,
			String commentaries, String startDateS, int numberOfDays, int rate)
			throws IOException, ServiceException, EventCreationException {

		URL postUrl = rechercherURL(calendarTitle);

		List<String> list = this.listOfDaysNumber(startDateS, numberOfDays,rate,
				postUrl);
		addDaysOfWork(calendarTitle, eventTitle, commentaries, list.get(0),
				list.get(list.size() - 1), postUrl);

	}

	@Override
	public void addDaysOfWork(String calendarTitle, String eventTitle,
			String commentaries, String startDateS, String endDateS)
			throws IOException, ServiceException, EventCreationException {

		URL postUrl = rechercherURL(calendarTitle);
		addDaysOfWork(calendarTitle, eventTitle, commentaries, startDateS,
				endDateS, postUrl);

	}

	/**
	 * @param calendarTitle
	 * @param eventTitle
	 * @param commentaries
	 * @param startDateS
	 * @param endDateS
	 * @param postUrl
	 * @throws IOException
	 * @throws ServiceException
	 * @throws EventCreationException
	 */
	private void addDaysOfWork(String calendarTitle, String eventTitle,
			String commentaries, String startDateS, String endDateS, URL postUrl)
			throws IOException, ServiceException, EventCreationException {

		CalendarEventFeed batchRequest = new CalendarEventFeed();

		List<String> datesString = listOfDays(startDateS, endDateS, postUrl);

		int jour = 0;

		for (String dateS : datesString) {
			jour++;
			CalendarEventEntry newEntry = this.createEventEntryForDayWork(
					eventTitle + " (jour : " + jour + ")", commentaries + "\n"
							+ new Date(), dateS, postUrl);
			BatchUtils.setBatchId(newEntry, String.valueOf(1));
			BatchUtils.setBatchOperationType(newEntry,
					BatchOperationType.INSERT);
			batchRequest.getEntries().add(newEntry);
		}

		// Get the URL to make batch requests to
		CalendarEventFeed feed = service.getFeed(postUrl,
				CalendarEventFeed.class);
		Link batchLink = feed.getLink(Link.Rel.FEED_BATCH, Link.Type.ATOM);
		URL batchUrl = new URL(batchLink.getHref());

		// Submit the batch request
		CalendarEventFeed batchResponse = service.batch(batchUrl, batchRequest);

		for (CalendarEventEntry entry : batchResponse.getEntries()) {
			String batchId = BatchUtils.getBatchId(entry);
			if (!BatchUtils.isSuccess(entry)) {
				BatchStatus status = BatchUtils.getBatchStatus(entry);
				LOGGER.warn("Anomalie lors du déclenchement du batch ");
			}

		}

	}

	/**
	 * @return
	 * @throws IOException
	 * @throws ServiceException
	 */
	private List<Date> getDaysOffInFrance() throws IOException,
			ServiceException {

		List<Date> retour = new ArrayList<Date>();

		CalendarEventFeed resultFeed = service
				.getFeed(
						new URL(
								"https://www.google.com/calendar/feeds/fr.french%23holiday%40group.v.calendar.google.com/public/full?singleevents=true"),
						CalendarEventFeed.class);

		for (int i = 0; i < resultFeed.getEntries().size(); i++) {
			CalendarEventEntry entry = resultFeed.getEntries().get(i);

			List<When> whens = entry.getTimes();

			retour.add(new Date(whens.get(0).getStartTime().getValue()));

		}
		return retour;
	}

	private boolean isOkForInsert(URL urlOfAgenda, String eventTitle)
			throws EventCreationException {
		CalendarEventFeed resultFeed;

		try {
			resultFeed = service.getFeed(urlOfAgenda, CalendarEventFeed.class);
			for (int i = 0; i < resultFeed.getEntries().size(); i++) {
				CalendarEventEntry entry = resultFeed.getEntries().get(i);
				if (entry.getTitle().getPlainText().equals(eventTitle)) {
					throw new EventCreationException();
				}

			}
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	/**
	 * @return
	 */
	private List<Date> getDaysOffSystalians() {
		List<Date> retour = new ArrayList<Date>();
		String tab2[] = { "2013-05-10", "2013-08-16", "2013-05-08" };
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < tab2.length; i++) {
			try {
				retour.add(format.parse(tab2[i]));
			} catch (ParseException e) {
				LOGGER.debug("Erreur de parsing", e);
			}
		}
		return retour;

	}

	/**
	 * @param startDateS
	 * @param endDateS
	 * @return
	 */
	private List<String> listOfDays(String startDateS, String endDateS,
			URL urlOfAgenda) {
		List<String> datesString = new ArrayList<String>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateDebut = format.parse(startDateS);
			Date dateFin = format.parse(endDateS);

			if (dateDebut.before(dateFin)) {
				addDateOfWorkUnderControl(dateDebut, format, datesString,
						urlOfAgenda);
				while (dateDebut.before(dateFin)) {
					dateDebut = new Date(dateDebut.getTime()
							+ TimeUnit.DAYS.toMillis(1));
					addDateOfWorkUnderControl(dateDebut, format, datesString,
							urlOfAgenda);
					;
				}

			}

		} catch (ParseException e) {
			LOGGER.error("probleme dans le parse de la date ", e);
		}
		return datesString;
	}

	/**
	 * @param startDateS
	 * @param number
	 * @return
	 */
	private List<String> listOfDaysNumber(String startDateS, int numberOfDays,
			URL urlOfAgenda) {
		List<String> datesString = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut;
		try {
			dateDebut = format.parse(startDateS);
			addDateOfWorkUnderControl(dateDebut, format, datesString,
					urlOfAgenda);
			while (datesString.size() < numberOfDays) {
				dateDebut = new Date(dateDebut.getTime()
						+ TimeUnit.DAYS.toMillis(1));
				addDateOfWorkUnderControl(dateDebut, format, datesString,
						urlOfAgenda);

			}
		} catch (ParseException e) {
			LOGGER.error("Erreur de parsing");
		}

		return datesString;
	}

	private List<String> listOfDaysNumber(String startDateS, int numberOfDays,
			int rate, URL urlOfAgenda) {

		int result = (numberOfDays * 100 / rate);
		return listOfDaysNumber(startDateS, result, urlOfAgenda);
	}

	/**
	 * @param dateToAdd
	 * @param format
	 * @param destination
	 */
	private void addDateOfWorkUnderControl(Date dateToAdd,
			SimpleDateFormat format, List<String> destination, URL urlOfAgenda) {

		// jour férié
		String dateToAddS = format.format(dateToAdd);
		for (Date dateferie : daysoff) {
			String dateFerieS = format.format(dateferie);
			if (dateFerieS.equals(dateToAddS)) {
				return;
			}
		}

		// jour systalians
		List<Date> dateSystalians = this.getDaysOffSystalians();
		for (Date dateSys : dateSystalians) {
			String dateSysS = format.format(dateSys);
			if (dateSysS.equals(dateToAddS)) {
				return;
			}
		}

		// jour occupé
		List<Date> datesOccupees = this.getDaysWithKnowEvent(urlOfAgenda);
		for (Date dateoccupe : datesOccupees) {
			String dateoccupeS = format.format(dateoccupe);
			if (dateoccupeS.equals(dateToAddS)) {
				return;
			}
		}

		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar
				.getInstance();
		cal.setTime(dateToAdd);

		if (cal.get(Calendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY
				&& cal.get(Calendar.DAY_OF_WEEK) != GregorianCalendar.SATURDAY) {
			destination.add(dateToAddS);
		}
	}

	/**
	 * @param eventTitle
	 * @param commentaries
	 * @param date
	 * @return
	 * @throws EventCreationException
	 */
	private CalendarEventEntry createEventEntryForDayWork(String eventTitle,
			String commentaries, String date, URL urlofAgenda)
			throws EventCreationException {

		if (!isOkForInsert(urlofAgenda, eventTitle))
			return null;

		CalendarEventEntry myEntry = new CalendarEventEntry();
		myEntry.setTitle(new PlainTextConstruct(eventTitle));
		myEntry.setContent(new PlainTextConstruct(commentaries));

		DateTime startTime = DateTime.parseDateTime(date + "T09:00:00+01:00");
		DateTime endTime = DateTime.parseDateTime(date + "T18:00:00+01:00");
		// "2013-01-30T10:00:00-08:00"

		When eventTimes = new When();
		eventTimes.setStartTime(startTime);
		eventTimes.setEndTime(endTime);
		myEntry.addTime(eventTimes);
		return myEntry;

	}

	/**
	 * search all calendar which have for title <calendarTitle>
	 * 
	 * @param calendarTitle
	 * @return
	 */
	private List<CalendarEntry> searchCalendar(String calendarTitle) {

		List<CalendarEntry> retour = new ArrayList<CalendarEntry>();
		CalendarFeed resultFeed;
		try {
			resultFeed = service.getFeed(owncalendarsFeedUrl,
					CalendarFeed.class);
			for (int i = 0; i < resultFeed.getEntries().size(); i++) {
				CalendarEntry entry = resultFeed.getEntries().get(i);
				if (entry.getTitle().getPlainText().equals(calendarTitle)) {
					retour.add(entry);
				}
			}
		} catch (IOException | ServiceException e) {
			LOGGER.error("Erreur lors de la recherche du calendrier", e);
		}

		return retour;

	}

	private List<Date> getDaysWithKnowEvent(URL urlOfAgenda) {
		CalendarEventFeed resultFeed;
		List<Date> retour = new ArrayList<Date>();

		try {
			resultFeed = service.getFeed(urlOfAgenda, CalendarEventFeed.class);
			for (int i = 0; i < resultFeed.getEntries().size(); i++) {
				CalendarEventEntry entry = resultFeed.getEntries().get(i);
				retour.add(new Date(entry.getTimes().get(0).getStartTime()
						.getValue()));

			}
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retour;

	}

	private URL rechercherURL(String calendarTitle) {

		URL postUrl = null;
		List<CalendarEntry> listVerifiantTitle = searchCalendar(calendarTitle);
		CalendarEventFeed batchRequest = new CalendarEventFeed();
		for (CalendarEntry calendarEntry : listVerifiantTitle) {
			try {
				postUrl = new URL(calendarEntry.getLink(Link.Rel.ALTERNATE,
						Link.Type.ATOM).getHref());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return postUrl;

	}
}
