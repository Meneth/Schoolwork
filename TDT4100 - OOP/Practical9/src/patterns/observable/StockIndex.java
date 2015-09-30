package patterns.observable;

import java.util.ArrayList;

public class StockIndex implements StockListener {
	private final String name;
	private ArrayList<Stock> stocks;
	private double index;
	
	public StockIndex(String name, Stock...stocks) {
		super();
		this.name = name;
		this.stocks = new ArrayList<>();
		index = 0;
		for (Stock stock : stocks) {
			addStock(stock);
		}
	}

	public void addStock(Stock stock) {
		if (!stocks.contains(stock)) {
			stock.addStockListener(this);
			stocks.add(stock);
			index += stock.getPrice();
		}
	}
	
	public void removeStock(Stock stock) {
		if (stocks.contains(stock)) {
			stock.removeStockListener(this);
			stocks.remove(stock);
			index -= stock.getPrice();
		}
	}
	
	public double getIndex() {
		return index;
	}

	@Override
	public void stockPriceChanged(Stock stock, double oldPrice, double newPrice) {
		index += newPrice - oldPrice;
	}
	
	public String toString() {
		return name + stocks.toString();
	}
}
