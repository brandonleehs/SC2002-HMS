package hms.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hms.entity.user.Receptionist;
import hms.entity.user.attributes.Gender;

/**
 * The ReceptionistSerializer class is responsible for deserializing receptionist data from a CSV file
 * and creating Receptionist objects. It processes the CSV data and stores the deserialized Receptionist
 * objects in a map for further use.
 */
public class ReceptionistSerializer extends UserSerializer<Receptionist> {

	/**
     * Constructs a ReceptionistSerializer to read data from the specified file.
     * This constructor initializes the serializer with the provided file path.
     *
     * @param filepath the path to the CSV file containing receptionist data
     */
	public ReceptionistSerializer(String filepath) {
		super(filepath);
	}

	/**
     * Reads the CSV file and processes each line, creating a Receptionist object from the data in each row.
     * Each row is passed to the getReceptionistFromRow method for processing. The method returns a map
     * of receptionist IDs to Receptionist objects.
     *
     * @return a map of receptionist IDs to Receptionist objects
     */
	@Override
	public Map<String, Receptionist> getMap() {
		Map<String, Receptionist> receptionistMap = new HashMap<String, Receptionist>();

		try {
			this.br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				String role = row[2];
				if (role.equals("Receptionist")) {
					Receptionist receptionist = getReceptionistFromRow(row);
					receptionistMap.put(receptionist.getId(), receptionist);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return receptionistMap;
	}

	/**
     * Creates a Receptionist object from the data in a CSV row. The method parses the receptionist's personal
     * details, including ID, name, gender, age, and password hash, and returns a fully initialized
     * Receptionist object.
     *
     * @param row an array of strings representing a single row from the CSV file
     * @return a Receptionist object created from the row data
     */
	private Receptionist getReceptionistFromRow(String[] row) {
		String id = row[0];
		String name = row[1];
		Gender gender = row[3].equals("Male") ? Gender.MALE : Gender.FEMALE;
		int age = Integer.parseInt(row[4]);
		if (row.length < 6) {
			return new Receptionist(id, "password", name, gender, age);
		}
		String passwordHash = row[5];
		Receptionist receptionist = new Receptionist(id, "password", name, gender, age);
		receptionist.setPasswordHash(passwordHash);
		return receptionist;
	}
}
