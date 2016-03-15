#pragma once

#include "stdafx.h"

class Vector : public Matrix {

public:
	Vector();
	explicit Vector(unsigned int nRows);
	Vector(const Matrix &other);
	void set(unsigned int row, double value);
	double get(unsigned int row) const;
	double dot(const Vector &rhs) const;
	double lengthSquared() const;
	double length() const;
};