package covid;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class City {

	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private int population, infected;

	public City(ContinuousSpace<Object> space, Grid<Object> grid, int population, int infected) {
		this.space = space;
		this.grid = grid;
		this.population = population;
		this.infected = infected;
	}

}
