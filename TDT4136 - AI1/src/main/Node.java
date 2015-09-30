package main;

import java.util.Queue;

abstract public class Node implements Comparable<Node> {
	// Cost represents the incurred cost traveling from the root to this node,
	// for the best path found so far
	private int cost = Integer.MAX_VALUE;
	// Expanded represents whether the node has been expanded yet, so that a
	// node is never expanded twice
	private boolean expanded = false;
	// Parent gives us the node from which the best path found so far originates
	private Node parent;

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isExpanded() {
		return expanded;
	}

	/**
	 * Returns the incremental cost to go to the node from any adjacent node
	 * 
	 * @param parent
	 *            The node one is arriving via
	 * @return The cost
	 */
	abstract public int getExpansionCost();

	/**
	 * Returns the heuristically estimated cost from the node to the goal
	 * 
	 * @return The cost
	 */
	abstract public int getHeuristicCost();

	/**
	 * Returns the incurred cost plus the heuristic cost
	 * 
	 * @return The total cost
	 */
	public int getEstimatedCost() {
		return getCost() + getHeuristicCost();
	}

	/**
	 * Expands the node
	 * 
	 * @param queue
	 *            The queue that children are to be inserted into
	 */
	public void expand(Queue<Node> queue) {
		expanded = true;
		expandNode(queue);
	}

	/**
	 * The implementation of the node expansion
	 * 
	 * @param queue
	 *            The queue that children are to be inserted into
	 */
	abstract protected void expandNode(Queue<Node> queue);

	/**
	 * @return Whether the node is the goal
	 */
	abstract public boolean isGoal();

	/**
	 * Used to determine the ordering of the node within a priority queue
	 */
	@Override
	public int compareTo(Node o) {
		return getEstimatedCost() - o.getEstimatedCost();
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}
