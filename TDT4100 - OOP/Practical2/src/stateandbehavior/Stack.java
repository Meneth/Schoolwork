package stateandbehavior;

import java.util.ArrayList;

public class Stack {
	ArrayList<String> stack = new ArrayList<String>();
	int listSize;
	
	public void push(String pusher) {
		stack.add(pusher);
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
	public int getSize() {
		return listSize;
	}
	public String toString() {
		int index = listSize - 1;
		String string = "";
		while (index >= 0) {
			string += stack.get(index) + ", ";
			index--;
		}
		return string;
	}
}
