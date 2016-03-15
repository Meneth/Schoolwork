#include "stdafx.h"

#include "Image.h"

/*****************************************************
 * You _should_ change the functions in this file.   *
 * The following functions are provided so that      *
 * the code will compile, note however that the      *
 * program will not run before you have implemented  *
 * all the functions in this file.                   *
 *****************************************************/

Image::Image( unsigned int width, unsigned int height ) {
	this-> width = width;
	this-> height = height;
	data = new unsigned char[width * height * 3];
	for (int i = 0; i < width * height * 3; i++)
		data[i] = 0;
}

Image::~Image()  {
	delete[] data;
	data = nullptr;
}

unsigned int Image::getWidth() const {
   return width;
}
unsigned int Image::getHeight() const  {
   return height;
}

unsigned int posToIndex(unsigned int x, unsigned int y, unsigned int width) {
	return 3 * (x + y * width);
}

const unsigned char * Image::getScanLine(unsigned int line) const  {
   return data + line * 3 * width;
}
unsigned char * Image::getScanLine(unsigned int line) {
	return data + line * 3 * width;
}

Color Image::getColor( unsigned int x, unsigned int y ) const {
	unsigned int pos = posToIndex(x, y, width);
	return Color(data[pos], data[pos + 1], data[pos + 2]);
}
void Image::setColor( unsigned int x, unsigned int y, const Color &color ) {
	if (x >= width || y >= height)
		throw std::invalid_argument("Position out of bounds!");
	unsigned int pos = posToIndex(x, y, width);
	data[pos] = color.red;
	data[pos + 1] = color.green;
	data[pos + 2] = color.blue;
}

void Image::fill( const Color &color ) {
	for (int i = 0; i < 3 * width * height; i++) {
		switch (i % 3) {
		case 0:
			data[i] = color.red;
			break;
		case 1:
			data[i] = color.green;
			break;
		case 2:
			data[i] = color.blue;
			break;
		}
	}
}
