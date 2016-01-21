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