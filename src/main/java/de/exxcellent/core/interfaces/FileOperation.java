package de.exxcellent.core.interfaces;

/**
 * Basic interface to define a complex operation that is done on a certain type of file (FileType), e.g., a CSV file. 
 * This can be used for different kind of files and operations, of which the result must be returned as ReturnType implies.
 * @author Matthias Bauer
 */
public interface FileOperation<FileType, ReturnType> {
	 /**
     * Stores the current file object on which the operation is to be executed.
     * @param file depicted by the FileType.
     */
	public void addFile(FileType file);
	
	/**
     * Executes the implemented function on the current stored file.
     * @return the final value which has been retrieved file during the execution.
     */
	public ReturnType doOperation();
	
}
