package hms.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import hms.entity.user.Administrator;
import hms.serializer.AdministratorSerializer;


/**
 * This class represents a repository for managing {@link Administrator} objects.
 * It implements the {@link IUserRepository} interface to handle CRUD operations for administrators.
 * The repository interacts with a CSV file to store and retrieve administrator data.
 */
public class AdministratorRepository implements IUserRepository<Administrator> {
	private final Map<String, Administrator> administratorMap;
	private static final String FILEPATH = "./src/main/resources/Staff_List.csv";

	/**
     * Constructs a new {@code AdministratorRepository}.
     * Initializes the repository by deserializing administrator data from the CSV file.
     */
	public AdministratorRepository() {
		AdministratorSerializer administratorSerializer = new AdministratorSerializer(FILEPATH);
		this.administratorMap = administratorSerializer.getMap();
	}

	/**
     * Retrieves an {@link Administrator} by its ID.
     *
     * @param id the ID of the administrator to retrieve.
     * @return the {@link Administrator} object associated with the given ID, or {@code null} if not found.
     */
	@Override
	public Administrator getById(String id) {
		return this.administratorMap.get(id);
	}

	/**
     * Removes an {@link Administrator} by its ID.
     *
     * @param id the ID of the administrator to remove.
     */
	@Override
	public void removeById(String id) {
		this.administratorMap.remove(id);
	}

	/**
     * Retrieves a list of all {@link Administrator} objects in the repository.
     *
     * @return a list of all administrators.
     */
	@Override
	public List<Administrator> getAll() {
		return this.administratorMap.values().stream().toList();
	}

	/**
     * Retrieves the map of administrator IDs to {@link Administrator} objects.
     *
     * @return a map where the key is the administrator ID, and the value is the corresponding {@link Administrator} object.
     */
	@Override
	public Map<String, Administrator> getMap() {
		return this.administratorMap;
	}

	/**
     * Serializes the current list of administrators and writes the data to a CSV file.
     * The file format includes administrator ID, name, role, gender, age, and password hash.
     */
	@Override
	public void deserialize() {
		try {
			FileWriter fw = new FileWriter(FILEPATH, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter printWriter = new PrintWriter(bw);
			for (Administrator administrator : getAll()) {
				String data = String.join(",", administrator.getId(), administrator.getName(), "Administrator",
						administrator.getGender().toString(), String.valueOf(administrator.getAge()),
						administrator.getPasswordHash());
				printWriter.println(data);
			}
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
