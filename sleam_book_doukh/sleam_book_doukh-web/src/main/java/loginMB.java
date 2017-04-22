import java.io.Console;
import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.User;
import services.userManagement.UserManagementLocal;


@RequestScoped
@ManagedBean
public class loginMB {
	
	@EJB
	private UserManagementLocal userManagement;
	
	private User user = new User();

	private String username="";
	private String password="";
	
	
	private String msg="";
	
	
	@PostConstruct
	public void init(){
		//
		
		System.out.print("hello from jsp");
		
}
	
	
	
public Boolean doLogin() {
 		
	msg="ok g!";
	
	/*try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("http://www.myUrl.com");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	System.out.println("doing login");

	//	userManagement.login(username, password);*/
			
		return true;
}
	

public String doSignUp(){
	

	user.setUsername("user");
	user.setPassword("pw");
	user.setName("ssd");
	Console console = System.console();
	 console.printf("sign up ...");
	  System.out.println("sign up ...");
	System.out.println(user.getPassword());
	System.out.println(user.getUsername());

	userManagement.addUser(user);
	return "";
		
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

	

	
}
