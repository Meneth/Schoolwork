#include "stdafx.h"

using namespace std;

string Card::suitToString(Suit suit) {
	switch (suit) {
	case CLUBS: return "Clubs";
	case DIAMONDS: return "Diamonds";
	case HEARTS: return "Hearts";
	case SPADES: return "Spades";
	default: throw invalid_argument("Invalid suit");
	}
}

string Card::rankToString(Rank rank) {
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

void Card::initialize(Suit s, Rank r) {
	this->s = s;
	this->r = r;
	invalid = false;
}

Card::Card() {
	invalid = true;
}

Card::Card(Suit suit, Rank rank) {
	s = suit;
	r = rank;
	invalid = false;
}

string Card::toString() const {
	if (invalid)
		return "Invalid card";
	return rankToString(r) + " of " + suitToString(s);
}

string Card::toStringShort() const {
	if (invalid)
		return "Invalid card";
	char buffer[100];
	sprintf(buffer, "%c%d", suitToString(s).at(0), r);
	return buffer;
}

Card::Suit Card::getSuit() const {
	return s;
}
Card::Rank Card::getRank() const {
	return r;
}