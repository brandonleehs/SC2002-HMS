package hms.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hms.entity.user.Administrator;
import hms.entity.user.attributes.Gender;

public class AdministratorSerializer extends UserSerializer<Administrator> {

	public AdministratorSerializer(String filepath) {
		super(filepath);
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return administratorMap;
	}

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
