package services;

import javax.ejb.Remote;

@Remote
public interface LoginServicesRemote {
	String login(String username,String password);

}
