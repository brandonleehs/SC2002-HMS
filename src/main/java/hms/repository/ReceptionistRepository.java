package hms.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import hms.entity.user.Receptionist;
import hms.serializer.ReceptionistSerializer;

/**
 * The ReceptionistRepository class manages the collection of receptionist records. It provides methods
 * for accessing, modifying, and serializing receptionist data. The data is stored in a map with the
 * receptionist's ID as the key and the Receptionist object as the value.
 */
public class ReceptionistRepository implements IUserRepository<Receptionist> {
    private final Map<String, Receptionist> receptionistMap;
	private static final String FILEPATH = "./src/main/resources/Staff_List.csv";

	/**
     * Constructor that initializes the ReceptionistRepository by deserializing receptionist data from a CSV file.
     * 
     * @param receptionistSerializer the serializer to deserialize the receptionist data
     */
	public ReceptionistRepository() {
		ReceptionistSerializer receptionistSerializer = new ReceptionistSerializer(FILEPATH);
		this.receptionistMap = receptionistSerializer.getMap();
	}

	/**
     * Retrieves a receptionist by their ID.
     * 
     * @param id the ID of the receptionist to retrieve
     * @return the receptionist associated with the specified ID, or {@code null} if no receptionist exists with that ID
     */
	@Override
	public Receptionist getById(String id) {
		return this.receptionistMap.get(id);
	}

	/**
     * Removes a receptionist from the repository by their ID.
     * 
     * @param id the ID of the receptionist to remove
     */
	@Override
	public void removeById(String id) {
		this.receptionistMap.remove(id);
	}

	/**
     * Retrieves all receptionists in the repository.
     * 
     * @return a list of all receptionists
     */
	@Override
	public List<Receptionist> getAll() {
		return this.receptionistMap.values().stream().toList();
	}

	/**
     * Retrieves the map containing all receptionists.
     * 
     * @return a map of receptionist IDs to receptionist objects
     */
	@Override
	public Map<String, Receptionist> getMap() {
		return receptionistMap;
	}

	/**
     * Adds a new receptionist to the repository.
     * 
     * @param id           the ID of the receptionist to add
     * @param receptionist the Receptionist object to add
     */
	public void addReceptionist(String id, Receptionist receptionist) {
		receptionistMap.put(id, receptionist);
	}

	/**
     * Serializes the receptionist data and appends it to a CSV file. 
     * The data includes receptionist ID, name, role, gender, age, and password hash.
     * 
     * @throws IOException if an I/O error occurs while writing to the file
     */
	@Override
	public void deserialize() {
		try {
			FileWriter fw = new FileWriter(FILEPATH, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter printWriter = new PrintWriter(bw);
			for (Receptionist receptionist : getAll()) {
				String data = String.join(",", receptionist.getId(), receptionist.getName(), "Receptionist",
						receptionist.getGender().toString(), String.valueOf(receptionist.getAge()),
						receptionist.getPasswordHash());
				printWriter.println(data);
			}
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
