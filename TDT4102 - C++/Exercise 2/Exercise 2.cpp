// Exercise 2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <math.h>
using namespace std;

// Task 1
// a)

void inputAndPrintInteger() {
	cout << "Enter an integer: ";
	int i;
	cin >> i;
	cout << "You wrote: " << i << endl;
}

// b)
int inputInteger() {
	cout << "Enter an integer: ";
	int i;
	cin >> i;
	return i;
}

// c) d)
void inputIntegersAndPrintSum() {
	// I chose to use inputInteger rather than inputAndPrintInteger, 
	// as the user doesn't need to be retold what number they've entered
	int a = inputInteger();
	int b = inputInteger();
	cout << "The sum is: " << a + b << endl;
}

// e)
bool isOdd(int i) {
	return i % 2 == 1;
}

// f)
void printHumanReadableTime(int seconds) {
	int hours = seconds / 60 / 60;
	int minutes = seconds % (60 * 60) / 60;
	seconds %= 60;
	cout << hours << " hours, " << minutes << " minutes, and " << seconds << " seconds." << endl;
}

// Task 2
// a) b)
void inputIntegersUsingLoopAndPrintSum() {
	// I went with ending when the user enters 0, 
	// as then the user doesn't have to remember/count how many numbers they have to enter
	cout << "Enter as many integers as you'd like. Enter 0 to get the sum of the numbers entered." << endl;

	int sum = 0;
	int i;
	while (true) {
		i = inputInteger();
		if (i == 0)
			break;
		sum += i;
	}
	cout << "The sum of the numbers is: " << sum << endl;
}

// c)
double inputDouble() {
	cout << "Enter a number: ";
	double d;
	cin >> d;
	return d;
}

// d) e)
void convertNOKtoEUR() { // Void as the task asked for a print-out, not a return value
	const double rate = 1.0 / 9.64; // Current NOK to EUR exchange rate
	
	double NOK = 0;
	while (true) {
		cout << "Please enter a non-negative amount of NOK." << endl;
		NOK = inputDouble(); // inputDouble used as the # of NOK might not be a whole number
		if (NOK >= 0) {
			break;
		}
	}
	printf("%.2f NOK is %.2f EUR. \n", NOK, NOK * rate);
}

void solveQuadraticEquation();

// Task 3
// a) and task 4 d)
void pickFunction() {
	cout << "Pick function:" << endl;
	cout << "0) Quit" << endl;
	cout << "1) Sum two numbers" << endl;
	cout << "2) Sum several numbers" << endl;
	cout << "3) Convert from NOK to EUR" << endl;
	cout << "4) Solve quadratic equation" << endl;
	cout << "Enter choice (0-4): ";
	int i;
	cin >> i;
	switch (i) {
	default:
	case 0:
		break;
	case 1:
		inputIntegersAndPrintSum();
		break;
	case 2:
		inputIntegersUsingLoopAndPrintSum();
	case 3:
		convertNOKtoEUR();
	case 4:
		solveQuadraticEquation();
	}
}

// b)
void printMultiplicationTable() {
	int x = inputInteger();
	int y = inputInteger();
	for (int i = 1; i <= x; i++) {
		for (int j = 1; j <= y; j++) {
			cout << i * j << "\t";
		}
		cout << endl;
	}
}

// Task 4
// a)
double discriminant(double a, double b, double c) {
	return b * b - 4 * a * c;
}

// b)
void printRealRoots(double a, double b, double c) {
	double disc = discriminant(a, b, c);
	if (disc < 0) {
		cout << "No solution." << endl;
	}
	else if (disc == 0) {
		double x = (-b + sqrt(b * b - 4 * a * c)) / (2 * a);
		cout << "x is " << x << endl;
	}
	else {
		double x1 = (-b + sqrt(b * b - 4 * a * c)) / (2 * a);
		double x2 = (-b - sqrt(b * b - 4 * a * c)) / (2 * a);
		cout << "x1 is " << x1 << " and x2 is " << x2 << endl;
	}
}

// c)
void solveQuadraticEquation() {
	double a, b, c;
	cout << "Enter a: ";
	cin >> a;
	cout << "Enter b: ";
	cin >> b;
	cout << "Enter c: ";
	cin >> c;
	printRealRoots(a, b, c);
}

// Task 5
// a) b)
void calculateLoanPayments() {
	const int numberOfYears = 10;
	int amount, interest;
	cout << "Enter loan amount: ";
	cin >> amount;
	cout << "Enter interest rate in percent: ";
	cin >> interest;
	int basePayment = amount / numberOfYears;
	int payment;

	cout << "Year\tPayment\tRemaining" << endl;
	for (int i = 1; i <= numberOfYears; i++) {
		payment = basePayment + interest * amount / 100;
		amount -= basePayment;
		cout << i << "\t" << payment << "\t" << amount << endl;
	}
}

int main() {
	/*
	inputAndPrintInteger();

	int i = inputInteger();
	std::cout << "You wrote: " << i << endl;

	inputIntegersAndPrintSum();

	for (int i = 10; i < 15; i++) {
		if (isOdd(i)) {
			std::cout << i << " is odd." << std::endl;
		}
		else {
			std::cout << i << " is even." << std::endl;
		}
	}

	printHumanReadableTime(10000);

	inputIntegersUsingLoopAndPrintSum();

	double d = inputDouble();
	std::cout << "You wrote: " << d << endl;

	convertNOKtoEUR();

	printMultiplicationTable();
	

	int i = discriminant(10, 5, 17);
	std::cout << "Discriminant: " << i << endl;

	printRealRoots(10, 50, 2);
	printRealRoots(10, 5, 2);
	printRealRoots(2, 4, 2);

	solveQuadraticEquation();

	pickFunction();
	*/

	calculateLoanPayments();
}