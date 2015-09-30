package patterns.observable;

import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class ObservableList<E> extends ArrayList<E> {
	private ArrayList<ListListener<E>> listeners;
	
	public ObservableList() {
		super();
		listeners = new ArrayList<>();
	}

	public void addListener(ListListener<E> listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void removeListener(ListListener<?> listener) {
		listeners.remove(listener);
	}
	
	private void alertListeners(int i) {
		for (ListListener<E> listener : listeners) {
			listener.listChanged(this, i);
		}
	}
	
	public E getElement(int i) {
		return get(i);
	}
	
	public void addElement(int i, E o) {
		if (!acceptsElement(o))
			throw new IllegalArgumentException("Invalid element.");
		add(i, o);
		alertListeners(i);
	}
	
	public void addElement(E o) {
		addElement(size(), o);
	}
	
	public void removeElement(int i) {
		remove(i);
		alertListeners(i);
	}
	
	public void addListListener(ListListener<E> listener) {
		addListener(listener);
	}
	
	public void removeListListener(ListListener<E> listener) {
		removeListener(listener);
	}
	
	public abstract boolean acceptsElement(E o);
}