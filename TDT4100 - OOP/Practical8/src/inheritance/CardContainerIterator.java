package inheritance;

import java.util.Iterator;

public class CardContainerIterator implements Iterator<Card> {
	private final CardContainer container;
	private int index;
	
	public CardContainerIterator(CardContainer container) {
		this.container = container;
		index = 0;
	}

	@Override
	public boolean hasNext() {
		return index < container.getCardCount();
	}

	@Override
	public Card next() {
		index++;
		return container.getCard(index - 1);
	}

	@Override
	public void remove() {
		container.remove(index);
	}

}
