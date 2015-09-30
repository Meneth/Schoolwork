package inheritance;

public class CreditAccount extends AbstractAccount {
	private double creditLine;
	
	public CreditAccount(double creditLine) {
		super();
		this.creditLine = creditLine;
	}
	
	@Override
	void internalWithdraw(double amount) {
		if (getBalance() - amount < 0) {
			if (Math.abs(getBalance() - amount) > creditLine)
				throw new IllegalStateException("Withdrawal would result in overdraft past credit line.");
		}
	}

	public double getCreditLine() {
		return creditLine;
	}

	public void setCreditLine(double creditLine) {
		if (creditLine < 0)
			throw new IllegalArgumentException("Credit cannot be negative.");
		if (getBalance() + creditLine < 0) {
			throw new IllegalStateException("Credit must always be equal or greater than current overdraft.");
		}
		this.creditLine = creditLine;
	}
}
