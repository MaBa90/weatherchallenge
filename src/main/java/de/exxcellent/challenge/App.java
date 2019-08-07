package de.exxcellent.challenge;

import de.exxcellent.impl.LocalCSVFile;

import de.exxcellent.impl.MinColDiffCSVInt;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 * @todo Refactor to apply the football challenge.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        //Load new CSV and the operation for getting the minimal column difference on it.
    	LocalCSVFile csvReader = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\weather.csv");
    	MinColDiffCSVInt colOperation = new MinColDiffCSVInt(csvReader, "MxT", "MnT");
    	colOperation.doOperation();

        String dayWithSmallestTempSpread = csvReader.getEntry(colOperation.getRowPosition(), "Day");
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        
       // String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
       // System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
