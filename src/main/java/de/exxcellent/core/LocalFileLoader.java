package de.exxcellent.core;

import java.io.File;
import java.io.IOException;

/**
 * This abstract class defines the basic mechanism to open a file locally. 
 * @author Matthias Bauer
 */
public abstract class LocalFileLoader implements FileLoader {
	protected File loadedFile = null;
	protected boolean fileLoaded = false;
	
	@Override
	public boolean loadFile(String path) {
		//Use local file access.
		try {
			loadedFile = new File(path);
		} catch (NullPointerException e) {
			System.out.println("The file " + path + " could not be loaded.");
			return false;
		}
		//Check if file can be loaded correctly.
		if (!loadedFile.canRead()) {
			System.out.println("The file " + path + " cannot be read or does not exist.");
			return false;
		}
		//Everything is ok and file has been loaded.
		fileLoaded = true;
		return true;
	}
	
	@Override
	public File getFile() {
		if (!fileLoaded) {
			return null;
		}
		return loadedFile;
	}
	
	@Override
	public boolean fileIsLoaded() {
		return fileLoaded;
	}
}
