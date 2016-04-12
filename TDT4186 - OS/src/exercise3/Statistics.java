package exercise3;

/**
 * This class contains a lot of public variables that can be updated by other
 * classes during a simulation, to collect information about the run.
 */
public class Statistics {
	/** The number of processes that have exited the system */
	public long nofCompletedProcesses = 0;
	/** The number of processes that have entered the system */
	public long nofCreatedProcesses = 0;
	/**
	 * The total time that all completed processes have spent waiting for memory
	 */
	public long totalTimeSpentWaitingForMemory = 0;
	/**
	 * The time-weighted length of the memory queue, divide this number by the
	 * total time to get average queue length
	 */
	public long memoryQueueLengthTime = 0;
	/** The largest memory queue length that has occured */
	public long memoryQueueLargestLength = 0;
	
	public long processSwitches = 0;
	public long totalIOTimeUsed = 0;
	public long totalCpuTimeUsed = 0;
	// TODO - Queue length for IO and CPU
	public long nofTimesInIoQueue = 0;
	public long nofTimesInReadyQueue = 0;
	public long totalTimeSpentWaitingForCPU = 0;
	public long totalTimeSpentWaitingForIo = 0;
	public long cpuQueueLengthTime = 0;
	public long ioQueueLengthTime = 0;
	public long cpuQueueLargestLength;
	public long ioQueueLargestLength;

	/**
	 * Prints out a report summarizing all collected data about the simulation.
	 * 
	 * @param simulationLength
	 *            The number of milliseconds that the simulation covered.
	 */
	public void printReport(long simulationLength) {
		System.out.println();
		System.out.println("Simulation statistics:");
		System.out.println();
		System.out.println("Number of completed processes:                                " + nofCompletedProcesses);
		System.out.println("Number of created processes:                                  " + nofCreatedProcesses);
		System.out.println("Number of (forced) processes switches:                        " + processSwitches);
		System.out.println("Number of processed IO operations:                            " + nofTimesInIoQueue);
		System.out.println("Throughput:                                                   " + (float) (1000 * nofCompletedProcesses) / (float) simulationLength);
		System.out.println();
		
		System.out.println("Total CPU time spent processing:                              " + totalCpuTimeUsed + " ms");
		System.out.println("Fraction of CPU time spent processing:                        " + 100 * (float) totalCpuTimeUsed / (float) simulationLength + "%");
		System.out.println("Total CPU time spent waiting:                                 " + (simulationLength - totalCpuTimeUsed) + " ms");
		System.out.println("Fraction of CPU time spent waiting:                           " + 100 * (float) (simulationLength - totalCpuTimeUsed) / (float) simulationLength + "%");
		System.out.println();
		
		System.out.println("Largest occuring memory queue length:                         " + memoryQueueLargestLength);
		System.out.println("Average memory queue length:                                  "
				+ (float) memoryQueueLengthTime / simulationLength);
		System.out.println("Largest occuring CPU queue length:                            " + cpuQueueLargestLength);
		System.out.println("Average CPU queue length:                                     "
				+ (float) cpuQueueLengthTime / simulationLength);
		System.out.println("Largest occuring IO queue length:                             " + ioQueueLargestLength);
		System.out.println("Average IO queue length:                                      "
				+ (float) ioQueueLengthTime / simulationLength);
		if (nofCompletedProcesses > 0) {
			System.out.println("Average # of times a process has been placed in memory queue: " + 1);
			System.out.println("Average # of times a process has been placed in CPU queue:    " + (float) nofTimesInReadyQueue / nofCompletedProcesses);
			System.out.println("Average # of times a process has been placed in IO queue:     " + (float) nofTimesInIoQueue / nofCompletedProcesses);
			System.out.println();
			
			System.out.println("Average time spent in system per process:                     "
					+ (totalTimeSpentWaitingForMemory + totalTimeSpentWaitingForCPU + totalIOTimeUsed + totalTimeSpentWaitingForIo + totalCpuTimeUsed)
					/ nofCompletedProcesses + " ms");
			System.out.println("Average time spent waiting for memory per process:            "
					+ totalTimeSpentWaitingForMemory / nofCompletedProcesses + " ms");
			System.out.println("Average time spent waiting for CPU per process:               "
					+ totalTimeSpentWaitingForCPU / nofCompletedProcesses + " ms");
			System.out.println("Average time spent processing per process:                    "
					+ totalCpuTimeUsed / nofCompletedProcesses + " ms");
			System.out.println("Average time spent waiting for I/O per process:               "
					+ totalTimeSpentWaitingForIo / nofCompletedProcesses + " ms");
			System.out.println("Average time spent in I/O per process:                        "
					+ totalIOTimeUsed / nofCompletedProcesses + " ms");
		}
	}
}
