package interfaces;

public class ThresholdMobileSubscription implements MobileSubscription {
	private double staticCallCost;
	private double messageCost;
	private double costBytesBelow;
	private double costBytesAbove;
	private int costBytesThreshold;
	
	public int computeTotalCost(MobileUsage usage) {
		double cost = staticCallCost;
		cost += messageCost * usage.getMessageCount();
		int bytesUsed = usage.getBytesReceived() + usage.getBytesSent();
		if (bytesUsed <= costBytesThreshold) {
			cost += bytesUsed * costBytesBelow;
		} else {
			cost += costBytesThreshold * costBytesBelow;
			cost += (bytesUsed - costBytesThreshold) * costBytesAbove;
		}
		return (int) cost; // Spec wants int for whatever reason
	}                      // A float or double seems more logical as currency can be subdivided
	
	public double getCostBytesBelow() {
		return costBytesBelow;
	}

	public void setCostBytesBelow(double costBytesBelow) {
		if (costBytesBelow < 0) {
			throw new IllegalArgumentException("Cost cannot be negative.");
		}
		this.costBytesBelow = costBytesBelow;
	}

	public double getCostBytesAbove() {
		return costBytesAbove;
	}

	public void setCostBytesAbove(double costBytesAbove) {
		if (costBytesAbove < 0) {
			throw new IllegalArgumentException("Cost cannot be negative.");
		}
		this.costBytesAbove = costBytesAbove;
	}

	public int getCostBytesThreshold() {
		return costBytesThreshold;
	}

	public void setCostBytesThreshold(int costBytesThreshold) {
		if (costBytesThreshold < 0) {
			throw new IllegalArgumentException("Threshold cannot be negative.");
		}
		this.costBytesThreshold = costBytesThreshold;
	}

	public double getStaticCallCost() {
		return staticCallCost;
	}
	public void setStaticCallCost(double staticCallCost) {
		if (staticCallCost < 0) {
			throw new IllegalArgumentException("Cost cannot be negative.");
		}
		this.staticCallCost = staticCallCost;
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
}
