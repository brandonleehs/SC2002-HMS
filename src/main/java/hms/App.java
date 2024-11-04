package hms;

import hms.boundary.LoginView;
import hms.boundary.ScheduleView;
import hms.boundary.View;

public class App {

	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.show();
		View.close();

//		PatientRepository patientRepository = new PatientRepository();
//		DoctorRepository doctorRepository = new DoctorRepository();
//		PharmacistRepository pharmacistRepository = new PharmacistRepository();
//		AdministratorRepository administratorRepository = new AdministratorRepository();
//
//		Patient user = patientRepository.getById("P1001");
//		Doctor doctor = doctorRepository.getById("D001");
//		Appointment appt = new Appointment(user.getId(), doctor.getId(), LocalDate.of(2024, 11, 5),
//				LocalTime.of(9, 30));
//		user.scheduleAppointment(doctor, appt);
//		doctor.acceptAppointment(appt);

//		doctor.completeAppointment(user, appt, "Consultation", "Hypertension. Lifestyle change recommended.");
//		doctor.prescribeMedicine(new Medicine("Paracetamol"), appt.getAppointmentOutcomeRecord());
//		Medicine medicine = new Medicine("Ibuprofen");
//		medicine.setMedicineStatus(MedicineStatus.DISPENSED);
//		doctor.prescribeMedicine(medicine, appt.getAppointmentOutcomeRecord());
//		MedicalRecordView medicalRecordView = new MedicalRecordView(user);
//		medicalRecordView.show();
		ScheduleView scheduleView = new ScheduleView();
		scheduleView.show();

	}
}
