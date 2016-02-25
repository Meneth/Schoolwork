#pragma once

class CourseCatalog {
	std::map<std::string, std::string> courses;

public:
	void addCourse(std::string courseCode, std::string courseName);
	std::string getCourseName(std::string courseCode);
	std::string toString() const;

	void writeToFile(std::string fileName) const;
	void readFromFile(std::string fileName);
};

std::ostream &operator<<(std::ostream &os, const CourseCatalog &course);