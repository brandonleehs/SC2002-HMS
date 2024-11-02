package hms.repository;

import java.util.Map;

import hms.entity.user.Administrator;
import hms.serializer.AdministratorSerializer;

public class AdministratorRepository implements IUserRepository<Administrator> {
	private final Map<String, Administrator> administratorMap;

	public AdministratorRepository() {
		AdministratorSerializer administratorSerializer = new AdministratorSerializer();
		this.administratorMap = administratorSerializer.getMap("Administrator_List.xlsx");
	}

	@Override
	public Administrator getById(String id) {
		return this.administratorMap.get(id);
	}
}
