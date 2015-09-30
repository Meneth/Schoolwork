package encapsulation;

import java.lang.Math;

public class Rectangle {
	private int minX; // Edge to the left
	private int maxX; // Edge to the right
	private int minY; // Edge to the top
	private int maxY; // Edge to the bottom
	
	public int getMinX() { return minX; }
	public int getMaxX() { return maxX; }
	public int getMinY() { return minY; }
	public int getMaxY() { return maxY; }
	public int getWidth() {
		if (isEmpty()) { return 0; }
		return maxX - minX + 1; // For some reason a point apparently has a width and height of 1. Blame the spec
	}
	public int getHeight() {
		if (isEmpty()) { return 0; }
		return maxY - minY + 1;
	}
	public boolean isEmpty() {
		return minX == 0 && minY == 0 && maxX == 0 && maxY == 0;
	}
	public boolean contains(int x, int y) {
		if (isEmpty()) {
			return false;
		}
		return minX <= x && x <= maxX && minY <= y && y <= maxY;
	}
	public boolean contains(Rectangle rect) {
		return contains(rect.getMaxX(), rect.getMaxY()) && contains(rect.getMinX(), rect.getMinY());
	}
	public boolean add(int x, int y) {
		if (contains(x, y)) { // Note: Will return false for all 0-size rectangles even if x = y = 0
			return false;
		}
		if (isEmpty()) { // Has to be replaced by the point if empty according to the spec
			if (x == 0 && y == 0) {
				return false; // Rectangle unchanged
			}
			maxX = x;
			minX = x;
			maxY = y;
			minY = y;
			return true;
		}
		maxX = Math.max(x, maxX);
		minX = Math.min(x, minX);
		maxY = Math.max(y, maxY);
		minY = Math.min(y, minY);
		return true;
	}
	public boolean add(Rectangle rect) {
		return add(rect.getMaxX(), rect.getMaxY()) | add(rect.getMinX(), rect.getMinY());
	}
	public Rectangle union(Rectangle rect) {
		Rectangle union = new Rectangle();
		union.add(this);
		union.add(rect);
		return union;
	}
	public Rectangle intersection(Rectangle rect) {
		Rectangle intersection = new Rectangle();
		if (intersects(rect)) { // If there's no intersection, taking these min/max values would give inaccurate results
			intersection.maxX = Math.min(this.maxX, rect.maxX);
			intersection.maxY = Math.min(this.maxY, rect.maxY);
			intersection.minX = Math.max(this.minX, rect.minX);
			intersection.minY = Math.max(this.minY, rect.minY);
		}
		return intersection;
	}
	public boolean intersects(Rectangle rect) {
		if (contains(rect.maxX, rect.maxY) || contains(rect.minX, rect.minY)) { //Checking two corners is enough
			return true; // At least one corner contained within this rectangle
		}
		if (rect.contains(maxX, minY) || rect.contains(minX, maxY)) { //Checking the other two corners
			return true; // Checking top-left and bottom right of one rect, and top-right and bottom left of the other
		}                // is enough to account for any intersection. Alternatively one could check all 4 corners of one rect,
		return false;    // and also check if the other rect is contained within it
	}
	public String toString() {
		return ("Right-most edge at " + maxX + " on the x-axis. Left-most edge at " + minX + " on the x-axis."
				+ " Top edge at " + maxY + " on the y-axis. Bottom edge at " + minY + " on the y-axis.");
	}
}
