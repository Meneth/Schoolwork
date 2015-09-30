import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Dictionary {
	private static Node build(HashMap<String, int[]> dict) {
		Node root = new Node();          // A search tree is created character by character
		Node node = root;                // It is assumed that all words consist only of [a-z]
		for (String s : dict.keySet()) { // Therefore children can be stored in a Node[26]
			node = root;                 // With the char minus 'a' used as the position
			for (char c : s.toCharArray()) {
				c -= 'a';
				if (node.children[c] == null)
					node.children[c] = new Node();
				node = node.children[c];
			}
			node.positions = dict.get(s); // Saves the positions of the word in the string being searched
		}
		return root;
	}
	
	// This method is used to concatenate lists of ints to keep track of word positions
	private static int[] concat(int[] A, int[] B) {
		int aLen = A.length;
		int bLen = B.length;
		int[] C= new int[aLen+bLen];
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);
		return C;
	}
	
	// Returns every position in the string where the word is present
	private static int[] positions(char[] word, int index, Node node) {
		if (index == word.length)
			return node.positions;
		char c = word[index];
		if (c == '?') { // Single character wildcards are accepted, so all children have to be looped through
			int[] positions = {};
			for (Node n : node.children) {
				if (n != null) { // As the children array is preallocated, not all children necessarily exist
					int[] pos = positions(word, index + 1, n);
					positions = concat(positions, pos);
				}
			}
			return positions;
		}
		c -= 'a'; // 26 spots are preallocated, one for each character [a-z]
		if (node.children[c] == null)
			return new int[0];
		else
			return positions(word, index + 1, node.children[c]);
	}
	
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
		} // A hashmap keeps track of every word in the string to be searched, and their positions
		HashMap<String, int[]> dict = new HashMap<String, int[]>();
		try {
			String line = in.readLine();
			int start = 0;
			int end = line.indexOf(' ');
			while (end != -1) {   // Substring and indexOf are used as it is faster than StringTokenizer or split
				String s = line.substring(start, end);
				int[] val = dict.get(s);
				dict.put(s, val == null ? new int[] {start} : concat(val, new int[] {start}));
				start = end + 1;
				end = line.indexOf(' ', start + 1);
			}
			String s = line.substring(start);
			int[] val = dict.get(s);
			dict.put(s, val == null ? new int[] {start} : concat(val, new int[] {start}));
			Node root = build(dict);
			line = in.readLine();
			java.util.LinkedList<String> out = new LinkedList<String>(); // Keeps track of output for printing at the end
			while (line != null) {
				line = line.trim();
				out.add(line + ":");
				int[] pos;
				if (line.contains("?")) { // If wildcards are included, the tree has to be searched
					pos = positions(line.toCharArray(), 0, root);
					Arrays.sort(pos);
				} else if (dict.containsKey(line)) { // If no wildcards, positions can simply be looked up in the hashtable
					pos = dict.get(line);
				}
				else { // The word isn't present at all
					pos = new int[0];
				}
				for (int j : pos) { // Proper spacing
					out.add(" " + j);
				}
				out.add("\n");
				line = in.readLine();
			}
			for (String string : out) {
				System.out.print(string);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}

class Node {
	int[] positions = {};
	Node[] children = new Node[26]; // Preallocates one position each for [a-z]. No other input is allowed
}