package com.fabyle.team.managing.Services.imp;

import static com.fabyle.managing.domain.EntreeAgendaCongee.PATTERN_CONGE;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fabyle.managing.domain.EntreeAgenda;
import com.fabyle.managing.domain.EntreeAgendaCongee;
import com.fabyle.managing.domain.Planification;
import com.fabyle.team.managing.Services.ICalendarServices;
import com.fabyle.team.managing.Services.ISerialisationServices;
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
	ISerialisationServices serviceSerialisation = new SerialisationServicesImp();

	private List<Date> daysoff;
	private List<Date> datesOccupees;

	@Override
	public void addEntreeAgendaTravail(EntreeAgenda entre, String startDateS,
			int numberOfDays, int rate) {
		try {
			reset();
			URL postUrl = rechercherURL(entre.getProprietaire());

			List<String> list = this.listOfDaysNumber(entre.getTitle(),
					startDateS, numberOfDays, rate, postUrl);
			
			batchEcriture(list, entre.getProprietaire(), entre.getTitle(),
					serviceSerialisation.serialise(entre), postUrl);

			batchEcriture(list, entre.getProprietaire(), entre.getTitle(),
					entre.dumpPrerequis(), postUrl);
		} catch (IOException | ServiceException e) {
			LOGGER.error("Probl�me lors de l'ajout d'une entr�e");
		}

	}

	@Override
	public void addEntreeAgendaConge(EntreeAgendaCongee entre,
			String startDateS, String endDateS) {
		try {
			LOGGER.info("Ajout de jour de cong� pour {}",
					entre.getProprietaire());
			reset();
			URL postUrl = rechercherURL(entre.proprietaire);

			// construit la liste des dates possibles
			List<String> datesString = listOfDays(entre.getTitle(), startDateS,
					endDateS, postUrl);

			batchEcriture(datesString, entre.getProprietaire(),
					entre.getTitle(), serviceSerialisation.serialise(entre), postUrl);

		} catch (IOException | ServiceException e) {
			LOGGER.info("Erreur sur ajout de jour de cong� pour {}",
					entre.getProprietaire());
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
	public CalendarEntry createCalendar(String title, String summary,
			String color) throws IOException, ServiceException {

		// Create the calendar
		CalendarEntry calendar = new CalendarEntry();
		calendar.setTitle(new PlainTextConstruct(title));
		calendar.setSummary(new PlainTextConstruct(summary));
		calendar.setTimeZone(new TimeZoneProperty("Europe/Paris"));
		calendar.setHidden(HiddenProperty.FALSE);
		calendar.setColor(new ColorProperty(color));
		calendar.addLocation(new Where("", "", "Paris"));

		LOGGER.info("Cr�ation du calendrier :{}", title);

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
		List<CalendarEntry> listVerifiantTitle = rechercherCalendrier(calendarTitle);
		for (CalendarEntry calendarEntry : listVerifiantTitle) {
			try {
				calendarEntry.delete();
			} catch (InvalidEntryException e) {
				LOGGER.error("Erreur lors de la suppression du calendrier", e);
			}
		}

	}

	@Override
	public Map<String, List<Planification>> dumpCalendars(List<String> titles)
			throws IOException, ServiceException {

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

		Map<String, List<Planification>> retour = new HashMap<String, List<Planification>>();
		for (String title : titles) {
			URL urlOfAgenda = rechercherURL(title);

			CalendarEventFeed resultFeed = service.getFeed(urlOfAgenda,
					CalendarEventFeed.class);
			for (int i = 0; i < resultFeed.getEntries().size(); i++) {
				CalendarEventEntry entry = resultFeed.getEntries().get(i);
				Date dateEntry = new Date(entry.getTimes().get(0)
						.getStartTime().getValue());

				Planification nouveau = new Planification(dateEntry, new Date(
						entry.getTimes().get(0).getEndTime().getValue()),
						serviceSerialisation.deSerialise(entry
								.getPlainTextContent()));
				if (!retour.containsKey(formatDate.format(dateEntry))) {
					retour.put(formatDate.format(dateEntry),
							new ArrayList<Planification>());

				}

				retour.get(formatDate.format(dateEntry)).add(nouveau);

			}

		}
		return retour;
	}

	/**
	 * initialisation du service
	 * 
	 * @param userName
	 * @param password
	 * @param application
	 */
	@Override
	public void init(String userName, String password, String application) {
		service = new CalendarService(application);
		try {
			service.setUserCredentials(userName, password);
		} catch (AuthenticationException e) {
			LOGGER.debug(
					"Anomalie d'authentification � l'initialisation du Service Calendar",
					e);
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
			LOGGER.error("Probl�me lors de l'initialisation du service", e);
		}
	}

	/**
	 * Permet d'ajouter un jour dans une liste en controlant que ce jour n'est
	 * pas f�ri�, off systalians, ou d�j� occup�
	 * 
	 * @param dateToAdd
	 * @param format
	 * @param destination
	 */
	private void addDateOfWorkUnderControl(String eventTitle, Date dateToAdd,
			SimpleDateFormat format, List<String> destination, URL urlOfAgenda) {

		String dateToAddS = format.format(dateToAdd);
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar
				.getInstance();
		cal.setTime(dateToAdd);

		// cong�
		if (eventTitle.matches(PATTERN_CONGE)) {
			if (cal.get(Calendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY
					&& cal.get(Calendar.DAY_OF_WEEK) != GregorianCalendar.SATURDAY) {
				destination.add(dateToAddS);
			}

			return;
		}

		// jour f�ri�

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

		// jour occup�
		if (datesOccupees == null) {
			datesOccupees = this.initDaysWithKnowEvent(urlOfAgenda);
		}
		for (Date dateoccupe : datesOccupees) {
			String dateoccupeS = format.format(dateoccupe);
			if (dateoccupeS.equals(dateToAddS)) {
				return;
			}
		}

		if (cal.get(Calendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY
				&& cal.get(Calendar.DAY_OF_WEEK) != GregorianCalendar.SATURDAY) {
			destination.add(dateToAddS);
			datesOccupees.add(dateToAdd);
		}
	}

	/**
	 * @param datesString
	 * @param calendarTitle
	 * @param eventTitle
	 * @param commentaries
	 * @param postUrl
	 * @throws IOException
	 * @throws ServiceException
	 */
	private void batchEcriture(List<String> datesString, String calendarTitle,
			String eventTitle, String commentaries, URL postUrl)
			throws IOException, ServiceException {

		CalendarEventFeed batchRequest = new CalendarEventFeed();
		// assure l'unicit�
		Map<String, CalendarEventEntry> mapDesEntree = new HashMap<String, CalendarEventEntry>();

		int jour = 0;

		for (String dateS : datesString) {
			jour++;
			// control que l'entr�e n'existe pas
			String titre = eventTitle + " (jour : " + jour + "/"
					+ datesString.size() + ")";
			CalendarEventEntry newEntry = this.createEventEntryForDayWork(
					titre, commentaries, dateS, postUrl);
			if (newEntry != null) {
				mapDesEntree.put(titre, newEntry);
			}
		}

		if (mapDesEntree.values().isEmpty()) {
			return;

		}

		// permet d'assurer l'unicit� de titre.
		Collection<CalendarEventEntry> listeEntree = mapDesEntree.values();
		for (CalendarEventEntry newEntry : listeEntree) {
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
				LOGGER.warn("Anomalie lors du d�clenchement du batch ");
			} else {
				LOGGER.info("Enregistrement avec succes de {}", entry
						.getTitle().getPlainText());
			}

		}

	}

	/**
	 * Cr�ation d'un �venement de calendrier
	 * 
	 * @param eventTitle
	 * @param commentaries
	 * @param date
	 * @return
	 * @throws EventCreationException
	 */
	private CalendarEventEntry createEventEntryForDayWork(String eventTitle,
			String commentaries, String date, URL urlofAgenda) {

		if (existeDeja(urlofAgenda, eventTitle, date))
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
	 * R�cup�re les jours f�ri�s en France d'apr�s le google calendar
	 * "free days in France"
	 * 
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
			LOGGER.info("init des jours f�ri�s : {}", entry.getTitle()
					.getPlainText());

		}
		return retour;
	}

	/**
	 * @return la liste des jours ferm�s systalians
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
	 * Recherche tous les �v�nements d'un calendrier
	 * 
	 * @param urlOfAgenda
	 * @return
	 */
	private List<Date> initDaysWithKnowEvent(URL urlOfAgenda) {
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
			LOGGER.error(
					"Erreur lors de la recherche des evenements dans le calendrier",
					e);
		}

		return retour;

	}

	private boolean existeDeja(URL urlOfAgenda, String eventTitle,
			String dateforEntry) {
		CalendarEventFeed resultFeed;

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

		try {

			resultFeed = service.getFeed(urlOfAgenda, CalendarEventFeed.class);
			LOGGER.info(
					"Rappel URL avec max-results=500. Le retour contient {} �l�ments",
					resultFeed.getEntries().size());

			for (int i = 0; i < resultFeed.getEntries().size(); i++) {
				CalendarEventEntry entry = resultFeed.getEntries().get(i);
				if (entry.getTitle().getPlainText().equals(eventTitle)) {

					if (eventTitle.matches(PATTERN_CONGE)) {
						Date dateEntry = new Date(entry.getTimes().get(0)
								.getStartTime().getValue());
						if (formatDate.format(dateEntry).equals(dateforEntry)) {
							LOGGER.info(
									"L'entr�e {} en date du {} existe d�j�",
									eventTitle, dateforEntry);
							return true;
						} else {
							LOGGER.info(
									"L'entr�e {} en date du {} n'existe pas",
									eventTitle, dateforEntry);
							return false;
						}
					} else {
						LOGGER.info("L'entr�e {} en date du {} existe d�j�",
								eventTitle, dateforEntry);
						return true;
					}

				}
			}
		} catch (IOException | ServiceException e) {
			LOGGER.error("Probl�me lors de l'analyse des entr�es existantes");
		}
		LOGGER.info("L'entr�e {} en date du {} n'existe pas", eventTitle,
				dateforEntry);
		return false;

	}

	/**
	 * Calcul une liste de string repr�sentant les jours compris entre 2 dates.
	 * Tient compte de - Des jours ayant d�j� une tache ou de cong�s.( les jours
	 * de cong�s sont inscrits dans l'agenda ) - Des jours f�ri�s - Des jours
	 * off Systalians
	 * 
	 * @param startDateS
	 * @param endDateS
	 * @return
	 */
	private List<String> listOfDays(String eventTitle, String startDateS,
			String endDateS, URL urlOfAgenda) {
		List<String> datesString = new ArrayList<String>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateDebut = format.parse(startDateS);
			Date dateFin = format.parse(endDateS);
			addDateOfWorkUnderControl(eventTitle, dateDebut, format,
					datesString, urlOfAgenda);

			while (dateDebut.before(dateFin)) {
				dateDebut = new Date(dateDebut.getTime()
						+ TimeUnit.DAYS.toMillis(1));
				addDateOfWorkUnderControl(eventTitle, dateDebut, format,
						datesString, urlOfAgenda);
				;
			}

		} catch (ParseException e) {
			LOGGER.error("probleme dans le parse de la date ", e);
		}
		return datesString;
	}

	private List<String> listOfDaysNumber(String eventTitle, String startDateS,
			int numberOfDays, int rate, URL urlOfAgenda) {

		int result = (numberOfDays * 100 / rate);
		return listOfDaysNumber(eventTitle, startDateS, result, urlOfAgenda);
	}

	/**
	 * @param startDateS
	 * @param number
	 * @return
	 */
	private List<String> listOfDaysNumber(String eventTitle, String startDateS,
			int numberOfDays, URL urlOfAgenda) {
		List<String> datesString = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut;
		try {
			dateDebut = format.parse(startDateS);
			addDateOfWorkUnderControl(eventTitle, dateDebut, format,
					datesString, urlOfAgenda);
			while (datesString.size() < numberOfDays) {
				dateDebut = new Date(dateDebut.getTime()
						+ TimeUnit.DAYS.toMillis(1));
				addDateOfWorkUnderControl(eventTitle, dateDebut, format,
						datesString, urlOfAgenda);

			}
		} catch (ParseException e) {
			LOGGER.error("Erreur de parsing");
		}

		return datesString;
	}

	/**
	 * Recherche l'URL d'un calendrier
	 * 
	 * @param calendarTitle
	 * @return
	 */
	private URL rechercherURL(String calendarTitle) {

		URL postUrl = null;
		List<CalendarEntry> listVerifiantTitle = rechercherCalendrier(calendarTitle);

		for (CalendarEntry calendarEntry : listVerifiantTitle) {
			try {
				postUrl = new URL(calendarEntry.getLink(Link.Rel.ALTERNATE,
						Link.Type.ATOM).getHref()
						+ "?max-results=500");

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return postUrl;

	}

	/**
	 * recherche un calendrier v�rifiant un titre ( potentiellement plusieurs
	 * calendrier on le m�me titre )
	 * 
	 * @param calendarTitle
	 * @return
	 */
	private List<CalendarEntry> rechercherCalendrier(String calendarTitle) {

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

	@Override
	public void reset() {
		datesOccupees = null;

	}

	@Override
	public void deleteEntreeAgendaConge(EntreeAgenda entre) {

		try {

			URL urlOfAgenda = this.rechercherURL(entre.getProprietaire());
			CalendarEventFeed resultFeed = service.getFeed(urlOfAgenda,
					CalendarEventFeed.class);
			LOGGER.info(
					"Rappel URL avec max-results=500. Le retour contient {} �l�ments",
					resultFeed.getEntries().size());
			List<CalendarEventEntry> list = new ArrayList<CalendarEventEntry>();

			for (int i = 0; i < resultFeed.getEntries().size(); i++) {
				CalendarEventEntry entry = resultFeed.getEntries().get(i);
				
				
				if (entry.getTitle().getPlainText()
						.contains(entre.getTitle())) {
					list.add(entry);
				}
			}

			batchDeleteEvents(list, urlOfAgenda);

		} catch (IOException | ServiceException e) {
			LOGGER.error("Probl�me lors de l'analyse des entr�es existantes");
		}

	}

	/**
	 * Makes a batch request to delete all the events in the given list. If any
	 * of the operations fails, the errors returned from the server are
	 * displayed. The CalendarEntry objects in the list given as a parameters
	 * must be entries returned from the server that contain valid edit links
	 * (for optimistic concurrency to work). Note: You can add entries to a
	 * batch request for the other operation types (INSERT, QUERY, and UPDATE)
	 * in the same manner as shown below for DELETE operations.
	 * 
	 * @param service
	 *            An authenticated CalendarService object.
	 * @param eventsToDelete
	 *            A list of CalendarEventEntry objects to delete.
	 * @throws ServiceException
	 *             If the service is unable to handle the request.
	 * @throws IOException
	 *             Error communicating with the server.
	 */
	private void batchDeleteEvents(List<CalendarEventEntry> eventsToDelete,
			URL postUrl) throws ServiceException, IOException {

		// Add each item in eventsToDelete to the batch request.
		CalendarEventFeed batchRequest = new CalendarEventFeed();
		for (int i = 0; i < eventsToDelete.size(); i++) {
			CalendarEventEntry toDelete = eventsToDelete.get(i);
			// Modify the entry toDelete with batch ID and operation type.
			BatchUtils.setBatchId(toDelete, String.valueOf(i));
			BatchUtils.setBatchOperationType(toDelete,
					BatchOperationType.DELETE);
			batchRequest.getEntries().add(toDelete);
		}

		// Get the URL to make batch requests to
		CalendarEventFeed feed = service.getFeed(postUrl,
				CalendarEventFeed.class);
		Link batchLink = feed.getLink(Link.Rel.FEED_BATCH, Link.Type.ATOM);
		URL batchUrl = new URL(batchLink.getHref());

		// Submit the batch request
		CalendarEventFeed batchResponse = service.batch(batchUrl, batchRequest);

		// Ensure that all the operations were successful.
		boolean isSuccess = true;
		for (CalendarEventEntry entry : batchResponse.getEntries()) {
			String batchId = BatchUtils.getBatchId(entry);
			if (!BatchUtils.isSuccess(entry)) {
				isSuccess = false;
				BatchStatus status = BatchUtils.getBatchStatus(entry);
				
			}
			LOGGER.info("Suppression de l'entr�e {}",entry.getTitle().getPlainText());
		}
		
	}
}
