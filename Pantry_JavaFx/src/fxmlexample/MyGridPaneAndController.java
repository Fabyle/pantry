package fxmlexample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

// maybe better to subclasses Scene
public class MyGridPaneAndController extends GridPane implements Initializable {
	
	@FXML private Text actiontarget;
    @FXML private TextField login;

    
    private FXMLLoginProperties model = new FXMLLoginProperties();
//	    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed "+model.getLogin());
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		model.setLogin("toto");
		Bindings.bindBidirectional(login.textProperty(), model.loginProperty());
		
	}

}