package hms.entity.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hms.entity.appointment.Appointment;
import hms.entity.user.attributes.Gender;
import hms.repository.DoctorRepository;
import hms.repository.IUserRepository;


/**
 * Represents an Administrator, a user with administrative privileges.
 * The Administrator has the ability to remove staff and retrieve appointments for a specific date.
 */
public class Administrator extends User {
	private final int age;

	/**
     * Constructs an Administrator instance with the provided details.
     *
     * @param id the unique identifier of the administrator.
     * @param password the password associated with the administrator's account.
     * @param name the name of the administrator.
     * @param gender the gender of the administrator.
     * @param age the age of the administrator.
     */
	public Administrator(String id, String password, String name, Gender gender, int age) {
		super(id, password, name, gender);
		this.age = age;
	}

	/**
     * Retrieves the age of the administrator.
     *
     * @return the age of the administrator.
     */
	public int getAge() {
		return this.age;
	}

	/**
     * Removes a user (staff member) from the user repository by their ID.
     *
     * @param user the user (staff member) to be removed.
     * @param userRepository the repository where users are stored.
     */
	public void removeStaff(User user, IUserRepository<User> userRepository) {
		userRepository.removeById(getId());
	}

	/**
     * Retrieves all appointments for a specific date across all doctors.
     *
     * @param date the date for which appointments are to be retrieved.
     * @param doctorRepository the repository containing all doctors.
     * @return a list of appointments for the specified date.
     */
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
}
