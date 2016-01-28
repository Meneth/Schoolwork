#pragma once
#include "Card.h"

class CardDeck {
	Card cards[52];
	int currentCardIndex;
	void swap(int a, int b);

public:
	CardDeck();
	void print();
	void printShort();
	void shuffle();
	Card drawCard();
};