package classesNeccessaires;



public interface Observateur {
	public void update(Configuration config);
	public void update(Partie partie);
	public void update(boolean test);
	public void update(String choixFinJeu);
	
}
