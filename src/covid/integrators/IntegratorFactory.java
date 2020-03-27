package covid.integrators;

public class IntegratorFactory {
	private IntegratorFactory() {
	}

	public static Integrator getIntegrator(IntegrationMethod integrationMethod) {
		switch (integrationMethod) {
		case RK:
			return new RungeKuttaIntegrator();
		default:
			throw new RuntimeException("Integration method not implemented");
		}
	}
}
