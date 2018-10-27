package test.divers;

import main.java.model.Configuration;

public class TestResolve {

	private Configuration config;
	private String solution, prosposition, indices;

	public TestResolve() {
		config = new Configuration();
		solution = "128912";
		indices = "";
		prosposition = "780780";
		resolve(prosposition);
	}

	public String resolve(String proposition) {
		Integer[] propositionTab = new Integer[config.getCombiPlusMoins()];
		Integer[] solutionTab = new Integer[config.getCombiPlusMoins()];
		for (int i = 0; i<config.getCombiPlusMoins(); i++) {
			solutionTab[i] =  Integer.valueOf(solution.substring(i, i+1));
			System.out.print("solution à l'enplacement "+i+" : "+solutionTab[i]+" ---- ");
			propositionTab[i] = Integer.valueOf(proposition.substring(i, i+1));
			System.out.println(" proposition à l'enplacement "+i+" : "+propositionTab[i]);
		}
		indices = "";
		for (int i = 0; i<config.getCombiPlusMoins(); i++) {
			if(propositionTab[i] < solutionTab[i]) {
				indices += "+";
			}
			else if(propositionTab[i] > solutionTab[i]) {
				indices += "-";
			}	
			else {
				indices += "=";
			}
			System.out.println(" La proposition : " +propositionTab[i]+ " La solution : " +solutionTab[i]+ " L'indices : " +indices);

		}
		return indices;
	}
	
	public static void main(String[] args) {
		TestResolve test = new TestResolve();
	}

}
