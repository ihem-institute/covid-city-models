package covid.models;

public class TransmissionDynamics {
	// size of population
	private long populationSize;

	// number of initial infections
	private long initialInfections;

	// Measure of contagiousness: the number of secondary infections each infected
	// individual produces. R0
	private float basicReproductionNumber;

	// Length of incubation period, Tinc (days)
	private float incubationPeriod;

	// Duration patient is infectious, Tinf (days)
	private float infectionDuration;

	public long getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(long populationSize) {
		this.populationSize = populationSize;
	}

	public long getInitialInfections() {
		return initialInfections;
	}

	public void setInitialInfections(long initialInfections) {
		this.initialInfections = initialInfections;
	}

	public float getBasicReproductionNumber() {
		return basicReproductionNumber;
	}

	public void setBasicReproductionNumber(float basicReproductionNumber) {
		this.basicReproductionNumber = basicReproductionNumber;
	}

	public float getIncubationPeriod() {
		return incubationPeriod;
	}

	public void setIncubationPeriod(float incubationPeriod) {
		this.incubationPeriod = incubationPeriod;
	}

	public float getInfectionDuration() {
		return infectionDuration;
	}

	public void setInfectionDuration(float infectionDuration) {
		this.infectionDuration = infectionDuration;
	}
}
