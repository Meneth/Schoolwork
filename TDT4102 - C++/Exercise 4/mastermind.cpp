#include "stdafx.h"

using namespace std;

// Task 5

void playMastermind() {
	const int SIZE = 4;
	const int LETTERS = 6;
	const char min = 'A';
	const char max = 'A' + LETTERS - 1;
	const int tries = 10;
	char code[SIZE + 1];
	char guess[SIZE + 1];

	randomizeCString(code, SIZE, min, max);
	int i = 0;
	do {
		cout << tries - i << " tries left." << endl;
		readInputToCString(guess, SIZE, min, max);

		cout << "Your guess: " << guess << endl;
		cout << checkCharactersAndPosition(guess, code, SIZE) << " correct letter(s) in the correct position." << endl;
		cout << checkCharacters(guess, code, min, max) << " correct letter(s)." << endl;
		i++;

	} while (checkCharactersAndPosition(guess, code, SIZE) < SIZE && i < tries);
	cout << "Contratulations, you won!" << endl;
	cout << "Do you want to play another round? [y/n] ";
	char answer;
	cin >> answer;
	if (tolower(answer) == 'y') {
		playMastermind();
	}
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