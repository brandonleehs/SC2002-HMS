package hms.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hms.entity.user.Administrator;
import hms.entity.user.attributes.Gender;

/**
 * The AdministratorSerializer class is responsible for deserializing and mapping
 * Administrator data from a CSV file. It extends the {@link UserSerializer} class
 * to handle specific logic for Administrator objects.
 */
public class AdministratorSerializer extends UserSerializer<Administrator> {

	/**
     * Constructs an AdministratorSerializer for the specified file path.
     * 
     * @param filepath the path to the CSV file containing the Administrator data
     */
	public AdministratorSerializer(String filepath) {
		super(filepath);
	}

	/**
     * Retrieves a map of Administrator objects, where the key is the administrator's ID
     * and the value is the corresponding Administrator object. The data is deserialized
     * from the CSV file, and only rows with the "Administrator" role are included.
     * 
     * @return a map of Administrator objects
     */
	@Override
	public Map<String, Administrator> getMap() {
		Map<String, Administrator> administratorMap = new HashMap<String, Administrator>();
		try {
			this.br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				String role = row[2];
				if (role.equals("Administrator")) {
					Administrator administrator = getAdministratorFromRow(row);
					administratorMap.put(administrator.getId(), administrator);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return administratorMap;
	}

	/**
     * Converts a row from the CSV file into an Administrator object.
     * 
     * @param row an array of strings representing a row from the CSV file
     * @return an Administrator object populated with the data from the row
     */
	private Administrator getAdministratorFromRow(String[] row) {
		String id = row[0];
		String name = row[1];
		Gender gender = row[3].equals("Male") ? Gender.MALE : Gender.FEMALE;
		int age = Integer.parseInt(row[4]);
		if (row.length < 6) {
			return new Administrator(id, "password", name, gender, age);
		}
		String passwordHash = row[5];
		Administrator administrator = new Administrator(id, "password", name, gender, age);
		administrator.setPasswordHash(passwordHash);
		return administrator;
	}
}
