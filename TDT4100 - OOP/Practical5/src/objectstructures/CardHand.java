package objectstructures;

import java.util.ArrayList;

public class CardHand {
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public void addCard(Card card) {
		hand.add(card);
	}
	public Card play(int n) {
		if (n > getCardCount()) {
			throw new IllegalStateException("Cannot remove a card that isn't in the hand.");
		}
		if (n < 0) {
			throw new IllegalArgumentException("Cannot play a negative numbered cards");
		}
		Card tempCard = getCard(n);
		hand.remove(n);
		return tempCard;
	}
	public int getCardCount() {
		return hand.size();
	}
	public Card getCard(int n) {
		if (n < getCardCount() && n >= 0) {
			return hand.get(n);
		}
		throw new IllegalArgumentException("Index outside bounds.");
	}
}
