package dtcc.itn262;

public class AirportSimulation {
	boolean plane1Turn = true;
	private CircularArrayQueue<Airplane> runway1;
	private CircularArrayQueue<Airplane> runway2;

	public AirportSimulation() {
		runway1 = new CircularArrayQueue<>();
		runway2 = new CircularArrayQueue<>();
		loadAirplanes();
	}

	private void loadAirplanes() { //populating the queues with airplanes
		slowPrint("Loading Airplane queues...\n",100);
		runway1.enqueue(new Airplane("451", "American Airlines"));
		runway1.enqueue(new Airplane("1561", "United Airlines"));
		runway1.enqueue(new Airplane("3134", "United Airlines"));

		runway2.enqueue(new Airplane("1134", "Southwest Airlines"));
		runway2.enqueue(new Airplane("1351", "American Airlines"));
		runway2.enqueue(new Airplane("1134", "United Airlines"));
		runway2.enqueue(new Airplane("1234", "Southwest Airlines\n"));
		slowPrint("Planes are ready for take off!\n", 100);
	}

	public void startSimulation() throws InterruptedException {
		while (runway1.length() > 0 || runway2.length() > 0) {
			displayRunways();
			if (plane1Turn) {
				if (runway1.length() > 0) {
					Airplane plane = runway1.dequeue();
					slowPrint(plane + " is taking off on runway 1\n", 100);
				} else if (runway2.length() > 0) {
					Airplane plane = runway2.dequeue();
					slowPrint(plane + " is taking off on runway 2\n", 100);
				}
			} else {
				if (runway2.length() > 0) {
					Airplane plane = runway2.dequeue();
					slowPrint(plane + " is taking off on runway 2\n", 100);
				} else if (runway1.length() > 0) {
					Airplane plane = runway1.dequeue();
					slowPrint(plane + " is taking off on runway 1\n", 100);
				}
			}
			plane1Turn = !plane1Turn;
			try {
				Thread.sleep(2000); // Delay for 1 second
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Simulation interrupted");
			}
		}
	}

	private void displayRunways() throws InterruptedException {
		slowPrint("Currently waiting in runways:\n", 100);

		// Display runway '1' airplanes
		System.out.println("Runway 1:");
		int runway1Length = runway1.length();
		for (int i = 0; i < runway1Length; i++) {
			Airplane plane = runway1.dequeue();
			slowPrint(plane.toString() + "\n", 50);  // Print each airplane slowly
			runway1.enqueue(plane);     // Re-enqueue to preserve order
			Thread.sleep(3000);
		}

		// Display runway 2 airplanes
		System.out.println("Runway 2:");
		int runway2Length = runway2.length();
		for (int i = 0; i < runway2Length; i++) {
			Airplane plane = runway2.dequeue();
			slowPrint(plane.toString() + "\n", 50);  // Print each airplane slowly
			runway2.enqueue(plane);     // Re-enqueue to preserve order
			Thread.sleep(2000);
		}
	}
	// Method to simulate slow text printing like an old CRT monitor
	private void slowPrint(String text, int delay) {
		for (char c : text.toCharArray()) {
			System.out.print(c);
			try {
				Thread.sleep(delay);  // Delay between each character
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
