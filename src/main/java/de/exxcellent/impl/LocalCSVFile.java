package de.exxcellent.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import de.exxcellent.core.CSVTableOperator;
import de.exxcellent.core.LocalFileLoader;

/**
 * This class implements the mechanisms to open a CSV file locally and the needed operations in order to fulfill 
 * the programming challenge. 
 * @author Matthias Bauer
 */
public class LocalCSVFile extends LocalFileLoader implements CSVTableOperator{
	//Here, an ArrayList is in order to dynamically load an unknown number of data rows.
	private ArrayList<ArrayList<String>> loadedCSVTable = null;
	private static final String _STRING_SEPARATOR = ",";
	
	public LocalCSVFile(String pathToCSV) {
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
		//If CSV file can be opened read and store it's contents.
		loadedCSVTable = new  ArrayList<ArrayList<String>>();
		if(readCSVTable() == 0) {
			System.out.println("CSV file cannot be loaded.");
			return;
		}
		if (checkTable()) {
			System.out.println("Table seems to be formatted properly.");	
		}
		System.out.println("CSV file has been loaded successfully.");
	}

	/**
     * Splits a line (String) by the separator defined by  @link _STRING_SEPARATOR and 
     * returns an ArrayList with the separated values.
     * @return ArrayList with separated values or an empty ArrayList.
     */
	private ArrayList<String> createRow(String line) {
		String[] rowValues = line.split(_STRING_SEPARATOR);
		return new ArrayList<String>(Arrays.asList(rowValues));
	}
	
	/**
     * Reads the content of the loaded file into memory row by row.
     * @return number of rows read from the file or zero if something is wrong.
     * @todo Account for empty entries.
     */
	public int readCSVTable() {
		int lineCounter = 0;
		String line = "";
		try {
			line = fileLineReader.readLine();
			//Read CSV table row by row.		
			while (line != null) {
				lineCounter++;
				ArrayList<String> row = createRow(line);
				loadedCSVTable.add(row);
				//Assume, for now, that file is correctly formated.
				line = fileLineReader.readLine();
			}	
		} catch (IOException e) {
			System.out.println("Cannot read line " + lineCounter);
			return 0;
		}
		return lineCounter;
	}
		
	@Override
	public ArrayList<String> getRow(int id) {
		if (id < loadedCSVTable.size()) {
			return loadedCSVTable.get(id);
		}
		return null;
	}

	@Override
	public ArrayList<String> getColumn(String name) {
		//There must be at least two rows inside the table.
		if (loadedCSVTable == null || loadedCSVTable.size()<2) {
			return null;
		}
		//Get row with column names.
		ArrayList<String> firstRow = loadedCSVTable.get(0);
		int colId = 0;
		boolean foundCol = false;
		for (String cName : firstRow) {
			if (cName.equals(name)) {
				foundCol = true;
				break;
			}
			colId++;
		}
		//Check if the column has been found and return null if that is not the case.
		if (!foundCol) {
			return null;
		}
		//Get all rows values for the found column.
		int rowCount = 0;
		ArrayList<String> column = new ArrayList<String>();
		for (int i = 1; i < loadedCSVTable.size(); i++) {
			if (loadedCSVTable.get(i).size() < colId) {
				System.out.println("Row "+ rowCount +" does not seem to have the right number of colums.");
			} else {
				column.add(loadedCSVTable.get(i).get(colId));
			}
			rowCount++;
		}
		//Remove first entry since it contains the name
		return column;
	}

	@Override
	public boolean checkTable() {
		//There must be at least two rows inside the table.
		if (loadedCSVTable == null || loadedCSVTable.size()<2) {
			return false;
		}
		int numCols = loadedCSVTable.get(0).size();
		int rowCount = 0;
		//Check if each row has the same amount of columns.
		for (ArrayList<String> row: loadedCSVTable) {
			if (row.size() != numCols) {
				System.out.println("Row "+ rowCount +" does not seem to have the right number of colums.");
				return false;
			}
			rowCount++;
		}
		return true;
	}

	@Override
	public int getNumberOfRows() {
		if (loadedCSVTable!= null) {
			return loadedCSVTable.size();
		}
		return 0;
	}

	@Override
	public int getNumberOfColumns() {
		//Only access the list if it has been initialized and there is at least one row.
		//Here, it is assumed that the table was formated properly (#columns is the same for each row).
		if (loadedCSVTable!= null || loadedCSVTable.get(0).size() > 0 ) {
			//Number of columns is equal to the number of entries in the first row.
			return loadedCSVTable.get(0).size();
		}
		return 0;
	}

	@Override
	public String getEntry(int rowId, String columnName) {
		if (loadedCSVTable== null || loadedCSVTable.size() == 0 ) {
			return null;
		}
		if(rowId >= loadedCSVTable.size() || !loadedCSVTable.get(0).contains(columnName)) {
			return null;
		}
		ArrayList<String> column = getColumn(columnName);
		if (rowId < column.size()) {
			return column.get(rowId);
		}
		return null;
	}
}
