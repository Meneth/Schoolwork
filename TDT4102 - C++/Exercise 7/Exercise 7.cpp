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
	Matrix matrix2(5);
	cout << matrix2 << endl;
	Matrix matrix3(3, 2);
	cout << matrix3 << endl;
	matrix3.set(1, 1, 5);
	cout << matrix3 << endl;
	cout << matrix3.get(1, 0) << endl;

	matrix = matrix2;
	cout << matrix << endl;

	Matrix matrix4(matrix);
	cout << matrix4 << endl;

	Matrix matrix5 = matrix2;
	cout << matrix5 << endl;

	matrix5 += matrix2;
	cout << matrix5 << endl;

	matrix5 -= matrix2;
	cout << matrix5 << endl;

	Matrix matrix6(2, 3);
	Matrix matrix7(3, 2);
	matrix6.set(0, 0, 1);
	matrix6.set(0, 1, 2);
	matrix6.set(0, 2, 3);
	matrix6.set(1, 0, 4);
	matrix6.set(1, 1, 5);
	matrix6.set(1, 2, 6);
	matrix7.set(0, 0, 7);
	matrix7.set(0, 1, 8);
	matrix7.set(1, 0, 9);
	matrix7.set(1, 1, 10);
	matrix7.set(2, 0, 11);
	matrix7.set(2, 1, 12);

	cout << (matrix6 *= matrix7) << endl;

    return 0;
}