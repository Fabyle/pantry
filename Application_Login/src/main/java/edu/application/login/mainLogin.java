package edu.application.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import edu.application.login.paneAndController.AnchorPaneAndControler;

public class mainLogin extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		/*
		 * StageStyle.DECORATED – A stage with solid white background and
		 * platform decorations.
		 * 
		 * StageStyle.UNDECORATED – A stage with a solid white background and no
		 * decorations. This is basically just a Stage with no decorations at
		 * all just a solid white rectangle with no borders for you to paint on.
		 * 
		 * StageStyle.TRANSPARENT – A stage with a transparent background and no
		 * decorations. Same as the above but with a transparent background
		 * instead of solid white.
		 * 
		 * StageStyle.UTILITY – A stage with a solid white background and
		 * minimal platform decorations.
		 */

		primaryStage.initStyle(StageStyle.UNDECORATED);
		AnchorPaneAndControler parent = (AnchorPaneAndControler) FXMLLoader
				.load(getClass().getResource("loginDialog.fxml"));

		primaryStage.setTitle("Bienvenue");
		primaryStage.setScene(new Scene(parent, 250, 175));
		primaryStage.setResizable(false);

		primaryStage.show();

	}

}
