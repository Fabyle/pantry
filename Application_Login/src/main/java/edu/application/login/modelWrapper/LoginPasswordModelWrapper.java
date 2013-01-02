package edu.application.login.modelWrapper;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import edu.application.login.model.LoginPasswordModel;

public class LoginPasswordModelWrapper {
	
	private LoginPasswordModel model = new LoginPasswordModel();

	// Define a variable to store the property
    private StringProperty loginProperty = new SimpleStringProperty();
    
 // Define a variable to store the property
    private StringProperty passwordProperty = new SimpleStringProperty();

	

	public LoginPasswordModel getModel() {
		return model;
	}

	public void setModel(LoginPasswordModel model) {
		this.model = model;
	}

	public StringProperty getLoginProperty() {
		return loginProperty;
	}

	public void setLoginProperty(StringProperty loginProperty) {
		this.loginProperty = loginProperty;
	
	}
	
	public StringProperty getPasswordProperty() {
		return passwordProperty;
	}

	public void setPasswordProperty(StringProperty passwordProperty) {
		this.passwordProperty = passwordProperty;
	}
    
    
	

}
