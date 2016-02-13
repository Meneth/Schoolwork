#pragma once

class Vector2 {
	double vector[2];

public:
	Vector2();
	Vector2(double x, double y);
	double get(int pos) const;
	void set(int pos, double value);
	double dot(const Vector2 &rhs) const;
	double lengthSquared() const;
	double length() const;

	Vector2 &operator+=(const Vector2 &other);
	Vector2 &operator-=(const Vector2 &other);
	Vector2 operator-();
};

std::ostream &operator<<(std::ostream &os, const Vector2 &vector);
Vector2 operator+(Vector2 lhs, const Vector2 &rhs);
Vector2 operator-(Vector2 lhs, const Vector2 &rhs);
double operator*(const Vector2 &lhs, const Vector2 &rhs);