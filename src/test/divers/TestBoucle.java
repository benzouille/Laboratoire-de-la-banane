package test.divers;

import java.util.ArrayList;

public class TestBoucle {
	private String proposition = "042043";
	private String solution = "012345";

	public TestBoucle() {
		test1();
		test2();
	}
	
	public String test1() {
		ArrayList<Integer> prop = new ArrayList<Integer>();
		ArrayList<Integer> sol = new ArrayList<Integer>();

		for (int i = 0; i<proposition.length(); i++) {
			prop.add(Integer.valueOf(proposition.substring(i, i+1)));
			sol.add(Integer.valueOf(solution.substring(i, i+1)));
		}

		System.out.println("Solution    : " + sol);
		System.out.println("Proposition : " + prop);
		ArrayList<String> result = new ArrayList<String>();
		int removed = 0;
		for (int i = 0 ; i < prop.size(); ++i){
			int y = 0;
			boolean match = false;
			for (y = 0 ;y < sol.size(); ++y ){
				if (prop.get(i).equals(sol.get(y))){
					match = true;
					if (i == y+removed){
						result.add("noir");
						sol.remove(y);
						removed += 1;
					}
					else
						result.add("blanc");
					break;
				}
			}
			if (match == false)
				result.add("faux");		
		}
		String str = result.toString();
		System.out.println(str);
		return str;
	}

	public String test2() {

		ArrayList<Integer> prop = new ArrayList<Integer>();
		ArrayList<Integer> sol = new ArrayList<Integer>();
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i<proposition.length(); i++) {
			prop.add(Integer.valueOf(proposition.substring(i, i+1)));
			sol.add(Integer.valueOf(solution.substring(i, i+1)));	
		}
		//-- Boucle pour trouver les noirs
		for (int i = 0; i<prop.size(); i++) {
			if(prop.get(i).equals(sol.get(i))) {
				prop.remove(i);
				sol.remove(i);
				result.add("noir");
			}
		}
		//-- Boucle pour trouver les blancs
		for (int i = 0; i<prop.size(); i++) {
			for(int y = 0; y<sol.size(); y++) {
				if(prop.get(i).equals(sol.get(y))) {
					prop.remove(i); 
					sol.remove(y); 
					result.add("blanc");
				}
			}
		}
		String str = result.toString();
		System.out.println(str);
		return str;
	}

	public static void main(String[] args) {
		TestBoucle testBoucle = new TestBoucle();
	}
}
