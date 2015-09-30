package stateandbehavior;

public class UpOrDownCounter {
	int end;
	int counter;
	
	public UpOrDownCounter(int counter, int end) {
		if (end == counter) {
			throw new IllegalArgumentException("Counter and end cannot be equal.");
		}
		this.end = end;
		this.counter = counter;
	}
	int getCounter() {
		return counter;
	}
	boolean count() {
		return count(1); //Default increment of 1
	}
	boolean count(int increment) { //Allows defining a specific increment
		if (counter < end) {
			counter += increment;
			if (counter > end) {
				counter = end;      //Can't have it count past the end
			}                       //Could instead have it not increment if that would take it past the end
		} else if (counter > end) { //Or simply have it call count() n times
			counter -= increment;
			if (counter < end) {
				counter = end;
			}
		}
		return counter != end;
	}
	public static void main(String[] args) {
		UpOrDownCounter count = new UpOrDownCounter(1, 10);
		do { //Loops from counter to one before end
			System.out.println(count.getCounter());
		} while (count.count() == true);
	}
}
