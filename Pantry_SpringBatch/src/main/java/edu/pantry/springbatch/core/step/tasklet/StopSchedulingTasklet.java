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

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class StopSchedulingTasklet implements Tasklet, InitializingBean {

    protected static final Logger logger = LoggerFactory.getLogger(StopSchedulingTasklet.class);

    private String stopFile;

    @Override
    /**
     * Execute sarchive command and map its exit code to {@link ExitStatus}
     * using {@link SystemProcessExitCodeMapper}.
     */
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info("Vérification présence fichier d'arrêt");
        File file = new File(stopFile);
        if (file.exists()) {
            chunkContext.getStepContext().getStepExecution().setTerminateOnly();
            chunkContext.getStepContext().getStepExecution().setExitStatus(ExitStatus.STOPPED);
            logger.info(
                    "Détection présence fichier d'arrêt : {}\n\n********************************\nArrêt du batch\n********************************",
                    stopFile);
            System.exit(0);
        }
        return RepeatStatus.FINISHED;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(stopFile, "'stop.file' property value is required");
    }

    /**
     * Setter.
     * 
     * @param stopFile
     *            valeur à affecter à stopFile
     */
    public void setStopFile(String stopFile) {
        this.stopFile = stopFile;
    }

    /**
     * Getter.
     * 
     * @return stopFile
     */
    public String getStopFile() {
        return stopFile;
    }
}
