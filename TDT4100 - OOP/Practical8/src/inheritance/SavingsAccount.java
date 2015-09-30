package inheritance;

public class SavingsAccount implements Account {
	private double balance;
	private final double interestRate;
	
	public SavingsAccount(double interestRate) {
		balance = 0;
		this.interestRate = interestRate;
	}
	
	@Override
	public void deposit(double amount) {
		if (amount < 0)
			throw new IllegalArgumentException("Cannot deposit a negative amount");
		balance += amount;
	}

	@Override
	public void withdraw(double amount) {
		if (amount < 0)
			throw new IllegalArgumentException("Cannot withdraw a negative amount");
		if (amount > balance)
			throw new IllegalStateException("Cannot withdraw more than the account's balance.");
		balance -= amount;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	public void endYearUpdate() {
		deposit(getBalance() * interestRate);
	}
}
