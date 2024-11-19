package hms.entity.record;

/**
 * Represents the consultation notes for a medical appointment, including
 * diagnosis details and additional notes.
 */
public class ConsultationNotes {
	private String diagnosis;
	private String notes;

	/**
     * Constructs a new {@code ConsultationNotes} instance with the specified diagnosis and notes.
     *
     * @param diagnosis the diagnosis provided during the consultation.
     * @param notes additional notes recorded during the consultation.
     */
	public ConsultationNotes(String diagnosis, String notes) {
		this.diagnosis = diagnosis;
		this.notes = notes;
	}

	/**
     * Retrieves the diagnosis provided during the consultation.
     *
     * @return the diagnosis details.
     */
	public String getDiagnosis() {
		return this.diagnosis;
	}

	/**
     * Retrieves the additional notes recorded during the consultation.
     *
     * @return the consultation notes.
     */
	public String getNotes() {
		return this.notes;
	}

	/**
     * Updates the diagnosis for the consultation.
     *
     * @param diagnosis the new diagnosis details.
     */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
     * Updates the notes for the consultation.
     *
     * @param notes the new consultation notes.
     */
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
