package edu.pantry.springbatch.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 
 * TODO ajouter un commentaire
 * 
 * @author Systalians - Laurent
 * @author Systalians - $Author:$
 * @version 1.0.0
 */
public class JobListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobListener.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unused")
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("********************************************************************");
        LOGGER.info("           BATCH VERSION : {}", getClass().getPackage().getImplementationVersion());
        LOGGER.info("********************************************************************");
    }

    /**
     * {@inheritDoc}
     */
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Status : {}", jobExecution.getStatus());
        LOGGER.info("Heure de debut : {}", jobExecution.getStartTime());
        LOGGER.info("Heure de fin : {}", jobExecution.getEndTime());
        LOGGER.info("Duree d'execution : {} ms", jobExecution.getEndTime().getTime()
                - jobExecution.getStartTime().getTime());
        LOGGER.info("********************************************************************");
        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            List<Throwable> exceptions = jobExecution.getFailureExceptions();
            for (Throwable throwable : exceptions) {
                LOGGER.info("Error StackTrace : {}", throwable);
            }
        }
    }
}
