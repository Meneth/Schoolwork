package encapsulation;

public class Digit {
	private int base;
	private int value;
	
	public Digit(int base) {
		if (base <= 0) {
			throw new IllegalArgumentException("The base has to be at least 1.");
		}
		this.base = base;
	}
	public int getValue() {
		return value;
	}
	public int getBase() {
		return base;
	}
	public boolean increment() {
		if ((value + 1) < base) {
			value++;
			return false;
		} else {
			value = 0;
			return true;
		}
	}
	public String toString() {
		return String.valueOf("0123456789ABCDEFGHIJKLMNOPQRSTUVXYZ".charAt(value));
	}
	public static void main(String[] args) {
		Digit digit1 = new Digit(4);
		Digit digit2 = new Digit(4);
		Digit digit3 = new Digit(4);
		do {
			do {
				do {
					System.out.println(digit1.toString()+digit2.toString()+digit3.toString());
				} while (digit3.increment() == false);
			} while (digit2.increment() == false);
		} while (digit1.increment() == false);
		System.out.println(digit1.toString()+digit2.toString()+digit3.toString());
	}
}
