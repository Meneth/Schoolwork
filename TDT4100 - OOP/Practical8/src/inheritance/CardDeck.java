package inheritance;

import java.util.ArrayList;

public class CardDeck extends CardContainerImpl {
	
	public CardDeck(int n) {
		super(52);
		if (n < 0 || n > 13) {
			throw new IllegalArgumentException("Illegal number of cards: " + n);
		}
		for (char suit : Card.suits) {
			for (int i = 0; i < n; i++) {
				addCard(new Card(suit, i+1));
			}
		}
	}
	public void deal(CardHand hand, int n) {
		if (n > getCardCount()) {
			throw new IllegalStateException("Not enough cards left in deck to deal.");
		}
		for (int i = 0; i < n; i++) {
			hand.addCard(getCard(getCardCount()-1));
			remove(getCardCount()-1);
		}
	}
	public void shufflePerfectly() {
		if (getCardCount() % 2 != 0) {
			throw new IllegalStateException("Deck has odd number of cards, thus cannot be split evenly");
		}
		int halfSize = getCardCount()/2;
		ArrayList<Card> firstHalf = new ArrayList<Card>();
		ArrayList<Card> secondHalf = new ArrayList<Card>();
		for (int i = 0; i < halfSize; i++) {
			firstHalf.add(getCard(i));
			secondHalf.add(getCard(i + halfSize));
		}
		for (int i = 0; i < getCardCount(); i++) {
			remove(0); // Empty the deck
		}
		for (int i = 0; i < halfSize; i++) {
			addCard(firstHalf.get(i));
			addCard(secondHalf.get(i));
		}
	}
}
