#include "stdafx.h"

Vector::Vector() : Matrix() {
}

Vector::Vector(unsigned int nRows) : Matrix(nRows, 1) {
}

Vector::Vector(const Matrix &other) : Matrix(other) {
	if (getColumns() != 1) // Vectors only have a single column
		invalidate();
}

void Vector::set(unsigned int row, double value) {
	Matrix::set(row, 0, value);
}

double Vector::get(unsigned int row) const {
	return Matrix::get(row, 0);
}

double Vector::dot(const Vector &rhs) const {
	if (getRows() != rhs.getRows() || !isValid())
		return nan("");
	double product = 0;
	for (int i = 0; i < getRows(); i++)
		product += get(i) * rhs.get(i);
	return product;
}

double Vector::lengthSquared() const {
	return dot(*this);
}

double Vector::length() const {
	return sqrt(lengthSquared());
}
