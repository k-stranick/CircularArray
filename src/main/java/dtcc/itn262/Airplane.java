package dtcc.itn262;

public class Airplane {
	private final String flightNumber;
	private final String airline;

	public Airplane(String flightNumber, String airline) {
		this.flightNumber = flightNumber;
		this.airline = airline;
	}

	@Override
	public String toString() {
		return "Flight number #" + flightNumber + " with " + airline + " Airlines";
	}
}