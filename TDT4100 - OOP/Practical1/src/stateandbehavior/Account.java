package stateandbehavior;

public class Account {
	double balance;      // Why would anyone use % rather than the actual number (E.G., 0.05 = 5%)?
	double interestRate; // It unnecessarily complicates things
	public void deposit(double deposit) {
		if (deposit > 0) {
			balance += deposit;
		}
	}
	public void addInterest() {
		balance *=(interestRate/100+1);
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
