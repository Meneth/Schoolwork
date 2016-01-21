// Task 2
#include "stdafx.h"

using namespace std;

double acclY() {
	const double acc = -9.81;
	return acc;
}

double velY(double initVelocity, double time) {
	return initVelocity + time * acclY();
}

double posX(double initVelocity, double time) {
	const double startPosition = 0;
	return startPosition + time * initVelocity;
}

double posY(double initVelocity, double time) {
	const double startPosition = 0;
	return startPosition + time * initVelocity + acclY() * time * time / 2;
}

void printTime(double time) {
	int hours = (int) time / 60 / 60;
	int minutes = (int) time % (60 * 60) / 60;
	int seconds = (int) time % 60;
	cout << hours << " hours, " << minutes << " minutes, and " << seconds << " seconds." << endl;
}

double flightTime(double initVelocity) {
	return -2 * initVelocity / acclY();
}

// Task 4

void getUserInput(double *theta, double *absVelocity) {
	cout << "Please enter the angle theta [0 - pi/2]: ";
	cin >> *theta;
	cout << "Please enter the absolute velocity: ";
	cin >> *absVelocity;
}

// Who asks someone to implement this without specifying the units for theta? Radians or degrees? I've assumed radians
double getVelocityX(double theta, double absVelocity) {
	return cos(theta) * absVelocity;
}

double getVelocityY(double theta, double absVelocity) {
	return sin(theta) * absVelocity;
}

void getVelocityVector(double theta, double absVelocity,
	double *velocityX, double *velocityY) {
	*velocityX = getVelocityX(theta, absVelocity);
	*velocityY = getVelocityY(theta, absVelocity);
}

// b)

double getDistanceTraveled(double velocityX, double velocityY) {
	return velocityX * flightTime(velocityY);
}

// c)

double targetPractice(double distanceToTarget,
	double velocityX,
	double velocityY) {
	return getDistanceTraveled(velocityX, velocityY) - distanceToTarget;
}

// Task 6
void playTargetPractice() {
	const int attempts = 10;
	int goalDistance = modernRandomWithLimits(100, 1000);
	double theta, absVelocity;
	double difference;
	for (int i = 0; i < attempts; i++) {
		cout << attempts - i << " attempts left." << endl;
		getUserInput(&theta, &absVelocity);
		difference = targetPractice(goalDistance, getVelocityX(theta, absVelocity), getVelocityY(theta, absVelocity));
		if (difference < 5 && difference > -5) {
			cout << "Congratulations, you hit the target!" << endl;
			return;
		}
		else if (difference < 0) {
			cout << "You undershot the target by " << difference * -1 << " meters!" << endl;
		}
		else {
			cout << "You overshot the target by " << difference << " meters!" << endl;
		}
	}
	cout << "You ran out of attempts!" << endl;
}