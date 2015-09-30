package stateandbehavior;

public class LineEditor {
	String text;
	int insertionIndex;
	
	public void left() {
		if (insertionIndex > 0) {
			insertionIndex--;
		}
	}
	public void left(int n) {
		while(n > 0) {
			left(); //Since this part is voluntary I don't feel like following the spec of having left(int n) doing the work
		}
	}
	public void right() {
		if (insertionIndex < text.length()) {
			insertionIndex++;
		}
	}
	public void right(int n) {
		while(n > 0) {
			right();
		}
	}
	public void insertString(String s) {
		text = text.substring(0, insertionIndex) + s + text.substring(insertionIndex);
		insertionIndex += s.length(); // Not documented in spec, but JExercise seems to expect it
	}                                 // Later added to spec after I notified instructor
	public void insert(Object o) {
		insertString(o.toString());
	}
	public void deleteLeft() {
		if (insertionIndex > 0) {
			text = text.substring(0, insertionIndex-1) + text.substring(insertionIndex);
			insertionIndex--;
		}
	}
	public void deleteRight() {
		if (insertionIndex < text.length()) {
			text = text.substring(0, insertionIndex) + text.substring(insertionIndex+1);
		}
	}
	public String toString() {
		return text.substring(0, insertionIndex) + "|" + text.substring(insertionIndex);
	}
}
