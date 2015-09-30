package interfaces;

import java.util.Iterator;

public class StringGridIterator implements Iterator<String> {
	private final StringGrid grid;
	private final boolean rowFirst;
	private int row, col;
	public StringGridIterator(StringGrid grid, boolean rowFirst) {
		this.grid = grid;
		this.rowFirst = rowFirst;
		row = rowFirst ? 0 : -1;
		col = rowFirst ? -1 : 0;
	}
	public boolean hasNext() {
		return row < grid.getRowCount() - 1 || col < grid.getColumnCount() - 1;
	}
	public String next() {
		if (!hasNext()) {
			throw new IllegalStateException("End of iterator reached");
		}
		if(rowFirst) {
			col++;
			if(col >= grid.getColumnCount()) {
				col = 0; row++;
			}
		} else {
			row++;
			if(row >= grid.getRowCount()) {
				row = 0; col++;
			}
		}
		return grid.getElement(row, col);
	}
	public void remove() {
		throw new UnsupportedOperationException("remove() not supported");
	}
}
