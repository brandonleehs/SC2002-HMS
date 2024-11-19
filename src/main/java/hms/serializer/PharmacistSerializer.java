package hms.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hms.entity.user.Pharmacist;
import hms.entity.user.attributes.Gender;

/**
 * The PharmacistSerializer class is responsible for deserializing pharmacist data from a CSV file
 * and creating Pharmacist objects. It processes the CSV data and stores the deserialized Pharmacist
 * objects in a map for further use.
 */
public class PharmacistSerializer extends UserSerializer<Pharmacist> {

	/**
     * Constructs a PharmacistSerializer to read data from the specified file.
     * This constructor initializes the serializer with the provided file path.
     *
     * @param filepath the path to the CSV file containing pharmacist data
     */
	public PharmacistSerializer(String filepath) {
		super(filepath);
	}

	/**
     * Reads the CSV file and processes each line, creating a Pharmacist object from the data in each row.
     * Each row is passed to the getPharmacistFromRow method for processing. The method returns a map
     * of pharmacist IDs to Pharmacist objects.
     *
     * @return a map of pharmacist IDs to Pharmacist objects
     */
	@Override
	public Map<String, Pharmacist> getMap() {
		Map<String, Pharmacist> pharmacistMap = new HashMap<String, Pharmacist>();

		try {
			this.br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				String role = row[2];
				if (role.equals("Pharmacist")) {
					Pharmacist pharmacist = getPharmacistFromRow(row);
					pharmacistMap.put(pharmacist.getId(), pharmacist);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pharmacistMap;
	}

	/**
     * Creates a Pharmacist object from the data in a CSV row. The method parses the pharmacist's personal
     * details, including ID, name, gender, age, and password hash, and returns a fully initialized
     * Pharmacist object.
     *
     * @param row an array of strings representing a single row from the CSV file
     * @return a Pharmacist object created from the row data
     */
	private Pharmacist getPharmacistFromRow(String[] row) {
		String id = row[0];
		String name = row[1];
		Gender gender = row[3].equals("Male") ? Gender.MALE : Gender.FEMALE;
		int age = Integer.parseInt(row[4]);
		if (row.length < 6) {
			return new Pharmacist(id, "password", name, gender, age);
		}
		String passwordHash = row[5];
		Pharmacist pharmacist = new Pharmacist(id, "password", name, gender, age);
		pharmacist.setPasswordHash(passwordHash);
		return pharmacist;
	}
}
