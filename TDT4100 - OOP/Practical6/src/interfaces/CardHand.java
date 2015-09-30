package interfaces;

import java.util.ArrayList;
import java.util.Iterator;

public class CardHand implements CardContainer {
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public void addCard(Card card) {
		hand.add(card);
	}
	public Card play(int n) {
		if (n > getCardCount()) {
			throw new IllegalStateException("Cannot remove a card that isn't in the hand.");
		}
		if (n < 0) {
			throw new IllegalArgumentException("Cannot play a negatively numbered card");
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
	@Override
	public Iterator<Card> iterator() {
		return new CardContainerIterator(this);
	}
	public void remove(int n) {
		if (n < getCardCount() && n >= 0) {
			hand.remove(n);
			return;
		}
		throw new IllegalArgumentException("Index outside bounds.");
	}
}
