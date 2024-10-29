package hms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import hms.model.Patient;
import hms.serializer.PatientSerializer;

// TODO: change to singleton once serializer is implemented
public class PatientController {
	private Map<String, Patient> patientTable;

	public PatientController(String filepath) {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(filepath);
		try {

			this.patientTable = PatientSerializer.getPatientTable(in);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Map<String, Patient> getPatientTable() {
		return patientTable;
	}

}
