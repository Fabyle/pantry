package javafxTable2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavafxTable2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("JavafxTable2.fxml"));
			 primaryStage.setTitle("FXML Welcome");
	    	 primaryStage.setScene(new Scene(parent, 300, 275));

	    	 

	    	 primaryStage.show();
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    
    	
     }
}