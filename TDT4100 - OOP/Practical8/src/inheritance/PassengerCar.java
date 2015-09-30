package inheritance;

public class PassengerCar extends TrainCar {
	private int passengers;

	public PassengerCar(int deadWeight, int passengers) {
		super(deadWeight);
		this.passengers = passengers;
	}
	public int getPassengerCount() {
		return passengers;
	}
	@Override
	public int getTotalWeight() {
		return getPassengerCount() * 80 + super.getTotalWeight();
	}
	@Override
	public String toString() {
		return "PassengerCar [Weight: " + getTotalWeight() + " Passengers: " + getPassengerCount() + "]";
	}
}
