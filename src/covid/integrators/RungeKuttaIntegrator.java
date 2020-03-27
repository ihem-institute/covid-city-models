package covid.integrators;

import covid.Covid;

public class RungeKuttaIntegrator implements Integrator {

	final double[][] RK4_PARAMS = new double[][] { { 0.5, 0.5 }, { 0.5, 0, 0.5 }, { 1, 0, 0, 1 },
			{ 1 / 6, 1 / 3, 1 / 3, 1 / 6 } };
	final int DT = 2;

	Covid covid = new Covid();

	/**
	 * Integrando por Runge Kutta de 4to orden.
	 * Uso f definido en covid.Covid.f
	 * Uso m = RK4_PARAMS
	 * 
	 * @param initialState es el estado inicial. Figura como "y" en el fuente en que nos estamos basando. Arranca con = [1 - I0/N, 0, I0/N, 0, 0, 0, 0, 0, 0, 0], pero se actualiza en cada paso (es la salida de este metodo integrate)
	 * @param time es el tiempo. Figura como "t" en el fuente en que nos estamos basando
	 * @param timeStep. Figura como "h" en el fuente en que nos estamos basando
	 * @return result
	 */
	public double[] integrate(double[] initialState, int time, int timeStep) {
		// Segunda dimension es igual a la cantidad de elementos del array que devuelve la funcion Covid.f
		double[][] k = new double[RK4_PARAMS.length][10];
		double[] initialStateCopy = null;
		
		for(int ki = 0; ki < RK4_PARAMS.length; ki++) {
			// TODO: OJO acá. Se está truncando. O cambiamos f para operar con doubles, o aceptamos el truncado. PROBAR
			initialStateCopy = initialState.clone(); // y.slice()
			timeStep = (int) (ki != 0?((RK4_PARAMS[ki-1][0])*timeStep):0);
			for(int l = 0; l < initialStateCopy.length; l++) {
				for(int j = 0; j <= ki; j++) {
					initialStateCopy[l] = initialStateCopy[l] + timeStep * (RK4_PARAMS[ki-1][j])*(k[ki-1][l]);
				}
			}
			
			k[ki]=covid.f(time + timeStep, initialState);
		}
		
		double[] initialStepSecondCopy = initialState.clone(); // r
		for(int l = 0; l < initialStateCopy.length; l++) {
			for(int j = 0; j < k.length; j++) {
				initialStepSecondCopy[l] = initialStepSecondCopy[l]+timeStep*(k[j][l])*(RK4_PARAMS[RK4_PARAMS.length-1][j]);
			}
		}
		
		return initialStepSecondCopy;
	}
}
