package encapsulation;

public class Location {
	private int x;
	private int y;
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	
	public boolean isValidCoordinates(int x, int y) {
		return x >= maxX && x <= minX && y >= maxY && y <= minY;
	}
	public void up() {
		if (isValidCoordinates(x, y - 1)) { y--; } // Someone prefers the computer definition to the mathematical definition,
	}                                              // so positive y is down, not up
	public void down() {
		if (isValidCoordinates(x, y + 1)) { y++; }
	}
	public void left() {
		if (isValidCoordinates(x - 1, y)) { x--; }
	}
	public void right() {
		if (isValidCoordinates(x + 1, y)) { x++; }
	}
	public void changedBounds() {   // Thought exercise to restore valid coordinates after changed bounds
		if (x < minX) { x = minX; } // Note that this assumes new bounds make physical sense
		if (x > maxX) { x = maxX; } // This will return x and y values even if the rectangle is physically impossible
		if (y < minY) { y = minY; } // E.G., a rectangle with higher minX than maxX
		if (y > maxY) { y = maxY; }
	}
	@SuppressWarnings("unused")
	private boolean validBounds(int minX, int maxX, int minY, int maxY) { // Thought exercise to validate potential new bounds
		return (x > minX && x < maxX && y > minY && y < maxY);
	}
	
	public void setX(int x) {
		if (x > maxX || x < minX) {
			throw new IllegalStateException("X value outside bounds.");
		}
		this.x = x;
	}
	public void setY(int y) {
		if (y > maxY || y < minY) {
			throw new IllegalStateException("Y value outside bounds.");
		}
		this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getMinX() { return minX; }
	public int getMaxX() { return maxX; }
	public int getMinY() { return minY; }
	public int getMaxY() { return maxY; }
	
	public String toString() {
		return "("+x+","+y+")";
	}
}
