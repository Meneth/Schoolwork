import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Change {

	public static short dynamicSolution(ArrayList<Integer> denominations, int value) {
		return dynBackend(denominations, value)[value];
	}
	
	public static short[] dynBackend(ArrayList<Integer> denominations, int value) {
		short[] coinsNeeded = new short[value + 1];
		short best = 0;
		short temp;
		for (int i = 1; i <= value; i++) {
			for (int coin : denominations) {
				if (coin > i)
					break;
				temp = coinsNeeded[i - coin];
				if (temp < best)
					best = temp;
			}
			best++;
			coinsNeeded[i] = best;
		}
		return coinsNeeded;
	}

	public static short greedySolution(ArrayList<Integer> denominations, int value) {
		short coinsNeeded = 0;
		while (value > 0) {
			for (Integer coin : denominations) {
				if (coin <= value) {
					value -= coin;
					coinsNeeded++;
					break;
				}
			}
		}
		return coinsNeeded;
	}

	private static boolean greedySet(ArrayList<Integer> coins) {
		if (coins.size() < 3)
			return true; // 2 or fewer coins is always canonical
		int c2 = coins.get(1);
		int c3 = coins.get(2);
		if (c3 % c2 < c2 - c3 / c2 && c3 % c2 != 0)
			return false;
		int fin = coins.size() - 1;
		boolean greedy = true;
		for (short i = 1; i < fin - 1; i++) {
			if (coins.get(i+1) % coins.get(i) != 0) {
				greedy = false;
				break;
			}
		}
		if (greedy)      // If every coin is a multiple of earlier coins,
			return true; // greedy is guaranteed to work
		int cMax = coins.get(fin);
		short[] dyn = dynBackend(coins, cMax);
		Collections.reverse(coins);
		for (int i = 2; i < cMax; i++) {
			if (greedySolution(coins, i) != dyn[i]) {
				Collections.reverse(coins);
				return false; // Not a tight set;
			}                 // A counter example exists below the max value
		}
		Collections.reverse(coins);
		for (short i = 1; i < fin; i++) {
			int ci = coins.get(i);
			if (ci * 2 <= cMax)
				break;
			for (short j = (short) (i + 1); j < fin; j++) {
				int cj = coins.get(j);
				if (ci + cj > cMax && greedySolution(coins, ci + cj) > 2)
					return false; // A counter example exists that is the combination of two coins
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> coins = new ArrayList<Integer>();
		String line = in.readLine();
		int start = 0;
		int end = line.indexOf(' ');
		Integer i;
		do {
			try {
				i = Integer.parseInt(line.substring(start, end));
			} catch (StringIndexOutOfBoundsException e) {
				i = Integer.parseInt(line.substring(start)); // Final part
			}
			coins.add(i);
			start = end + 1;
			end = line.indexOf(' ', start + 1);
		} while (start != 0);
		in.readLine(); // Skip irrelevant line
		Collections.sort(coins);
		if (greedySet(coins)) {
			Collections.reverse(coins);
			line = in.readLine();
			while (line != null) {
				System.out.println(greedySolution(coins, Integer.parseInt(line)));
				line = in.readLine();
			}
		} else {
			coins.remove(0); // Remove the 1-coin, as that's handled by incrementing
			line = in.readLine();
			while (line != null) {
				System.out.println(dynamicSolution(coins,
						Integer.parseInt(line)));
				line = in.readLine();
			}
		}
	}
}