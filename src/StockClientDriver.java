import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StockClientDriver {
	private static Scanner scan = new Scanner(System.in); 
	public static List<Trader> tradersList = new ArrayList<Trader>();
	public static List<Stock> stockList = new ArrayList<Stock>();
	
	public static void main(String[] args) {
		
		//populate Trader
		populateTrader(); 
		
		//populate stock;
		populateStock();
		
		//populate observers in subjects;
		populateWatchList();
		int option = 0; 
		do {
			option = menu(); 
			switch(option) {
			case 1: 
				Buy();
				break;
			case 2: 
				Sell();
				break;
			default: 
				System.exit(0);
			}
			
		}while(option > 0 && option < 5);
		
		scan.close();
	}
	
	/**
	 * Menu Function
	 * @return
	 */
	private static int menu() {
		System.out.println("\n\n===============MENU==================");
		System.out.println("1- Buy Stock"); 
		System.out.println("2- Sell Stock");  
		System.out.println("\n\nSelect an option : "); 
		return scan.nextInt();
	}
	
	/** 
	 * Populate or Create 5 concrete trader information 
	 */
	private static void populateTrader() {
		tradersList.add(new Trader("doe", "john", "doejohn", "12345"));
		tradersList.add(new Trader("doe", "jane", "doejane", "12345"));
		tradersList.add(new Trader("doe", "joanne", "doejoanne", "12345"));
		tradersList.add(new Trader("doe", "joseph", "doejoseph", "12345"));
		tradersList.add(new Trader("doe", "jerico", "doejerico", "12345"));
	}
	
	/** 
	 * Populate or Create 3 concrete stock information 
	 */
	private static void populateStock() {
		stockList.add(new Stock("jawwal", 100, 1000, 100, 100));
		stockList.add(new Stock("ooredoo",  180, 1500, 180, 200));
		stockList.add(new Stock("gas",  200, 2000, 200, 20));
	}
	
	/** 
	 * Populate WatchList
	 */
	private static void populateWatchList() {
		String option = ""; 
		
		displayAllTraderInfromation();
		
		for(Stock s : stockList) {
			System.out.println("\n**********************************************"); 
			System.out.format("\nAdd Observers for %s stocks.", s.getStockName());
			do {
				System.out.println("\nEnter Trader ID: ");
				Trader trader = getTraderByID(scan.nextInt());
				s.registerObserver(trader);
				
				System.out.format("\nAdd more observers for %s stock ? (Yes/No)", s.getStockName());
				option = scan.next().trim();
			}while(option.equalsIgnoreCase("yes")); 
			
		}
		
		
	}
	
	/**
	 * Display all TraderInformation
	 */
	public static void displayAllTraderInfromation() {
		System.out.println("\n\n***********************************************"); 
		System.out.println("Traders Information : "); 
		
		for(Trader trader: tradersList) {
			trader.displayTraderInfo();
			System.out.println("------\n");
		}
	}
	
	/**
	 * Display all StockInfromation
	 */
	public static void displayAllStockInformation() {
		System.out.println("\n\n***********************************************"); 
		System.out.println("Stock Information : "); 
		
		for(Stock stock: stockList) {
			stock.viewStock();
			System.out.println("------\n");
		}
	}
	
	/**
	 * Buy Stocks
	 */
	public static void Buy() {
		displayAllStockInformation(); 
		System.out.println("\nBuy Stock . Please enter ID : ");
		Integer stockID = scan.nextInt();
		Stock data = getStockByID (stockID);
		
		if(data != null) {
			data.Buy();
		}
		
	}
	
	/**
	 * Buy Stocks
	 */
	public static void Sell() {
		displayAllStockInformation(); 
		System.out.println("\n Sell Stock . Please enter ID : ");
		Integer stockID = scan.nextInt();
		Stock data = getStockByID (stockID);
		
		if(data != null) {
			data.Sell();
		}
	}
	
	/**
	 * Get Stock by ID
	 * @param id
	 * @return
	 */
	public static Stock getStockByID(int id) {
		Stock data = null;
		for(Stock temp : stockList) {
			if(temp.getStockID() == id) {
				data= temp; 
				break;
			}
		}
		
		return data;
	}

	/**
	 * Get TraderByID
	 * @param id
	 * @return
	 */
	public static Trader getTraderByID(int id) {
		Trader data = null;
		for(Trader temp : tradersList) {
			if(temp.getTraderId() == id) {
				data= temp; 
				break;
			}
		}
		
		return data;
	}
}
