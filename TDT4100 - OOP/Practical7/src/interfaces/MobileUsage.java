package interfaces;

public class MobileUsage {
	int callCount;
	int callSeconds;
	int messageCount;
	int bytesReceived;
	int bytesSent;
	
	public MobileUsage() {
		callCount = 0;
		callSeconds = 0;
		messageCount = 0;
		bytesReceived = 0;
		bytesSent = 0;
	}
	public void registerCall(int seconds) {
		if (seconds <= 0) {
			throw new IllegalArgumentException("Time must be positive.");
		}
		callCount++;
		callSeconds += seconds;
	}
	public void registerMessage() {
		messageCount++;
	}
	public void registerBytes(int sent, int received) {
		if (sent < 0 || received < 0) {
			throw new IllegalArgumentException("Bytes sent/received cannot be negative.");
		}
		bytesReceived += received;
		bytesSent += sent;
	}
	public int getCallCount() {
		return callCount;
	}
	public int getCallSeconds() {
		return callSeconds;
	}
	public int getMessageCount() {
		return messageCount;
	}
	public int getBytesReceived() {
		return bytesReceived;
	}
	public int getBytesSent() {
		return bytesSent;
	}
}
