import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Sorting {
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
			char[] chars = line.toCharArray();
			int count = 1;
			for (char c : chars) {
				if (c == ' ')
					count++;
			}
			String[] array = new String[count];
			int start = 0;
			int end = line.indexOf(' ');
			String s;
			int i = 0;
			do { // Substring and indexOf are used as it is faster than StringTokenizer or split
				try { s = line.substring(start, end); }
				catch (StringIndexOutOfBoundsException e) {
					s = line.substring(start); // Final part
				}
				array[i] = "000000".substring(0, 7 - s.length()) + s;
				start = end + 1;
				end = line.indexOf(' ', start + 1);
				i++;
			} while (start != 0);
			array = sort(array);
			line = in.readLine();
			while (line != null) {
				int space = line.indexOf(' ');
				int[] minMax = minMax(line.substring(0, space), line.substring(space + 1), array);
				System.out.println(minMax[0] + " " + minMax[1]);
				line = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int[] minMax(String min, String max, String[] array) {
		min = "000000".substring(0, array[0].length() - min.length()) + min;
		max = "000000".substring(0, array[0].length() - max.length()) + max;
		int[] minMax = new int[2];
		int lower = 0;
		int upper = array.length - 1;
		int mid = 0;
		int compare;
		mid = (lower+upper)/2;
		while (lower != upper) {
			compare = min.compareTo(array[mid]);
			if (compare == 0)
				break;
			else if (compare < 0)
				upper = mid - 1;
			else
				lower = mid;
			mid = (lower+upper+1)/2;
		}
		minMax[0] = Integer.parseInt(array[mid]);
		lower = mid;
		upper = array.length - 1;
		mid = (lower+upper)/2;
		while (lower != upper) {
			compare = max.compareTo(array[mid]);
			if (compare == 0)
				break;
			else if (compare < 0)
				upper = mid;
			else
				lower = mid + 1;
			mid = (lower+upper)/2;
		}
		minMax[1] = Integer.parseInt(array[mid]);
		return minMax;
	}

	@SuppressWarnings("unchecked")
	private static String[] sort(String[] array) { // Radix sort
		LinkedList<String>[] counter = new LinkedList[] {
	        new LinkedList<String>(),
	        new LinkedList<String>(),
	        new LinkedList<String>(),
	        new LinkedList<String>(),
	        new LinkedList<String>(),
	        new LinkedList<String>(),
	        new LinkedList<String>(),
	        new LinkedList<String>(),
	        new LinkedList<String>(),
	        new LinkedList<String>()
	    };
		
		// Find longest
		int length = 1;
		for (String s : array) {
			if (s.length() > length)
				length = s.length();
		}
		for (int i = 0; i < array.length; i++) {
			String s = array[i];
			array[i] = "000000".substring(0, length - s.length()) + s;
		}
		
		for (int i = 0; i < length; i++) {
			for (String s : array) {
				counter[s.charAt(length - i - 1) - '0'].add(s);
			}
			int pos = 0;
			for (int j = 0; j < 10; j++) {
				String value = null;
                while ((value = counter[j].poll()) != null) {
                    //System.out.println(value);
                    array[pos++] = value;
                }
			}
		}
		return array;
	}
}