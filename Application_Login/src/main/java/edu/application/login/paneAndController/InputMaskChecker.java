package edu.application.login.paneAndController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class InputMaskChecker implements ChangeListener<String> {
	
	// logger
		private static final Logger LOGGER = LoggerFactory
				.getLogger(InputMaskChecker.class);

    public static final String NUMERIC      = "^[0-9]*$";
    public static final String TEXTONLY     = "^\\w*$";
    public static final String PASSWORD     = "^[\\w\\+\\!\\?\\-\\$\\&\\%£]+$";
    public static final String DATASOURCE   = "^([a-zA-Z]+:){3}@([a-zA-Z0-9]+:)+[a-zA-Z0-9]+$";
    public static final String TCPPORT      = "^(6553[0-5]|655[0-2]\\d|65[0-4]\\d\\d|6[0-4]\\d{3}|[1-5]\\d{4}|[2-9]\\d{3}|1[1-9]\\d{2}|10[3-9]\\d|102[4-9])$";

    private static final String STYLE = "-fx-effect: dropshadow(gaussian, red, 4, 0.0, 0, 0);";

    public final BooleanProperty erroneous = new SimpleBooleanProperty(false);

    private final String mask;
    private final int max_lenght;
    private final TextField control;
    public SimpleBooleanProperty valid = new SimpleBooleanProperty();


    public InputMaskChecker(String mask, TextField control,Integer maxLenght) {
        this.mask = mask;
        this.max_lenght = (maxLenght == null ? 0 : maxLenght.intValue());        
        this.control = control;
    }

    public InputMaskChecker(String mask, int max_lenght, TextField control) {
        this.mask = mask;
        this.max_lenght = max_lenght;
        this.control = control;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
    	valid.set(newValue.matches(mask) && ((max_lenght > 0) ? newValue.length() < max_lenght : true) && newValue.length() != 0);
        LOGGER.debug("valeur de valide {}",valid);
       
    	// BTW, I would suggest to use styleclasses instead for performance reasons
        control.setStyle( valid.get() ?  "-fx-effect: null;" :STYLE  );
    }
}