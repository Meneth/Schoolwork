package patterns.observable;

public interface ListListener<E> {
	public void listChanged(ObservableList<E> observableList, int i);
}
