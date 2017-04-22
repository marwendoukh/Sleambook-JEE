package services;

import javax.ejb.Local;

@Local
public interface LoginServicesLocal {
	String login(String username,String password);
}
