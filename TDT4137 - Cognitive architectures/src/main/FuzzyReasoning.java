package main;

public class FuzzyReasoning {
	static class Set {
		public static void main(String[] args) {
			Set[] distances = getDistances();
			Set[] deltas = getDeltas();
			Set[] actions = getActions();
			// 3.6 and 1.1 are (roughly) the values given in task A. Edit those to change the distance and delta
			findResult(distances, deltas, actions, 3.6, 1.1);
		}
		
		private static void findResult(Set[] distances, Set[] deltas,
				Set[] actions, double distance, double delta) {
			// IF distance is VerySmall THEN action is BrakeHard
			actions[0].setClip(distances[0].getValue(distance));
			// IF distance is Small AND delta is Stable THEN action is SlowDown
			actions[1].setClip(Math.min(distances[1].getValue(distance), deltas[2].getValue(delta)));
			// IF distance is Small AND delta is Growing THEN action is None
			actions[2].setClip(Math.min(distances[1].getValue(distance), deltas[3].getValue(delta)));
			// IF distance is Perfect AND delta is Growing THEN action is SpeedUp
			actions[3].setClip(Math.min(distances[2].getValue(distance), deltas[3].getValue(delta)));
			// IF distance is VeryBig AND (delta is NOT Growing OR delta is NOT GrowingFast) THEN action is FloorIt
			actions[4].setClip(Math.min(distances[4].getValue(distance), Math.max(1 - deltas[3].getValue(delta), 1 - deltas[4].getValue(delta))));
			double centerOfGravity = findCenterOfGravity(-10, 10, 20, actions);
			double max = 0;
			int bestIndex = -1;
			for (int i = 0; i < actions.length; i++) {
				if (actions[i].getValue(centerOfGravity) > max) {
					max = actions[i].getValue(centerOfGravity);
					bestIndex = i;
				}
			}
			System.out.println("The action at index " + bestIndex + " will be taken.");
		}

		private static Set[] getActions() {
			Set s0 = new Set(Set.Shape.REVERSEGRADE, -8, -5);
			Set s1 = new Set(Set.Shape.TRIANGLE, -7, -4, -1);
			Set s2 = new Set(Set.Shape.TRIANGLE, -3, 0, 3);
			Set s3 = new Set(Set.Shape.TRIANGLE, 1, 4, 7);
			Set s4 = new Set(Set.Shape.GRADE, 5, 8);
			return new Set[] {s0, s1, s2, s3, s4};
		}

		private static Set[] getDeltas() {
			Set s0 = new Set(Set.Shape.REVERSEGRADE, -4, -2.5);
			Set s1 = new Set(Set.Shape.TRIANGLE, -3.5, -2, -0.5);
			Set s2 = new Set(Set.Shape.TRIANGLE, -1.5, 0, 1.5);
			Set s3 = new Set(Set.Shape.TRIANGLE, 0.5, 2, 3.5);
			Set s4 = new Set(Set.Shape.GRADE, 2.5, 4);
			return new Set[] {s0, s1, s2, s3, s4};
		}

		private static Set[] getDistances() {
			Set s0 = new Set(Set.Shape.REVERSEGRADE, 1, 2.5);
			Set s1 = new Set(Set.Shape.TRIANGLE, 1.5, 3, 4.5);
			Set s2 = new Set(Set.Shape.TRIANGLE, 3.5, 5, 6.5);
			Set s3 = new Set(Set.Shape.TRIANGLE, 5.5, 7, 8.5);
			Set s4 = new Set(Set.Shape.GRADE, 7.5, 9);
			return new Set[] {s0, s1, s2, s3, s4};
		}

		public static double findCenterOfGravity(double start, double end, int intervals, Set...sets) {
			double weightedSum = 0, sum = 0;
			for (int i = 0; i <= intervals; i++) {
				double position = start + (end - start) * i / intervals;
				double max = 0;
				for (Set set : sets) {
					if (set.getValue(position) > max)
						max = set.getValue(position);
				}
				weightedSum += position * max;
				sum += max;
			}
			return weightedSum / sum;
		}
		
		enum Shape {
			TRIANGLE, GRADE, REVERSEGRADE;
		}
		private final Shape shape;
		private final double[] points;
		private double clip;
		
		public Set(Shape shape, double...points) {
			this.shape = shape;
			this.points = points;
			clip = 1;
		}
		
		/**
		 * Returns the value of the set at a given position
		 */
		public double getValue(double position) {
			switch (shape) {
			case TRIANGLE:
				return triangle(position, points[0], points[1], points[2], clip);
			case GRADE:
				return grade(position, points[0], points[1], clip);
			case REVERSEGRADE:
				return reversegrade(position, points[0], points[1], clip);
			default:
				throw new IllegalStateException();
			}
		}
		
		/**
		 * Sets what value the set clips at
		 */
		public void setClip(double clip) {
			this.clip = clip;
		}
		
		public static double triangle(double position, double x0, double x1,
				double x2, double clip) {
			double value = 0.0;
			if (position >= x0 && position <= x1)
				value = (position - x0) / (x1 - x0);
			else if (position >= x1 && position <= x2)
				value = (x2 - position) / (x1 - x0);
			if (value > clip)
				value = clip;
			return value;
		}

		public static double grade(double position, double x0, double x1,
				double clip) {
			double value = 0.0;
			if (position >= x1)
				value = 1.0;
			else if (position <= x0)
				value = 0.0;
			else
				value = (position - x0) / (x1 - x0);
			if (value > clip)
				value = clip;
			return value;
		}
		
		public static double reversegrade(double position, double x0, double x1,
				double clip) {
			double value = 1 - grade(position, x0, x1, clip);
			if (value > clip)
				value = clip;
			return value;
		}
	}
}
