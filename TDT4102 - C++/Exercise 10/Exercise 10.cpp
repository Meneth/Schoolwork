// Exercise 10.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using namespace std;

int main() {
	Vector v(4);
	v.set(0, 5);
	cout << v << endl;

	Matrix m(4, 4, 2);

	Matrix z = m * v;

	cout << z << endl;

	Image i(400, 300);

	i.fill(Color(193, 84, 193));

	i.setColor(50, 60, Color(255, 0, 0));

	i.getColor(50, 50);

	saveImage(i, "test.bmp");

    return 0;
}

