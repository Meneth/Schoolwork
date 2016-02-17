#pragma once

#include "stdafx.h"

class Blackjack {
	CardDeck deck;
	int playerHand, dealerHand;
	int playerCardsDrawn, dealerCardsDrawn;

	static bool isAce(const Card &card);
	static int getCardValue(const Card &card);
	static int getPlayerCardValue(const Card &card);
	static int getDealerCardValue(const Card &card, int handValue);
	static bool askPlayerDrawCard();
	void drawCard(bool player, bool print);
	void drawInitialCards();

public:
	void playGame();
};