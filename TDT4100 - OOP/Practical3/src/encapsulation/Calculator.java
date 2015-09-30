package encapsulation;

import java.util.Scanner;

public class Calculator {
	private double firstOperand;
	private double secondOperand;
	private char operator;
	
	public static boolean validOperator(char operator) {
		return operator == '*' || operator == '/' || operator == '+' || operator == '-' || operator == '%';
	}
	public void setFirstOperand(double operand) {
		firstOperand = operand;
	}
	public void setSecondOperand(double operand) {
		secondOperand = operand;
	}
	public void setOperator(char operator) {
		if (validOperator(operator)) {
			this.operator = operator;
		} else {
			throw new IllegalArgumentException("Invalid operator! */+-% are supported.");
		}
	}
	private static boolean validCalculation(char operator, double secondOperand) {
		return operator != '\u0000' && (secondOperand != 0 || (operator != '/' && operator != '%'));
	}
	public double calculateResult() {
		return calculateResult(firstOperand, operator, secondOperand);
	}
	public static double calculateResult(double firstOperand, char operator, double secondOperand) {
		if (validCalculation(operator, secondOperand)) {
			if (operator == '+') {
				return firstOperand + secondOperand;	
			}
			if (operator == '-') {
				return firstOperand - secondOperand;	
			}
			if (operator == '/') {
				return firstOperand / secondOperand;	
			}
			if (operator == '*') {
				return firstOperand * secondOperand;	
			}
			if (operator == '%') {
				return firstOperand % secondOperand;	
			}
		}
		throw new IllegalStateException("The calculation cannot be computed. The calculation was " + firstOperand + operator + secondOperand);
	}
	public void calculateAndSetFirstOperand() {
		Scanner scanner = new Scanner(System.in);
		String localOperand;
		System.out.println("Type x to calculate. Enter calculation: ");
		if (scanner.hasNextDouble()) {
			double currentResult = scanner.nextDouble();
			while (scanner.hasNext()) {
				localOperand = scanner.next();
				if (!validOperator(localOperand.charAt(0))) {
					firstOperand = currentResult;
					break;
				}
				if (scanner.hasNextDouble()) {
					currentResult = calculateResult(currentResult, localOperand.charAt(0), scanner.nextDouble());
				}
			}
		}
		scanner.close();
	}
	
	public double getFirstOperand() { return firstOperand; }
	public double getSecondOperand() { return secondOperand; }
	public char getOperator() { return operator; }
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.calculateAndSetFirstOperand();
		System.out.println(calc.firstOperand);
	}
}
