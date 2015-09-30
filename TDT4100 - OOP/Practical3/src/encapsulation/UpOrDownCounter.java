package encapsulation;

public class UpOrDownCounter {
	private int end;
	private int counter;
	
	public UpOrDownCounter(int counter, int end) {
		if (end == counter) {
			throw new IllegalArgumentException("Counter and end cannot be equal.");
		}
		this.end = end;
		this.counter = counter;
	}
	public int getCounter() { return counter; }
	public int getEnd() { return end; }
	public boolean count() {
		return count(1); //Default increment of 1
	}
	public boolean count(int increment) { //Allows defining a specific increment
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
}
