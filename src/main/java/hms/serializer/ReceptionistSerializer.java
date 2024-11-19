package hms.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hms.entity.user.Receptionist;
import hms.entity.user.attributes.Gender;

public class ReceptionistSerializer extends UserSerializer<Receptionist> {

	public ReceptionistSerializer(String filepath) {
		super(filepath);
	}

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
