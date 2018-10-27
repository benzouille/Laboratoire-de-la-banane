package test.divers;

public class TestCharAt {

	public TestCharAt() {
		String chaine = "123456";
		char[] tab = chaine.toCharArray();
		
		char chaine2[] = new char[5];
		chaine2[0]='8';
		Integer chaine3[] = new Integer[5];
		chaine3[0] = Character.getNumericValue(chaine2[0]);
		//Character.getNumericValue(chaine2[0]);
		System.out.println("charAt : "+chaine.charAt(0));
		System.out.println(Character.getNumericValue(chaine2[0]));
		System.out.println(String.copyValueOf(tab));
		System.out.println("");
	}
	public static void main(String[] args) {
		TestCharAt tca = new TestCharAt();
	}
}
