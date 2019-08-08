package de.exxcellent.challenge;

import de.exxcellent.impl.LocalCSVFile;
import de.exxcellent.impl.MinColDiffIntCSV;

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
    	LocalCSVFile csvReaderWeather = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\weather.csv");
    	MinColDiffIntCSV colOperation = new MinColDiffIntCSV(csvReaderWeather);
    	colOperation.addColumnByName("MxT");
    	colOperation.addColumnByName("MnT");
    	colOperation.doOperation();
        String dayWithSmallestTempSpread = csvReaderWeather.getEntry(colOperation.getLastRowPosition(), "Day");
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        
        LocalCSVFile csvReaderFootball = new LocalCSVFile(".\\src\\main\\resources\\de\\exxcellent\\challenge\\football.csv");
        colOperation.addFile(csvReaderFootball);
        colOperation.removeColumns();
    	colOperation.addColumnByName("Goals");
    	colOperation.addColumnByName("Goals Allowed");
    	colOperation.setUseAbsoluteValue(true);
    	colOperation.doOperation();
        String teamWithSmallestGoalSpread = csvReaderFootball.getEntry(colOperation.getLastRowPosition(), "Team");
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
