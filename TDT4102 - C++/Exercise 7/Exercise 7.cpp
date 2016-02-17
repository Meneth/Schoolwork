// Exercise 7.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using namespace std;

void fillInFibonacciNumbers(int result[], int length) {
	for (int i = 0; i < length; i++) {
		if (i == 0 || i == 1)
			result[i] = i;
		else
			result[i] = result[i - 1] + result[i - 2];
	}
}

void printArray(int arr[], int length) {
	for (int i = 0; i < length; i++)
		cout << arr[i] << endl;
}

void createFibonacci() {
	int length;
	cout << "Enter fibonacci list length: ";
	cin >> length;

	int *arr = new int[length] {};
	fillInFibonacciNumbers(arr, length);
	printArray(arr, length);

	delete arr;
	arr = nullptr;
}

int main() {
	//createFibonacci();

	Matrix matrix;
	cout << matrix;
	matrix = Matrix(5);
	cout << matrix;
	matrix = Matrix(3, 2);
	cout << matrix;
	matrix.set(1, 1, 5);
	cout << matrix;
	cout << matrix.get(1, 0) << endl;
    return 0;
}

