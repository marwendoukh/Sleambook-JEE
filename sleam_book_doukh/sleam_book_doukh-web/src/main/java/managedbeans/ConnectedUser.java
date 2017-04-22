package managedbeans;

import entities.User;

public class ConnectedUser {
	
	static User u ;

	public static User getU() {
		return u;
	}

	public static void setU(User u) {
		ConnectedUser.u = u;
	}
	
	

	
	
}
