package covid;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;

public class CitiesBuilder implements ContextBuilder<Object> {

	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId("covid-city-models");
		NetworkBuilder<Object> netBuilder = new NetworkBuilder<Object>(
				"infection network", context, true);
		netBuilder.buildNetwork();

		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder
				.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace(
				"space", context, new RandomCartesianAdder<Object>(),
				new repast.simphony.space.continuous.WrapAroundBorders(), 50,
				50);

		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context,
				new GridBuilderParameters<Object>(new WrapAroundBorders(),
						new SimpleGridAdder<Object>(), true, 50, 50));

		Parameters params = RunEnvironment.getInstance().getParameters();
		
		City capital = new City(space, grid, "Capital", 114822, 0);
		context.add(capital);
		
		City guaymallen = new City(space, grid, "Guaymallen", 280880, 0);
		context.add(guaymallen);
		new CityLink(capital, guaymallen);
		
		City las_heras = new City(space, grid, "Las Heras", 203507, 0);
		context.add(las_heras);
		new CityLink(capital, las_heras); 

		City san_rafael = new City(space, grid, "San Rafael", 191323, 0);
		context.add(san_rafael);
		new CityLink(capital, san_rafael);
		
		City godoy_cruz = new City(space, grid, "Godoy Cruz", 189578, 0);
		context.add(godoy_cruz);
		new CityLink(capital, godoy_cruz);
		
		City maipu = new City(space, grid, "Maipú", 172861, 0);
		context.add(maipu);
		new CityLink(capital, maipu);
		new CityLink(godoy_cruz, maipu);
		new CityLink(guaymallen, maipu);
		
		City lujan_cuyo = new City(space, grid, "Luján de Cuyo", 124418, 0);
		context.add(lujan_cuyo);
		new CityLink(capital, lujan_cuyo);
		new CityLink(lujan_cuyo, maipu);
		
		City san_martin = new City(space, grid, "San Martín", 118561, 0);
		City rivadavia = new City(space, grid, "Rivadavia", 56269, 0);
		City tunuyan = new City(space, grid, "Tunuyán", 49132, 0);
		City alvear = new City(space, grid, "General Alvear", 46156, 0);
		City junin = new City(space, grid, "Junín", 37807, 0);
		City lavalle = new City(space, grid, "Lavalle", 35895, 0);
		City tupungato = new City(space, grid, "Tupungato", 32865, 0);
		City san_carlos = new City(space, grid, "San Carlos", 32683, 0);
		City malargue = new City(space, grid, "Malargüe", 28887, 0);
		City santa_rosa = new City(space, grid, "Santa Rosa", 16099, 0);
		City la_paz = new City(space, grid, "La Paz", 9867, 0);		

		for (Object obj : context) {
			NdPoint pt = space.getLocation(obj);
			grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
		}
		
		if (RunEnvironment.getInstance().isBatch()) {
			RunEnvironment.getInstance().endAt(20);
		}

		return context;
	}	

}
