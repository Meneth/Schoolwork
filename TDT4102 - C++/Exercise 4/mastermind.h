#pragma once

void playMastermind();
int checkCharactersAndPosition(char *guess, char *code, int length);
int checkCharacters(char *guess, char *code, char min, char max);
void printHistory(char *guesses, int attemptsMade, char *code, int size, char min, char max);