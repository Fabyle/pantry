package fxmlexample;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
		try {
			MyGridPaneAndController parent = (MyGridPaneAndController) FXMLLoader.load(getClass().getResource("fxml_example.fxml"));
			 primaryStage.setTitle("FXML Welcome");
	    	 primaryStage.setScene(new Scene(parent, 300, 275));

	    	 

	    	 primaryStage.show();
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    
    	
     }
}