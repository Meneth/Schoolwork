package inheritance;

public interface CardContainer extends Iterable<Card> {
	int getCardCount();
	Card getCard(int n);
	void remove(int index);
	void addCard(Card card);
}
