import java.io.*;

public class LinkedList {

	private static int getHighestWeight(Element element) {
		int highest = 0;
		while (element != null) {
			if (element.weight > highest)
				highest = element.weight;
			element = element.next;
		}
		return highest;
	}

	public static void main(String args[]) {
		BufferedReader in;
		if (args.length > 0) {
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
			Element first = null, latest = null, previous = null;
			String line = in.readLine();
			while (line != null) {
				previous = latest;
				int i = Integer.parseInt(line);
				latest = new Element(i);
				if(first == null)
					first = latest;
				else
					previous.next = latest;
				line = in.readLine();
			}
			long start = System.nanoTime();
			System.out.println(getHighestWeight(first));
			long elapsedTime = System.nanoTime() - start;
			System.out.println((double) elapsedTime / 100000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Element {
	protected final int weight;
	protected Element next;
	public Element(int weight) { this.weight = weight; }
}