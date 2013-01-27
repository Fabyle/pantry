package sample.jChart;
/**
 * @Author Kushal Paudyal
 * www.sanjaal.com/java
 * Last Modified On: 2009-September 25
 *
 * Demonstrating the use of JFreeChart to create
 * Gantt Chart
 **/
 
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
 
public class MyGanttChart {
 
private static final long serialVersionUID = 3488074583840465632L;
 
public static IntervalCategoryDataset createDataset() {
 
/**
* Creating a task series
* And adding planned tasks dates on the series.
*/
TaskSeries seriesOne = new TaskSeries("Planned Implementation");
 
/**Adding data in this series**/
seriesOne.add(new Task("toto",
new SimpleTimePeriod(makeDate(10, Calendar.JUNE, 2007),
makeDate(15, Calendar.JUNE, 2007))));
 
seriesOne.add(new Task("tutu",
new SimpleTimePeriod(makeDate(9, Calendar.JULY, 2007),
makeDate(19, Calendar.JULY, 2007))));
 
seriesOne.add(new Task("Feature Addition - PHPBB Forum",
new SimpleTimePeriod(makeDate(10, Calendar.AUGUST, 2007),
makeDate(15, Calendar.AUGUST, 2007))));
 
seriesOne.add(new Task("Feature Addition - Tagged Mails",
new SimpleTimePeriod(makeDate(6, Calendar.MAY, 2007), makeDate(
30, Calendar.MAY, 2007))));
 
seriesOne.add(new Task("truc",
new SimpleTimePeriod(makeDate(2, Calendar.JUNE, 2007),
makeDate(2, Calendar.JUNE, 2007))));
 
seriesOne.add(new Task("Feature Addition - Events Gallery",
new SimpleTimePeriod(makeDate(3, Calendar.JUNE, 2007),
makeDate(31, Calendar.JULY, 2007))));
 
seriesOne.add(new Task("Google Adsense Integration",
new SimpleTimePeriod(makeDate(1, Calendar.AUGUST, 2007),
makeDate(8, Calendar.AUGUST, 2007))));
 
seriesOne.add(new Task("Adbrite Advertisement Integration",
new SimpleTimePeriod(makeDate(10, Calendar.AUGUST, 2007),
makeDate(10, Calendar.AUGUST, 2007))));
 
seriesOne.add(new Task("InfoLink Advertisement Integration",
new SimpleTimePeriod(makeDate(12, Calendar.AUGUST, 2007),
makeDate(12, Calendar.SEPTEMBER, 2007))));
 
seriesOne.add(new Task("Feature Testing", new SimpleTimePeriod(
makeDate(13, Calendar.SEPTEMBER, 2007), makeDate(31,
Calendar.OCTOBER, 2007))));
 
seriesOne.add(new Task("Public Release", new SimpleTimePeriod(makeDate(
1, Calendar.NOVEMBER, 2007), makeDate(15, Calendar.NOVEMBER,
2007))));
 
seriesOne.add(new Task("Post Release Bugs Collection",
new SimpleTimePeriod(makeDate(28, Calendar.NOVEMBER, 2007),
makeDate(30, Calendar.NOVEMBER, 2007))));
 
/**
* Creating another task series
*/
TaskSeries seriesTwo = new TaskSeries("Actual Implementation");
 
/**Adding data in this series**/
seriesTwo.add(new Task("Sanjaal Domain Registration",
new SimpleTimePeriod(makeDate(11, Calendar.JUNE, 2007),
makeDate(14, Calendar.JUNE, 2007))));
 
seriesTwo.add(new Task("Feature Addition - Java Blog",
new SimpleTimePeriod(makeDate(11, Calendar.JULY, 2007),
makeDate(19, Calendar.JULY, 2007))));
 
seriesTwo.add(new Task("Feature Addition - PHPBB Forum",
new SimpleTimePeriod(makeDate(10, Calendar.AUGUST, 2007),
makeDate(17, Calendar.AUGUST, 2007))));
 
seriesTwo.add(new Task("Feature Addition - Tagged Mails",
new SimpleTimePeriod(makeDate(7, Calendar.MAY, 2007), makeDate(
1, Calendar.JUNE, 2007))));
 
seriesTwo.add(new Task("Feature Addition - H1B Visa Portal",
new SimpleTimePeriod(makeDate(2, Calendar.JUNE, 2007),
makeDate(4, Calendar.JUNE, 2007))));
 
seriesTwo.add(new Task("Feature Addition - Events Gallery",
new SimpleTimePeriod(makeDate(3, Calendar.JUNE, 2007),
makeDate(13, Calendar.JULY, 2007))));
 
seriesTwo.add(new Task("Google Adsense Integration",
new SimpleTimePeriod(makeDate(2, Calendar.AUGUST, 2007),
makeDate(7, Calendar.AUGUST, 2007))));
 
seriesTwo.add(new Task("Adbrite Advertisement Integration",
new SimpleTimePeriod(makeDate(10, Calendar.AUGUST, 2007),
makeDate(11, Calendar.AUGUST, 2007))));
 
seriesTwo.add(new Task("InfoLink Advertisement Integration",
new SimpleTimePeriod(makeDate(13, Calendar.AUGUST, 2007),
makeDate(15, Calendar.SEPTEMBER, 2007))));
 
seriesTwo.add(new Task("Feature Testing", new SimpleTimePeriod(
makeDate(13, Calendar.SEPTEMBER, 2007), makeDate(3,
Calendar.NOVEMBER, 2007))));
 
seriesTwo.add(new Task("Public Release", new SimpleTimePeriod(makeDate(
4, Calendar.NOVEMBER, 2007), makeDate(15, Calendar.NOVEMBER,
2007))));
 
seriesTwo.add(new Task("Post Release Bugs Collection",
new SimpleTimePeriod(makeDate(28, Calendar.NOVEMBER, 2007),
makeDate(3, Calendar.DECEMBER, 2007))));
 
final TaskSeriesCollection collection = new TaskSeriesCollection();
 
/**
* Adding the series to the collection
* Holds actual Dates.
*/
collection.add(seriesOne);
collection.add(seriesTwo);
 
return collection;
}
 
private static Date makeDate(final int day, final int month, final int year) {
 
final Calendar calendar = Calendar.getInstance();
calendar.set(year, month, day);
final Date result = calendar.getTime();
return result;
 
}
 
/**
* Creates a Gantt chart based on input data set
*/
private JFreeChart createChart(final IntervalCategoryDataset dataset) {
final JFreeChart chart = ChartFactory.createGanttChart(
"Gantt Chart - Sanjaal.com Feature Implmentation", // chart title
"Task", // domain axis label
"Date", // range axis label
dataset, // data
true, // include legend
true, // tooltips
false // urls
);
return chart;
 
}
 
public void saveChart(JFreeChart chart, String fileLocation) {
String fileName = fileLocation;
try {
/**
* This utility saves the JFreeChart as a JPEG First Parameter:
* FileName Second Parameter: Chart To Save Third Parameter: Height
* Of Picture Fourth Parameter: Width Of Picture
*/
ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 800, 600);
} catch (IOException e) {
e.printStackTrace();
System.err.println("Problem occurred creating chart.");
}
}
 
/**
* Testing the Gantt Chart Creation
*/
public static void main(final String[] args) {
 
final MyGanttChart chartCreator = new MyGanttChart();
System.out.println("...Creating Dataset");
IntervalCategoryDataset dataset = createDataset();
 
System.out.println("...Creating Chart");
JFreeChart chart = chartCreator.createChart(dataset);
 
String fileName = "C:/temp/myGantChartDemo.jpg";
 
System.out.println("...Saving the Chart");
chartCreator.saveChart(chart, fileName);
 
System.out.println("...Chart Created Successfully and Saved");
System.out.println("Output Chart File Location: " + fileName);
 
}
}