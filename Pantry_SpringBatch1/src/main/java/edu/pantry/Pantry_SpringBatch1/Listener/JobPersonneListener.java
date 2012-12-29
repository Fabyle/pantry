/*
 * StepReadWritePersonneListener.java créé le 27 déc. 2012
 * Systalians
 */
package edu.pantry.Pantry_SpringBatch1.Listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * TODO ajouter un commentaire
 * 
 * @author Systalians - lemoinef
 * @author Systalians - $Author:$
 * @version $Name: $
 */
public class JobPersonneListener implements JobExecutionListener {

	/**
	 * @see org.springframework.batch.core.JobExecutionListener#afterJob(org.springframework.batch.core.JobExecution)
	 */
	public void afterJob(JobExecution arg0) {
		// TODO squelette auto-généré
		System.out.println("afterJob");
	}

	/**
	 * @see org.springframework.batch.core.JobExecutionListener#beforeJob(org.springframework.batch.core.JobExecution)
	 */
	public void beforeJob(JobExecution arg0) {
		// TODO squelette auto-généré
		System.out.println("beforeJob");
		
	}

}
