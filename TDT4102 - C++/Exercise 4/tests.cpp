#include "stdafx.h"

using namespace std;

void testPart1() {
	int v0 = 5;
	int increment = 2;
	int iterations = 10;
	incrementByValueNumTimes(&v0, increment, iterations);
	cout << "v0: " << v0 // v0 is still 5 since it was passed by value, not reference
		<< " increment: " << increment
		<< " iterations: " << iterations << endl;

	int a = 10;
	int b = 20;
	swapNumbers(&a, &b);
	cout << "a: " << a
		<< " b: " << b << endl;
}

void testPart2() {
	int table[20];


	randomizeArray(table, 20);
	printArray(table, 20);
	swapNumbers(table, table + 1);
	printArray(table, 20);
	sortArray(table, 20);
	printArray(table, 20);
	cout << medianOfArray(table, 20) << endl;
}

void testPart3() {
	char grades[9];
	randomizeCString(grades, 8, 'A', 'F');
	cout << grades << endl;
	//readInputToCString(table, 8, 'A', 'F');
	//cout << table << endl;

	int table[6];
	for (char c = 'A'; c <= 'F'; c++) {
		table[c - 'A'] = countOccurencesOfCharacter(grades, c);
	}
	int count = 0;
	int sum = 0;
	for (int i = 0; i < 6; i++) {
		count += table[i];
		sum += table[i] * (i + 1);
	}
	printArray(table, 6);
	cout << sum << " - " << count << endl;
	cout << "GPA is " << (double) sum / count << endl;

	char grades2[21];
	randomizeCString(grades2, 20, 'A', 'E');
	cout << grades2 << endl;
	//readInputToCString(table, 8, 'A', 'F');
	//cout << table << endl;

	for (char c = 'A'; c <= 'F'; c++) {
		table[c - 'A'] = countOccurencesOfCharacter(grades2, c);
	}
	count = 0;
	sum = 0;
	for (int i = 0; i < 6; i++) {
		count += table[i];
		sum += table[i] * (i + 1);
	}
	printArray(table, 6);
	cout << sum << " - " << count << endl;
	cout << "GPA is " << (double)sum / count << endl;


}