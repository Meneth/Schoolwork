package objectstructures;

public class Card {
	private final char suit;
	private final int value;
	public static final char[] suits = {'S', 'H', 'D', 'C'};
	
	public Card (char suit, int value) {
		this.suit = suit;
		if (value > 0 && value < 14) {
			this.value = value;
		} else {
			throw new IllegalArgumentException("Invalid value: " + value);
		}
		for (char legalSuit : suits) {
			if (suit == legalSuit) {
				return;
			}
		}
		throw new IllegalArgumentException("Invalid suit: " + suit);
	}

	public char getSuit() {
		return suit;
	}

	public int getFace() {
		return value;
	}
	
	public String toString() {
		return suit + "" + value;
	}
}
