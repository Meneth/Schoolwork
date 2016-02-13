// Exercise 6.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using namespace std;

int main()
{
	Matrix2x2 matrix = Matrix2x2();
	cout << matrix << endl;

	Matrix2x2 matrix2 = Matrix2x2(1, 2, 3, 4);
	cout << matrix2 << endl;

	cout << matrix + matrix2 << endl;

	cout << (matrix == matrix2) << endl;

	Matrix2x2 a = Matrix2x2(1, 2, 3, 4);
	Matrix2x2 b = Matrix2x2(4, 3, 2, 1);
	Matrix2x2 c = Matrix2x2(1.0, 3.0, 1.5, 2.0);

	cout << (a += b) << endl;
	cout << (a -= b) << endl;

	cout << (a + b) << endl;
	cout << a << endl;

	Matrix2x2 d = a + b;
	d *= b;
	d = d - a + c;
	cout << d << endl;
    return 0;
}