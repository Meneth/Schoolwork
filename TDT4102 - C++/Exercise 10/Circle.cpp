#include "stdafx.h"
#include "Circle.h"

void Circle::draw(Image & image) {
	for (int i = center.x - radius; i <= center.x + radius; i++) {
		// y = +/- sqrt(r^2 - (x - x0)^2) + y0
		int dy = sqrt(pow(radius, 2) - pow(i - center.x, 2)) + 0.5; // Rounding up
		image.setColor(i, center.y + dy, getColor());
		image.setColor(i, center.y - dy, getColor());
	}

	for (int i = center.y - radius; i <= center.y + radius; i++) {
		// x = +/- sqrt(r^2 - (y - y0)^2) + x0
		int dx = sqrt(pow(radius, 2) - pow(i - center.y, 2)) + 0.5; // Rounding up
		image.setColor(center.x + dx, i, getColor());
		image.setColor(center.x - dx, i, getColor());
	}
}

Circle::Circle(Pos center, int radius, Color color) : Shape(color) {
	this->center = center;
	this->radius = radius;
}
