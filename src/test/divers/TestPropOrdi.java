package test.divers;

import classesNeccessaires.Configuration;
import classesNeccessaires.Partie;

public class TestPropOrdi {
	private Partie partie = new Partie(null);;
	private Configuration config = new Configuration();
	int solution = 0, proposition = 0;
	String indice = "-";
	float intervaleMax[], intervaleMin[];
	float a = 5.0f;
	float d = 2.0f;

	public TestPropOrdi() {
		partie.setSolution("123456");
		partie.setProposition("222257");
		partie.setIndice("-=++=-");
		initIntevale();
		float result = a/d;
		System.out.println(result);
		System.out.println(Math.round(a/d));
		System.out.println("proposition : "+proposition+". indice : "+indice);
		System.out.println(propositionOrdinateur(partie));
	}

	public void initIntevale() {
		intervaleMax = new float[config.getCombiPlusMoins()];
		intervaleMin = new float[config.getCombiPlusMoins()];
		for (int i = 0; i<config.getCombiPlusMoins(); i++) {
			intervaleMax[i] =9.0f;
			intervaleMin[i] =0.0f;
		}
	}

	public String propositionOrdinateur(Partie partie) {
		int [] propositionTab = new int[config.getCombiPlusMoins()];
		char [] indiceTab = new char[config.getCombiPlusMoins()];
		String nouvelleProp = "";
		for (int i = 0; i<config.getCombiPlusMoins(); i++) {
			propositionTab[i] = partie.getProposition().charAt(i);
			System.out.print("proposition à l'enplacement "+i+" : "+(propositionTab[i]-48)+". ");
			indiceTab[i] = partie.getIndice().charAt(i);
			System.out.println(" indice à l'enplacement "+i+" : "+indiceTab[i]);
		}
		for(int i =0; i<partie.getSolution().length(); i++) {
			if(indiceTab[i] == '+') {
				this.intervaleMin[i] = propositionTab[i]-48;
				nouvelleProp += (propositionTab[i]-48)+Math.round((intervaleMax[i]-intervaleMin[i])/2);
				System.out.println(" nouvelle prop à l'enplacement "+i+" : "+nouvelleProp+" et interMax : "+intervaleMax[i]+". interMin : "+intervaleMin[i]);
			}
			else if(indiceTab[i] == '-') {
				this.intervaleMax[i] = propositionTab[i]-48;
				nouvelleProp += (propositionTab[i]-48)-Math.round((intervaleMax[i]-intervaleMin[i])/2);
				System.out.println(" nouvelle prop à l'enplacement "+i+" : "+nouvelleProp+" et interMax : "+intervaleMax[i]+". interMin : "+intervaleMin[i]);
			}
			else {
				nouvelleProp += (propositionTab[i]-48);
				System.out.println(" nouvelle prop à l'enplacement "+i+" : "+nouvelleProp+" et interMax : "+intervaleMax[i]+". interMin : "+intervaleMin[i]);
			}
		}
		return nouvelleProp;
	}

	public static void main(String[] args) {
		TestPropOrdi ti = new TestPropOrdi();

	}
}
