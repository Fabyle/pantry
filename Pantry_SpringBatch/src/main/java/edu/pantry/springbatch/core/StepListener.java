package edu.pantry.springbatch.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

public class StepListener {

    protected static final Logger logger = LoggerFactory.getLogger(StepListener.class);

    @SuppressWarnings("unused")
    @BeforeStep
    public void handlingBeforeStep(StepExecution stepExecution) {
        logger.info("Called before step starts");
    }

    @SuppressWarnings("unused")
    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("Called after step ends");
        return ExitStatus.COMPLETED;
    }
}
