package stateandbehavior;

import java.util.Scanner;

public class Calculator {
	double firstOperand;
	double secondOperand;
	char operator;
	
	boolean validOperator(char operator) {
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
	boolean validCalculation(char operator, double secondOperand) {
		return operator != '\u0000' && (secondOperand != 0 || (operator != '/' && operator != '%'));
	}
	public double calculateResult() {
		return calculateResult(firstOperand, operator, secondOperand);
	}
	public double calculateResult(double firstOperand, char operator, double secondOperand) {
        if (operator == '/' && secondOperand == 0) {
            throw new IllegalArgumentException("Man kan ikke dele på null!");
        } else if (firstOperand != Double.NaN && secondOperand != Double.NaN) {
            if (operator == '+') {
                return firstOperand + secondOperand;
            } else if (operator == '-') {
                return firstOperand - secondOperand;
            } else if (operator == '*') {
                return firstOperand * secondOperand;
            } else if (operator == '/') {
                return firstOperand / secondOperand;
            } else if (operator == '%') {
                return firstOperand % secondOperand;
            } else if (operator == '^') {
                return Math.pow(firstOperand, secondOperand);
            } else {
                throw new IllegalArgumentException("Ugyldig operator");
            }
        } else {
            if (firstOperand == Double.NaN && secondOperand == Double.NaN) {
                throw new IllegalArgumentException("firstOperand og secondOperand er ikke angitt");
            } else if (firstOperand == Double.NaN) {
                throw new IllegalArgumentException("firstOperand er ikke angitt");
            } else {
                throw new IllegalArgumentException("secondOperand er ikke angitt");
            }
        }
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
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.calculateAndSetFirstOperand();
		System.out.println(calc.firstOperand);
	}
}
