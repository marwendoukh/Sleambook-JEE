package services.userManagement;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Comment;
import entities.GroupOfSleamBooker;
import entities.Message;
import entities.Publication;
import entities.Review;
import entities.ReviewType;
import entities.User;

/**
 * Session Bean implementation class UserManagement
 */
@Stateless
public class UserManagement implements UserManagementRemote, UserManagementLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserManagement() {
	}

	@Override
	public void addUser(User user) {
		System.out.println("adding user ...");
		entityManager.persist(user);
		System.out.println("user added");

	}

	@Override
	public User findUserById(Integer id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Override
	public void deleteUserById(Integer id) {
		entityManager.remove(findUserById(id));

	}

	@Override
	public void deleteUser(User user) {
		entityManager.remove(entityManager.merge(user));

	}

	@Override
	public void createPublicationByUser(User user, Publication publication) {

		publication.setOwner(user);

		entityManager.merge(publication);

	}

	@Override
	public void assignPublicationToUser(Publication publication, User user) {

		List<Publication> publicationsOld = user.getPublications();
		publicationsOld.add(publication);

		user.linkPublicationsToThisUser(publicationsOld);

		updateUser(user);

	}

	@Override
	public List<Publication> findPublicationsByUser(User user) {
		return entityManager.createQuery("FROM Publication p WHERE p.owner=:param", Publication.class)
				.setParameter("param", user).getResultList();
	}

	@Override
	public List<Publication> findPublicationsByFriends(User user) {

		List<User> friends = this.getFriendsByUser(user);

		List<Publication> publications = new ArrayList<>();
		for (User f : friends) {
			List<Publication> publicationsByFriend = findPublicationsByUser(f);
			for (Publication p : publicationsByFriend) {
				publications.add(p);
			}
		}
		return publications;
	}

	@Override
	public void addFriend(User me, User friend) {
		me.getFriends().add(friend);
		entityManager.merge(me);
	}

	@Override
	public void addGroup(GroupOfSleamBooker group) {
		entityManager.persist(group);
	}

	@Override
	public void sendMessage(User sender, User receiver, String messageText) {
		Message message = new Message(messageText, sender, receiver);
		entityManager.persist(message);
	}

	@Override
	public void commentPublication(User user, Publication publication, String comment) {
		Comment commenT = new Comment(comment, user, publication);
		entityManager.persist(commenT);

	}

	@Override
	public void reviewPublication(User user, Publication publication, ReviewType reviewType) {
		Review review = new Review(reviewType, user, publication);
		entityManager.persist(review);

	}

	@Override
	public void subscribeToGroup(User user, GroupOfSleamBooker groupOfSleamBooker) {
		user.getGroupsSubscribedIn().add(groupOfSleamBooker);
		entityManager.merge(user);
	}

	@Override
	public void sharePublication(User user, Publication publication) {
		// user.getPublicationsShared().add(publication);
		List<User> usersSharedThis = fetchUsersThatSharedThis(publication);
		usersSharedThis.add(user);
		publication.setUsersThatSharedThis(usersSharedThis);
		entityManager.merge(publication);

	}

	@Override
	public User login(String username, String password) {

		try {

			return entityManager.createQuery("FROM User WHERE username=:u AND password=:p", User.class)
					.setParameter("u", username).setParameter("p", password).getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> getFriendsByUser(User u) {

		return entityManager.createQuery("SELECT u FROM User u INNER JOIN u.friends ufs WHERE ufs=:p", User.class).setParameter("p", u).getResultList();

			
	
	}

	@Override
	public List<User> fetchUsersThatSharedThis(Publication p) {
		try{
		p= entityManager.createQuery("SELECT  p FROM Publication p JOIN FETCH p.usersThatSharedThis WHERE p = :param", Publication.class).setParameter("param", p).getSingleResult();
		}
		catch (Exception e) {
			return new ArrayList<User>();
		}
		return p.getUsersThatSharedThis();

	}

}
