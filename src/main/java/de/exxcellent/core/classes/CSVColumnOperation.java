package de.exxcellent.core.classes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import de.exxcellent.core.interfaces.CSVTableOperator;
import de.exxcellent.core.interfaces.FileOperation;
import de.exxcellent.impl.LocalCSVFile;

/**
 * This abstract class defines an operation which should be done between multiple columns of a CSV file. 
 * @author Matthias Bauer
 */
public abstract class CSVColumnOperation<ReturnType> implements FileOperation<CSVTableOperator, ReturnType>  {
	//Here, we use a linked hash map since the ordering of the inserted keys could be important.
	protected LinkedHashMap<String,ArrayList<String>> columnValues = null; 
	protected CSVTableOperator storedFile = null;
	
	@Override
	public void addFile(CSVTableOperator file) {
		storedFile = file;	
	}
	
	/**
     * Adds a column read from the CSV file into the LinkedHasMap with it's key being the column identifier.
     * @param colName the name identifier of the column as used in the CSV file.
     * @return true if the column could be added successfully.
     */
	public boolean addColumnByName(String colName) {
		if (storedFile == null ) {
			System.out.println("File is not initialized");
			return false;	
		}
		//Get the column and get data values.
		ArrayList<String> col = storedFile.getColumn(colName);
		if (col == null) {
			System.out.println("Cannot find column with name" + colName + ".");
			return false;
		};
		if (col.size() == 0) {
			System.out.println("Could not retrieve values for column" + colName + ".");
			return false;
		}
		if (columnValues == null) {
			columnValues = new LinkedHashMap<String,ArrayList<String>>();
		}
		//Add new column with values to memory.
		if (columnValues.put(colName, col) != null) {
			System.out.println("Row already contained, replaced previous entry.");
		}	
		return true;
	}
	
	/**
     * Clears the LinkedHashMap and removes all added columns.
     */
	public void removeColumns() {
		if (columnValues != null) {
			columnValues.clear();
		}
	}
}
