#pragma once

#include "Shape.h"

class Rectangle : public Shape {
	Pos start, end;
public:
	void draw(Image &image);

	Rectangle(Pos start, Pos end, Color color);
};