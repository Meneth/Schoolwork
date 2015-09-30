import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Merge {
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
			String line = in.readLine();
			char type;
			ArrayList<Card> list = new ArrayList<Card>();
			while (line != null) {
				type = line.charAt(0);
				int start = 2;
				int end = line.indexOf(',');
				int i = 0;
				do {
					try { i = Integer.parseInt(line.substring(start, end)); }
					catch (StringIndexOutOfBoundsException e) {
						i = Integer.parseInt(line.substring(start)); // Final part
					}
					list.add(new Card(i, type));
					start = end + 1;
					end = line.indexOf(',', start + 1);
				} while (start != 0);
				line = in.readLine();
			}
			Card[] heap = (Card[]) list.toArray(new Card[list.size()]);
			heapify(heap);
			char[] out = new char[heap.length];
			for (int i = 0; i < heap.length; i++) {
				out[i] = heap[0].type;
				heap[0].value = Integer.MAX_VALUE;
				minHeapify(heap, 0);
			}
			System.out.println(new String(out));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void heapify(Card[] heap) {
		int h = heap.length / 2;
		for (int i = 0; i < h; i++) {
			minHeapify(heap, h - i - 1);
		}
	}

	private static void minHeapify(Card[] heap, int i) {
		int left = i * 2 + 1;
		int right = i * 2 + 2;
		int smallest = i;
		if (left < heap.length && heap[left].value < heap[smallest].value)
			smallest = left;
		if (right < heap.length && heap[right].value < heap[smallest].value)
			smallest = right;
		if (smallest != i) {
			Card temp = heap[i];
			heap[i] = heap[smallest];
			heap[smallest] = temp;
			minHeapify(heap, smallest);
		}
	}
}

class Card {
	public int value;
	public final char type;
	
	public Card(int value, char type) {
		super();
		this.value = value;
		this.type = type;
	}

	@Override
	public String toString() {
		return "[" + value + ", " + type + "]";
	}
}
