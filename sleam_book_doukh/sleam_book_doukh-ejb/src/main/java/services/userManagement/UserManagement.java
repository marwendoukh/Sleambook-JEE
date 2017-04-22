package services.userManagement;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public void createPublicationByUser(Integer idUser, Publication publication) {
		User owner = findUserById(idUser);

		publication.setOwner(owner);

		entityManager.merge(publication);

	}

	@Override
	public void assignPublicationToUser(Publication publication, Integer idUser) {
		User user = findUserById(idUser);

		List<Publication> publicationsOld = user.getPublications();
		publicationsOld.add(publication);

		user.linkPublicationsToThisUser(publicationsOld);

		updateUser(user);

	}

	@Override
	public List<Publication> findPublicationsByUser(Integer idUser) {
		return entityManager.createQuery("SELECT p FROM Publication p WHERE p.owner.id=:param", Publication.class)
				.setParameter("param", idUser).getResultList();
	}

	@Override
	public List<Publication> findPublicationsByFriends(Integer idUser) {
		User user = findUserById(idUser);

		List<User> friends = user.getFriends();

		List<Publication> publications = new ArrayList<>();
		for (User f : friends) {
			List<Publication> publicationsByFriend = findPublicationsByUser(f.getId());
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
		//user.getPublicationsShared().add(publication);
		publication.getUsersThatSharedThis().add(user);
		entityManager.merge(publication);


	}

	@Override
	public User login(String username, String password) {
		return entityManager.createQuery("SELECT u FROM User WHERE u.username=:u AND u.password=:p", User.class)
				.setParameter("u", username).setParameter("p", password).getSingleResult();
	}

}
