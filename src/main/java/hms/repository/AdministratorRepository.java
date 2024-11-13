package hms.repository;

import java.util.List;
import java.util.Map;

import hms.entity.user.Administrator;
import hms.serializer.AdministratorSerializer;

public class AdministratorRepository implements IUserRepository<Administrator> {
	private final Map<String, Administrator> administratorMap;

	public AdministratorRepository() {
		AdministratorSerializer administratorSerializer = new AdministratorSerializer();
		this.administratorMap = administratorSerializer.getMap("Staff_List.xlsx");
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
}
