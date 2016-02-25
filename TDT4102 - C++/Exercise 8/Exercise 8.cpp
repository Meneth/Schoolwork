// Exercise 8.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using namespace std;

void writeUserInput(string fileName) {
	ofstream file;
	file.open(fileName);

	cout << "Enter whatever text you'd like. Write 'quit' to exit." << endl;

	string s = "";
	cin >> s;
	while (s.compare("quit") != 0) {
		file << s << endl;
		cin >> s;
	}
	file.close();
}

void addLineNumbers(string source, string destination) {
	ofstream destFile;
	ifstream sourceFile;
	sourceFile.open(source);
	destFile.open(destination);

	string line;
	int i = 1;
	while (getline(sourceFile, line)) {
		destFile << i << " " << line << endl;
		i++;
	}
	destFile.close();
	sourceFile.close();
}

void countCharacterOccurences(char min, char max, string fileName) {
	int characters = max - min;
	int *occurences = new int[characters];

	for (int i = 0; i < characters; i++)
		occurences[i] = 0;

	ifstream file;
	file.open(fileName);
	char c;
	int unidentified = 0;
	int length = 0;

	while (file >> noskipws >> c) {
		int pos = c - min;
		if (pos >= 0 && pos < characters)
			occurences[pos]++;
		else
			unidentified++;
		length++;
	}
	file.close();

	cout << "Character statistics:" << endl;
	cout << "Total number of characters: " << length << endl;
	cout << "Unidentified characters: " << unidentified << endl;
	for (int i = 0; i < characters; i++) {
		cout << (char) (min + i) << ": " << occurences[i];
		if (i % 4 == 3)
			cout << endl;
		else
			cout << "\t";
	}
	cout << endl;
}

void wordStatistics(string fileName) {
	map<string, int> words;

	ifstream file;
	file.open(fileName);

	int wordCount = 0, charCount = 0, lineCount = 0, maxWordLength = 0;
	string longestWord;

	string line, word;
	stringstream stream;
	// TODO - longest word
	while (getline(file, line)) {
		stream = stringstream(line);
		lineCount++;
		while (stream >> word) {
			wordCount++;
			charCount += word.length();
			if (words.find(word) == words.end())
				words[word] = 1;
			else
				words[word]++;
			if (word.length() > maxWordLength) {
				maxWordLength = word.length();
				longestWord = word;
			}
		}
	}

	cout << "Text statistics:" << endl;
	cout << "Number of characters: " << charCount << endl;
	cout << "Number of words: " << wordCount << endl;
	cout << "Number of lines: " << lineCount << endl;
	cout << "Average word length: " << charCount / wordCount << endl;
	cout << "Longest word: " << longestWord << endl;
	for (map<string, int>::iterator it = words.begin(); it != words.end(); ++it) {
		cout << it->first << ": " << it->second << endl;
	}
	file.close();
}

void testCourseCatalog() {
	CourseCatalog cat;
	cat.addCourse("TDT4110", "Informasjonsteknologi grunnkurs");
	cat.addCourse("TDT4102", "Prosedyre- og objektorientert programmering");
	cat.addCourse("TMA4100", "Matematikk 1");

	cout << cat << endl;

	cat.addCourse("TDT4102", "C++");
	cout << cat << endl;

	cat.writeToFile("test3.txt");

	CourseCatalog cat2;
	cat2.readFromFile("test3.txt");
	cout << cat2 << endl;
}

int main() {
	//writeUserInput("test.txt");
	addLineNumbers("test.txt", "test2.txt");
	countCharacterOccurences(32, 127, "test2.txt");

	testCourseCatalog();

	wordStatistics("test3.txt");
    return 0;
}

