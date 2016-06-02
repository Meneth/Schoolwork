// Exercise 11.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

#include <vector>
#include <string>
#include <iostream>
#include <set>
#include <random>
#include <forward_list>

#include "SimpleSet.h"
#include "Person.h"
#include "LList.h"

static void replace(std::vector<std::string> * v, std::string old, std::string replacement) {
	for (auto i = v->begin(); i != v->end(); i++) {
		if (*i == old) {
			*i = replacement;
		}
	}
}

int random(int min, int max) {
	std::random_device device;
	std::mt19937 generator(device());
	std::uniform_int_distribution<int> uniform(min, max);

	return uniform(generator);
}

template <typename T> void shuffle(T * arr, int size) {
	T * shuffled = new T[size] {};
	bool * used = new bool[size] {};
	for (int i = 0; i < size; i++) {
		used[i] = false;
	}
	for (int i = 0; i < size; i++) {
		int r = random(0, size - i - 1);
		int index = 0;
		for (int j = 0; j < r || used[index]; j++) {
			index++;
			if (used[index]) {
				j--;
			}
		}
		shuffled[i] = arr[index];
		used[index] = true;
	}

	for (int i = 0; i < size; i++) {
		arr[i] = shuffled[i];
	}
}

template <typename T> T maximum(T lhs, T rhs) {
	if (lhs > rhs)
		return lhs;
	else
		return rhs;
}

void insertOrdered(std::forward_list<Person> &l, Person p) {
	if (l.empty() || p < l.front()) {
		l.push_front(p);
		return;
	}
	for (auto i = l.begin(); i != l.end(); i++) {
		if (p >= *i) {
			l.insert_after(++i, p);
			return;
		}
	}
}

int main() {
	std::vector<std::string> v;

	v.push_back("lorem");
	v.push_back("ipsum");
	v.push_back("dolor");
	v.push_back("sit");
	v.push_back("amet");
	v.push_back("ipsum");

	replace(&v, "ipsum", "dolor");

	for (std::string s : v) {
		std::cout << s << std::endl;
	}

	for (auto i = v.rbegin(); i != v.rend(); i++) {
		std::cout << *i << std::endl;
	}

	SimpleSet<int> s;

	for (int i = 0; i <= 100; i++) {
		s.insert(i);
	}

	std::cout << s << std::endl;

	for (int i = 0; i <= 100; i++) {
		if (i % 2 == 0 && i != 2) {
			s.erase(i);
		}
	}

	std::cout << s << std::endl;

	int a[] = { 1, 2, 3, 4, 5, 6, 7 };
	shuffle(a, 7);
	for (int i = 0; i < 7; i++) {
		std::cout << a[i] << " ";
	}
	std::cout << std::endl;

	double b[] = { 1.2, 2.2, 3.2, 4.2 };
	shuffle(b, 4);
	for (int i = 0; i < 4; i++) {
		std::cout << b[i] << " ";
	}
	std::cout << std::endl;

	std::string c[] = { "one", "two", "three", "four" };
	shuffle(c, 4);
	for (int i = 0; i < 4; i++) {
		std::cout << c[i] << " ";
	}
	std::cout << std::endl;

	int x = 1;
	int y = 2;
	int z = maximum(x, y);	std::cout << z << std::endl;

	double d = 2.4;
	double e = 3.2;
	double f = maximum(d, e);	std::cout << f << std::endl;

	Person p1("Magne", "Skjæran");
	Person p2("Ola", "Nordmann");
	Person p3("Jens", "Stoltenberg");
	Person p4("Siv", "Jensen");
	Person p5("Erna", "Solberg");

	std::forward_list<Person> pList;

	insertOrdered(pList, p1);
	insertOrdered(pList, p2);
	insertOrdered(pList, p3);
	insertOrdered(pList, p4);
	insertOrdered(pList, p5);

	for (Person p : pList) {
		std::cout << p << std::endl;
	}

	LinkedList l;

	l.insertAtBack(p1.toString());
	l.insertAtBack(p2.toString());
	l.insertAtBack(p3.toString());
	l.insertAtBack(p4.toString());
	l.insertAtBack(p5.toString());

	std::cout << l << std::endl;

    return 0;
}

