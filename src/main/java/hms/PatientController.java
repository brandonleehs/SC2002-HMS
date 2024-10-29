package hms;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import hms.model.Patient;
import hms.serializer.PatientSerializer;

// TODO: change to singleton once serializer is implemented
public class PatientController {
	private Map<String, Patient> patientTable;

	public PatientController(String filepath) {
		URL url = this.getClass().getResource(filepath);
		try {
			this.patientTable = PatientSerializer.getPatientTable(new File(URI.create(url.toString())));
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public Map<String, Patient> getPatientTable() {
		return patientTable;
	}

}
