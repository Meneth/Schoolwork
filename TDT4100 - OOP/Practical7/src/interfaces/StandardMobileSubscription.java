package interfaces;

public class StandardMobileSubscription implements MobileSubscription {
	private double callCost;
	private double callMinuteCost;
	private double messageCost;
	private double receiveMBytesCost;
	private double sentMBytesCost;
	
	public int computeTotalCost(MobileUsage usage) {
		double cost = callCost * usage.getCallCount();
		cost += callMinuteCost / 60 * usage.getCallSeconds();
		cost += messageCost * usage.getMessageCount();
		cost += receiveMBytesCost / 1024 / 1024 * usage.getBytesReceived();
		cost += sentMBytesCost / 1024 / 1024 * usage.getBytesSent();
		return (int) cost; // Spec wants int for whatever reason
	}                      // A float or double seems more logical as currency can be subdivided
	
	public double getCallCost() {
		return callCost;
	}
	public void setCallCost(double callCost) {
		if (callCost < 0) {
			throw new IllegalArgumentException("Cost cannot be negative.");
		}
		this.callCost = callCost;
	}
	public double getCallMinuteCost() {
		return callMinuteCost;
	}
	public void setCallMinuteCost(double callMinuteCost) {
		if (callMinuteCost < 0) {
			throw new IllegalArgumentException("Cost cannot be negative.");
		}
		this.callMinuteCost = callMinuteCost;
	}
	public double getMessageCost() {
		return messageCost;
	}
	public void setMessageCost(double messageCost) {
		if (messageCost < 0) {
			throw new IllegalArgumentException("Cost cannot be negative.");
		}
		this.messageCost = messageCost;
	}
	public double getReceiveMBytesCost() {
		return receiveMBytesCost;
	}
	public void setReceiveMBytesCost(double receiveMBytesCost) {
		if (receiveMBytesCost < 0) {
			throw new IllegalArgumentException("Cost cannot be negative.");
		}
		this.receiveMBytesCost = receiveMBytesCost;
	}
	public double getSentMBytesCost() {
		return sentMBytesCost;
	}
	public void setSentMBytesCost(double sentMBytesCost) {
		if (sentMBytesCost < 0) {
			throw new IllegalArgumentException("Cost cannot be negative.");
		}
		this.sentMBytesCost = sentMBytesCost;
	}
}
