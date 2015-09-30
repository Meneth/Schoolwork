package patterns.observable;

import java.util.HashMap;

public class Stock {
	private final String ticker;
	private double price;
	private HashMap<StockListener, double[]> listeners;
	
	public Stock(String ticker, double price) {
		super();
		this.ticker = ticker;
		listeners = new HashMap<StockListener, double[]>();
		setPrice(price);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price <= 0)
			throw new IllegalArgumentException("Price cannot be 0 or less.");
		for (StockListener listener : listeners.keySet()) {
			double[] vars = listeners.get(listener);
			switch (vars.length) {
				case 0: listener.stockPriceChanged(this, getPrice(), price);
						break;
				case 2:
					if (Math.abs(vars[0] - price) > vars[1]) {
						listener.stockPriceChanged(this, vars[0], price);
						vars[0] = price;
					}
					break;
				case 3:
					if (price < vars[1] || price > vars[2]) {
						listener.stockPriceChanged(this, vars[0], price);
						vars[0] = price;
					}
			}
			
		}
		this.price = price;
	}

	public String getTicker() {
		return ticker;
	}
	
	public void addStockListener(StockListener listener) {
		if (!listeners.containsKey(listener))
			listeners.put(listener, new double[0]);
	}
	
	public void addStockListener(StockListener listener, double diff) {
		if (!listeners.containsKey(listener))
			listeners.put(listener, new double[] {getPrice(), diff});
	}
	
	public void addStockListener(StockListener listener, double min, double max) {
		if (!listeners.containsKey(listener))
			listeners.put(listener, new double[] {getPrice(), min, max});
	}
	
	public void removeStockListener(StockListener listener) {
		listeners.remove(listener);
	}
	
	public String toString() {
		return getTicker() + " - " + getPrice();
	}
}
