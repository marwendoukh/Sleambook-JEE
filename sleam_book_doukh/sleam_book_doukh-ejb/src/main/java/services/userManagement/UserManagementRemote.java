package services.userManagement;

import java.util.List;

import javax.ejb.Remote;

import entities.GroupOfSleamBooker;
import entities.Publication;
import entities.ReviewType;
import entities.User;

@Remote
public interface UserManagementRemote {
	void addUser(User user);

	User findUserById(Integer id);

	void updateUser(User user);

	void deleteUserById(Integer id);

	void deleteUser(User user);

	void createPublicationByUser(User user, Publication publication);

	void assignPublicationToUser(Publication publication, User user);

	List<Publication> findPublicationsByUser(User user);

	List<Publication> findPublicationsByFriends(User user);

	void addFriend(User me, User friend);

	void addGroup(GroupOfSleamBooker group);

	void sendMessage(User sender, User receiver, String messageText);

	void commentPublication(User user, Publication publication, String comment);

	void reviewPublication(User user, Publication publication, ReviewType reviewType);

	void subscribeToGroup(User user, GroupOfSleamBooker groupOfSleamBooker);

	void sharePublication(User user, Publication publication);

	User login(String username, String password);
	
	List<User> getFriendsByUser(User u);


}
