package hms.serializer;

import java.util.Map;

import hms.entity.user.User;

/**
 * The UserSerializer class is an abstract class that extends the Serializer class and is designed to handle 
 * the serialization of User-related entities (such as administrators, doctors, patients, etc.).
 * It defines an abstract method {@link #getMap()}, which must be implemented by subclasses to provide
 * a map of User objects, where the key is a unique identifier (e.g., user ID) and the value is the User object.
 *
 * @param <E> the type of User being serialized, which must be a subclass of {@link User}
 */
public abstract class UserSerializer<E extends User> extends Serializer {

	/**
     * Constructs a UserSerializer object and initializes the BufferedReader to read from the specified file.
     * It passes the filepath to the constructor of the superclass {@link Serializer} to initialize file reading.
     *
     * @param filepath the path to the file that contains user data
     */
	protected UserSerializer(String filepath) {
		super(filepath);
	}

	/**
     * Abstract method that should be implemented by subclasses to deserialize user data into a map of User objects.
     * The map should associate a unique user identifier (e.g., ID) with the corresponding User object.
     *
     * @return a map where the key is the user identifier (e.g., user ID), and the value is the User object
     */
	public abstract Map<String, E> getMap();
}
