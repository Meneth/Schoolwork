#pragma once

#include <string>

class Person {
	std::string firstName, lastName;
public:
	// Name is immutable
	Person(std::string firstName, std::string lastName);

	bool operator==(const Person & other) const;
	bool operator<(const Person & other) const;
	bool operator<=(const Person & other) const;
	bool operator>(const Person & other) const;
	bool operator>=(const Person & other) const;

	std::string toString() const;
	std::string getFirstName() const;
	std::string getLastName() const;
};

std::ostream &operator<<(std::ostream &os, Person p);