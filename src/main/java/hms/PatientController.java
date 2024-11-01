package hms;

import java.util.Map;

import hms.model.Patient;
import hms.serializer.PatientSerializer;

public class PatientController {
	private static final PatientController patientController = new PatientController();
	private Map<String, Patient> patientMap;

	private PatientController() {
	}

	public static PatientController getInstance() {
		return patientController;
	}

	public void loadPatientMap(String filepath) {
		try {
			this.patientMap = PatientSerializer.getPatientMap(filepath);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public Map<String, Patient> getPatientMap() {
		return this.patientMap;
	}

}
