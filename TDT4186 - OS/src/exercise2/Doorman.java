package exercise2;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread {
	private boolean stopped;
	private final CustomerQueue queue;
	private final Gui gui;
	
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) { 
		this.queue = queue;
		this.gui = gui;
		stopped = false;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		start();		
	}
	
	/**
	 * Actual running of the thread
	 */
	@Override
	public void run() {
		while (!stopped) {
			try {
				gui.println("Doorman is waiting for room for a new customer");
				queue.addCustomer(new Customer());
				gui.println("Doorman has added a new customer");
				sleep(Globals.doormanSleep +  + (int) Math.random() * Globals.doormanSleep / 2);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		stopped = true;
	}

	// Add more methods as needed
}
