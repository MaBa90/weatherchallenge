package de.exxcellent.impl;

import de.exxcellent.core.LocalFileLoader;

/**
 * This class implements the mechanisms to open a CSV file locally and the needed operations in order to fulfill 
 * the programming challenge. 
 * @author Matthias Bauer
 */
public class LocalCSVReader extends LocalFileLoader {
	
	public LocalCSVReader(String pathToCSV) {
		System.out.println("Loading file " + pathToCSV);
		//Check if the file ends with .csv and is long enough to be a path.
		if (pathToCSV.length()>4 &&
			!pathToCSV.substring(pathToCSV.length()-4,pathToCSV.length()).equals(".csv")) {
			System.out.println("The file does not seem to be a CSV file.");
			//File not loaded.
			return;
		}
		//Load file locally.
		if (!loadFile(pathToCSV)) {
			System.out.println("CSV file cannot opened.");
			//File not loaded.
			return;
		} 
		System.out.println("CSV file has been loaded successfully");
	}
}
