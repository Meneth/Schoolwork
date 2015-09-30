package inheritance;

import java.util.Scanner;

public class CalculatorProgram {
	private Scanner scanner;
	private Calculator calc;
	
	public CalculatorProgram() {
		scanner = new Scanner(System.in);
	}

	public void init() {
		System.out.println("Do you want to use a [simple] calculator or [RPN] calulator?");
		String calc = scanner.nextLine().toLowerCase();
		if (calc.equals("rpn"))
			this.calc = new RPNCalc();
		else if (calc.equals("simple"))
			this.calc = new SimpleCalculator();
		else
			throw new IllegalArgumentException("Invalid calculator type.");
	}
	
	public void run() {
		String s;
		System.out.println("Input number or operator:");
		while (scanner.hasNext()) {
			s = scanner.next();
			try {
				calc.takeInputNumber(Double.parseDouble(s));
			} catch (NumberFormatException e) {
				calc.takeInputOperator(s.charAt(0));
			}
			if (calc.hasOutput()) {
				System.out.println("Result: " + calc.getOutput());
			}
			System.out.println("Input number or operator:");
		}
	}
	
	public static void main(String[] args) {
		CalculatorProgram program = new CalculatorProgram();
		program.init();
		program.run();
	}
}
