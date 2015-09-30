package main;

import java.util.List;
import java.util.Queue;

public class PathNode extends Node {
	private final int expansionCost;
	private final int[] position;
	private final int[] goalPosition;
	protected final List<List<Node>> map;

	public PathNode(int expansionCost, int[] position, int[] goalPosition,
			List<List<Node>> map) {
		this.expansionCost = expansionCost;
		this.position = position;
		this.goalPosition = goalPosition;
		this.map = map;
	}

	@Override
	public int getExpansionCost() {
		return expansionCost;
	}

	@Override
	public int getHeuristicCost() {
		return Math.abs(position[0] - goalPosition[0])
				+ Math.abs(position[1] - goalPosition[1]);
	}

	@Override
	public void expandNode(Queue<Node> queue) {
		// Attempt to expand all adjacent nodes
		expand(position[0] + 1, position[1], queue);
		expand(position[0] - 1, position[1], queue);
		expand(position[0], position[1] + 1, queue);
		expand(position[0], position[1] - 1, queue);
	}

	protected void expand(int x, int y, Queue<Node> queue) {
		Node node;
		try {
			node = map.get(x).get(y);
		} catch (IndexOutOfBoundsException e) {
			return; // Index not actually on the board
		}
		if (node.isExpanded() || queue.contains(node)
				|| node.getExpansionCost() == -1)
			return; // Node has already been visited, or there's a better path
					// already, or it is impossible to visit
		node.setCost(getCost() + node.getExpansionCost());
		node.setParent(this);
		queue.add(node);
	}

	public int[] getPosition() {
		return position;
	}

	@Override
	public boolean isGoal() {
		return position[0] == goalPosition[0] && position[1] == goalPosition[1];
	}

	@Override
	public String toString() {
		if (isGoal())
			return "B";
		switch (getExpansionCost()) {
		case -1:
			return "#";
		case 1:
			return ".";
		case 5:
			return "g";
		case 10:
			return "f";
		case 50:
			return "m";
		case 100:
			return "w";
		default:
			throw new IllegalArgumentException();
		}
	}
}
