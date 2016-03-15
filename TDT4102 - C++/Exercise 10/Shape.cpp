#include "stdafx.h"
#include "Shape.h"

Shape::Shape(Color color) {
	this->color = color;
}

Color Shape::getColor() const {
	return color;
}
