package edu.application.login.services.imp;

import edu.application.login.services.LoginService;
import edu.application.login.services.model.LoginPassword;

public class LoginServiceImp implements LoginService {

	@Override
	public boolean validateLogin(LoginPassword login) {
		
		return login.getLogin().equals(login.getPassword());
	}

}
