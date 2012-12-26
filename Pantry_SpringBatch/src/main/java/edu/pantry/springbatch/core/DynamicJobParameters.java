package edu.pantry.springbatch.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

/**
 * @author LLA
 * 
 */

public class DynamicJobParameters implements JobParametersIncrementer {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    public JobParameters getNext(JobParameters parameters) {
        parameters = new JobParametersBuilder().addString("currentDate", dateFormat.format(new Date()))
                .toJobParameters();
        return parameters;
    }
}