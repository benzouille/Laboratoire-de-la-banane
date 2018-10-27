package test.watchDog;

public class TestWatchDog {
	
	private Dog1 bulldog;
	private Dog2 yorkshire;
	
	public TestWatchDog() {
		bulldog = new Dog1();
		yorkshire = new Dog2();
		bulldog.addObservateur(yorkshire);
		yorkshire.addObservateur(bulldog);
		bulldog.init();
	}

	public static void main(String[] args) {
		TestWatchDog testWatchDog = new TestWatchDog();

	}

}
