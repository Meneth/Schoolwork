#pragma once
#include "Vector2.h"

class Matrix2x2 {
	double arr[2][2];

public:
	Matrix2x2();
	Matrix2x2(double a1, double a2, double b1, double b2);
	double get(int row, int column) const;
	void set(int row, int column, double value);
	
	double det() const;
	Matrix2x2 inverse() const;

	Matrix2x2 &operator+=(const Matrix2x2 &other);
	Matrix2x2 &operator-=(const Matrix2x2 &other);
	Matrix2x2 &operator*=(const Matrix2x2 &other);
};

std::ostream &operator<<(std::ostream &os, const Matrix2x2 &matrix);
bool operator==(const Matrix2x2 &lhs, const Matrix2x2 &rhs);
bool operator!=(const Matrix2x2 &lhs, const Matrix2x2 &rhs);
Matrix2x2 operator+(Matrix2x2 lhs, const Matrix2x2 &rhs);
Matrix2x2 operator-(Matrix2x2 lhs, const Matrix2x2 &rhs);
Matrix2x2 operator*(const Matrix2x2 &lhs, const Matrix2x2 &rhs);
Vector2 operator*(const Matrix2x2 &lhs, const Vector2 &rhs);