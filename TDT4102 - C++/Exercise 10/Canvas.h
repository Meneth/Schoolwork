#pragma once

#include <vector>

class Shape;
class Image;

class Canvas {
	std::vector<Shape*> shapes;

public:
	void addShape(Shape* shape);
	void rasterizeTo(Image & image);
};