package hms.repository;

import java.util.List;
import java.util.Map;

import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.entity.user.User;

/**
 * This interface defines the contract for repositories that manage {@link User} entities.
 * It provides methods for common operations like retrieving, removing, and serializing user data.
 * The generic type parameter {@code E} represents the type of {@link User} managed by the repository (e.g., {@link Doctor}, {@link Patient}).
 * 
 * @param <E> the type of {@link User} that the repository manages
 */
public interface IUserRepository<E extends User> {

	/**
     * Retrieves a user by their unique identifier.
     * 
     * @param id the unique identifier of the user
     * @return the user with the given ID, or {@code null} if no such user exists
     */
	public E getById(String id);

	/**
     * Removes a user by their unique identifier.
     * 
     * @param id the unique identifier of the user to be removed
     */
	public void removeById(String id);

	/**
     * Retrieves all users in the repository.
     * 
     * @return a list of all users in the repository
     */
	public List<E> getAll();

	/**
     * Retrieves the map of users, with user IDs as the keys.
     * 
     * @return a map where the key is the user ID and the value is the corresponding user
     */
	public Map<String, E> getMap();

	/**
     * Deserializes the user data and writes it to a persistent storage (e.g., file or database).
     * This method is used for serializing the user entities, typically into a CSV or database format.
     */
	public void deserialize();
}
