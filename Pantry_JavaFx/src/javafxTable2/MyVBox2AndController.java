package javafxTable2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class MyVBox2AndController extends VBox implements Initializable {
	
	@FXML private ObservableList<Person2> observable;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		observable.add(new Person2("Fabien", "Lemoine", "Fabien_lemoine@example.com"));
		observable.get(0).setEmail("ttoo");
//		((Person) observable.get(0)).setFirstName("Audrey");
		
	

	}

}
