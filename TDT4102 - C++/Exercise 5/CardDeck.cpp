#include "stdafx.h"

CardDeck::CardDeck() {
	currentCardIndex = 0;
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 13; j++) {
			cards[i*13 + j] = Card((Card::Suit) i, (Card::Rank) (j + 2));
		}
	}
}

void CardDeck::swap(int a, int b) {
	Card temp = cards[a];
	cards[a] = cards[b];
	cards[b] = temp;
}

void CardDeck::print() {
	for (int i = 0; i < sizeof(cards) / sizeof(*cards); i++) {
		cout << cards[i].toString() << endl;
	}
}

void CardDeck::printShort() {
	for (int i = 0; i < sizeof(cards) / sizeof(*cards); i++) {
		cout << cards[i].toStringShort() << endl;
	}
}

int random(int min, int max) {
	random_device device;
	mt19937 generator(device());
	uniform_int_distribution<int> uniform(min, max);

	return uniform(generator);
}

// Fisher-Yates shuffle
void CardDeck::shuffle() {
	Card shuffled[52];
	bool unused[52];
	for (int i = 0; i < 52; i++) {
		int r = random(0, 52 - i - 1);
		int index = 0;
		for (int j = 0; j < r || !unused[index]; j++) {
			index++;
			if (!unused[index]) {
				j--;
			}
		}
		shuffled[i] = cards[index];
		unused[index] = false;
	}

	for (int i = 0; i < 52; i++) {
		cards[i] = shuffled[i];
	}
}

Card CardDeck::drawCard() {
	Card card = cards[currentCardIndex];
	currentCardIndex++;
	if (currentCardIndex == 52)
		currentCardIndex = 0;
	return card;
}