#include "stdafx.h"
#include "Line.h"

void Line::draw(Image &image) {
	if (start.x > end.x)
		std::swap(start, end);
	double dy = (double) (end.y - start.y) / (double) (end.x - start.x);
	
	for (int i = start.x; i <= end.x; i++) {
		image.setColor(i, start.y + dy * (i - start.x), getColor());
	}

	if (start.y > end.y)
		std::swap(start, end);
	double dx = (double)(end.x - start.x) / (double)(end.y - start.y);
	for (int i = start.y; i <= end.y; i++) {
		image.setColor(start.x + dx * (i - start.y), i, getColor());
	}
}

Line::Line(Pos start, Pos end, Color color) : Shape(color) {
	this->start = start;
	this->end = end;
}
