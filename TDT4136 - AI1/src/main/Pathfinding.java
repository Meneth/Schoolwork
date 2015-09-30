package main;

import java.util.Queue;

public class Pathfinding {
	/**
	 * Attempts to find the shortest path based on a start node or set of start
	 * nodes
	 * 
	 * @param queue
	 *            The queue containing the starting node
	 * @param returnOnGoalFound
	 *            Whether to stop searching once any path to the goal has been
	 *            found. True for algorithms like A* and Djikstra where the
	 *            first path is guaranteed to be the shortest
	 * @return The goal node, which can now be used to reconstruct the path.
	 *         Null if no goal found
	 */
	public static Node findPath(Queue<Node> queue, boolean returnOnGoalFound) {
		Node node;
		Node goalNode = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			if (node.isGoal()) {
				if (returnOnGoalFound)
					return node;
				else {

				}
			} else {
				node.expand(queue);
			}
		}
		return goalNode;
	}
}
