package stateandbehavior;

import java.util.ArrayList;
import java.util.Scanner;

import stateandbehavior.Calculator;

public class RPNCalc {
	ArrayList<Double> stack = new ArrayList<Double>();
	Calculator calc = new Calculator();
	int listSize; // Could simply use stack.size() directly, but this uses fewer cycles
	
	public void push(double pusher) {
		stack.add(pusher);
		listSize++;
	}
	public double pop() {
		if (listSize > 0) {
			double element = stack.get(listSize - 1);
			stack.remove(listSize - 1);
			listSize--;
			return element;
		}
		return Double.NaN;
	}
	public double peek(int index) {
		if (index < listSize && index >= 0) {
			return stack.get(listSize - index - 1);
		}
		return Double.NaN;
	}
	public int getSize() {
		return listSize;
	}
	public void performOperation(char operator) {
		double secondOperand = pop();
		if (operator != '~') {
			push(calc.calculateResult(pop(), operator, secondOperand));
		} else {
			double firstOperand = pop();
			push(secondOperand);
			push(firstOperand);
		}
	}
	public String toString() {
		int index = listSize - 1;
		String string = "";
		while (index >= 0) {
			string += stack.get(index) + ", ";
			index--; // Forgot this earlier. Debugging infinite loops sure is fun
		}
		return string;
	}
	public void computeUserInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Type x to exit. Enter calculations: ");
		while (scanner.hasNext()) {
			if (scanner.hasNextDouble()) {
				push(scanner.nextDouble());
			} else {
				char operator = scanner.next().charAt(0);
				if (!calc.validOperator(operator)) { break; }
				performOperation(operator);
			}
		}
		scanner.close();
	}
	public static void main(String[] args) {
		RPNCalc stack = new RPNCalc();
		stack.computeUserInput();
		System.out.println(stack.toString());
	}
}
