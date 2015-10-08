package assignment4;

public interface Solution {
	/**
	 * Returns the value assigned to the solution by the objective value function
	 * @return The objective value
	 */
	public double getObjectiveValue();
	/**
	 * Returns the (valid) neighbors of the solution
	 * @return The neighbors
	 */
	public Solution[] generateNeigbhors();
}
