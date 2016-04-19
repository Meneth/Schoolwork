package exercise4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.DelayQueue;

public class DecisionTree {
	private static boolean random = true;
	
	public static Node decisionTreeLearning(List<Example> examples, int[] attributes, List<Example> parentExamples) {
		if (examples.isEmpty()) {
			return pluralityValue(parentExamples);
		} else if (sameClassification(examples)) {
			return new Node(examples.get(0).classification);
		} else if (attributes.length == 0) {
			return pluralityValue(examples);
		}
		int a = importance(attributes, examples);
		
		Node n = new Node();
		n.attribute = a;
		
		List<Example> trueExamples = new ArrayList<>();
		List<Example> falseExamples = new ArrayList<>();

		for (Example example : examples) {
			if (example.attributes[a])
				trueExamples.add(example);
			else
				falseExamples.add(example);
		}
		
		int[] newAttributes = new int[attributes.length - 1];
		
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i] < a)
				newAttributes[i] = attributes[i];
			else if (attributes[i] > a)
				newAttributes[i - 1] = attributes[i];
		}
		n.left = decisionTreeLearning(trueExamples, newAttributes, examples);
		n.right = decisionTreeLearning(falseExamples, newAttributes, examples);
		
		return n;
	}
	
	private static int randomImportance(int[] attributes) {
		int i = (int) (Math.random() * attributes.length);
		return attributes[i];
	}
	
	private static int importance(int[] attributes, List<Example> examples) {
		if (random)
			return randomImportance(attributes);
		
		int bestAttribute = attributes[0];
		double mostBits = 0;
		
		for (int a : attributes) {
			double gain = calculateGain(a, examples);
			if (gain > mostBits) {
				mostBits = gain;
				bestAttribute = a;
			}
		}
		
		return bestAttribute;
	}
	
	private static double calculateGain(int a, List<Example> examples) {
		int p_true = 0, p = 0;
		int n_true = 0, n = 0;
		
		for (Example example : examples) {
			if (example.classification) {
				p++;
				if (example.attributes[a])
					p_true++;
			} else {
				n++;
				if (example.attributes[a])
					n_true++;
			}
		}
		
		double e1 = calculateWeightedRemainder(p_true, n_true, p, n);
		double e2 = calculateWeightedRemainder(p - p_true, n - n_true, p, n);
		
		return calculateEntropy(p, n) - (e1 + e2);
	}
	
	private static double calculateWeightedRemainder(double pk, double nk, double p, double n) {
		return (pk + nk) / (p + n) * calculateEntropy(pk, nk);
	}
	
	private static double calculateEntropy(double p, double n) {
		double q = p / (p + n);
		if (q == 1 || q == 0)
			return 0;
		double part1 = q * log2(q);
		double part2 = (1 - q) * log2(1 - q);
		double sum = part1 + part2;
		return -sum;
	}
	
	private static double log2(double n) {
		n = Math.log(n) / Math.log(2);
		return n;
	}

	private static Node pluralityValue(List<Example> examples) {
		int x = 0;
		for (Example example : examples) {
			if (example.classification)
				x++;
		}
		boolean c =  x > examples.size() / 2;
		return new Node(c);
	}
	
	private static boolean sameClassification(List<Example> examples) {
		boolean firstExample = examples.get(0).classification;
		for (Example example : examples) {
			if (example.classification != firstExample)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		List<Example> training = readExamples("training.txt");
		List<Example> test = readExamples("test.txt");
		
		int[] attributes = new int[training.get(0).attributes.length];
		for (int i = 0; i < attributes.length; i++) {
			attributes[i] = i;
		}
		
		Node n = decisionTreeLearning(training, attributes, null);
		
		int correct = 0;
		for (Example e : test) {
			if (e.classification == n.classify(e))
				correct++;
		}
		
		System.out.println("Random:");
		System.out.println("Correct: " + correct);
		System.out.println("Total: " + test.size());
		System.out.println();
		
		random = false;
		n = decisionTreeLearning(training, attributes, null);
		correct = 0;
		for (Example e : test) {
			if (e.classification == n.classify(e))
				correct++;
		}
		System.out.println("Importance:");
		System.out.println("Correct: " + correct);
		System.out.println("Total: " + test.size());
		System.out.println();
		
		Deque<Node> nodes = new ArrayDeque<>();
		Deque<Node> nextLevel = new ArrayDeque<>();
		nodes.add(n);
		System.out.println("Importance decision tree:");
		System.out.println(n);
		int size = 1;
		do {
			nextLevel.clear();
			while (!nodes.isEmpty()) {
				n = nodes.pop();
				if (n.attribute != -1) {
					System.out.print(n.left + " ");
					System.out.print(n.right + " ");
					nextLevel.push(n.left);
					nextLevel.push(n.right);
				}
			}
			System.out.println();
			nodes = new ArrayDeque<>(nextLevel);
			size += nextLevel.size();
		} while (!nextLevel.isEmpty());
		System.out.println();
		System.out.println("Size: " + size);
	}
	
	private static List<Example> readExamples(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		
		List<Example> examples = new ArrayList<>();
		
		String line = in.readLine();
		Example e = null;
		while (line != null) {
			String[] values = line.split("\t");
			boolean[] attributes = new boolean[values.length - 1];
			for (int i = 0; i < values.length - 1; i++) {
				// Assumed that all values are boolean
				attributes[i] = values[i].equals("1");
			}
			e = new Example(attributes, values[values.length - 1].equals("1"));
			examples.add(e);
			line = in.readLine();
		}
		in.close();
		return examples;
	}
}

class Node {
	Node left, right;
	boolean classification;
	int attribute;
	
	public Node() {
		
	}
	
	public Node(boolean classification) {
		attribute = -1;
		this.classification = classification;
	}
	
	public boolean classify(Example e) {
		if (attribute == -1)
			return classification;
		if (e.attributes[attribute])
			return left.classify(e);
		else
			return right.classify(e);
	}
	
	public String toString() {
		if (attribute == -1)
			return classification ? "true" : "false";
		return attribute + "";
	}
}

class Example {
	final boolean[] attributes;
	final boolean classification;
	
	public Example(boolean[] attributes, boolean classification) {
		this.attributes = attributes;
		this.classification = classification;
	}

	@Override
	public String toString() {
		return "Example [attributes=" + Arrays.toString(attributes) + ", classification=" + classification + "]";
	}
}