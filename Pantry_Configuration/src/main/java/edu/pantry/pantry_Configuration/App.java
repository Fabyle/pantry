package edu.pantry.pantry_Configuration;



import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * <p>Example application to demonstrate the Apache Commons
 * Configuration framework.</p>
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0-SNAPSHOT
 */
public class App {

    private static final String PROPERTIES_FILE_NAME = "configuration.properties";

    /**
     * <p>Default no-arg constructor.</p>
     */
    public App() {
    }

    /**
     * <p>Main application entry point.</p>
     * @param args
     * @throws ConfigurationException
     */
    public static void main(String[] args)  {
    	App application = new App();
        application.createProperties();
        application.setBackground("#c53478");
        String background = application.getBackground();
        System.out.println("background-color:" + background);
    }

    /**
     * <p>Creates a property file on the local system disk.</p>
     * @throws ConfigurationException if an <code>Exception</code> is encountered.
     */
    public void createProperties()  {
        // Create a new property and save it.
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
			config.save(PROPERTIES_FILE_NAME);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * <p>Sets a property called <code>colors.background</code> to the value provided, or assigns a color
     * value{@literal #000000} if none is provided .</p>
     * @param backgroundColor
     * @throws ConfigurationException if an <code>Exception</code> is encountered. 
     */
    public void setBackground(String backgroundColor)  {
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
			config.load(PROPERTIES_FILE_NAME);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (backgroundColor == null) {
            config.setProperty("colors.background", "#000000");
        } else {
            config.setProperty("colors.background", backgroundColor);
        }
        try {
			config.save(PROPERTIES_FILE_NAME);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * <p>Retrieves the background property from the properties file on
     * the system disk.</p>
     * @return the background color assigned in the properties file.
     * @throws ConfigurationException if an <code>Exception</code> is encountered.
     */
    public String getBackground()  {
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
			config.load(PROPERTIES_FILE_NAME);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String background = (String) config.getProperty("colors.background");
        return background;
    }
}
