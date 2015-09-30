package stateandbehavior;

public class Location {
	int x, y;
	
	public void up() {
		y--; // Someone prefers the computer definition to the mathematical definition,
	}        // so positive y is down, not up
	public void down() {
		y++;
	}
	public void left() {
		x--;
	}
	public void right() {
		x++;
	}
	public String toString() {
		return "("+x+","+y+")";
	}
}
