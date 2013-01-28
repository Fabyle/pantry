package com.fabyle.team.managing.Services.imp;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import com.fabyle.managing.domain.GantEntry;
import com.fabyle.managing.domain.Planification;
import com.fabyle.team.managing.Services.ICalendarServices;
import com.google.gdata.util.ServiceException;

public class GantServicesImp {

	public void constructGantt(ICalendarServices calendrierService,
			List<String> TitlesOfCalendar) {

		Map<String, List<Planification>> plans = new HashMap<String, List<Planification>>();
		Map<String, GantEntry> tache = new HashMap<String, GantEntry>();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		TaskSeriesCollection collection = new TaskSeriesCollection();

		try {
			plans = calendrierService.dumpCalendars(TitlesOfCalendar);
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Set<Entry<String, List<Planification>>> set = plans.entrySet();
		for (Entry<String, List<Planification>> entry : set) {
			String cle = entry.getKey();
			Date dateprevu = new Date();

			try {
				dateprevu = formatDate.parse(cle);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<Planification> planifications = entry.getValue();
			for (Planification planification : planifications) {
				if (!tache.containsKey(planification.getEntree().getTitle())) {
					GantEntry gant = new GantEntry();
					gant.setDateDebut(dateprevu);
					gant.setDateFin(dateprevu);
					gant.setProprietaire(planification.getEntree()
							.getProprietaire());
					gant.setTache(planification.getEntree().getTitle());
					tache.put(planification.getEntree().getTitle(), gant);
				} else {
					GantEntry gant = tache.get(planification.getEntree()
							.getTitle());
					if (dateprevu.before(gant.getDateDebut())) {
						gant.setDateDebut(dateprevu);
					}
					if (dateprevu.after(gant.getDateFin())) {
						gant.setDateFin(dateprevu);
					}
				}

			}
		}

		Map<String, TaskSeries> proprio = new HashMap<String, TaskSeries>();
		Collection<GantEntry> planifications = tache.values();
		for (GantEntry gantEntry : planifications) {
			if (!proprio.containsKey(gantEntry.getProprietaire())) {
				proprio.put(gantEntry.getProprietaire(), new TaskSeries(
						gantEntry.getProprietaire()));
				collection.add(proprio.get(gantEntry.getProprietaire()));
			}
			proprio.get(gantEntry.getProprietaire()).add(
					new Task(gantEntry.getTache(), new SimpleTimePeriod(
							gantEntry.getDateDebut(), gantEntry.getDateFin())));

		}

		JFreeChart chart = ChartFactory
				.createGanttChart(
						"Planning BTE - 1er trimestre 2013 - "
								+ new SimpleDateFormat("dd-MM-yyyy")
										.format(new Date()), // chart title
						"Tâche", // domain axis label
						"Date", // range axis label
						collection, // data
						true, // include legend
						true, // tooltips
						false // urls
				);

		try {
			/**
			 * This utility saves the JFreeChart as a JPEG First Parameter:
			 * FileName Second Parameter: Chart To Save Third Parameter: Height
			 * Of Picture Fourth Parameter: Width Of Picture
			 */
			ChartUtilities.saveChartAsJPEG(new File(
					"C:/temp/Planning projets BTE ("+new SimpleDateFormat("dd-MM-yyyy")
										.format(new Date())+").jpg"), chart, 1680, 1050);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problem occurred creating chart.");
		}

	}

}
