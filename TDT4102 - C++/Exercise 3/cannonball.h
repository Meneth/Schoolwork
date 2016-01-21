#pragma once

// Task 1
double acclY();
double velY(double initVelocity, double time);
double posX(double initVelocity, double time);
double posY(double initVelocity, double time);
void printTime(double time);
double flightTime(double initVelocity);

// Task 4
void getUserInput(double *theta, double *absVelocity);
double getVelocityX(double theta, double absVelocity);
double getVelocityY(double theta, double absVelocity);
void getVelocityVector(double theta, double absVelocity,
	double *velocityX, double *velocityY);
double getDistanceTraveled(double velocityX, double velocityY);
double targetPractice(double distanceToTarget,
	double velocityX,
	double velocityY);
void playTargetPractice();