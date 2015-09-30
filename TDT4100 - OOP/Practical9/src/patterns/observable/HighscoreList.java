package patterns.observable;

@SuppressWarnings("serial")
public class HighscoreList extends ObservableList<Integer> {
	private final int maxSize;
	
	public HighscoreList(int maxSize) {
		this.maxSize = maxSize;
	}
	
	public void addResult (int r) {
		for (int i = 0; i < size(); i++) {
			if (r < get(i)) {
				if (size() == maxSize)
					removeElement(maxSize-1); // Removes worst result
				addElement(i, r);
				return;
			}
		}
		if (size() < maxSize) {
			addElement(r);
		}
	}
	
	@Override
	public boolean acceptsElement(Integer o) {
		for (int i = 0; i < size(); i++) {
			if (o < get(i)) {
				return true;
			}
		}
		return size() < maxSize;
	}
}
