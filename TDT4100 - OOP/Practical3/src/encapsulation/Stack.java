package encapsulation;

import java.util.ArrayList;

public class Stack {
	private ArrayList<String> stack = new ArrayList<String>();
	private int listSize; // Not necessary, but is less intensive than calling .size()
	
	public void push(String pusher) {
		stack.add(pusher); // Add to end. Other methods read from (listSize - index) instead of index
		listSize++;
	}
	public String pop() {
		if (listSize > 0) {
			String element = stack.get(listSize - 1);
			stack.remove(listSize - 1);
			listSize--;
			return element;
		}
		return null;
	}
	public String peek(int index) {
		if (index < listSize && index >= 0) {
			return stack.get(listSize - index - 1);
		}
		return null;
	}
	public int getSize() { return listSize; }
	public String toString() {
		int index = this.listSize - 1;
		String string = "";
		while (index >= 0) {
			string += stack.get(index) + ", ";
			index--;
		}
		return string;
	}
}
