package hms.repository;

import java.util.List;
import java.util.Map;

import hms.entity.user.Pharmacist;
import hms.serializer.PharmacistSerializer;

public class PharmacistRepository implements IUserRepository<Pharmacist> {
	private final Map<String, Pharmacist> pharmacistMap;

	public PharmacistRepository() {
		PharmacistSerializer pharmacistSerializer = new PharmacistSerializer();
		this.pharmacistMap = pharmacistSerializer.getMap("Pharmacist_List.xlsx");
	}

	@Override
	public Pharmacist getById(String id) {
		return this.pharmacistMap.get(id);
	}

	@Override
	public void removeById(String id) {
		this.pharmacistMap.remove(id);
	}

	@Override
	public List<Pharmacist> getAll() {
		return this.pharmacistMap.values().stream().toList();
	}
}
