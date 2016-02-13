package exercise2;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread {
	private final CustomerQueue queue;
	private final Gui gui;
	private final int pos;
	private boolean stopped;
	
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.queue = queue;
		this.gui = gui;
		this.pos = pos;
		stopped = false;
		gui.barberIsAwake(pos);
	}

	/**
	 * Starts the barber running as a separate thread.
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
				gui.println("Barber #" + pos + " is waiting for a customer.");
				Customer customer = queue.popCustomer();
				gui.println("Barber #" + pos + " is now serving a customer.");
				gui.fillBarberChair(pos, customer);
				sleep(Globals.barberWork + (int) Math.random() * Globals.barberWork / 2);
				gui.emptyBarberChair(pos);
				sleep(Globals.barberSleep + (int) Math.random() * Globals.barberSleep / 2);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		stopped = true;
	}

	// Add more methods as needed
}

