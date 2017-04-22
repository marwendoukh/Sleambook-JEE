package services.userManagement;

import java.util.List;

import javax.ejb.Local;

import entities.GroupOfSleamBooker;
import entities.Publication;
import entities.ReviewType;
import entities.User;

@Local
public interface UserManagementLocal {
	void addUser(User user);

	User findUserById(Integer id);

	void updateUser(User user);

	void deleteUserById(Integer id);

	void deleteUser(User user);

	void createPublicationByUser(Integer idUser, Publication publication);

	void assignPublicationToUser(Publication publication, Integer idUser);

	List<Publication> findPublicationsByUser(Integer idUser);

	List<Publication> findPublicationsByFriends(Integer idUser);

	void addFriend(User me, User friend);

	void addGroup(GroupOfSleamBooker group);

	void sendMessage(User sender, User receiver, String messageText);

	void commentPublication(User user, Publication publication, String comment);

	void reviewPublication(User user, Publication publication, ReviewType reviewType);

	void subscribeToGroup(User user, GroupOfSleamBooker groupOfSleamBooker);

	void sharePublication(User user, Publication publication);

	User login(String username, String password);


}
