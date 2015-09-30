package inheritance;

import java.util.Stack;

public class Locomotive {
	private Stack<TrainCar> cars;
	
	public Locomotive() {
		cars = new Stack<>();
	}
	public void addTrainCar(TrainCar car) {
		cars.push(car);
	}
	public boolean contains(TrainCar car) {
		return cars.contains(car);
	}
	public int getTotalWeight() {
		int weight = 0;
		for (TrainCar car : cars) {
			weight += car.getTotalWeight();
		}
		return weight;
	}
	public int getPassengerCount() {
		int count = 0;
		for (TrainCar car : cars) {
			if (car instanceof PassengerCar)
				count += ((PassengerCar) car).getPassengerCount();
		}
		return count;
	}
	public int getCargoWeight() {
		int weight = 0;
		for (TrainCar car : cars) {
			if (car instanceof CargoCar)
				weight += ((CargoCar) car).getCargoWeight();
		}
		return weight;
	}
	@Override
	public String toString() {
		return cars.toString();
	}
}
