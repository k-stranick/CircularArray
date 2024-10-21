package dtcc.itn262;

public class AirportSimulation {
	boolean plane1Turn;
	private CircularArrayQueue<Airplane> runway1;
	private CircularArrayQueue<Airplane> runway2;

	public AirportSimulation() {
		runway1 = new CircularArrayQueue<>();
		runway2 = new CircularArrayQueue<>();
		loadAirplanes();
	}

	private void loadAirplanes() { //populating the queues with airplanes
		System.out.println("Loading Airplane queues...");
		runway1.enqueue(new Airplane("451", "American Airlines"));
		runway1.enqueue(new Airplane("1561", "United Airlines"));
		runway1.enqueue(new Airplane("3134", "United Airlines"));
		runway2.enqueue(new Airplane("1134", "Southwest Airlines"));
		runway2.enqueue(new Airplane("1351", "American Airlines"));
		runway2.enqueue(new Airplane("1134", "United Airlines"));
		runway2.enqueue(new Airplane("1234", "Southwest Airlines"));
		System.out.println("Planes are ready for take off!");
	}

	public void startSimulation() {
		while (runway1.length() > 0 || runway2.length() > 0) {
			displayRunways();

			if (plane1Turn) {
				if (runway1.length() > 0) {
					Airplane plane = runway1.dequeue();
					System.out.println(plane + " is taking off on runway 1\n");
				} else if (runway2.length() > 0) {
					Airplane plane = runway2.dequeue();
					System.out.println(plane + " is taking off on runway 2\n");
				}
			} else {
				if (runway2.length() > 0) {
					Airplane plane = runway2.dequeue();
					System.out.println(plane + " is taking off on runway 2\n");
				} else if (runway1.length() > 0) {
					Airplane plane = runway1.dequeue();
					System.out.println(plane + " is taking off on runway 1\n");
				}
			}
			plane1Turn = !plane1Turn;
		}
	}

	private void displayRunways() {
		System.out.println("Currently waiting in runways:");
		int runway1Length = runway1.length();
		for (int i = 0; i < runway1Length; i++) {
			Airplane plane = runway1.dequeue();
			System.out.println(plane);
			runway1.enqueue(plane);
		}
		int runway2Length = runway2.length();
		for(int i = 0; i < runway2Length; i++){
			Airplane plane = runway2.dequeue();
			System.out.println(plane);
			runway2.enqueue(plane);
		}
	}
}
