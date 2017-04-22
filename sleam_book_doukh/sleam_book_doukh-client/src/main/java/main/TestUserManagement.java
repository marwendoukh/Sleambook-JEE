package main;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.User;
import services.LoginServicesRemote;
import services.userManagement.UserManagementLocal;
import services.userManagement.UserManagementRemote;

public class TestUserManagement {

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			String jndiName = "sleam_book_doukh-ear/sleam_book_doukh-ejb/UserManagement!services.userManagement.UserManagementRemote";
			UserManagementRemote proxy = (UserManagementRemote) context.lookup(jndiName);

			User user = new User();
			user.setName("ahla");
			user.setPassword("slt");
			
			System.out.println("adding user ");
			proxy.addUser(user);

			System.out.println("logged in ?"+proxy.login("ahla", "slt"));

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
