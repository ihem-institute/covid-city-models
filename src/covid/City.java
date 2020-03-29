package covid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cern.jet.random.Exponential;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class City {

	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private double population;
	private double infected;
	private String name;
	private Exponential infectionPrevision = RandomHelper.createExponential(2);
	private List<CityLink> links = new ArrayList<CityLink>();
	private long dead;
	private long hospital;
	private long recovered;
	private long infectious;
	private long exposed;
	private double[] lastSeir = Covid.SEIR20.clone();

	public City(ContinuousSpace<Object> space, Grid<Object> grid, String name, int population, int infected) {
		this.name = name;
		this.space = space;
		this.grid = grid;
		this.population = population;
		this.infected = infected;
		this.lastSeir[0] = 1 - (this.infected / this.population);
	}

	public String toString() {
		return this.name;
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
//		this.setInfected(this.getInfected() + infectionPrevision.nextInt());
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
		int tickCount = (int) schedule.getTickCount() % 1000000000;

		Covid.apply_integration(this.lastSeir, 0.5, 0);
		
        //    
		this.dead = (long) (this.population * this.lastSeir[9]);  
		this.hospital = (long) (this.population * this.lastSeir[5]+this.lastSeir[6]);         
		this.recovered = (long) (this.population * this.lastSeir[7] + this.lastSeir[8]);       
		this.infectious = (long) (this.population * this.lastSeir[2]);  
		this.exposed = (long) (this.population * this.lastSeir[1]);
		
//		this.population -= transitPeople;
		this.infected = (int) (this.population * (1-this.lastSeir[0]));
				 		
		for (CityLink cityLink : links) {
//			int transitInfected = this.infected * (cityLink.getTransitAverage()/1000) * (cityLink.getOpenessStrategy() / 100);
//			int transitPeople = cityLink.getTransitAverage() * (cityLink.getOpenessStrategy() / 100);
			
			cityLink.getOtherCity(this).transit(
				0, //transitPeople,  
				0 //transitInfected
			);
		}
		System.out.println(
			"(" + tickCount + ") " +
			this.name + ": " +
			" dead: " + this.dead + 
			" hospital: " + this.hospital +
			" recovered: " + this.recovered +
			" infectious: " + this.infectious +
			" exposed " + this.exposed +
			" infected: " + this.infected
		);
	}

	private void transit(int transitPeople, int transitInfected) {
		this.infected += transitInfected;
		this.population += transitPeople;
	}

	public double getInfected() {
		return infected;
	}

	public void setInfected(int infected) {
		this.infected = infected;
	}

	public double getPopulation() {
		return population;
	}

	public void setPopulation(double population) {
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

	public long getDead() {
		return dead;
	}

	public long getHospital() {
		return hospital;
	}

	public long getRecovered() {
		return recovered;
	}

	public long getInfectious() {
		return infectious;
	}

	public long getExposed() {
		return exposed;
	}
}
