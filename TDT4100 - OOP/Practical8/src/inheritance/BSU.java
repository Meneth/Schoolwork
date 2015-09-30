package inheritance;

public class BSU extends SavingsAccount {
	private final double maxDeposit;
	private int depositedThisYear;
	
	public BSU(double interestRate, double maxDeposit) {
		super(interestRate);
		this.maxDeposit = maxDeposit;
		depositedThisYear = 0;
	}
	
	@Override
	public void deposit(double amount) {
		if (amount + depositedThisYear > maxDeposit)
			throw new IllegalStateException("Can't go above the deposit limit for the year");
		super.deposit(amount);
		depositedThisYear += amount;
	}
	
	@Override
	public void withdraw(double amount) {
		if (depositedThisYear - amount < 0)
			throw new IllegalStateException("Can't withdraw more than has been deposited this year");
		super.withdraw(amount);
		depositedThisYear -= amount;
	}
	
	@Override
	public void endYearUpdate() {
		super.endYearUpdate();
		depositedThisYear = 0;
	}
	
	public double getTaxDeduction() {
		return depositedThisYear * 0.2;
	}
}
