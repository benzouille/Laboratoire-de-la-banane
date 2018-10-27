package test.watchDog;

import java.util.ArrayList;

import test.watchDog.observer.ObservableDog;
import test.watchDog.observer.ObservateurDog;



public class Dog1 implements ObservableDog, ObservateurDog {

	// -- observable
	protected ArrayList<ObservateurDog> listObservateur = new ArrayList<ObservateurDog>();

	//private ObservateurDog obs;

	private int nbreWaf;

	public Dog1() {
		//this.obs = obs;
		//this.addObservateur(obs);
		nbreWaf = 0;
	}

	public void init() {
		nbreWaf++;
		System.out.println("Woof Woof je suis le bulldog, j'aboie "+nbreWaf+" fois !");
		updateObservateur();
	}

	public void boucle() {
		nbreWaf++;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Woof Woof je suis le bulldog, j'aboie " + nbreWaf + " fois !");
		updateObservateur();
	}

	public void update(int nbreWaf) {
		if (this.nbreWaf <= nbreWaf) {
			//System.out.println("update bulldog. la variable : " + this.nbreWaf + ". le parametre : "+nbreWaf);
			boucle();
		}

	}

	public int getNbreWaf() {return nbreWaf;}
	public void setNbreWaf(int nbreWaf) {this.nbreWaf = nbreWaf;}

	public void addObservateur(ObservateurDog obs) {
		listObservateur.add(obs);
	}
	public void updateObservateur() {
		for(ObservateurDog obs : listObservateur) {
			obs.update(nbreWaf);
		}
	}
	public void delObservateur() {}
}