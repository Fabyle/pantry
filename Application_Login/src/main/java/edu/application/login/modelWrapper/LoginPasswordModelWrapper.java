package edu.application.login.modelWrapper;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import edu.application.login.services.model.LoginPassword;


public class LoginPasswordModelWrapper implements LoginPassword{
	
		// Define a variable to store the property
    private StringProperty loginProperty = new SimpleStringProperty();
    
 // Define a variable to store the property
    private StringProperty passwordProperty = new SimpleStringProperty();
	

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

	@Override
	public String getLogin() {
		// TODO Auto-generated method stub
		return loginProperty.getValue();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passwordProperty.getValue();
	}
    
    
	

}
