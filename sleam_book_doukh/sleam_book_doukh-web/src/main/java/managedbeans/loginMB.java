package managedbeans;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.User;
import services.userManagement.UserManagementLocal;

@ViewScoped
@ManagedBean
public class loginMB {
	
	@EJB
	private UserManagementLocal userManagement;
	
	private User user = new User();

	private String username="";
	private String password="";
	private String name="";
	private String msg="";
	
	
	
public String doLogin() {
	
		
	User userLoggedIn;
	
		 userLoggedIn = userManagement.login(username, password);
	
		 if(userLoggedIn==null)
		msg="username or password incorrect";
		 else
			 ConnectedUser.setU(userLoggedIn);
		 

			
		return "/home?faces-redirect=true";
}
	

public void doSignUp(){
	

	user.setUsername(username);
	user.setPassword(password);
	user.setName(name);
	
	  System.out.println("sign up ...");
	System.out.println(user.getPassword());
	System.out.println(user.getUsername());

	userManagement.addUser(user);
		
}


public String printBonjou()
{
	return msg;
	
}


public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}




public String getMsg() {
	return msg;
}




public void setMsg(String msg) {
	this.msg = msg;
}




public User getUser() {
	return user;
}




public void setUser(User user) {
	this.user = user;
}



public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}

	

	
}
