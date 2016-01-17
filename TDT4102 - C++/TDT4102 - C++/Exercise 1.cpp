// TDT4102 - C++.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>
#include <initializer_list>
using namespace std;

// a)
int fibonacci(int n) {
	int a = 0, b = 1;
	cout << "Fibonacci numbers" << endl;
	int temp;
	for (int x = 1; x < n; x++) {
		temp = b;
		b = a + b;
		a = temp;
		cout << x << " " << b << endl;
	}
	cout << "----" << endl;
	return b;
}

// b)
void triangleNumbersBelow(int n) {
	int acc = 1, num = 2;
	cout << "Triangle numbers below " << n << ":" << endl;
	while (acc + num < n) {
		acc = acc + num;
		num++;
		cout << acc << endl;
	}
	cout << endl;
}

bool isTriangleNumber(int number) {
	int acc = 1;
	while (number > 0) {
		number -= acc;
		acc++;
	}
	if (number == 0) {
		return true;
	}
	else {
		return false;
	}
}

// c)
int squareNumberSum(int n) {
	int totalSum = 0;
	for (int i = 0; i < n; i++) {
		totalSum += i * i;
		cout << i * i << endl;
	}
	cout << totalSum << endl;
	return totalSum;
}

// d)
double max(double a, double b) {
	if (a > b) {
		cout << "A is greater than B" << endl;
		return a;
	}
	else {
		cout << "B is greater than A" << endl;
		return b;
	}
}

// e)
bool isPrime(int n) {
	// Got rid of some pointless stuff from the Python code
	for (int i = 2; i < n; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

// f)
void naivePrimeNumberSearch(int n) {
	for (int number = 2; number < n; number++) {
		if (isPrime(number)) {
			cout << number << " is a prime" << endl;
		}
	}
}

// g)
int findGreatestDivisor(int n) {
	for (int divisor = n - 1; divisor > 0; divisor--) {
		if (n % divisor == 0) {
			return divisor;
		}
	}
	throw; // Should be impossible
}

// h)
void compareListOfNumbers(double *l, int size) {
	int r[3] = { 0, 0, 0 };
	double val;
	for (int i = 0; i < size; i++) {
		val = l[i];
		if (val < 0) {
			r[0]++;
		}
		else if (val == 0) {
			r[1]++;
		}
		else {
			r[2]++;
		}
	}
	cout << r[0] << " numbers were below zero" << endl;
	cout << r[1] << " numbers were zero" << endl;
	cout << r[2] << " numbers were greater than 0" << endl; // For consistency this really should be "above", or the first print should be "less than"
}

int main2() {
	fibonacci(20);
	triangleNumbersBelow(20);
	cout << isTriangleNumber(10) << endl;
	cout << isTriangleNumber(15) << endl;
	cout << isTriangleNumber(20) << endl;
	cout << endl;

	squareNumberSum(20);
	cout << endl;

	max(1, 2);
	max(3, 2);
	max(5, 5);
	cout << endl;

	naivePrimeNumberSearch(20);
	cout << endl;

	cout << findGreatestDivisor(20) << endl;
	cout << findGreatestDivisor(23) << endl;
	cout << findGreatestDivisor(100) << endl;
	cout << endl;

	double list[] = { 0, 2, 5, -3, 12, 18 };
	compareListOfNumbers(list, sizeof(list) / sizeof(*list));
	return 0;
}