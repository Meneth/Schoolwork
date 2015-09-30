package inheritance;

public class SimpleCalculator extends Calculator {
	private boolean lastInputWasOperator = false;
	private boolean hasOutput = false;
	
	@Override
	public void takeInputNumber(double number) {
		if (lastInputWasOperator) {
			setRightOperand(number);
			lastInputWasOperator = false;
			hasOutput = true;
		} else {
			setLeftOperand(number);
			hasOutput = false;
		}
	}

	@Override
	public void takeInputOperator(char operator) {
		setOperator(operator);
		lastInputWasOperator = true;
		hasOutput = false; // Spec wasn't entirely clear on whether there's any output if operator is overwritten
	}

	@Override
	public boolean hasOutput() {
		return hasOutput;
	}

	@Override
	public double getOutput() {
		if (!hasOutput()) {
			throw new IllegalStateException("No output available.");
		}
		hasOutput = false;
		double leftOperand = getResult();
		takeInputNumber(leftOperand);
		return leftOperand;
	}
}
