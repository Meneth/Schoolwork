package application;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

public class SpaceObject extends BaseSpaceObject {
	private double vx, vy;
	
	public SpaceObject() {
		super();
	}

	public Point2D getVelocity() {
		return new Point2D(vx, vy);
	}
	
	public Point2D getSpeed() { return getVelocity(); }

	public void setVelocity(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	public void accelerate(double ax, double ay) {
		vx += ax;
		vy += ay;
	}
	
	public void applyForce(double fx, double fy) {
		if (getMass() == 0) {
			throw new IllegalStateException("Cannot apply force to a massless object.");
		}
		accelerate(fx / getMass(), fy / getMass());
	}

	public double getMass() {
		return 0;
	}

	public void tick() {
		translate(vx, vy);
	}

	public boolean intersects(SpaceObject so1) {
		for (int i = 0; i < so1.getPointCount(); i++) {
			if (contains(so1, i))
				return true;
		}
		for (int i = 0; i < getPointCount(); i++) {
			if (so1.contains(this, i))
				return true;
		}
		if (contains(parentToLocal(so1.getCenter(true))))
			return true;
		return so1.contains(so1.parentToLocal(getCenter(true)));
	}
}
