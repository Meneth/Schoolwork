#include "stdafx.h"

Matrix::Matrix() {
	matrix = nullptr;
}

void Matrix::initialize(unsigned int nRows, unsigned int nColumns) {
	width = nColumns;
	height = nRows;
	matrix = new double*[nRows] {};
	for (int i = 0; i < nRows; i++) {
		matrix[i] = new double[nColumns];
		for (int j = 0; j < nColumns; j++)
			matrix[i][j] = 0;
	}
}

Matrix::Matrix(unsigned int nRows, unsigned int nColumns) {
	initialize(nRows, nColumns);
}

Matrix::Matrix(unsigned int nRows) {
	initialize(nRows, nRows);
	for (int i = 0; i < nRows; i++) {
		matrix[i][i] = 1;
	}
}

Matrix::~Matrix() {
	if (isValid()) {
		for (int i = 0; i < height; i++) {
			delete[] matrix[i];
			matrix[i] = nullptr;
		}
		delete[] matrix;
		matrix = nullptr;
	}
}

Matrix &Matrix::operator=(Matrix rhs) {
	std::swap(matrix, rhs.matrix);
	std::swap(height, rhs.height);
	std::swap(width, rhs.width);
	return *this;
}

Matrix::Matrix(const Matrix &other) {
	if (other.isValid()) {
		initialize(other.height, other.width);
		for (int i = 0; i < other.height; i++) {
			for (int j = 0; j < other.width; j++)
				matrix[i][j] = other.matrix[i][j];
		}
	} else {
		matrix = nullptr;
	}
}

double Matrix::get(unsigned int row, unsigned int col) const {
	return matrix[row][col];
}

void Matrix::set(unsigned int row, unsigned int col, double value) {
	matrix[row][col] = value;
}

int Matrix::getHeight() const {
	return height;
}

int Matrix::getWidth() const {
	return width;
}

bool Matrix::isValid() const {
	return matrix != nullptr;
}

Matrix &Matrix::operator+=(const Matrix &other) {
	if (isValid() && height == other.height && width == other.width) {
		for (int i = 0; i < other.height; i++) {
			for (int j = 0; j < other.width; j++)
				matrix[i][j] += other.matrix[i][j];
		}
	} else {
		// Invalidate matrix
		matrix = nullptr;
	}
	return *this;
}

Matrix &Matrix::operator-=(const Matrix &other) {
	if (isValid() && height == other.height && width == other.width) {
		for (int i = 0; i < other.height; i++) {
			for (int j = 0; j < other.width; j++)
				matrix[i][j] -= other.matrix[i][j];
		}
	} else {
		// Invalidate matrix
		delete this;
	}
	return *this;
}

Matrix &Matrix::operator*=(const Matrix &other) {
	*this = *this * other;
	return *this;
}

Matrix Matrix::operator-() {
	return Matrix();
}

std::ostream & operator<<(std::ostream &os, const Matrix &matrix) {
	if (!matrix.isValid())
		os << "Invalid matrix" << std::endl;
	else {
		for (int i = 0; i < matrix.getHeight(); i++) {
			for (int j = 0; j < matrix.getWidth(); j++)
				os << matrix.get(i, j) << "\t";
			os << std::endl;
		}
	}
	return os;
}

bool operator==(const Matrix &lhs, const Matrix &rhs) {
	if (!lhs.isValid() || !rhs.isValid())
		return false; // Invalid matrices are not equal to anything
	else if (lhs.getHeight() != rhs.getHeight() || lhs.getWidth() != rhs.getWidth())
		return false;
	for (int i = 0; i < lhs.getHeight(); i++)
		for (int j = 0; j < lhs.getWidth(); j++)
			if (lhs.get(i, j) != rhs.get(i, j))
				return false;
	return true;
}

bool operator!=(const Matrix &lhs, const Matrix &rhs) {
	return !(lhs == rhs);
}

Matrix operator+(Matrix lhs, const Matrix &rhs) {
	lhs += rhs;
	return lhs;
}

Matrix operator-(Matrix lhs, const Matrix &rhs) {
	lhs -= rhs;
	return lhs;
}

Matrix operator*(const Matrix &lhs, const Matrix &rhs) {
	if (lhs.isValid() && lhs.getWidth() == rhs.getHeight()) {
		Matrix result(lhs.getHeight(), rhs.getWidth());
		for (int i = 0; i < result.getHeight(); i++) {
			for (int j = 0; j < result.getWidth(); j++) {
				double res = 0;
				for (int k = 0; k < lhs.getWidth(); k++)
						res += lhs.get(i, k) * rhs.get(k, j);
				result.set(i, j, res);
			}
		}
		return result;
	} else {
		// Invalid matrix
		return Matrix();
	}
}
