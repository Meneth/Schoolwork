package inheritance;

public abstract class Calculator {
	private double leftOperand;
	private double rightOperand;
	private char operator = ' ';
	
	protected void setLeftOperand(double leftOperand) {
		this.leftOperand = leftOperand;
	}
	protected void setRightOperand(double rightOperand) {
		this.rightOperand = rightOperand;
	}
	protected void setOperator(char operator) {
		if (operator == '+' || operator == '-' || operator == '/' || operator == '*' || operator == '%')
			this.operator = operator;
		else
			throw new IllegalArgumentException("Invalid operator");
	}
	
	protected double getResult() {
		return calculateResult(leftOperand, operator, rightOperand);
	}
	
	private static boolean validCalculation(char operator, double secondOperand) {
		return operator != ' ' && (secondOperand != 0 || (operator != '/' && operator != '%'));
	}
	
	private static double calculateResult(double firstOperand, char operator, double secondOperand) {
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
	
	abstract public void takeInputNumber(double number);
	abstract public void takeInputOperator(char operator);
	abstract public boolean hasOutput();
	abstract public double getOutput();
}

