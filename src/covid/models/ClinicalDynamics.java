package covid.models;

public class ClinicalDynamics {
	// Case fatality rate (percentage)
	private float fatalityRate;

	// Time from end of incubation to death (days)
	private int timeToDeath;
	
	// Length of hospital stay (days)
	private float lengthHospitalStay;

	// Recovery time for mild cases (days)
	private float mildCaseRecoveryTime;

	// Hospitalization rate (percentage
	private float hospitalizationRate;

	// Time to hospitalization (days)
	private float timeToHospitalization;

	public float getFatalityRate() {
		return fatalityRate;
	}

	public void setFatalityRate(float fatalityRate) {
		this.fatalityRate = fatalityRate;
	}

	public int getTimeToDeath() {
		return timeToDeath;
	}

	public void setTimeToDeath(int timeToDeath) {
		this.timeToDeath = timeToDeath;
	}

	public float getLengthHospitalStay() {
		return lengthHospitalStay;
	}

	public void setLengthHospitalStay(float lengthHospitalStay) {
		this.lengthHospitalStay = lengthHospitalStay;
	}

	public float getMildCaseRecoveryTime() {
		return mildCaseRecoveryTime;
	}

	public void setMildCaseRecoveryTime(float mildCaseRecoveryTime) {
		this.mildCaseRecoveryTime = mildCaseRecoveryTime;
	}

	public float getHospitalizationRate() {
		return hospitalizationRate;
	}

	public void setHospitalizationRate(float hospitalizationRate) {
		this.hospitalizationRate = hospitalizationRate;
	}

	public float getTimeToHospitalization() {
		return timeToHospitalization;
	}

	public void setTimeToHospitalization(float timeToHospitalization) {
		this.timeToHospitalization = timeToHospitalization;
	}
}
