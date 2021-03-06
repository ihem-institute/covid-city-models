package covid;

import java.util.ArrayList;
import java.util.List;

public class Covid {

	public static void main(String[] args) {
		new Covid().integrate();		
	}

	long Time_to_death = 32;
	double logN = 15.761420707019587;
	public static final  double N = 6999999.999999998;
	public static final  int I0 = 1;
	public static final double [] SEIR20 = {1-I0/N, 0, I0/N, 0, 0, 0, 0, 0, 0, 0};
	static double R0 = 2.2;
	static double D_incbation = 5.2;
	static double D_infectious = 2.9;
	static double D_recovery_mild = 11.1;
	static double D_recovery_severe = 28.6;
	static double D_hospital_lag = 5;
	static double D_death = 29.1;
	static double CFR = 0.02;
	static double InterventionTime = 100;
	double OMInterventionAmt = 0.6666666666666666;
	static double InterventionAmt = 0.33333333333333337;
	double Time = 220;
	double Xmax = 110000;
	double dt = 2;
	static double P_SEVERE = 0.2;
	static long duration = 840000000000L;
//		state =  "http = //gabgoh.github.io/COVID/index.html?CFR=0.02&D_hospital_lag=5&D_incbation=5.2&D_infectious=2.9&D_recovery_mild=11.1&D_recovery_severe=28.6&I0=1&InterventionAmt=0.33333333333333337&InterventionTime=100&P_SEVERE=0.2&R0=2.2&Time_to_death=32&logN=15.761420707019587"
//		Sol =  undefined
//		P =  undefined
//		timestep =  undefined
//		tmax =  undefined
//		deaths =  undefined
//		total =  undefined
//		total_infected =  undefined
//		Iters =  undefined
//		dIters =  undefined
//		Pmax =  undefined
	boolean lock = false;
	String parsed = "";
//		xScaleTime =  undefined
//		xScaleTimeInv =  undefined
//		indexToTime =  undefined
	boolean[] checked = { true, true, false, true, true };
	int active = 0;
//		active_ =  undefined
//		ode_eqn =  undefined
	int p_num_ind = 40;
//		get_d =  undefined
//		milestones =  undefined
	boolean log = true;
	
	public List<double[]> integrate() {
		double NSTEPS = 10000;
		double TTOT = 200;
		
		List<double []> output = new ArrayList<double[]>();
				
		double[] lastSeir = SEIR20.clone();
		output.add(lastSeir.clone());
		double d = TTOT/NSTEPS;
		for(int t = 0; t <= NSTEPS; t++) {
			apply_integration(lastSeir, d, t);
			output.add(lastSeir.clone());
		}
		return output;
	}
	public static void apply_integration(double[] lastSeir, double d, int t) {
		double[] result = f(t, lastSeir);
		double [] subTotal = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		for(int index = 0; index < 10; index ++) {
			subTotal[index] = d * result[index];
			lastSeir[index] = lastSeir[index] + subTotal[index];
//			System.out.print(lastSeir[index]+", ");
		}
//		System.out.println();
	}
	
	
	private static double [] f(int t, double[] x){

	      // SEIR ODE
	      double beta;
		if (t > InterventionTime && t < InterventionTime + duration){
	        beta = (InterventionAmt)*R0/(D_infectious);
	      } else if (t > InterventionTime + duration) {
	        beta = 0.5*R0/(D_infectious);        
	      } else {
	        beta = R0/(D_infectious);
	      }
	      double a = 1/D_incbation;
	      double gamma = 1/D_infectious;
	      
	      double S = x[0]; // Susectable
	      double E = x[1]; // Exposed
	      double I = x[2]; // Infectious 
	      double Mild = x[3]; // Recovering (Mild)     
	      double Severe = x[4]; // Recovering (Severe at home)
	      double Severe_H = x[5]; // Recovering (Severe in hospital)
	      double Fatal = x[6]; // Recovering (Fatal)
	      double R_Mild = x[7]; // Recovered
	      double R_Severe = x[8]; // Recovered
	      double R_Fatal = x[9]; // Dead

	      double p_severe = P_SEVERE;
	      double p_fatal = CFR;
	      double p_mild = 1 - P_SEVERE - CFR;

	      double dS = -beta*I*S;
	      double dE = beta*I*S - a*E;
	      double dI = a*E - gamma*I;
	      double dMild = p_mild*gamma*I   - (1/D_recovery_mild)*Mild;
	      double dSevere = p_severe*gamma*I - (1/D_hospital_lag)*Severe;
	      double dSevere_H = (1/D_hospital_lag)*Severe - (1/D_recovery_severe)*Severe_H;
	      double dFatal = p_fatal*gamma*I  - (1/D_death)*Fatal;
	      double dR_Mild = (1/D_recovery_mild)*Mild;
	      double dR_Severe = (1/D_recovery_severe)*Severe_H;
	      double dR_Fatal = (1/D_death)*Fatal;
	      //                   0   1   2   3      4        5          6       7        8          9
	      double [] result = {dS, dE, dI, dMild, dSevere, dSevere_H, dFatal, dR_Mild, dR_Severe, dR_Fatal};
	      return result;
	}
}
