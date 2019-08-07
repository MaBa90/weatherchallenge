package de.exxcellent.core.interfaces;

import java.util.ArrayList;

/**
 * Basic interface to define functions that are necessary to access the contents of a loaded CVS file. 
 * It is defined as interface since the way of retrieving data can be different depending on the access scheme (web, local).
 * It is further assumed that each CSV file has the first row reserved for defining the names of the columns.
 * @author Matthias Bauer
 */
public interface CSVTableOperator {
	/**
     * Get's a row by id, represented as ArrayList. If the id exceeds the number of rows, the last row is returned.
     * @param id the id of the row (starting with zero).
     * @return An ArrayList with the entries being equal to the value of the rows or null if the row cannot be found.
     */
	public ArrayList<String> getRow(int id);
	
	 /**
     * Get's a column by it's name, represented as ArrayList with the entries being equal to the values of the column.
     * Only adds values of a row if it has the defined number of columns (# of names).
     * The first row is skipped since it should contain the name of the column.
     * @param name identifier of the column.
     * @return An ArrayList having the found entries for the column (can be empty), null if the CSV file has not been loaded or if the name of the column could not be found. 
     */
	public ArrayList<String> getColumn(String name);
	
	 /**
     * Checks the file for validity, e.g., if the same amount of columns can be found in every row.
     * @return true if the formation of the table is correct.
     */
	public boolean checkTable();
	
	 /**
     * Returns the number of rows (lines in the CSV file).
     * @return number of rows, 0 if the CSV file has not been loaded.
     */
	public int getNumberOfRows();
	
	 /**
     * Returns the number of columns (values separated by the defined separator) in the rows.
     * Here, the number of columns is referred to the names defined in the first row.
     * @return number of columns, 0 if the CSV file has not been loaded.
     */
	public int getNumberOfColumns();

	
	 /**
     * Gets a specific entry of the table at a position (referred as (row,column) entry).
     * @param rowId where the entry 0 is the second row in the dataset.
     * @param columnName identifier of column.
     * @return the value represented as string, null if the position cannot be accessed or the CSV file has not been loaded.
     */
	public String getEntry(int rowId, String columnName);
}
