package inheritance;

public class Card implements Comparable<Card> {
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

	@Override
	public int compareTo(Card o) {
		int c1val = getFace();
		int c2val = o.getFace();
		for (int i = 0; i < Card.suits.length; i++) {
			if (getSuit() == Card.suits[i]) {
				c1val += (3 - i) * 13; // Suit is valued over face value
			}
			if (o.getSuit() == Card.suits[i]) {
				c2val += (3 - i) * 13;
			}
		}
		return c1val - c2val;
	}
}
