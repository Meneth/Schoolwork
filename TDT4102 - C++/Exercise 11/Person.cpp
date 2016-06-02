#include "stdafx.h"
#include "Person.h"

Person::Person(std::string firstName, std::string lastName) {
	this->firstName = firstName;
	this->lastName = lastName;
}

bool Person::operator==(const Person & other) const {
	return firstName == other.firstName && lastName == other.lastName;
}

bool Person::operator<(const Person & other) const {
	return toString() < other.toString();
}

bool Person::operator<=(const Person & other) const {
	return toString() <= other.toString();
}

bool Person::operator>(const Person & other) const {
	return toString() > other.toString();
}

bool Person::operator>=(const Person & other) const {
	return toString() >= other.toString();
}

std::string Person::toString() const {
	return lastName + ", " + firstName;
}

std::string Person::getFirstName() const {
	return firstName;
}

std::string Person::getLastName() const {
	return lastName;
}

std::ostream & operator<<(std::ostream & os, Person p) {
	os << p.toString();
	return os;
}