package main;

import java.util.List;
import java.util.Queue;

public class PathNodeBreadth extends PathNodeDjikstra {
	public PathNodeBreadth(int expansionCost, int[] position,
			int[] goalPosition, List<List<Node>> map) {
		super(expansionCost, position, goalPosition, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void expand(int x, int y, Queue<Node> queue) {
		Node node;
		try {
			node = map.get(x).get(y);
		} catch (IndexOutOfBoundsException e) {
			return;
		}
		if (node.getExpansionCost() == -1)
			return; // Only difference from Djistra is that it might already
					// have been expanded, but this path might be better
		if (node.getCost() <= getCost() + node.getExpansionCost())
			return;
		node.setCost(getCost() + node.getExpansionCost());
		node.setParent(this);
		if (!queue.contains(node))
			queue.add(node);
	}
}