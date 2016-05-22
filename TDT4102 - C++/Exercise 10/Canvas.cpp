#include "stdafx.h"
#include "Canvas.h"

#include "Image.h"
#include "Shape.h"

void Canvas::addShape(Shape * shape) {
	shapes.push_back(shape);
}

void Canvas::rasterizeTo(Image & image) {
	for (Shape* shape : shapes) {
		shape->draw(image);
	}
}
