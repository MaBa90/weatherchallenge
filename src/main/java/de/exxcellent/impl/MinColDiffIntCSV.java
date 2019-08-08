package de.exxcellent.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import de.exxcellent.core.classes.CSVColumnOperation;

/**
 * This class gets the minimal distance between each pair of row values (as Integers) for TWO columns.
 * @author Matthias Bauer
 */
public class MinColDiffIntCSV extends CSVColumnOperation<Integer> {
	private int foundRow = -1;
	private boolean useAbsoluteValue = false;
	
	public MinColDiffIntCSV(LocalCSVFile file) {
		addFile(file);
	}
	
	@Override
	public Integer doOperation() {
		if (storedFile == null) {
			return null;	
		}
		//This operation should only be done on two given columns.
		if ( columnValues== null || columnValues.size() != 2) {
			System.out.println("No or too many columns are present for this operation.");
			return null;
		}
		//Go though the defined columns and get the minimal value.
		ArrayList<String> c1 =  null; 
		ArrayList<String> c2 =  null;
		Iterator<Entry<String, ArrayList<String>>> it = columnValues.entrySet().iterator();
		//Get the added columns in the original order.
		try {		
			c1 = it.next().getValue();
			c2 = it.next().getValue();
		} catch (Exception e){
			System.out.println("Cannot access saved column(s).");
		}
		if (c1 == null  || c2 == null ||c1.size()< 1 || c1.size() != c2.size()) {
			System.out.println("Cannot calculate minimal difference of the given columns.");
			return null;
		}
		foundRow = -1;
		//Compare values of each entry.
		int smallestDiff = 0;
		try {
			smallestDiff = Integer.parseInt(c1.get(0)) - Integer.parseInt(c2.get(0)); 
			//Check if absolute value should be calculated between the column values. 
			smallestDiff = (useAbsoluteValue && smallestDiff<0) ? Math.abs(smallestDiff) :smallestDiff;
			foundRow = 0;
			for (int i = 1; i < c1.size(); i ++) {
				int nextDiff = Integer.parseInt(c1.get(i)) - Integer.parseInt(c2.get(i));
				nextDiff = (useAbsoluteValue && nextDiff<0) ? Math.abs(nextDiff) :nextDiff;
				if (smallestDiff > nextDiff) {
					foundRow = i;
					smallestDiff = nextDiff;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Row value is not an Integer.");
			return null;
		}
		return smallestDiff;
	}
	
	/**
     * Return the last found row entry for the given operation.
     */
	public int getLastRowPosition() {
		return foundRow;
	}
	
	/**
     * Setter to depict whether the absolute value is used in the operation.
     * @param useAbsoluteValue boolean value to enable or disable the use of the absolute value.
     */
	public void setUseAbsoluteValue(boolean useAbsoluteValue) {
		this.useAbsoluteValue = useAbsoluteValue;
	}
}
