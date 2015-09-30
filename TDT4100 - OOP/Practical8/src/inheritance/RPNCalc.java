package inheritance;

import java.util.Stack;

public class RPNCalc extends Calculator {
	private Stack<Double> stack = new Stack<>();
	private boolean hasOutput = false;
	
	@Override
	public void takeInputNumber(double number) {
		stack.add(number);
		hasOutput = false;
	}
	@Override
	public void takeInputOperator(char operator) {
		setRightOperand(stack.pop());
		setLeftOperand(stack.pop());
		setOperator(operator);
		stack.push(getResult());
		hasOutput = true;
	}
	@Override
	public boolean hasOutput() {
		return hasOutput;
	}
	@Override
	public double getOutput() {
		if (!hasOutput)
			throw new IllegalStateException("No output available.");
		hasOutput = false;
		return stack.peek();
	}
}
