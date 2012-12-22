package login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LoginWithCSS extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// primary stage
		primaryStage.setTitle("JavaFX Welcome");
			
		
		// layout
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		// component
		Text scenetitle = new Text("Welcome");
		scenetitle.setId("welcome-text");//id for the css
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		// a button in a layout HBox, which is put in the Grid.
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		// a text to display informations
		final Text actiontarget = new Text();
		actiontarget.setId("actiontarget"); // id for the CSS
        grid.add(actiontarget, 1, 6);
        
        // event management
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Sign in button pressed");
            }
        });
		
		// Scene with the layout
		Scene scene = new Scene(grid, 300, 275);
		
		// take the css
		scene.getStylesheets().add(Login.class.getResource("LoginWithCSS.css").toExternalForm());
		
		
		primaryStage.setScene(scene);
		
		

		primaryStage.show();
	}
}
