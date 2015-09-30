package inheritance;

public class TrainCar {
	private int DeadWeight;

	public TrainCar(int deadWeight) {
		DeadWeight = deadWeight;
	}

	public int getTotalWeight() {
		return DeadWeight;
	}
 	public void setDeadWeight(int deadWeight) {
		DeadWeight = deadWeight;
	}
}
