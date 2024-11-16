package hms.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import hms.entity.user.Pharmacist;
import hms.serializer.PharmacistSerializer;

public class PharmacistRepository implements IUserRepository<Pharmacist> {
	private final Map<String, Pharmacist> pharmacistMap;
	private static final String FILEPATH = "./src/main/resources/Staff_List.csv";

	public PharmacistRepository() {
		PharmacistSerializer pharmacistSerializer = new PharmacistSerializer(FILEPATH);
		this.pharmacistMap = pharmacistSerializer.getMap();
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

	@Override
	public Map<String, Pharmacist> getMap() {
		return pharmacistMap;
	}

	public void addPharmacist(String id, Pharmacist pharmacist) {
		pharmacistMap.put(id, pharmacist);
	}

	@Override
	public void deserialize() {
		File file = new File(FILEPATH);
		try {
			PrintWriter printWriter = new PrintWriter(file);
			for (Pharmacist pharmacist : getAll()) {
				String data = String.join(",", pharmacist.getId(), pharmacist.getName(), "Pharmacist",
						pharmacist.getGender().toString(), String.valueOf(pharmacist.getAge()));
				printWriter.println(data);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
