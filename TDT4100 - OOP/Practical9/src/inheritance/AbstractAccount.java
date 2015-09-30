package inheritance;

public abstract class AbstractAccount {
	private double balance;
	
	public AbstractAccount() {
		balance = 0;
	}
	
	public void deposit(double amount) {
		if (amount < 0)
			throw new IllegalArgumentException("Cannot deposit a negative amount");
		balance += amount;
	}

	public void withdraw(double amount) {
		if (amount < 0)
			throw new IllegalArgumentException("Cannot withdraw a negative amount");
		internalWithdraw(amount);
		balance -= amount;
	}

	public double getBalance() {
		return balance;
	}
	
	abstract void internalWithdraw(double amount);
}
