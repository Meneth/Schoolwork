#pragma once

#include "Shape.h"

class Line : public Shape {
	Pos start, end;
public:
	void draw(Image &image);

	Line(Pos start, Pos end, Color color);
};