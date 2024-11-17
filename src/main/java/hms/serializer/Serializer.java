package hms.serializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

abstract class Serializer {
	protected BufferedReader br;

	public Serializer(String filepath) {
		try {
			this.br = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e) {
			if (!(filepath.contains("Appointment_List") || filepath.contains("AppointmentOutcomeRecord_List"))) {
				e.printStackTrace();
			}
		}
	}
}
