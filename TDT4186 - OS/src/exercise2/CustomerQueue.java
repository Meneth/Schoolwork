package exercise2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	private final Customer[] queue;
	private final Gui gui;
	private int next;
	private int last;
	private int count;
	private final int capacity;
	
	private final Lock lock = new ReentrantLock();
	
	private final Condition notEmpty = lock.newCondition();
	private final Condition notFull = lock.newCondition();
	
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
		this.queue = new Customer[queueLength];
		this.gui = gui;
		this.capacity = queueLength;
		next = 0;
		last = -1;
		count = 0;
	}

    public void addCustomer(Customer customer) throws InterruptedException {
    	lock.lock();
    	
    	
		while (count == capacity)
			notFull.await();
		
		last++;
    	last %= queue.length;
    	queue[last] = customer;
    	count++;
    	
    	gui.fillLoungeChair(last, customer);
    	
    	notEmpty.signal();
    	lock.unlock();
    }
    
    public Customer popCustomer() throws InterruptedException {
    	lock.lock();
    	
		while (count == 0)
			notEmpty.await();
		Customer customer = queue[next];
    	queue[next] = null;
    	count--;
    	gui.emptyLoungeChair(next);
    	
    	next++;
    	next %= queue.length;
    	
    	if (customer == null) {
    		throw new IllegalStateException();
    	}
    	
    	notFull.signal();
    	lock.unlock();
    	
    	return customer;
    }
}
