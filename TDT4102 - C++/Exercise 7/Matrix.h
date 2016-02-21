#pragma once

#include "stdafx.h"

class Matrix {
	double **matrix;
	int width, height;

public:
	Matrix();
	Matrix(unsigned int nRows, unsigned int nColumns);
	explicit Matrix(unsigned int nRows);
	~Matrix();

	double get(unsigned int row, unsigned int col) const;
	int getHeight() const;
	int getWidth() const;
	bool isValid() const;
};

std::ostream &operator<<(std::ostream &os, const Matrix &matrix);