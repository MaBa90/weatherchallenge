package de.exxcellent.challenge;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import de.exxcellent.impl.LocalCSVFile;
import de.exxcellent.impl.MinColDiffCSVInt;

/**
 * JUnit4 test case.
 * @author Matthias Bauer <matthias.bauer90@t-online.de>
 */

public class MinColDiffCSVTest {
	@Test
    public void NoIntTest() {  
		//Do some testing for columns that do not contain integers. In each case the result should be null
		LocalCSVFile csvReader = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\weather.csv");	
		System.out.println("\nNoIntTest 1");
		MinColDiffCSVInt minColDivOp = new MinColDiffCSVInt(csvReader,"R AvSLP", "1HrP TPcpn");
		assertNull(minColDivOp.doOperation());
		
		System.out.println("\nNoIntTest 2");
		csvReader = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\football.csv");	
		minColDivOp.addFile(csvReader); 
		new MinColDiffCSVInt(csvReader,"Team", "Games");
		assertNull(minColDivOp.doOperation());
	}
	
    @Test
    public void MinValDiffValueTest() {
    	 LocalCSVFile csvReader = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\weather.csv");
    	//Do some testing for different column combinations
    	System.out.println("\nTestMinValDiffValue 1");
    	MinColDiffCSVInt minColDivOp = new MinColDiffCSVInt(csvReader,"MxT","MnT");
    	assertEquals((Integer) 2, minColDivOp.doOperation());
    	//Test for reversed column values
    	System.out.println("\nTestMinValDiffValue 2");
    	minColDivOp.setColumnNames("MnT","MxT");
    	assertEquals((Integer) (-54), minColDivOp.doOperation());
    }

}
