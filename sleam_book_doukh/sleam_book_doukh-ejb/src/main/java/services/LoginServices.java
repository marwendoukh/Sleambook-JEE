package services;

import javax.ejb.Stateless;

@Stateless
public class LoginServices implements LoginServicesLocal, LoginServicesRemote{

	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		
		
		return "Welcome to Mozilla !";
	}

	
}
