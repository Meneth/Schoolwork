package interfaces;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
	private final boolean aceHigh;
	private final char trumpf;
	private final char[] suits;
	
	public CardComparator(boolean aceHigh, char trumpf) {
		this.aceHigh = aceHigh;
		this.trumpf = trumpf;
		suits = Card.suits;
		validateConstructor();
	}
	public CardComparator(boolean aceHigh, char trumpf, char[] suits) {
		this.aceHigh = aceHigh;
		this.trumpf = trumpf;
		this.suits = suits;
		validateConstructor();
	}
	private void validateConstructor() {
		if (trumpf == ' ') {
			return;
		}
		for (char legalSuit : Card.suits) {
			if (trumpf == legalSuit) {
				return;
			}
		}
		throw new IllegalArgumentException("Invalid suit specified as trumpf.");
	}

	@Override
	public int compare (Card c1, Card c2) {
		int c1val = c1.getFace();
		int c2val = c2.getFace();
		if (aceHigh) {
			if (c1val == 1) {
				c1val += 13; // Ace value of 14
			}
			if (c2val == 1) {
				c2val += 13;
			}
		}
		for (int i = 0; i < suits.length; i++) {
			if (c1.getSuit() == suits[i]) {
				if (c1.getSuit() != trumpf) {
					c1val += (3 - i) * 13; // Suit is valued over face value
				} else {
					c1val += 4 * 13; // Beats any suit at the same face value
				}
			}
			if (c2.getSuit() == suits[i]) {
				if (c2.getSuit() != trumpf) {
					c2val += (3 - i) * 13;
				} else {
					c2val += 4 * 13; // Beats any suit at the same face value
				}
			}
		}
		return c1val - c2val;
	}
}
