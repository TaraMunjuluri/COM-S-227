package hw1;

/**
 * This program models being printed from a simple printer
 * @author taram
 */
public class Printer {
	private int sheetsAvailable;
	private int nextPage;
	private int totalPages;
	private int traySheetsAvailable;
	private int trayCap;
	private int pagesToPrint;
	/*
	 * public constructor that constructs a new printer with maximum tray capacity
	 * 
	 * @param takes in trayCapacity 
	 */
	public Printer(int trayCapacity) {
		trayCap = trayCapacity;
	}
	/*
	 * Returns number of sheets available for printing
	 * @return an int value representing number of sheets that are available
	 */
	public int getSheetsAvailable() {
		return sheetsAvailable;
	}
	/*
	 * Simulates removing tray and adding given numbers of sheets and replacing tray
	 * in the printer
	 */
	public void addPaper(int sheets) {
		sheetsAvailable = Math.min(trayCap,sheetsAvailable + sheets);
		traySheetsAvailable = Math.min(trayCap, traySheetsAvailable + sheets);
		replaceTray();
	}
	/*
	 * Starts new print job to make copies of a document that is specified page length
	 * @param takes in documentPages
	 */
	public void startPrintJob(int documentPages) {
		pagesToPrint = documentPages;
		nextPage=0;
	}
	/*
	 * Simulates removing the tray and removing given number of sheets and replacing
	 * tray in printer
	 */
	public void removePaper(int sheets) {
		traySheetsAvailable = Math.max(traySheetsAvailable - sheets, 0);
		replaceTray();
	}
	
	/*
	 * Simulates the printer printing a page
	 */
	public void printPage() {
		int printPageOption = Math.min(sheetsAvailable, 1);
		nextPage += printPageOption;
		nextPage = nextPage % pagesToPrint;
		sheetsAvailable = sheetsAvailable - printPageOption;
		traySheetsAvailable = traySheetsAvailable - printPageOption;
		totalPages += printPageOption;
		
	
	}
	/*
	 * Removes paper tray in printer and makes the sheet available to printer 0
	 */
	public void removeTray() {
		sheetsAvailable = 0;
	}
	/*
	 * Replaces tray in printer and makes sheets available to printer equal to
	 * sheets in tray
	 */
	public void replaceTray() {
		sheetsAvailable = traySheetsAvailable;
	}

	/*
	 * Returns the next page number of the document that will be printed
	 * 
	 * @return an int value representing the page number of the document
	 */
	public int getNextPage() {
		return nextPage;
	}
	/*
	 * Returns the count of all pages printed by the printer since its construction
	 * 
	 * @return an int value of number of pages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/*
	 * 
	 * Sets total pages
	 */

	public int getTraySheetsAvailable() {
		return traySheetsAvailable;
	}




	
}
