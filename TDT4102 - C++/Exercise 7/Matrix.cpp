#include "stdafx.h"

Matrix::Matrix() {
	matrix = nullptr;
}

Matrix::Matrix(unsigned int nRows, unsigned int nColumns) {
	width = nColumns;
	height = nRows;
	matrix = new double*[nRows] {};
	for (int i = 0; i < nRows; i++) {
		matrix[i] = new double[nColumns];
		for (int j = 0; j < nColumns; j++)
			matrix[i][j] = 0;
	}
}

Matrix::Matrix(unsigned int nRows) : Matrix(nRows, nRows) {
	for (int i = 0; i < nRows; i++) {
		matrix[i][i] = 1;
	}
}

Matrix::~Matrix() {
	if (isValid()) {
		for (int i = 0; i < height; i++)
			delete[] matrix[i];
		delete[] matrix;
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