package main;

import java.util.List;
import java.util.Queue;

public class PathNodeDjikstra extends PathNode {

	public PathNodeDjikstra(int expansionCost, int[] position,
			int[] goalPosition, List<List<Node>> map) {
		super(expansionCost, position, goalPosition, map);
	}

	/**
	 * Uses the incurred cost so far without caring about the estimated cost,
	 * since djikstra doesn't use heuristics
	 */
	@Override
	public int compareTo(Node o) {
		return getCost() - o.getCost();
	}

	@Override
	protected void expand(int x, int y, Queue<Node> queue) {
		Node node;
		try {
			node = map.get(x).get(y);
		} catch (IndexOutOfBoundsException e) {
			return;
		}
		if (node.isExpanded() || node.getExpansionCost() == -1)
			return; // Only difference from A* is that it might already have a
					// path from another node, but this path might be better
		if (node.getCost() <= getCost() + node.getExpansionCost())
			return;
		node.setCost(getCost() + node.getExpansionCost());
		node.setParent(this);
		if (!queue.contains(node))
			queue.add(node);
	}
}
