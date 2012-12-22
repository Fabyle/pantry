package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.error("Test du logger");
	}

}
