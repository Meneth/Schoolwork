#include "stdafx.h"

using namespace std;

void CourseCatalog::addCourse(std::string courseCode, string courseName) {
	courses[courseCode] = courseName;
}

string CourseCatalog::getCourseName(string courseCode) {
	return courses[courseCode];
}

string CourseCatalog::toString() const {
	string s = "";
	for (map<string, string>::const_iterator it = courses.begin(); it != courses.end(); ++it) {
		s += it->first;
		s += " - ";
		s += it->second;
		s += "\n";
	}
	return s;
}

void CourseCatalog::writeToFile(std::string fileName) const {
	ofstream out;
	out.open(fileName);
	out << toString();
	out.close();
}

void CourseCatalog::readFromFile(std::string fileName) {
	ifstream in;
	in.open(fileName);

	string line;
	int i;
	while (getline(in, line)) {
		i = line.find(" - ");
		addCourse(line.substr(0, i), line.substr(i + 3));
	}

	in.close();
}


std::ostream &operator<<(std::ostream &os, const CourseCatalog &course) {
	os << course.toString();
	return os;
}
