package fxmlexample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXMLLoginProperties {
	
	 // Define a variable to store the property
    private StringProperty login = new SimpleStringProperty();
 
    // Define a getter for the property's value
    public final String getLogin(){return login.get();}
 
    // Define a setter for the property's value
    public final void setLogin(String value){login.set(value);}
 
     // Define a getter for the property itself
    public StringProperty loginProperty() {return login;}
 

}
