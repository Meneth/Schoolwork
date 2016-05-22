#include "stdafx.h"
#include "Rectangle.h"

void Rectangle::draw(Image & image) {
	if (start.x > end.x)
		std::swap(start, end);

	for (int i = start.x; i <= end.x; i++) {
		image.setColor(i, start.y, getColor());
		image.setColor(i, end.y, getColor());
	}

	if (start.y > end.y)
		std::swap(start, end);

	for (int i = start.y; i <= end.y; i++) {
		image.setColor(start.x, i, getColor());
		image.setColor(end.x, i, getColor());
	}
}

Rectangle::Rectangle(Pos start, Pos end, Color color) : Shape(color) {
	this->start = start;
	this->end = end;
}