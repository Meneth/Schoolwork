package k2011;

public class Rating {
	private double points; // Has to be a double as the points a person gives is multiplied by their rating (a double)
	private double votes;
	
	public void receivePoints(int points, Person giver) {
		if(giver == null)
			throw new IllegalArgumentException("Giver does not exist.");
		if(points < 1 || points > 5)
			throw new IllegalArgumentException("Points must be between 1 and 5.");
		double rating = giver.getRating();
		this.points += points * rating;
		votes += rating;
	}
	
	public double getRating() {
		return points == 0 ? 1 : points / votes; // Avoid division by 0
	}
}
