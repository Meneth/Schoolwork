import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Stack;


public class EdgeDensity {
	public static void main(String[] args) {
		BufferedReader in;
		if (args.length > 0) { // File to read is entered via run configurations
			try {
				in = new BufferedReader(new FileReader(args[0]));
			}
			catch (FileNotFoundException e) {
				System.out.println("Could not open file " + args[0]);
				return;
			}
		}
		else {
			in = new BufferedReader(new InputStreamReader(System.in));
		}
		try {
			int size = Integer.parseInt(in.readLine());
			boolean[][] graph = new boolean[size][size];
			for (int i = 0; i < size; i++) {
				char[] line = in.readLine().toCharArray();
				for (int j = 0; j < size; j++) {
					if (line[j] == '1')
						graph[i][j] = true;
				}
			}
			String line = in.readLine();
			while (line != null) {
				int start = Integer.parseInt(line);
				System.out.println(density(graph, start));
				line = in.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static BigDecimal density(boolean[][] graph, int start) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] connected = new boolean[graph.length];
		stack.add(start);
		connected[start] = true;
		while (!stack.isEmpty()) {
			int i = stack.pop();
			for (int j = 0; j < connected.length; j++) {
				if (!connected[j] && graph[i][j]) {
					connected[j] = true;
					stack.push(j);
				}
			}
		}
		int nodes = 0;
		int edges = 0;
		for (int i = 0; i < connected.length; i++) {
			if (connected[i])
				continue;
			nodes++;
			for (int j = 0; j < connected.length; j++) {
				if (graph[i][j] && !connected[j])
					edges++;
			}
		}
		if (nodes == 0)
			return new BigDecimal(0).setScale(3);
		return new BigDecimal((double) edges / nodes / nodes).setScale(3, BigDecimal.ROUND_HALF_UP);
	}
}