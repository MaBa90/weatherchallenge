package de.exxcellent.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This abstract class defines the basic mechanism to open a file locally. 
 * I'm pretty sure that there are better ways of loading a file instead of using a BufferedReader.
 * @author Matthias Bauer
 */
public abstract class LocalFileLoader implements FileLoader {
	protected BufferedReader fileLineReader = null;
	protected FileReader fileReader = null;
	protected boolean fileLoaded = false;
	
	@Override
	public boolean loadFile(String path) {
		//Use local file access.
		try {
			fileReader = new FileReader(path);
		} catch (FileNotFoundException  e) {
			System.out.println("The file " + path + " cannot be found.");
			return false;
		}
		//Check if file can be read.
		boolean fileReady = false;
		try {
			fileReady = fileReader.ready();
		} catch (IOException e) {
			System.out.println("The file cannot be read.");
			return false;
		}
		if (!fileReady) {
			System.out.println("An I/O error occured.");
			return false;
		}
		fileLineReader = new BufferedReader(fileReader);
		//Everything is ok and file has been loaded.
		fileLoaded = true;
		return true;
	}
	
	@Override
	public BufferedReader getFile() {
		if (!fileLoaded) {
			return null;
		}
		return fileLineReader;
	}
	
	@Override
	public boolean fileIsLoaded() {
		return fileLoaded;
	}
}
