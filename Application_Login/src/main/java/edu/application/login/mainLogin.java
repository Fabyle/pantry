package edu.application.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.application.login.paneAndController.LoginDialogController;

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
		
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"Spring-Module.xml");
 
		AnnotationConfigApplicationContext context
        = new AnnotationConfigApplicationContext(SampleAppFactory.class);

		LoginDialogController sampleController = context.getBean(LoginDialogController.class);
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		
		primaryStage.setTitle("Bienvenue");
		Scene scene = new Scene((Parent) sampleController.getView(), 250, 175);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);

		primaryStage.show();

	}

}
