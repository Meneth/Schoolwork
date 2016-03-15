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

const unsigned char * Image::getScanLine(unsigned int line) const  {
   /* Enter your code here */
   return nullptr;
}
unsigned char * Image::getScanLine(unsigned int line) {
   /* Enter your code here */
   return nullptr;
}

Color Image::getColor( unsigned int x, unsigned int y ) const {
   /* Enter your code here */
   return Color();
}
void Image::setColor( unsigned int x, unsigned int y, const Color &color ) {
   /* Enter your code here */
}

void Image::fill( const Color &color ) {
   /* Enter your code here */
}
