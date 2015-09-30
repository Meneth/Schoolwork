package inheritance;

public class CardHand extends CardContainerImpl {
	
	public CardHand(int maxCardCount) {
		super(maxCardCount);
	}

	public Card play(int n) {
		if (n > getCardCount()) {
			throw new IllegalStateException("Cannot remove a card that isn't in the hand.");
		}
		if (n < 0) {
			throw new IllegalArgumentException("Cannot play a negatively numbered card");
		}
		Card tempCard = getCard(n);
		remove(n);
		return tempCard;
	}
}
