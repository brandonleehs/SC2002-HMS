package hms.entity.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hms.entity.appointment.Appointment;
import hms.entity.medicine.ReplenishRequest;
import hms.entity.user.attributes.Gender;
import hms.repository.DoctorRepository;
import hms.repository.IUserRepository;
import hms.repository.MedicineInventory;

public class Administrator extends User {
	private final int age;

	public Administrator(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public void removeStaff(User user, IUserRepository<User> userRepository) {
		userRepository.removeById(getId());
	}

	public List<Appointment> getAllAppointments(LocalDate date, DoctorRepository doctorRepository) {
		List<Appointment> res = new ArrayList<Appointment>();
		for (Doctor doctor : doctorRepository.getAll()) {
			Appointment[] appointmentArr = doctor.getSchedule().getScheduleMap().get(date);
			if (appointmentArr != null) {
				res.addAll(Arrays.asList(appointmentArr));
			}
		}
		return res;
	}

// 	public void addMedicineStock(String medicineName, int stockToAdd) {
// 		medicineInventory.addMedicineStock(medicineName, stockToAdd);
// 	}

// 	public void removeMedicineStock(String medicineName, int stockToRemove) {
// 		medicineInventory.addMedicineStock(medicineName, stockToRemove);
// 	}

// 	public void setMedicineStock(String medicineName, int stock) {
// 		medicineInventory.setMedicineStock(medicineName, stock);
// 	}

// 	public void setLowStockLevelAlertValue(String medicineName, int LowStockLevelAlertValue) {
// 		medicineInventory.setLowStockLevelAlertValue(medicineName, LowStockLevelAlertValue);
// 	}

// 	public void approveRequest(ReplenishRequest replenishRequest) {
// 		medicineInventory.addMedicineStock(replenishRequest.getMedicineName(), replenishRequest.getStockToAdd());
// 		medicineInventory.removeReplenishmentRequest(replenishRequest);
// 	}
}
