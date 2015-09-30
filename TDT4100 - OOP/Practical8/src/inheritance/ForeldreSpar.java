package inheritance;

public class ForeldreSpar extends SavingsAccount {
	private int withdrawLimit;
	private int remainingWithdrawals;

	public ForeldreSpar(double interestRate, int withdrawLimit) {
		super(interestRate);
		this.withdrawLimit = withdrawLimit;
		this.remainingWithdrawals = withdrawLimit;
	}

	public int getRemainingWithdrawals() {
		return remainingWithdrawals;
	}
	
	@Override
	public void withdraw(double amount) {
		if (remainingWithdrawals <= 0)
			throw new IllegalStateException("All withdrawals for the year have been used up.");
		super.withdraw(amount);
		remainingWithdrawals--;
	}
	
	@Override
	public void endYearUpdate() {
		super.endYearUpdate();
		remainingWithdrawals = withdrawLimit;
	}
}
