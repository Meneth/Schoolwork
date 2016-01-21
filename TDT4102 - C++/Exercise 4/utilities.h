#pragma once

int randomWithLimits(int lower, int upper);
int modernRandomWithLimits(int lower, int upper);
void incrementByValueNumTimes(int *startValue, int increment, int numTimes);
void swapNumbers(int *a, int *b);
void printArray(int *array, int length);
void randomizeArray(int *array, int length);
void sortArray(int *array, int length);
double medianOfArray(int *array, int length);
void randomizeCString(char *array, int length, char min, char max);
void readInputToCString(char *array, int length, char min, char max);
int countOccurencesOfCharacter(char *array, int length, char c);