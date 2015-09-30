package interfaces;

import java.util.ArrayList;
import java.util.Iterator;

public class CardDeck implements CardContainer {
	private ArrayList<Card> deck;
	
	public CardDeck(int n) {
		deck = new ArrayList<Card>();
		if (n < 0 || n > 13) {
			throw new IllegalArgumentException("Illegal number of cards: " + n);
		}
		for (char suit : Card.suits) {
			for (int i = 0; i < n; i++) {
				deck.add(new Card(suit, i+1));
			}
		}
	}
	public int getCardCount() {
		return deck.size();
	}
	public Card getCard(int n) {
		if (n < getCardCount() && n >= 0) {
			return deck.get(n);
		}
		throw new IllegalArgumentException("Index outside bounds.");
	}
	public void deal(CardHand hand, int n) {
		if (n > getCardCount()) {
			throw new IllegalStateException("Not enough cards left in deck to deal.");
		}
		for (int i = 0; i < n; i++) {
			hand.addCard(getCard(getCardCount()-1));
			deck.remove(getCardCount()-1);
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
		for (int i = 0; i < halfSize; i++) {
			deck.set(2 * i, firstHalf.get(i));
			deck.set(2 * i + 1, secondHalf.get(i));
		}
	}
	@Override
	public Iterator<Card> iterator() {
		return new CardContainerIterator(this);
	}
	public void remove(int n) {
		if (n < getCardCount() && n >= 0) {
			deck.remove(n);
			return;
		}
		throw new IllegalArgumentException("Index outside bounds.");
	}
}
