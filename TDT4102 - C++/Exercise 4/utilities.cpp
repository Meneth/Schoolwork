#include "stdafx.h"

using namespace std;

int randomWithLimits(int lower, int upper) {
	return rand() % (upper - lower) + lower;
}

int modernRandomWithLimits(int lower, int upper) {
	random_device device;
	mt19937 generator(device());
	uniform_int_distribution<int> uniform(lower, upper);

	return uniform(generator);
}

void incrementByValueNumTimes(int *startValue, int increment, int numTimes) {
	for (int i = 0; i < numTimes; i++) {
		*startValue += increment;
	}
	return;
}

void swapNumbers(int *a, int *b) {
	int temp = *a;
	*a = *b;
	*b = temp;
}

void printArray(int *array, int length) {
	for (int i = 0; i < length; i++) {
		cout << array[i] << " ";
	}
	cout << endl;
}

void randomizeArray(int *array, int length) {
	for (int i = 0; i < length; i++) {
		array[i] = modernRandomWithLimits(0, 100);
	}
}

// QuickSort
void sortArray(int *array, int length) {
	if (length <= 1) {
		return; // Sorted by definition
	}
	int pivot = array[0];
	int divider = length;
	for (int i = 1; i < divider; i++) {
		if (array[i] > pivot) {
			divider--;
			swapNumbers(array + i, array + divider);
			i--;
		}
	}

	// Ensure the pivot itself is left out of the recursive solving
	swapNumbers(array, array + divider - 1);

	sortArray(array, divider - 1);
	sortArray(array + divider, length - divider);
}

double medianOfArray(int *array, int length) {
	if (length % 2 == 0) {
		// Cast to double, as the average of the two numbers might be something.5
		return (double) (array[length / 2] + array[length / 2 + 1]) / 2;
	}
	else {
		return array[length/2];
	}
}

void randomizeCString(char *array, int length, char min, char max) {
	for (int i = 0; i < length ;i++) {
		array[i] = modernRandomWithLimits((int) min, (int) max);
	}
	array[length] = '\0';
}

char getChar(char min, char max) {
	char c;
	for (;;) {
		cout << "Please enter a character between " << min << " and " << max << ": ";
		cin >> c;
		c = toupper(c);
		if (c >= min && c <= max) {
			return c;
		}
	}
}

void readInputToCString(char *array, int length, char min, char max) {
	for (int i = 0; i < length ; i++) {
		cout << "Position " << i + 1 << " of " << length << endl;
		array[i] = getChar(min, max);
	}
	array[length] = '\0';
}

int countOccurencesOfCharacter(char *array, char c) {
	int count = 0;
	for (int i = 0; ; i++) {
		if (array[i] == '\0') {
			break;
		}
		else if (array[i] == c) {
			count++;
		}
	}
	return count;
}