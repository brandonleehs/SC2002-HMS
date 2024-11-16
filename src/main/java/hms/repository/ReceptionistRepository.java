package hms.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import hms.entity.user.Receptionist;
import hms.serializer.ReceptionistSerializer;

public class ReceptionistRepository implements IUserRepository<Receptionist> {
    private final Map<String, Receptionist> receptionistMap;
	private static final String FILEPATH = "./src/main/resources/Staff_List.csv";

	public ReceptionistRepository() {
		ReceptionistSerializer receptionistSerializer = new ReceptionistSerializer(FILEPATH);
		this.receptionistMap = receptionistSerializer.getMap();
	}

	@Override
	public Receptionist getById(String id) {
		return this.receptionistMap.get(id);
	}

	@Override
	public void removeById(String id) {
		this.receptionistMap.remove(id);
	}

	@Override
	public List<Receptionist> getAll() {
		return this.receptionistMap.values().stream().toList();
	}

	@Override
	public Map<String, Receptionist> getMap() {
		return receptionistMap;
	}

	public void addReceptionist(String id, Receptionist receptionist) {
		receptionistMap.put(id, receptionist);
	}

	@Override
	public void deserialize() {
//		File file = new File(FILEPATH);
		try {
			FileWriter fw = new FileWriter(FILEPATH, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter printWriter = new PrintWriter(bw);
			for (Receptionist receptionist : getAll()) {
				String data = String.join(",", receptionist.getId(), receptionist.getName(), "Pharmacist",
						receptionist.getGender().toString(), String.valueOf(receptionist.getAge()));
				printWriter.println(data);
			}
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
