package test;

public class test2 {
	int nbreChiffre = 5;
	int[] solution = new int[] {5,5,5,5,5};
	int[] proposition = new int[] {5, 2, 4, 8, 1};
	String indices, sol;
	String[] prop;
	String[] indice;
	public test2() {
		char [] charac = new char[nbreChiffre];
		for (int i = 0; i<nbreChiffre; i++) {
			charac[i] = '#';
		}
		String str = new String(charac);
		System.out.println(str);
		resolve(proposition);
		System.out.println(toString());
	}

	public String toString() {
		String str = (" La proposition : " +prop+ "\n La solution : " +sol+ "\n Les indices : " +indices+ ".");
		return str;
		
	}
	
	public void resolve(int[] proposition) {
		indice = new String[proposition.length];
		for (int i = 0; i<proposition.length; i++) {
			if(proposition[i] < solution[i]) 
				indice[i] = "+";
			else if(proposition[i] > solution[i]) 
				indice[i] = "-";
			else
				indice[i] = "=";
			System.out.println(" La proposition : " +proposition[i]+ " La solution : " +solution[i]+ " L'indice : " +indice[i]);
		}
		
	}
	public static void main(String[] args) {
		test2 test = new test2();
	}
}
