package de.exxcellent.core;

/**
 * Basic interface to load a file from a path. 
 * Since this can be a file located remotely (URL) or locally (absolute path)
 * the class which will be used to load the file needs to implement the listed method accordingly.
 * @author Matthias Bauer
 */
public interface FileLoader {
	
	 /**
     * Main method declaration to load a file remotely or locally.
     * @param path The path to the file. Which could also contain an URL with port.
     * @return true if the file could be loaded.
     */
	public boolean loadFile(String path);

	/**
     * Returns the current state of the file
     * @return true if file has been loaded.
     */
	public boolean fileIsLoaded();
	
	/**
     * Returns the current loaded file (depending on the implementation) or null if it hasn't been loaded yet.
     * @return loaded file or null.
     */
	public Object getFile();
}
