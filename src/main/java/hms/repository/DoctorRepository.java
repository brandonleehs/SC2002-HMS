package hms.repository;

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
}
