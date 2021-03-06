package exercise3;

import java.io.*;

/**
 * The main class of the P3 exercise. This class is only partially complete.
 */
public class Simulator implements Constants {
	/** The queue of events to come */
	private EventQueue eventQueue;
	/** Reference to the memory unit */
	private Memory memory;
	/** Reference to the GUI interface */
	private Gui gui;
	/** Reference to the statistics collector */
	private Statistics statistics;
	/** The global clock */
	private long clock;
	/** The length of the simulation */
	private long simulationLength;
	/** The average length between process arrivals */
	private long avgArrivalInterval;

	private final Queue cpuQueue, ioQueue;
	private Process activeProcess, activeIOProcess;
	
	private final long maxCpuTime;
	private final long avgIoTime;

	/**
	 * Constructs a scheduling simulator with the given parameters.
	 * 
	 * @param memoryQueue
	 *            The memory queue to be used.
	 * @param cpuQueue
	 *            The CPU queue to be used.
	 * @param ioQueue
	 *            The I/O queue to be used.
	 * @param memorySize
	 *            The size of the memory.
	 * @param maxCpuTime
	 *            The maximum time quant used by the RR algorithm.
	 * @param avgIoTime
	 *            The average length of an I/O operation.
	 * @param simulationLength
	 *            The length of the simulation.
	 * @param avgArrivalInterval
	 *            The average time between process arrivals.
	 * @param gui
	 *            Reference to the GUI interface.
	 */
	public Simulator(Queue memoryQueue, Queue cpuQueue, Queue ioQueue, long memorySize, long maxCpuTime, long avgIoTime,
			long simulationLength, long avgArrivalInterval, Gui gui) {
		this.simulationLength = simulationLength;
		this.avgArrivalInterval = avgArrivalInterval;
		this.gui = gui;
		statistics = new Statistics();
		eventQueue = new EventQueue();
		memory = new Memory(memoryQueue, memorySize, statistics);
		clock = 0;
		this.cpuQueue = cpuQueue;
		this.ioQueue = ioQueue;
		
		this.maxCpuTime = maxCpuTime;
		this.avgIoTime = avgIoTime;
	}

	/**
	 * Starts the simulation. Contains the main loop, processing events. This
	 * method is called when the "Start simulation" button in the GUI is
	 * clicked.
	 */
	public void simulate() {
		// TODO: You may want to extend this method somewhat.

		System.out.print("Simulating...");
		// Genererate the first process arrival event
		eventQueue.insertEvent(new Event(NEW_PROCESS, 0));
		// Process events until the simulation length is exceeded:
		while (clock < simulationLength && !eventQueue.isEmpty()) {
			// Find the next event
			Event event = eventQueue.getNextEvent();
			// Find out how much time that passed...
			long timeDifference = event.getTime() - clock;
			// ...and update the clock.
			clock = event.getTime();
			// Let the memory unit and the GUI know that time has passed
			memory.timePassed(timeDifference);
			gui.timePassed(timeDifference);
			// Deal with the event
			if (clock < simulationLength) {
				processEvent(event);
			}
			
			statistics.cpuQueueLengthTime += cpuQueue.getQueueLength() * timeDifference;
			statistics.ioQueueLengthTime += ioQueue.getQueueLength() * timeDifference;
			if (cpuQueue.getQueueLength() > statistics.cpuQueueLargestLength) {
				statistics.cpuQueueLargestLength = cpuQueue.getQueueLength();
			}
			if (ioQueue.getQueueLength() > statistics.ioQueueLargestLength) {
				statistics.ioQueueLargestLength = ioQueue.getQueueLength();
			}
		}
		System.out.println("..done.");
		// End the simulation by printing out the required statistics
		statistics.printReport(simulationLength);
	}

	/**
	 * Processes an event by inspecting its type and delegating the work to the
	 * appropriate method.
	 * 
	 * @param event
	 *            The event to be processed.
	 */
	private void processEvent(Event event) {
		switch (event.getType()) {
		case NEW_PROCESS:
			createProcess();
			break;
		case SWITCH_PROCESS:
			switchProcess();
			break;
		case END_PROCESS:
			endProcess();
			break;
		case IO_REQUEST:
			processIoRequest();
			break;
		case END_IO:
			endIoOperation();
			break;
		}
	}

	/**
	 * Simulates a process arrival/creation.
	 */
	private void createProcess() {
		// Create a new process
		Process newProcess = new Process(memory.getMemorySize(), clock);
		memory.insertProcess(newProcess);
		flushMemoryQueue();
		// Add an event for the next process arrival
		long nextArrivalTime = clock + 1 + (long) (2 * Math.random() * avgArrivalInterval);
		eventQueue.insertEvent(new Event(NEW_PROCESS, nextArrivalTime));
		// Update statistics
		statistics.nofCreatedProcesses++;
	}

	/**
	 * Transfers processes from the memory queue to the ready queue as long as
	 * there is enough memory for the processes.
	 */
	private void flushMemoryQueue() {
		Process p = memory.checkMemory(clock);
		// As long as there is enough memory, processes are moved from the
		// memory queue to the cpu queue
		while (p != null) {
			cpuQueue.insert(p);
			// If no active process, the inserted process is to be run
			if (activeProcess == null)
				switchProcess();

			// Check for more free memory
			p = memory.checkMemory(clock);
		}
	}

	/**
	 * Simulates a process switch.
	 */
	private void switchProcess() {
		// TODO - Statistics
		if (activeProcess != null) {
			activeProcess.suspend(clock);
			cpuQueue.insert(activeProcess);
		}
		activeProcess = cpuQueue.removeNext();
		gui.setCpuActive(activeProcess);
		if (activeProcess == null)
			return;
		activeProcess.run(clock);
		
		
		Event event = null;
		long nextIO = activeProcess.getTimeToNextIoOperation();
		long timeLeft = activeProcess.getCPUTimeLeft();
		
		for (Event e : eventQueue) {
			if (e.getType() == IO_REQUEST || e.getType() == END_PROCESS || e.getType() == SWITCH_PROCESS)
				System.out.println("ERROR");
		}
		
		if (nextIO < timeLeft
				&& nextIO < maxCpuTime) {
			// Next event is IO
			event = new Event(IO_REQUEST, clock + nextIO);
		} else if (timeLeft < nextIO
				&& timeLeft < maxCpuTime) {
			// Next event is completion
			event = new Event(END_PROCESS, clock + timeLeft);
		} else {
			// Next event is switch
			event = new Event(SWITCH_PROCESS, clock + maxCpuTime);
			if (clock + maxCpuTime < simulationLength)
				statistics.processSwitches++;
		}
		eventQueue.insertEvent(event);
	}

	/**
	 * Ends the active process, and deallocates any resources allocated to it.
	 */
	private void endProcess() {
		activeProcess.end(clock);
		memory.processCompleted(activeProcess);
		// Update statistics
		activeProcess.updateStatistics(statistics);
		activeProcess = null;
		gui.setCpuActive(null);
		switchProcess();
		flushMemoryQueue();
	}

	/**
	 * Processes an event signifying that the active process needs to perform an
	 * I/O operation.
	 */
	private void processIoRequest() {
		activeProcess.enterIOQueue(clock);
		ioQueue.insert(activeProcess);
		activeProcess = null;
		switchProcess();
		if (activeIOProcess == null)
			processIO();
	}

	/**
	 * Processes an event signifying that the process currently doing I/O is
	 * done with its I/O operation.
	 */
	private void endIoOperation() {
		activeIOProcess.endIO(clock);
		cpuQueue.insert(activeIOProcess);
		activeIOProcess = null;
		gui.setIoActive(null);
		if(activeProcess == null)
			switchProcess();
		processIO();
	}

	private void processIO() {
		Process p = ioQueue.removeNext();
		gui.setIoActive(p);
		if (p != null) {
			p.startIO(clock);
			activeIOProcess = p;
			long t = clock + 1 + (long) (2 * Math.random() * avgIoTime);
			eventQueue.insertEvent(new Event(END_IO, t));
		}
	}

	/**
	 * Reads a number from the an input reader.
	 * 
	 * @param reader
	 *            The input reader from which to read a number.
	 * @return The number that was inputted.
	 */
	public static long readLong(BufferedReader reader) {
		try {
			return Long.parseLong(reader.readLine());
		} catch (IOException ioe) {
			return 100;
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	/**
	 * The startup method. Reads relevant parameters from the standard input,
	 * and starts up the GUI. The GUI will then start the simulation when the
	 * user clicks the "Start simulation" button.
	 * 
	 * @param args
	 *            Parameters from the command line, they are ignored.
	 */
	public static void main(String args[]) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please input system parameters: ");

		System.out.print("Memory size (KB): ");
		long memorySize = readLong(reader);
		while (memorySize < 400) {
			System.out.println("Memory size must be at least 400 KB. Specify memory size (KB): ");
			memorySize = readLong(reader);
		}

		System.out.print("Maximum uninterrupted cpu time for a process (ms): ");
		long maxCpuTime = readLong(reader);

		System.out.print("Average I/O operation time (ms): ");
		long avgIoTime = readLong(reader);

		System.out.print("Simulation length (ms): ");
		long simulationLength = readLong(reader);
		while (simulationLength < 1) {
			System.out.println("Simulation length must be at least 1 ms. Specify simulation length (ms): ");
			simulationLength = readLong(reader);
		}

		System.out.print("Average time between process arrivals (ms): ");
		long avgArrivalInterval = readLong(reader);

		SimulationGui gui = new SimulationGui(memorySize, maxCpuTime, avgIoTime, simulationLength, avgArrivalInterval);
	}
}
