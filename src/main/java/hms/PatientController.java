package hms;

import java.util.Map;

import hms.entity.user.Patient;
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
			PatientSerializer patientSerializer = new PatientSerializer();
			this.patientMap = patientSerializer.getMap(filepath);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public Map<String, Patient> getPatientMap() {
		return this.patientMap;
	}

}
