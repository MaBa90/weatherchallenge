package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import de.exxcellent.impl.LocalCSVFile;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Example JUnit4 test case.
 * @author Matthias Bauer <matthias.bauer90@t-online.de>
 */
public class LocalCSVFileTest {
	
	private static LocalCSVFile csvReader;
	
	@BeforeAll
	public static void init() {
		csvReader = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\weather.csv"); 
	}
	
	@Test
    public void emptyStringTest() {
		//Incorrect file should not crash the program but return null.
		System.out.println("\nemptyStringTest");
		LocalCSVFile csvReader = new LocalCSVFile(""); 
    	assertNull(csvReader.getFile()); 
    	assertEquals(false, csvReader.fileIsLoaded()); 
    }
    
    @Test
    public void noCSVFileTest() {
    	//No csv file should not crash the program but return null.
    	System.out.println("\nnoCSVFileTest");
    	LocalCSVFile csvReader = new LocalCSVFile("someFile.test"); 
    	assertNull(csvReader.getFile()); 
    	assertEquals(false, csvReader.fileIsLoaded()); 
    }
    
    @Test
    public void CSVFileTest() {
    	//A real csv file should be opened without problems.
    	System.out.println("\nCSVFileTest");
    	assertNotNull(csvReader.getFile()); 
    	assertEquals(true, csvReader.fileIsLoaded()); 
    }
    
    @Test
    public void ReadCSVTest() {
    	//Do some random testing on the CSV datastructure.
    	//Get existing column.
    	System.out.println("\nReadCSVTest 1");
    	ArrayList<String> column = csvReader.getColumn("MxT");
    	ArrayList<String> row = csvReader.getRow(0);
    	//Check number of columns and rows.
    	System.out.println("\nReadCSVTest 2");
    	int numCol = csvReader.getNumberOfColumns();
    	assertEquals(14,numCol);
    	int numRows = csvReader.getNumberOfRows();
    	assertEquals(31,numRows);
    	//Check row and column values
    	System.out.println("\nReadCSVTest 3");
    	assertTrue(row.equals(new ArrayList<String>(Arrays.asList("Day", "MxT", "MnT", "AvT", "AvDP", "1HrP TPcpn", "PDir", "AvSp", "Dir", "MxS", "SkyC", "MxR", "Mn", "R AvSLP"))));;
    	assertTrue(column.equals(new ArrayList<String>(Arrays.asList("88","79", "77", "77", "90", "81", "73", "75", "86", "84", "91", "88", "70", "61", "64", "79", "81", "82", "81", "84", "86", "90", "90", "90", "90", "97", "91", "84", "88", "90"))));
    	//Test field access methods.
    	System.out.println("\nReadCSVTest 4");
    	assertEquals("1",csvReader.getEntry(0,"Day"));
    	assertEquals("84",csvReader.getEntry(28,"MxR"));
    	assertEquals("1018.6",csvReader.getEntry(8,"R AvSLP"));
    }

}