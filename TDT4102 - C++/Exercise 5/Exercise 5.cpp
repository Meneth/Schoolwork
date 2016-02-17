// Exercise 5.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using namespace std;

enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES };
enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };

string suitToString(Suit suit) {
	switch (suit) {
	case CLUBS: return "Clubs";
	case DIAMONDS: return "Diamonds";
	case HEARTS: return "Hearts";
	case SPADES: return "Spades";
	default: throw invalid_argument("Invalid suit");
	}
}

string rankToString(Rank rank) {
	switch (rank) {
	case TWO: return "Two";
	case THREE: return "Three";
	case FOUR: return "Four";
	case FIVE: return "Five";
	case SIX: return "Six";
	case SEVEN: return "Seven";
	case EIGHT: return "Eight";
	case NINE: return "Nine";
	case TEN: return "Ten";
	case JACK: return "Jack";
	case QUEEN: return "Queen";
	case KING: return "King";
	case ACE: return "Ace";
	default: throw invalid_argument("Invalid rank");
	}
}

struct CardStruct {
	Suit s;
	Rank r;
};

string toString(CardStruct card) {
	return rankToString(card.r) + " of " + suitToString(card.s);
}

string toStringShort(CardStruct card) {
	char buffer[100];
	sprintf_s(buffer, 100, "%c%d", suitToString(card.s).at(0), card.r + 2);
	return buffer;
}

int main()
{
	CardStruct cardstruct;
	cardstruct.r = QUEEN;
	cardstruct.s = HEARTS;
	cout << toString(cardstruct) << endl;
	cout << toStringShort(cardstruct) << endl;

	Card card(Card::SPADES, Card::ACE);

	cout << card.toString() << endl;
	cout << card.toStringShort() << endl;

	Card card2;
	cout << card2.toString() << endl;
	card2.initialize(Card::CLUBS, Card::EIGHT);

	cout << card2.toString() << endl;
	cout << card2.toStringShort() << endl;

	CardDeck deck;
	deck.print();
	cout << endl << endl;

	deck.shuffle();
	deck.print();

	Blackjack game;
	game.playGame();

    return 0;
}