#include "stdafx.h"

bool Blackjack::isAce(const Card &card) {
	return card.getRank() == card.ACE;
}

int Blackjack::getCardValue(const Card &card) {
	if (isAce(card)) {
		return -1;
	}
	int value = card.getRank();
	if (value > 10)
		value = 10;
	return value;
}

int Blackjack::getPlayerCardValue(const Card &card) {
	int value = getCardValue(card);
	if (value == -1) {
		while (value != 1 && value != 11) {
			cout << "Enter ace value [1 / 11]: ";
			cin >> value;
		}
	}
	return value;
}

int Blackjack::getDealerCardValue(const Card &card, int handValue) {
	int value = getCardValue(card);
	if (value == -1) {
		value = handValue <= 10 ? 11 : 1;
	}
	return value;
}

bool Blackjack::askPlayerDrawCard() {
	char drawCard = '\0';

	while (drawCard != 'y' && drawCard != 'n') {
		cout << "Do you want to draw a card? [y / n] ";
		cin >> drawCard;
	}
	
	return drawCard == 'y';
}

void Blackjack::drawCard(bool player, bool hiddenCard) {
	Card card = deck.drawCard();
	if (player) {
		cout << "You drew " << card.toString() << endl;
		playerCardsDrawn++;
		playerHand += getPlayerCardValue(card);
	}
	else {
		if (hiddenCard)
			cout << "The dealer drew a hidden card" << endl;
		else
			cout << "The dealer drew " << card.toString() << endl;
		dealerCardsDrawn++;
		dealerHand += getDealerCardValue(card, 0);
	}

}

void Blackjack::drawInitialCards() {
	drawCard(true, false);
	drawCard(true, false);
	drawCard(false, false);
	drawCard(false, true);
}

void Blackjack::playGame() {
	playerHand = dealerHand = playerCardsDrawn = dealerCardsDrawn = 0;

	deck.shuffle();
	drawInitialCards();


	cout << "Your hand is worth " << playerHand << endl;
	while (playerHand <= 21 && dealerHand <= 21 && askPlayerDrawCard()) {
		drawCard(true, false);
		cout << "Your hand is worth " << playerHand << endl;
		if (dealerHand < 17 && playerHand <= 21)
			drawCard(false, true);
	}
	while (dealerHand < 17 && playerHand <= 21)
		drawCard(false, true);
	cout << "The dealer's hand is worth " << dealerHand << endl;

	if (playerHand > 21)
		cout << "Sorry, you lost due to going bust" << endl;
	else if (dealerHand > 21)
		cout << "Congratulations, you won as the dealer went bust!" << endl;
	else if (playerHand > dealerHand)
		cout << "Congratulations, you won due to having the larger hand!" << endl;
	else if (playerHand == dealerHand) {
		if (playerHand == 21) {
			if (playerCardsDrawn == 2) {
				if (dealerCardsDrawn == 2)
					cout << "Sorry, you tied with the dealer" << endl;
				else
					cout << "Congratulations, you won as blackjack beats other 21 hands!" << endl;
			}
			else if (dealerCardsDrawn == 2)
				cout << "Sorry, you lost as blackjack beats other 21 hands" << endl;
			else
				cout << "Sorry, you tied with the dealer" << endl;
		}
		else {
			cout << "Sorry, you tied with the dealer" << endl;
		}
	}
	else
		cout << "Sorry, you won due to having the smaller hand" << endl;
}
