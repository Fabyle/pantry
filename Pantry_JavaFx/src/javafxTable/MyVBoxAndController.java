package javafxTable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class MyVBoxAndController extends VBox implements Initializable {
	
	@FXML private ObservableList<Person> observable;
	
	final ObservableList<Person> data = FXCollections.observableArrayList(
		    new Person("Fabien", "Smith", "jacob.smith@example.com"),
		    new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
		    new Person("Ethan", "Williams", "ethan.williams@example.com"),
		    new Person("Emma", "Jones", "emma.jones@example.com"),
		    new Person("Michael", "Brown", "michael.brown@example.com")
		);
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		observable = data;
		

		

	}

}
