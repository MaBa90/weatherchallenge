package de.exxcellent.challenge;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import de.exxcellent.impl.LocalCSVFile;
import de.exxcellent.impl.MinColDiffIntCSV;

/**
 * JUnit4 test cases for the MinColDiffIntCSV class.
 * @author Matthias Bauer <matthias.bauer90@t-online.de>
 */

public class MinColDiffIntCSVTest {
	@Test
    public void NoIntTest() {  
		//Do some testing for columns that do not contain integers. In each case the result should be null
		LocalCSVFile csvReader = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\weather.csv");	
		System.out.println("\nNoIntTest 1");
		//MinColDiffIntCSV minColDivOp = new MinColDiffIntCSV(csvReader,"R AvSLP", "1HrP TPcpn");
		MinColDiffIntCSV minColDivOp = new MinColDiffIntCSV(csvReader);
		minColDivOp.addColumnByName("R AvSLP");
		minColDivOp.addColumnByName("1HrP TPcpn");
		assertNull(minColDivOp.doOperation());
		
		//Do some testing if columns are composed of strings.
		System.out.println("\nNoIntTest 2");
		csvReader = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\football.csv");	
		minColDivOp.addFile(csvReader);
		minColDivOp.removeColumns();
		minColDivOp.addColumnByName("Team");
		minColDivOp.addColumnByName("Games");
		
		//Do some testing if only one column was added.
		System.out.println("\nNoIntTest 3");
		assertNull(minColDivOp.doOperation());
		minColDivOp.removeColumns();
		minColDivOp.addColumnByName("Team");
		assertNull(minColDivOp.doOperation());
	}
	
    @Test
    public void MinValDiffValueTest() {
    	 LocalCSVFile csvReader = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\weather.csv");
    	//Do some testing for different column combinations
    	System.out.println("\nTestMinValDiffValue 1");
    	MinColDiffIntCSV minColDivOp = new MinColDiffIntCSV(csvReader);
    	minColDivOp.addColumnByName("MxT");
		minColDivOp.addColumnByName("MnT");
    	assertEquals((Integer) 2, minColDivOp.doOperation());
    	
    	//Test for reversed column values
    	System.out.println("\nTestMinValDiffValue 2");
    	minColDivOp.removeColumns();
    	minColDivOp.addColumnByName("MnT");
		minColDivOp.addColumnByName("MxT");
    	assertEquals((Integer) (-54), minColDivOp.doOperation());
    	
    	//Test for absolute value column values
    	System.out.println("\nTestMinValDiffValue 3");
    	minColDivOp.removeColumns();
    	minColDivOp.addColumnByName("MnT");
		minColDivOp.addColumnByName("MxT");
		minColDivOp.setUseAbsoluteValue(true);
    	assertEquals((Integer) 2, minColDivOp.doOperation());
    }

}
