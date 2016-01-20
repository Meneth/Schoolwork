// Exercise 3.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using namespace std;

bool verifyAnswer(double expectedResult, double result, double marginOfError) {
	double error = pow(expectedResult - result, 2.0);
	return error < marginOfError;
}

int main()
{
	// Task 3
	cout << verifyAnswer(-9.81, acclY(), 0.01) << endl;
	cout << verifyAnswer(25.0, velY(25.0, 0), 0.01) << endl;
	cout << verifyAnswer(0.475, velY(25.0, 2.5), 0.01) << endl;
	cout << verifyAnswer(-24.05, velY(25.0, 5.0), 0.01) << endl;
	cout << verifyAnswer(0.0, posX(50.0, 0), 0.01) << endl;
	cout << verifyAnswer(125.0, posX(50.0, 2.5), 0.01) << endl;
	cout << verifyAnswer(250.0, posX(50.0, 5.0), 0.01) << endl;
	cout << verifyAnswer(0.0, posY(25.0, 0), 0.01) << endl;
	cout << verifyAnswer(31.84, posY(25.0, 2.5), 0.01) << endl;
	cout << verifyAnswer(2.375, posY(25.0, 5.0), 0.01) << endl;

	double theta = 0;
	double absVelocity = 0;
	//getUserInput(&theta, &absVelocity);
	cout << theta << " - " << absVelocity << endl;

	double pi = acos(-1);
	cout << verifyAnswer(10.0, getVelocityX(0.0, 10.0), 0.01) << endl;
	cout << verifyAnswer(0.0, getVelocityX(pi / 2, 10.0), 0.01) << endl;
	cout << verifyAnswer(0.0, getVelocityY(0.0, 10.0), 0.01) << endl;
	cout << verifyAnswer(10.0, getVelocityY(pi / 2, 10.0), 0.01) << endl;
	cout << verifyAnswer(sqrt(50.0), getVelocityY(pi / 4, 10.0), 0.01) << endl;
	
	cout << verifyAnswer(20.0, getDistanceTraveled(10.0, 9.81), 0.01) << endl;
	cout << verifyAnswer(10.0, targetPractice(10.0, 10.0, 9.81), 0.01) << endl;

	double velocityX = 0.0;
	double velocityY = 0.0;
	getVelocityVector(0.0, 10.0, &velocityX, &velocityY);
	cout << velocityX << " - " << velocityY << endl;

    return 0;
}