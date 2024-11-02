package hms.repository;

import java.util.List;
import java.util.Map;

import hms.entity.user.Doctor;
import hms.serializer.DoctorSerializer;

public class DoctorRepository implements IUserRepository<Doctor> {
	private final Map<String, Doctor> doctorMap;

	public DoctorRepository() {
		DoctorSerializer doctorSerializer = new DoctorSerializer();
		this.doctorMap = doctorSerializer.getMap("Staff_List.xlsx");
	}

	@Override
	public Doctor getById(String id) {
		return this.doctorMap.get(id);
	}

	@Override
	public void removeById(String id) {
		this.doctorMap.remove(id);
	}

	@Override
	public List<Doctor> getAll() {
		return this.doctorMap.values().stream().toList();
	}
}
