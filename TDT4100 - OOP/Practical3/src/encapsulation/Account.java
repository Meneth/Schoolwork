package encapsulation;

public class Account {
	private double balance;      // Why would anyone use % rather than the actual number (E.G., 0.05 = 5%)?
	private double interestRate; // It unnecessarily complicates things
	
	public Account() {
		this(0, 0);
	}
	public Account(double balance, double interestRate) {
		if (balance < 0 || interestRate < 0) {
			throw new IllegalArgumentException("The balance and interest rate cannot be negative.");
		}
		this.balance = balance;
		this.interestRate = interestRate;
	}
	private static boolean validDeposit(double deposit) {
		if ( deposit >= 0) { return true; }
		return false;
	}
	public void deposit(double deposit) {
		if (!validDeposit(deposit)) {
			throw new IllegalArgumentException("All deposits must be non-negative, but the deposit was " + deposit);
		}
		if (deposit > 0) {
			balance += deposit;
		}
	}
	private static boolean validWithdrawal(int withdrawal) {
		if ( withdrawal >= 0) { return true; }
		return false;
	}
	public int withdraw(int withdrawal) {
		if (!validWithdrawal(withdrawal)) {
			throw new IllegalArgumentException("All withdrawals must be non-negative, but the withdrawal was " + withdrawal);
		}
		if (withdrawal > balance) {
			throw new IllegalStateException("Withdrawals cannot be larger than the current balance."); // Spec prefers this
			// withdrawal = (int) Math.floor(balance); // Withdrawal must be an integer. If not one could simply set withdrawal = balance
		}
		balance -= withdrawal;
		return withdrawal;
	}
	public void addInterest() {
		balance *=(interestRate/100+1);
	}
	
	public double getBalance() {
		return balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	
	public String toString() {
		return "Balance: " + balance + ". Interest rate: " + interestRate + "%";
	}
	public static void main(String[] args) {
		Account account = new Account();
		System.out.println(account.toString());
		account.interestRate = 4;
		account.deposit(1000);
		System.out.println(account.toString());
		account.addInterest();
		System.out.println(account.toString());
	}
}
