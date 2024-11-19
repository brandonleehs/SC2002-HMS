package hms.serializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * The Serializer class is an abstract class that provides functionality for reading data from a file.
 * It initializes a BufferedReader to read from the provided file path. Subclasses of Serializer can
 * implement specific deserialization logic using the BufferedReader.
 */
abstract class Serializer {
	protected BufferedReader br;

	/**
     * Constructs a Serializer object and initializes the BufferedReader to read from the specified file.
     * If the file is not found, a FileNotFoundException is caught. If the file contains either "Appointment_List"
     * or "AppointmentOutcomeRecord_List" in its name, the exception is ignored; otherwise, the stack trace is printed.
     *
     * @param filepath the path to the file to be read
     */
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
