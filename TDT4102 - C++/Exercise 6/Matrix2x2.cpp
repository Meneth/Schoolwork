#include "stdafx.h"

Matrix2x2::Matrix2x2() {
	arr[0][0] = 1;
	arr[0][1] = 0;
	arr[1][0] = 0;
	arr[1][1] = 1;
}

Matrix2x2::Matrix2x2(double a1, double a2, double b1, double b2) {
	arr[0][0] = a1;
	arr[0][1] = a2;
	arr[1][0] = b1;
	arr[1][1] = b2;
}

double Matrix2x2::get(int row, int column) const {
	return arr[row][column];
}

void Matrix2x2::set(int row, int column, double value) {
	arr[row][column] = value;
}

double Matrix2x2::det() const {
	return get(0, 0) * get(1, 1) + get(0, 1) * get(1, 0);
}

Matrix2x2 Matrix2x2::inverse() const {
	double d = det();
	if (d == 0)
		throw std::invalid_argument("Determinant is 0, so no inverse exists.");
	double factor = 1 / d;
	return Matrix2x2(get(1, 1) * factor, -get(0, 1) * factor,
		            -get(1, 0) * factor, get(0, 0) * factor);
}

Matrix2x2 &Matrix2x2::operator+=(const Matrix2x2 &other) {
	arr[0][0] += other.get(0, 0);
	arr[0][1] += other.get(0, 1);
	arr[1][0] += other.get(1, 0);
	arr[1][1] += other.get(1, 1);
	return *this;
}

Matrix2x2 &Matrix2x2::operator-=(const Matrix2x2 &other) {
	arr[0][0] -= other.get(0, 0);
	arr[0][1] -= other.get(0, 1);
	arr[1][0] -= other.get(1, 0);
	arr[1][1] -= other.get(1, 1);
	return *this;
}

Matrix2x2 &Matrix2x2::operator*=(const Matrix2x2 &other) {
	*this = *this * other; // Necessary as the LHS doesn't get changed
	return *this;
}

bool operator==(const Matrix2x2 &lhs, const Matrix2x2 &rhs) {
	return lhs.get(0, 0) == rhs.get(0, 0) && lhs.get(0, 1) == rhs.get(0, 1)
		&& lhs.get(1, 0) == rhs.get(1, 0) && lhs.get(1, 1) == rhs.get(1, 1);
}

bool operator!=(const Matrix2x2 &lhs, const Matrix2x2 &rhs) {
	return !(lhs == rhs);
}

Matrix2x2 operator+(Matrix2x2 lhs, const Matrix2x2 &rhs) {
	return lhs += rhs;
}

Matrix2x2 operator-(Matrix2x2 lhs, const Matrix2x2 &rhs) {
	return lhs -= rhs;
}

Matrix2x2 operator*(const Matrix2x2 &lhs, const Matrix2x2 &rhs) {
	Matrix2x2 matrix = Matrix2x2();
	matrix.set(0, 0, lhs.get(0, 0) * rhs.get(0, 0) + lhs.get(0, 1) * rhs.get(1, 0));
	matrix.set(0, 1, lhs.get(0, 0) * rhs.get(0, 1) + lhs.get(0, 1) * rhs.get(1, 1));
	matrix.set(1, 0, lhs.get(1, 0) * rhs.get(0, 0) + lhs.get(1, 1) * rhs.get(1, 0));
	matrix.set(1, 1, lhs.get(1, 0) * rhs.get(0, 1) + lhs.get(1, 1) * rhs.get(1, 1));
	return matrix;
}

std::ostream &operator<<(std::ostream &os, const Matrix2x2 &matrix) {
	os << matrix.get(0, 0) << "\t" << matrix.get(0, 1) << std::endl;
	os << matrix.get(1, 0) << "\t" << matrix.get(1, 1) << std::endl;
	return os;
}

Vector2 operator*(const Matrix2x2 &lhs, const Vector2 &rhs) {
	return Vector2(lhs.get(0, 0) * rhs.get(0) + lhs.get(0, 1) * rhs.get(1), 
		           lhs.get(1, 0) * rhs.get(0) + lhs.get(1, 1) * rhs.get(1));
}