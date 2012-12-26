/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.pantry.springbatch.core.step.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ReadPropertyTasklet implements Tasklet {

    protected static final Logger logger = LoggerFactory.getLogger(ReadPropertyTasklet.class);

    private String myProperty;

    @Override
    /**
     * Execute sarchive command and map its exit code to {@link ExitStatus}
     * using {@link SystemProcessExitCodeMapper}.
     */
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info("Valeur propriété : {}", myProperty);
        return RepeatStatus.FINISHED;
    }

    /**
     * Setter.
     * 
     * @param mypropertie
     *            valeur à affecter à mypropertie
     */
    public void setMyProperty(String myproperty) {
        this.myProperty = myproperty;
    }

}
