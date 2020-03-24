package covid;

import cern.jet.random.Exponential;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class City {

	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private int population, infected;
	private Exponential infectionPrevision = RandomHelper.createExponential(2);

	public City(ContinuousSpace<Object> space, Grid<Object> grid, int population, int infected) {
		this.space = space;
		this.grid = grid;
		this.setPopulation(population);
		this.setInfected(infected);
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		this.setInfected(this.getInfected() + infectionPrevision.nextInt());
	}

	public int getInfected() {
		return infected;
	}

	public void setInfected(int infected) {
		this.infected = infected;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
}
