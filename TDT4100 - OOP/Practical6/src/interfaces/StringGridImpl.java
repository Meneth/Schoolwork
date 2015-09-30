package interfaces;

import java.util.Iterator;

public class StringGridImpl implements StringGrid {
	private String[][] grid;
	
	public StringGridImpl(int x, int y) {
		grid = new String[x][y];
	}

	@Override
	public Iterator<String> iterator() {
		return new StringGridIterator(this, true);
	}

	@Override
	public int getRowCount() {
		return grid.length;
	}

	@Override
	public int getColumnCount() {
		return grid[0].length;
	}

	@Override
	public String getElement(int row, int column) {
		if (row < 0 || column < 0 || row > getRowCount() || column > getColumnCount()) {
			throw new IllegalArgumentException("Index outside bounds.");
		}
		return grid[row][column];
	}

	@Override
	public void setElement(int row, int column, String element) {
		if (row < 0 || column < 0 || row > getRowCount() || column > getColumnCount()) {
			throw new IllegalArgumentException("Index outside bounds.");
		}
		grid[row][column] = element;
	}
}
