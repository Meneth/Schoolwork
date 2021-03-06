package k2011;

public class Submission {
	private final String name;
	private final Rating rating = new Rating();
	
	public Submission(String name) {
		this.name = name;
	}

	public void receivePoints(int points, Person giver) {
		rating.receivePoints(points, giver);
	}
	
	public double getRating() {
		return rating.getRating();
	}
}
