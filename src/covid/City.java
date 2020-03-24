package covid;

import java.util.ArrayList;
import java.util.List;

import cern.jet.random.Exponential;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class City {

	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private int population, infected;
	private String name;
	private Exponential infectionPrevision = RandomHelper.createExponential(2);
	private List<CityLink> links = new ArrayList<CityLink>();

	public City(ContinuousSpace<Object> space, Grid<Object> grid, String name, int population, int infected) {
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CityLink> getLinks() {
		return links;
	}

	public void setLinks(List<CityLink> links) {
		this.links = links;
	}
}
