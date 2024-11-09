package hms.entity.record;

public class ConsultationNotes {
	private String diagnosis;
	private String notes;

	public ConsultationNotes(String diagnosis, String notes) {
		this.diagnosis = diagnosis;
		this.notes = notes;
	}

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
