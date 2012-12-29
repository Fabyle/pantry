package edu.pantry.quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;



public class QuartzTest {

	public static void main(String[] args) {

		try {
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// and start it off
			scheduler.start();
			
			JobDetail jd=new JobDetail("myjob",scheduler.DEFAULT_GROUP,HelloJob.class);
			
			
			// SEE Spring approach <bean id="envoiNotificationRejetCronTrigger"
            //class="org.springframework.scheduling.quartz.CronTriggerBean">
            //<property name="jobDetail" ref="envoiNotificationRejetJobQuartz"/>
            //<property name="cronExpression" value="${cron.expression.envoiRejet}"/>
			//</bean>
			SimpleTrigger st=new SimpleTrigger("mytrigger",scheduler.DEFAULT_GROUP,new Date(),
			  null,SimpleTrigger.REPEAT_INDEFINITELY,60L*1000L);
			scheduler.scheduleJob(jd, st);
			
			
			

			//scheduler.shutdown();

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
}
