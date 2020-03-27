package covid.integrators;

public interface Integrator {
	double[] integrate(double[] initialState, int time, int timeStep);
}
