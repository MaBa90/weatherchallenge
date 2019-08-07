package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import de.exxcellent.impl.LocalCSVReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Example JUnit4 test case.
 * @author Matthias Bauer <matthias.bauer90@t-online.de>
 */
public class LocalCSVReaderTest {

	@Test
    public void emptyStringTest() {
		//Incorrect file should not crash the program but return null.
    	LocalCSVReader csvReader = new LocalCSVReader(""); 
    	assertNull(csvReader.getFile()); 
    	assertEquals(false, csvReader.fileIsLoaded()); 
    }
    
    @Test
    public void noCSVFileTest() {
    	//No csv file should not crash the program but return null.
    	LocalCSVReader csvReader = new LocalCSVReader("someFile.test"); 
    	assertNull(csvReader.getFile()); 
    	assertEquals(false, csvReader.fileIsLoaded()); 
    }
    
    @Test
    public void CSVFileTest() {
    	//A real csv file should be opened without problems.
    	LocalCSVReader csvReader = new LocalCSVReader(".\\src\\main\\resources\\de\\exxcellent\\challenge\\weather.csv"); 
    	assertNotNull(csvReader.getFile()); 
    	assertEquals(true, csvReader.fileIsLoaded()); 
    }

}