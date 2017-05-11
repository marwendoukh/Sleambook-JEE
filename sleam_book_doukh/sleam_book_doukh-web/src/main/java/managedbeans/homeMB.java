package managedbeans;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entities.Publication;
import entities.User;
import services.userManagement.UserManagementLocal;

@ManagedBean
@SessionScoped
public class homeMB {
	
	
	private String publicationContent;
	private List<Publication> publications=new ArrayList<>();
	private String myName;

	@EJB
	private UserManagementLocal userManagement;
	
	
	
	
	public String addPublication()
	{
		User u = userManagement.findUserById(1);
		System.out.println(u.getName());
		Publication p = new Publication(publicationContent);
		userManagement.createPublicationByUser(u, p);
		return "/home?faces-redirect=true";

	}
	
	
	@PostConstruct
	public void fetchPublications() throws IOException
	{
		if(ConnectedUser.getUser()==null)
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
		User u = userManagement.findUserById(ConnectedUser.getUser().getId());
		publications.addAll(userManagement.findPublicationsByUser(u));
		publications.addAll(userManagement.findPublicationsByFriends(u));
		myName=u.getName();
	}
	
	public String logOut()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	            .getExternalContext().getSession(false);
	    session.invalidate();
	
		//ConnectedUser.logOut();
		return "/login?faces-redirect=true";

	}
	
	
	public void shareThisPublication() {
		// TODO Auto-generated method stub
		
			userManagement.sharePublication(ConnectedUser.getUser(), publications.get(2));
			
	}
	

	public String getPublicationContent() {
		return publicationContent;
	}


	public void setPublicationContent(String publicationContent) {
		this.publicationContent = publicationContent;
	}


	public List<Publication> getPublications() {
		return publications;
	}


	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}


	public String getMyName() {
		return myName;
	}


	public void setMyName(String myName) {
		this.myName = myName;
	}
	
	
	
	
	
}
