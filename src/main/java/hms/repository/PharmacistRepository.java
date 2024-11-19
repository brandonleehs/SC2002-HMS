package hms.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import hms.entity.user.Pharmacist;
import hms.serializer.PharmacistSerializer;

/**
 * The PharmacistRepository class manages the collection of pharmacist records. It provides methods
 * for accessing, modifying, and serializing pharmacist data. The data is stored in a map with the
 * pharmacist's ID as the key and the Pharmacist object as the value.
 */
public class PharmacistRepository implements IUserRepository<Pharmacist> {
	private final Map<String, Pharmacist> pharmacistMap;
	private static final String FILEPATH = "./src/main/resources/Staff_List.csv";

	/**
     * Constructor that initializes the PharmacistRepository by deserializing pharmacist data from a CSV file.
     * 
     * @param pharmacistSerializer the serializer to deserialize the pharmacist data
     */
	public PharmacistRepository() {
		PharmacistSerializer pharmacistSerializer = new PharmacistSerializer(FILEPATH);
		this.pharmacistMap = pharmacistSerializer.getMap();
	}

	/**
     * Retrieves a pharmacist by their ID.
     * 
     * @param id the ID of the pharmacist to retrieve
     * @return the pharmacist associated with the specified ID, or {@code null} if no pharmacist exists with that ID
     */
	@Override
	public Pharmacist getById(String id) {
		return this.pharmacistMap.get(id);
	}

	/**
     * Removes a pharmacist from the repository by their ID.
     * 
     * @param id the ID of the pharmacist to remove
     */
	@Override
	public void removeById(String id) {
		this.pharmacistMap.remove(id);
	}

	/**
     * Retrieves all pharmacists in the repository.
     * 
     * @return a list of all pharmacists
     */
	@Override
	public List<Pharmacist> getAll() {
		return this.pharmacistMap.values().stream().toList();
	}

	/**
     * Retrieves the map containing all pharmacists.
     * 
     * @return a map of pharmacist IDs to pharmacist objects
     */
	@Override
	public Map<String, Pharmacist> getMap() {
		return pharmacistMap;
	}

	/**
     * Adds a new pharmacist to the repository.
     * 
     * @param id the ID of the pharmacist to add
     * @param pharmacist the Pharmacist object to add
     */
	public void addPharmacist(String id, Pharmacist pharmacist) {
		pharmacistMap.put(id, pharmacist);
	}

	/**
     * Serializes the pharmacist data and appends it to a CSV file. 
     * The data includes pharmacist ID, name, role, gender, age, and password hash.
     * 
     * @throws IOException if an I/O error occurs while writing to the file
     */
	@Override
	public void deserialize() {
		try {
			FileWriter fw = new FileWriter(FILEPATH, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter printWriter = new PrintWriter(bw);
			for (Pharmacist pharmacist : getAll()) {
				String data = String.join(",", pharmacist.getId(), pharmacist.getName(), "Pharmacist",
						pharmacist.getGender().toString(), String.valueOf(pharmacist.getAge()),
						pharmacist.getPasswordHash());
				printWriter.println(data);
			}
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
