#pragma once

#include "Shape.h"

class Circle : public Shape {
	Pos center;
	int radius;

public:
	void draw(Image &image);

	Circle(Pos center, int radius, Color color);
};