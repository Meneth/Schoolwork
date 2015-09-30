package inheritance;

public class SavingsAccount extends AbstractAccount {
	private final double fee;
	private int withdrawals;
	
	public SavingsAccount(int withdrawals, double fee) {
		super();
		this.fee = fee;
		this.withdrawals = withdrawals;
	}

	@Override
	protected void internalWithdraw(double amount) {
		if (getBalance() - amount < 0)
			throw new IllegalStateException("DebitAccount cannot have a negative balance.");
		if (withdrawals == 0) {
			if (getBalance() - amount - fee < 0)
				throw new IllegalStateException("DebitAccount cannot have a negative balance."); 
			withdrawals++; // Fee won't incur another fee
			super.withdraw(fee);
		} else {
			withdrawals--;
		}
	}
}
