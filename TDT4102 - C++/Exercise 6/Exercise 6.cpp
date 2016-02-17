// Exercise 6.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using namespace std;

void solveEquations() {
	float a, b, c, d, p, q;
	cout << "ax + by = p" << endl;
	cout << "Enter a: ";
	cin >> a;
	cout << "Enter b: ";
	cin >> b;
	cout << "Enter p: ";
	cin >> p;

	cout << "cx + dy = q" << endl;
	cout << "Enter c: ";
	cin >> c;
	cout << "Enter d: ";
	cin >> d;
	cout << "Enter q: ";
	cin >> q;

	Matrix2x2 matrix = Matrix2x2(a, b, c, d);
	Vector2 vector = Vector2(p, q);

	try {
		Vector2 answer = matrix.inverse() * vector;
		cout << "x = " << answer.get(0) << endl;
		cout << "y = " << answer.get(1) << endl;
	}
	catch (std::invalid_argument e) {
		cout << "No solution possible." << endl;
	}
	
}

int main()
{
	/*
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
	*/
	solveEquations();
    return 0;
}