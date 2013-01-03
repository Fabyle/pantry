package edu.application.login.paneAndController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.application.login.modelWrapper.LoginPasswordModelWrapper;
import edu.application.login.services.LoginService;


/**
 * Cette classe joue un double role
 * c'est le parent de la scene, elle implémente initializable ce qui permet de définir le bindings avec le modele
 * c'est aussi le controller déclarée dans le fichier fxml.
 * @author fabien
 *
 */

public class LoginDialogController  implements Initializable {

	

	// logger
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginDialogController.class);
	
	// model 
	private LoginPasswordModelWrapper modelWrapper = new LoginPasswordModelWrapper();
	
	// services
	@Autowired
	public LoginService loginService;

	// widgets 
	@FXML
	private TextField login;
	@FXML
	private PasswordField password;
	@FXML
	private Label nonValide;
	@FXML
	private Button ok;
	
	@FXML 
	private Node view;
	
	

	public Node getView() {
		return view;
	}

	/* Permet d'initialiser le bingind bidirectionnel
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LOGGER.info("Initialisation du binding avec le modele");
		Bindings.bindBidirectional(login.textProperty(),
				modelWrapper.getLoginProperty());
		Bindings.bindBidirectional(password.textProperty(),
				modelWrapper.getPasswordProperty());
				
		final InputMaskChecker maskLogin = new InputMaskChecker(InputMaskChecker.TEXTONLY, login,null);
		final InputMaskChecker maskPassword = new InputMaskChecker(InputMaskChecker.TEXTONLY, password,null);
		
		login.textProperty().addListener(maskLogin);
		password.textProperty().addListener(maskPassword);
		
		ok.disableProperty().bind(
				maskLogin.valid.and(maskPassword.valid).not());
		

	}
	
	public LoginService getLoginService() {
		return loginService;
	}

	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	

	/**
	 * Réaction du controler sur le bouton OK
	 * @param event
	 */
	@FXML
	protected void handleSubmitButtonOk(ActionEvent event) {
		LOGGER.info("Action sur le bouton OK pour le login : "+modelWrapper.getLoginProperty().getValue());
				
		boolean isOk = loginService.validateLogin(modelWrapper);
		nonValide.setVisible(!isOk);
			
		if (isOk){
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		}
		

	}

	/**
	 * Réaction du controler sur le bouton Annuler
	 * @param event
	 */
	@FXML
	protected void handleSubmitButtonAnnuler(ActionEvent event) {
		LOGGER.info("Action sur le bouton Annuler");
		modelWrapper.setLoginProperty(null);
		modelWrapper.setPasswordProperty(null);
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}
