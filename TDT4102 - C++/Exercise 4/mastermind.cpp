#include "stdafx.h"

using namespace std;

// Task 5

void playMastermind() {
	const int SIZE = 4;
	const int LETTERS = 6;
	const char MIN = 'A';
	const char MAX = 'A' + LETTERS - 1;
	const int TRIES = 10;
	char code[SIZE + 1];
	char guess[SIZE + 1];

	char guesses[TRIES][SIZE + 1];

	randomizeCString(code, SIZE, MIN, MAX);

	cout << "x means a correct character in the correct position. o means a correct character in the wrong position." << endl;

	int i = 0;
	do {
		cout << TRIES - i << " tries left." << endl;
		readInputToCString(guess, SIZE, MIN, MAX);

		for (int j = 0; j < SIZE; j++) {
			guesses[i][j] = guess[j];
		}
		guesses[i][SIZE] = '\0';

		printHistory(*guesses, i, code, SIZE + 1, MIN, MAX);

		i++;

	} while (checkCharactersAndPosition(guess, code, SIZE) < SIZE && i < TRIES);
	cout << "Contratulations, you won!" << endl;
	cout << "Do you want to play another round? [y/n] ";
	char answer;
	cin >> answer;
	if (tolower(answer) == 'y') {
		playMastermind();
	}
}

void printHistory(char *guesses, int attemptsMade, char *code, int size, char min, char max) {
	cout << endl << endl;

	cout << "Guess\tStatus" << endl;
	char *guess;
	for (int j = 0; j <= attemptsMade; j++) {
		guess = &guesses[j * size];
		cout << guess;
		cout << "\t";
		
		for (int k = 0; k < checkCharactersAndPosition(guess, code, size); k++) {
			cout << "x";
		}
		for (int k = 0; k < checkCharacters(guess, code, min, max) - checkCharactersAndPosition(guess, code, size); k++) {
			cout << "o";
		}
		cout << endl;
	}
	cout << endl;
}

int checkCharactersAndPosition(char *guess, char *code, int length) {
	int correct = 0;
	for (int i = 0; i < length; i++) {
		if (guess[i] == code[i]) {
			correct++;
		}
	}
	return correct;
}

int checkCharacters(char *guess, char *code, char min, char max) {
	int correct = 0;
	for (; min <= max; min++) {
		correct += std::min(countOccurencesOfCharacter(guess, min), countOccurencesOfCharacter(code, min));
	}
	return correct;
}