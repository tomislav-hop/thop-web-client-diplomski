package implementations;

import requests.LoginRequest;

public class LoginImpl {
	
	public int login(String username, String password){
		LoginRequest loginRequest = new LoginRequest();
		return loginRequest.sendLoginRequest(username, password);
	}

}
