package edu.application.login.services;

import edu.application.login.services.model.LoginPassword;

public interface LoginService {
	
	public boolean validateLogin(LoginPassword login);

}
