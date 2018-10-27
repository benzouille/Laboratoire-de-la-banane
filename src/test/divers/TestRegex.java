package test.divers;

import java.util.regex.Pattern;

public class TestRegex {
	
	public TestRegex() {
		
		System.out.println(chiffre());
	}
	
	public boolean mail() {
		String email = "moi@couc@ou.com";
		boolean  essai = Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", email);
		return essai;
	}
	
	public boolean signe() {
		
		boolean b = Pattern.matches("^[+-=]{"+7+"}$", "+-+-+++");
		return b;
	}
	
	public boolean chiffre() {
		boolean chiffre = Pattern.matches("^[0-9]{7}$", "2221333");
		return chiffre;
		
	}
	
	public static void main(String[] args) {
		TestRegex tr = new TestRegex();
	}
}
