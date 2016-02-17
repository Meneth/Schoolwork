#pragma once
#include <string>

using namespace std;

class Card {
public:
	enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES };
	enum Rank { TWO = 2, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };

private:
	Suit s;
	Rank r;
	bool invalid;

	static string suitToString(Suit suit);
	static string rankToString(Rank rank);

public:
	void initialize(Suit s, Rank r);
	Card(Suit suit, Rank rank);
	Card();
	string toString() const;
	string toStringShort() const;
	Suit getSuit() const;
	Rank getRank() const;
};