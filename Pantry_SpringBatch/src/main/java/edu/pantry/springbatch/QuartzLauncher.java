/*
 * QuartzLauncher.java créé le 11 avr. 2012
 * Systalians
 */
package edu.pantry.springbatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO ajouter un commentaire
 * 
 * @author Systalians - Laurent
 * @author Systalians - $Author:$
 * @version $Name: $
 */
public class QuartzLauncher {

    /**
     * TODO ajouter un commentaire
     * 
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "com\\systalians\\qlf\\saf\\proto\\springbatch\\quartz-job-context.xml" });

    }

}
