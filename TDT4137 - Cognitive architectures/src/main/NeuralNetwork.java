package main;

import java.util.Arrays;

public class NeuralNetwork {
	private double[] weights;
	private final double learningRate, threshold;
	private final boolean print;
	
	public NeuralNetwork(int numberOfInputs, double learningRate, double threshold, boolean print) {
		weights = new double[numberOfInputs];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = Math.random() - 0.5;
		}
		if (print)
			System.out.println("Initializing weights to: " + Arrays.toString(weights));
		
		this.learningRate = learningRate;
		this.threshold = threshold;
		this.print = print;
	}
	
	public NeuralNetwork(double[] startingWeights, double learningRate, double threshold, boolean print) {
		weights = startingWeights;
		if (print)
			System.out.println("Initializing weights to: " + Arrays.toString(weights));
		this.learningRate = learningRate;
		this.threshold = threshold;
		this.print = print;
	}

	public static void main(String[] args) {
		double learningRate = 0.1, threshold = 0.5;
		
		// AND function
		NeuralNetwork network = new NeuralNetwork(new double[] {0.5, 0.2}, learningRate, threshold, true);
		
		IO[] exercises = new IO[4];
		exercises[0] = new IO(new double[] {0, 0}, false);
		exercises[1] = new IO(new double[] {0, 1}, false);
		exercises[2] = new IO(new double[] {1, 0}, false);
		exercises[3] = new IO(new double[] {1, 1}, true);
		
		network.train(exercises);
		
		// OR function
		System.out.println();
		
		network = new NeuralNetwork(new double[] {0.1, 0.1}, learningRate, threshold, true);
		
		// The first and last exercise are still correct, so left unchanged
		exercises[1] = new IO(new double[] {0, 1}, true);
		exercises[2] = new IO(new double[] {1, 0}, true);
		
		network.train(exercises);
	}
	
	private void train(IO[] exercises) {
		boolean convergence = false;
		int epoch = 1;
		while (!convergence) {
			convergence = true;
			for (IO io : exercises) {
				boolean result = getResult(io.inputs);
				if (print)
					System.out.println(epoch + ": Weights " + toString() + " and inputs " + Arrays.toString(io.inputs) + " gave the result " + result);
				if (result != io.expectedOutput) {
					convergence = false;
					updateWeights(io, result);
				}
			}
			epoch++;
		}
	}

	private void updateWeights(IO io, boolean result) {
		int error = io.expectedOutput ? 1 : -1;
		for (int i = 0; i < weights.length; i++) {
			weights[i] += learningRate * io.inputs[i] * error;
		}
		System.out.println("\tWeights updated to " + toString());
	}

	private boolean getResult(double[] inputs) {
		double sum = 0;
		for (int i = 0; i < inputs.length; i++) {
			sum += inputs[i] * weights[i];
		}
		return sum >= threshold;
	}

	private static class IO {
		private final double[] inputs;
		private final boolean expectedOutput;

		public IO(double[] inputs, boolean expectedOutput) {
			this.inputs = inputs;
			this.expectedOutput = expectedOutput;
		}
	}
	
	public String toString() {
		return Arrays.toString(weights);
	}
}
