/*
 * HeeloJob.java créé le 28 déc. 2012
 * Systalians
 */
package edu.pantry.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * TODO ajouter un commentaire
 * 
 * @author Systalians - lemoinef
 * @author Systalians - $Author:$
 * @version $Name: $
 */
public class HelloJob implements Job {
	public HelloJob() {
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("Hello!  HelloJob is executing.");
	}

}
