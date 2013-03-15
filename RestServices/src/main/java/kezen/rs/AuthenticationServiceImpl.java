package kezen.rs;

import javax.jws.WebService;

@WebService(
    endpointInterface = "kezen.rs.AuthenticationService",
    serviceName = "authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

 public Boolean getAccess(String login, String password) {
  if ( login.equals("tarzan") && password.equals("jane")) {
   return Boolean.TRUE;
  }

  return Boolean.FALSE;
 }
}