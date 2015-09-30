package inheritance;

public class DebitAccount extends AbstractAccount {
	@Override
	protected void internalWithdraw(double amount) {
		if (getBalance() - amount < 0)
			throw new IllegalStateException("DebitAccount cannot have a negative balance.");
	}
}
