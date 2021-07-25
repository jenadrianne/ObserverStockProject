import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Stock implements Subject{
	
	/*
	 * State Variables
	 */
	private int StockID; 
	private String StockName; 
	private double currPrice; 
	private double maxPrice; 
	private double minPrice; 
	private int stockQty;
	private Date lastModified; 
	private List<Trader> tradersList; 

	/*
	 * Constructors
	 */
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Custom Constructor 
	 * @param stockName
	 * @param currPrice
	 * @param maxPrice
	 * @param minPrice
	 * @param stockQty
	 */
	public Stock(String stockName, double currPrice, double maxPrice, double minPrice, int stockQty) {
		this.StockName = stockName; 
		this.currPrice = currPrice; 
		this.maxPrice = maxPrice; 
		this.minPrice = minPrice; 
		this.stockQty = stockQty; 
		Random random = new Random();
        this.StockID = Math.abs(random.nextInt());
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		this.lastModified = date;
		
		tradersList = new ArrayList<Trader>();
	}

	
	public List<Trader> getTradersList() {
		return tradersList;
	}

	public void setTradersList(List<Trader> tradersList) {
		this.tradersList = tradersList;
	}

	public int getStockID() {
		return StockID;
	}

	public void setStockID(int stockID) {
		StockID = stockID;
	}

	public String getStockName() {
		return StockName;
	}

	public void setStockName(String stockName) {
		StockName = stockName;
	}

	public double getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(double currPrice) {
		this.currPrice = currPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	/**
	 * View Stock Information
	 */
	public void viewStock() {
		System.out.format("StockID : %d\n", this.StockID); 
		System.out.format("Stock Name: %s\n ", this.StockName); 
		System.out.format("Stock Current Selling price: $%.2f\n ", this.currPrice); 
		System.out.format("Stock Max Selling price: $%.2f \n", this.maxPrice); 
		System.out.format("Stock Min Selling price: $%.2f \n", this.minPrice); 
	}
	
	/**
	 * Update Stock Information
	 * @param stockName
	 * @param currPrice
	 * @param maxPrice
	 * @param minPrice
	 * @param stockQty
	 */
	public void updateStock(String stockName, double currPrice, double maxPrice, double minPrice, int stockQty) {
		this.StockName = stockName; 
		this.currPrice = currPrice; 
		this.maxPrice = maxPrice; 
		this.minPrice = minPrice; 
		this.stockQty = stockQty; 
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		this.lastModified = date;
	}
	

	/**
	 * Update Stock Information
	 * @param stockName
	 * @param currPrice
	 * @param maxPrice
	 * @param minPrice
	 * @param stockQty
	 */
	public List<Stock>  deleteStock(List<Stock> stockList, int id) {
		for(Stock temp : stockList) {
			if(temp.getStockID() == id) {
				stockList.remove(temp); 
				System.out.println("Info Deleted Successfully");
				break; 
			}
		}
		return stockList;
	}
	
	/**
	 * Add or Create new Stock Item
	 * @param stockName
	 * @param currPrice
	 * @param maxPrice
	 * @param minPrice
	 * @param stockQty
	 * @return
	 */
	public Stock addstock(String stockName, double currPrice, double maxPrice, double minPrice, int stockQty) {
		return new Stock( stockName,  currPrice,  maxPrice,  minPrice,  stockQty); 
	}
	
	
	/**
	 * Check the qty of available stock items
	 * @param stockId
	 * @param stockList
	 * @return
	 */
	public int checkAvailability(int stockId, List<Stock> stockList) {
		for(Stock temp : stockList) {
			if(temp.getStockID() == stockId) {
				return temp.stockQty;
			}
		}
		return 0;
	}
	
	/**
	 * Search Stock 
	 * @param stockList
	 * @param id
	 */
	public boolean searchStock(List<Stock> stockList, int id) {
		for(Stock temp : stockList) {
			if(temp.getStockID() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Register Observer
	 */
	@Override
	public void registerObserver(Trader info) {
		// TODO Auto-generated method stub
		tradersList.add(info);
		
	}

	/**
	 * Delete an observer from list
	 */
	@Override
	public void unregisterObsever(Trader info) {
		// TODO Auto-generated method stub
		tradersList.remove(info);
	}

	/**
	 * Notify all observer for changes when something is sold
	 */
	@Override
	public void notifySellObserver(StockPurchase invoice, Trader trader) {
		System.out.println("Attention:  ");
		if(tradersList.size() >0) {
			for(Trader temp : tradersList) {
				temp.notifyUpdates(invoice, trader, "sold", this.getStockName());
			}
		}
	}

	/**
	 * Notify all observer for changes when something is bought
	 */
	@Override
	public void notifyBuyObserver(StockPurchase invoice, Trader trader) {
		System.out.println("Attention:  ");
		if(tradersList.size() >0) {
			for(Trader temp : tradersList) {
				temp.notifyUpdates(invoice, trader, "bought", this.getStockName());
			}
		}
	}

	/**
	 * Display All Observer Information
	 */
	@Override
	public void displayObserver() {
		System.out.println("\n-----------------"); 
		System.out.println("Observer's List"); 
		System.out.println("\n-----------------"); 
		if(tradersList.size() >0) {
			for(Trader temp : tradersList) {
				temp.displayTraderInfo();
				System.out.println("--------\n");
			}
		}
	}
	/**
	 * Get Observer from the list 
	 * @param id
	 * @return
	 */
	private Trader getObserverFromList(int id) {
		for(Trader temp : tradersList) {
			if(temp.getTraderId() == id) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Buy Stocks
	 */
	public void Buy() {
		Scanner s = new Scanner(System.in); 
		
		displayObserver();
		
		System.out.println("Enter Observer's Id:"); 
		int traderID = s.nextInt();
		System.out.println("Stock Quantity to Buy : "); 
		int qty = s.nextInt(); 
		
		Double stockPrice = this.currPrice * qty;
		
		//verify purchase info
		if(getObserverFromList(traderID) != null && qty <= this.stockQty) {
			StockPurchase invoice = new StockPurchase(this.StockID, traderID, qty ,stockPrice);
			
			//update stock quantity; 
			this.stockQty -=qty;
			
			//notify changes
			notifyBuyObserver(invoice, getObserverFromList(traderID));
		}
	}

	/**
	 * Sell Stocks 
	 */
	public void Sell() {
		Scanner s = new Scanner(System.in); 
		
		displayObserver();
		
		System.out.println("Enter Observer's Id:"); 
		int traderID = s.nextInt();
		System.out.println("Stock Quantity to Sell : "); 
		int qty = s.nextInt(); 
		
		Double stockPrice = this.currPrice * qty;
		
		//verify purchase info
		if(getObserverFromList(traderID) != null && qty <= this.stockQty) {
			StockPurchase invoice = new StockPurchase(this.StockID, traderID, qty ,stockPrice);
			
			//update stock quantity; 
			this.stockQty +=qty;
			
			//notify changes
			notifySellObserver(invoice, getObserverFromList(traderID));
		}
	}
	
}
