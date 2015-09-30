package application;

public class Asteroid extends SpaceObject {
	private final double radius;
	private final double density;
	
	public Asteroid(double density, double radius, double eccentricity, int points) {
		super(); 
		this.density = density;
		this.radius = radius;
		for (int i = 0; i < points; i++) { // Rough circle
			addPolarPoint((i * 2 * Math.PI + eccentricity * Math.random()) / points, radius * 20);
		}
		setTranslateX(radius);
		setTranslateY(radius);
	}
	
	public Asteroid(double density, double radius) {
		this(density, radius, 0.25, 10);
	}

	@Override
	public double getMass() {
		return radius * radius * radius * density; // Pi not necessary, as that can be considered part of the density
	}
}
