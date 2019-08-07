package de.exxcellent.impl;

import java.util.ArrayList;

import de.exxcellent.core.FileOperation;

/**
 * This class gets the minimal distance between the rows of two defined columns (as Integers).
 * @author Matthias Bauer
 */
public class MinColDiffCSVInt implements FileOperation<LocalCSVFile, Integer> {
	private LocalCSVFile storedFile = null;
	private String nameColumn1 = "";
	private String nameColumn2 = "";
	private int foundRow = -1;
	
	public MinColDiffCSVInt(LocalCSVFile file, String nameC1, String nameC2) {
		addFile(file);
		setColumnNames(nameC1, nameC2);
	}
	
	@Override
	public void addFile(LocalCSVFile file) {
		storedFile = file;	
	}

	@Override
	public Integer doOperation() {
		if (storedFile == null) {
			return null;	
		}
		if (nameColumn1 == "" || nameColumn2 == "") {
			return null;
		}
		//Go though the defined columns and get the minimal value.
		ArrayList<String> c1 = storedFile.getColumn(nameColumn1);
		ArrayList<String> c2 = storedFile.getColumn(nameColumn2);
		if (c1 == null  || c2 == null ||c1.size()< 1 || c1.size() != c2.size()) {
			System.out.println("Cannot calculate minimal difference of the given columns.");
			return null;
		}
		//Compare values of each entry.
		int smallestDiff = 0;
		try {
			smallestDiff = Integer.parseInt(c1.get(0)) - Integer.parseInt(c2.get(0)); 
			foundRow = 0;
			for (int i = 1; i < c1.size(); i ++) {
				int nextDiff = Integer.parseInt(c1.get(i)) - Integer.parseInt(c2.get(i));
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
     * Set the two columns for which the operations should be done.
     * @param nameC1 name of the first column.
     * @param nameC2 name of the second column.
     */
	public void setColumnNames(String nameC1, String nameC2) {
		nameColumn1 = nameC1;
		nameColumn2 = nameC2;
	}
	
	public int getRowPosition() {
		return foundRow;
	}

}
