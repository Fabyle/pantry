/*
 * StepReadWritePersonneListener.java créé le 27 déc. 2012
 * Systalians
 */
package edu.pantry.Pantry_SpringBatch1.Listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

/**
 * TODO ajouter un commentaire
 * 
 * @author Systalians - lemoinef
 * @author Systalians - $Author:$
 * @version $Name: $
 */
public class StepReadWritePersonneListener {
	  @SuppressWarnings("unused")
	    @BeforeStep
	    public void handlingBeforeStep(StepExecution stepExecution) {
		  System.out.println("handlingBeforeStep"); 
	    }

	    @SuppressWarnings("unused")
	    @AfterStep
	    public ExitStatus afterStep(StepExecution stepExecution) {
	    	System.out.println("afterStep"); 
	        return ExitStatus.COMPLETED;
	    }

}
