package managedbeans;

import entities.User;

public class ConnectedUser {
	
	static User user ;

	public static User getUser() {
		return user;
	}

	public static void setUser(User u) {
		ConnectedUser.user = u;
	}
	
	public static void logOut() {
		ConnectedUser.user = null;
	}
	
	

	
	
}
