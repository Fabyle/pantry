package edu.pantry.Pantry_SpringBatch1.MainBatch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Classe qui va lancer le batch
 * 
 * @author trainee
 * 
 */
public class BatchPersonne {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext cpt = new ClassPathXmlApplicationContext(
				"batch-context.xml");
		cpt.start();

		JobLauncher jobLauncher = (JobLauncher) cpt.getBean("jobLauncher");
		Job job = (Job) cpt.getBean("importPersonnes");
		JobParameters parameter = new JobParametersBuilder().addDate("date",
				new Date()).toJobParameters();
		jobLauncher.run(job, parameter);

		// REMARK : data aren't flush on the database file.
		// maybe I need to take a more recent version of HSQLDB

	}

}
