package inheritance;

import java.util.ArrayList;
import java.util.Iterator;

public class CardContainerImpl implements CardContainer {
	private ArrayList<Card> container;
	private final int maxCardCount;
	
	public CardContainerImpl(int maxCardCount) {
		container = new ArrayList<>();
		this.maxCardCount = maxCardCount;
	}
	
	@Override
	public Iterator<Card> iterator() {
		return new CardContainerIterator(this);
	}

	@Override
	public int getCardCount() {
		return container.size();
	}

	@Override
	public Card getCard(int n) {
		if (n < getCardCount() && n >= 0) {
			return container.get(n);
		}
		throw new IllegalArgumentException("Index outside bounds.");
	}

	@Override
	public void remove(int index) {
		container.remove(index);
	}
	
	@Override
	public void addCard(Card card) {
		if (getCardCount() == maxCardCount)
			throw new IllegalStateException("Cannot exceed max number of cards.");
		container.add(card);
	}
}
