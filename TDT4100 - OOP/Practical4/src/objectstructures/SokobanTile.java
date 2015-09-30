package objectstructures;

public class SokobanTile {
	private String type;
	private String contents;
	public SokobanTile(char c) {
		contents = "empty";
		type = "empty";
		if (c == '#') {
			type = "wall";
			contents = "wall";
		} else if (c == '.') {
			type = "goal";
		} else if (c == '$') {
			contents = "box";
		} else if (c == '*') {
			contents = "box";
			type = "goal";
		} else if (c == '@') {
			contents = "player";
		} else if (c == '+') {
			contents = "player";
			type = "goal";
		} else if (c == ' ') { }
		else {
			throw new IllegalArgumentException("Invalid character: " + c);
		}
	}
	public void setContents(String s) {
		if (((s == "player" || s == "box") && isEmpty()) || s == "empty") {
			contents = s;
		} else {
			throw new IllegalArgumentException("Invalid content set attempted.");
		}
	}
	
	public String getType() {
		return type;
	}
	public String getContents() {
		return contents;
	}
	public boolean isEmpty() {
		return contents == "empty";
	}
	public String toString() {
		if (type == "wall") {
			return "#";
		} else if (type == "goal") {
			if (contents == "box") {
				return "ø";
			} else if (contents == "player") {
				return "¤";
			} else {
				return "o";
			}
		} else {
			if (contents == "box") {
				return "|";
			} else if (contents == "player") {
				return "x";
			} else {
				return " ";
			}
		}
	}
}
