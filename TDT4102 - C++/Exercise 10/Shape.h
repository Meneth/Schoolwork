#pragma once

#include "Image.h"

struct Pos {
	int x, y;
};

class Shape {
	Color color;

public:
	Shape(Color color);
	Color getColor() const;

	virtual void draw(Image &image) = 0;
};