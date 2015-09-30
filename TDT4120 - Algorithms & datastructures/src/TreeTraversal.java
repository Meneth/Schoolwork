import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class TreeTraversal {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			in.readLine();
			int numOfNodes = Integer.parseInt(in.readLine());
			String root = in.readLine(), goal = in.readLine();
			if (goal.equals(root)) {
				System.out.println(0);
				return;
			}
			int depth = 1;
			String line = in.readLine();
			HashMap<String, String> nodes = new HashMap<String, String>(numOfNodes);
			while (line != null) {
				int i = line.indexOf(' ') + 1;
				String node = line.substring(0, i);
				int j = line.indexOf(' ', i);
				while (j != -1) {
					nodes.put(line.substring(i, j), node);
					i = j + 1;
					j = line.indexOf(' ', i);
				}
				nodes.put(line.substring(i), node);
				node = nodes.get(goal);
				while (node != null) {
					goal = node;
					if (goal.equals(root)) {
						System.out.println(depth);
						return;
					}
					depth++;
					node = nodes.get(goal);
				}
				line = in.readLine();
			}
		} catch(Exception ioe){
			ioe.printStackTrace();
		}
	}
}