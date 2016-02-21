#pragma once

#include "stdafx.h"

class Matrix {
	double **matrix;
	int width, height;

public:
	Matrix();
	void initialize(unsigned int nRows, unsigned int nColumns);
	Matrix(unsigned int nRows, unsigned int nColumns);
	explicit Matrix(unsigned int nRows);
	~Matrix();
	Matrix &operator =(Matrix rhs);
	Matrix(Matrix &other);

	double get(unsigned int row, unsigned int col) const;	void set(unsigned int row, unsigned int col, double value);
	int getHeight() const;
	int getWidth() const;
	bool isValid() const;

	Matrix &operator+=(const Matrix &other);
	Matrix &operator-=(const Matrix &other);
	Matrix &operator*=(const Matrix &other);
	Matrix operator-();
};

std::ostream &operator<<(std::ostream &os, const Matrix &matrix);
bool operator==(const Matrix &lhs, const Matrix &rhs);
bool operator!=(const Matrix &lhs, const Matrix &rhs);
Matrix operator+(Matrix lhs, const Matrix &rhs);
Matrix operator-(Matrix lhs, const Matrix &rhs);
Matrix operator*(const Matrix &lhs, const Matrix &rhs);