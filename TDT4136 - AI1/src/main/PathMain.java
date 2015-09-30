package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathMain {
	private static Algorithm algorithm = Algorithm.ASTAR;

	enum Algorithm {
		DJIKSTRA, ASTAR, BREADTH;
	}

	public static void main(String[] args) throws IOException {
		List<String> lines = readFile(args);
		int[] goal = findGoalCoordinates(lines);
		Queue<Node> queue = getQueue();
		List<List<Node>> map = createNodes(lines, queue, goal);

		// Traverses the nodes until the goal is found
		Node goalNode = null;
		switch (algorithm) {
		case ASTAR:
		case DJIKSTRA:
			goalNode = Pathfinding.findPath(queue, true);
			break;
		case BREADTH:
			goalNode = Pathfinding.findPath(queue, false);
			break;
		}
		output(goalNode, map, queue);
	}

	/**
	 * Creates a node for each spot on the 2D grid
	 * 
	 * @param lines
	 *            The 2D grid
	 * @param queue
	 *            The queue that is to contain open nodes
	 * @param goal
	 *            The coordinates of the goal node
	 * @return A 2D list of nodes corresponding to the 2D grid
	 */
	private static List<List<Node>> createNodes(List<String> lines,
			Queue<Node> queue, int[] goal) {
		List<Node> currentLine;
		String line;
		List<List<Node>> map = new ArrayList<List<Node>>();
		for (int i = 0; i < lines.size(); i++) {
			line = lines.get(i);
			currentLine = new ArrayList<>();
			map.add(currentLine);
			char[] charline = line.toCharArray();
			for (int j = 0; j < charline.length; j++) {
				char c = charline[j];
				Node n = null;
				switch (c) {
				case 'A':
					n = createNode(1, new int[] { i, j }, goal, map);
					queue.add(n);
					n.setCost(0);
					break;
				case 'B':
				case '.':
				case 'r':
					n = createNode(1, new int[] { i, j }, goal, map);
					break;
				case 'g':
					n = createNode(5, new int[] { i, j }, goal, map);
					break;
				case 'f':
					n = createNode(10, new int[] { i, j }, goal, map);
					break;
				case 'm':
					n = createNode(50, new int[] { i, j }, goal, map);
					break;
				case 'w':
					n = createNode(100, new int[] { i, j }, goal, map);
					break;
				case '#':
					n = createNode(-1, new int[] { i, j }, goal, map);
					break;
				}
				currentLine.add(n);
			}
		}
		return map;
	}

	/**
	 * Creates a queue of the appropriate type for a given algorithm
	 * 
	 * @return An empty node queue
	 */
	private static Queue<Node> getQueue() {
		switch (algorithm) {
		case ASTAR:
		case DJIKSTRA:
			return new PriorityQueue<>();
		case BREADTH:
			// Breadth-first needs a FIFO queue rather than a priority queue
			return new ArrayDeque<>();
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Finds the coordinates of the goal node within the 2D grid
	 * 
	 * @param grid
	 *            The 2D grid
	 * @return The coordinates of the goal node
	 */
	private static int[] findGoalCoordinates(List<String> grid) {
		int[] goal = new int[2];
		for (int i = 0; i < grid.size(); i++) {
			int index = grid.get(i).indexOf('B');
			if (index != -1) {
				goal[0] = i;
				goal[1] = index;
			}
		}
		return goal;
	}

	/**
	 * Reads a file (or System.in if none is provided) into an array
	 * 
	 * @param args
	 *            The run parameters
	 * @return An array containing the input
	 * @throws IOException
	 */
	private static List<String> readFile(String[] args) throws IOException {
		BufferedReader in;
		if (args.length > 0) { // File to read is entered via run configurations
			try {
				in = new BufferedReader(new FileReader(args[0]));
			} catch (FileNotFoundException e) {
				System.out.println("Could not open file " + args[0]);
				throw new IllegalArgumentException("Invalid file name");
			}
		} else {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		// Finds the goal first, as the nodes require it at instantiation
		String line = in.readLine();
		List<String> lines = new ArrayList<>();
		while (line != null) {
			lines.add(line);
			line = in.readLine();
		}
		return lines;
	}

	/**
	 * Outputs an overview of the path chosen
	 * 
	 * @param n
	 *            The goal node
	 * @param map
	 *            The map of all nodes
	 */
	private static void output(Node n, List<List<Node>> map, Queue<Node> queue) {
		Node parent = n.getParent();
		List<int[]> positions = new ArrayList<>();
		while (parent != null) {
			positions.add(((PathNode) parent).getPosition());
			parent = parent.getParent();
		}
		String output = "Path:\n";
		String output2 = "Open, closed, and unvisited nodes:\n";
		for (int i = 0; i < map.size(); i++) {
			List<Node> nodes = map.get(i);
			for (int j = 0; j < nodes.size(); j++) {
				Node node = nodes.get(j);
				boolean inPath = false;
				for (int k = 0; k < positions.size(); k++) {
					int[] position = positions.get(k);
					if (i == position[0] && j == position[1]) {
						int order = positions.size() - k - 1;
						output += order;
						if (order < 10)
							output += " ";
						inPath = true;
						break;
					}
				}
				if (!inPath) {
					output += node.toString() + " ";
				}
				output += " ";
				if (node.isExpanded())
					output2 += "x";
				else if (queue.contains(node))
					output2 += "*";
				else
					output2 += " ";
				output2 += "  ";
			}
			output += "\n";
			output2 += "\n";
		}
		System.out.println("Total cost is: " + n.getCost());
		System.out.println(algorithm + ":");
		System.out.println(output);
		System.out.println(output2);
	}

	private static Node createNode(int expansionCost, int[] position,
			int[] goalPosition, List<List<Node>> map) {
		switch (algorithm) {
		case ASTAR:
			return new PathNode(expansionCost, position, goalPosition, map);
		case BREADTH:
			return new PathNodeBreadth(expansionCost, position, goalPosition,
					map);
		case DJIKSTRA:
			return new PathNodeDjikstra(expansionCost, position, goalPosition,
					map);
		default:
			throw new IllegalArgumentException();
		}
	}
}
