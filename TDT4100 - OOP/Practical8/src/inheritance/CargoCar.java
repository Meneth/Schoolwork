package inheritance;

public class CargoCar extends TrainCar {
	private int cargoWeight;

	public CargoCar(int deadWeight, int cargoWeight) {
		super(deadWeight);
		this.cargoWeight = cargoWeight;
	}
	public int getCargoWeight() {
		return cargoWeight;
	}
	@Override
	public int getTotalWeight() {
		return getCargoWeight() + super.getTotalWeight();
	}
	@Override
	public String toString() {
		return "PassengerCar [Weight: " + getTotalWeight() + " Cargo: " + getCargoWeight() + "]";
	}
}
