
public interface Subject {
	/**
	 * Interface : Register Observer to the list
	 * @param info
	 */
	public void registerObserver(Trader info);
	
	/**
	 * Interface : Delete Observer from the list
	 * @param info
	 */
	public void unregisterObsever(Trader info);
	
	/**
	 * Uses the notify function from the observer 
	 * @param invoice
	 * @param trader
	 */
	public void notifySellObserver(StockPurchase invoice, Trader trader);
	
	/**
	 * Uses the notify function from the observer
	 * @param invoice
	 * @param trader
	 */
	public void notifyBuyObserver(StockPurchase invoice, Trader trader);
	
	/**
	 * Display all observer infromation 
	 * Uses the display function from the observer
	 */
	public void displayObserver();
}
