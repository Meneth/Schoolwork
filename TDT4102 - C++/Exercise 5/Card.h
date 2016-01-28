#pragma once
#include <string>

using namespace std;

class Card {
public:
	enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES };
	enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };

private:
	Suit s;
	Rank r;
	bool invalid;

	string suitToString(Suit suit);
	string rankToString(Rank rank);

public:
	void initialize(Suit s, Rank r);
	Card(Suit suit, Rank rank);
	Card();
	string toString();
	string toStringShort();
	Suit getSuit();
	Rank getRank();
};