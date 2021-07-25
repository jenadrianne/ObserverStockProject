
public interface Observer {
	/**
	 * Notify function
	 * @param invoice
	 * @param trader
	 * @param type
	 * @param stockName
	 */
	public void notifyUpdates(StockPurchase invoice, Trader trader, String type, String stockName);
}
