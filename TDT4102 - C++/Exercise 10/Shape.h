#pragma once

#include "Image.h"

class Shape {
	Color color;

public:
	Shape(Color color);
	Color getColor() const;

	virtual void draw(Image image) = 0;
};