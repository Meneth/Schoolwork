#include "stdafx.h"

Vector2::Vector2() {
	vector[0] = 0;
	vector[1] = 0;
}

Vector2::Vector2(double x, double y) {
	vector[0] = x;
	vector[1] = y;
}

double Vector2::get(int pos) const {
	return vector[pos];
}

void Vector2::set(int pos, double value) {
	vector[pos] = value;
}

double Vector2::dot(const Vector2 &rhs) const {
	return get(0) * rhs.get(0) + get(1) * rhs.get(1);
}

double Vector2::lengthSquared() const {
	return get(0) * get(0) + get(1) * get(1);
}

double Vector2::length() const {
	return std::sqrt(lengthSquared());
}

Vector2 &Vector2::operator+=(const Vector2 &other) {
	vector[0] += other.get(0);
	vector[1] += other.get(1);
	return *this;
}

Vector2 &Vector2::operator-=(const Vector2 &other) {
	vector[0] -= other.get(0);
	vector[1] -= other.get(1);
	return *this;
}

Vector2 Vector2::operator-() {
	Vector2 inversion = Vector2(-get(0), -get(1));
	return inversion;
}

std::ostream &operator<<(std::ostream &os, const Vector2 &vector) {
	os << "[ " << vector.get(0) << ", " << vector.get(1) << " ]" << std::endl;
	return os;
}

Vector2 operator+(Vector2 lhs, const Vector2 &rhs) {
	return lhs += rhs;
}

Vector2 operator-(Vector2 lhs, const Vector2 &rhs) {
	return lhs -= rhs;
}

double operator*(const Vector2 &lhs, const Vector2 &rhs) {
	return lhs.dot(rhs);
}