package hms.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import hms.entity.user.Administrator;
import hms.serializer.AdministratorSerializer;

public class AdministratorRepository implements IUserRepository<Administrator> {
	private final Map<String, Administrator> administratorMap;
	private static final String FILEPATH = "./src/main/resources/Staff_List.csv";

	public AdministratorRepository() {
		AdministratorSerializer administratorSerializer = new AdministratorSerializer(FILEPATH);
		this.administratorMap = administratorSerializer.getMap();
	}

	@Override
	public Administrator getById(String id) {
		return this.administratorMap.get(id);
	}

	@Override
	public void removeById(String id) {
		this.administratorMap.remove(id);
	}

	@Override
	public List<Administrator> getAll() {
		return this.administratorMap.values().stream().toList();
	}

	@Override
	public Map<String, Administrator> getMap() {
		return this.administratorMap;
	}

	@Override
	public void deserialize() {
		File file = new File(FILEPATH);
		try {
			PrintWriter printWriter = new PrintWriter(file);
			for (Administrator administrator : getAll()) {
				String data = String.join(",", administrator.getId(), administrator.getName(), "Administrator",
						administrator.getGender().toString(), String.valueOf(administrator.getAge()));
				printWriter.println(data);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
