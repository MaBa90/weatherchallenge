Dear all,

here is my solution of the weather challenge. 
I tried my best to separate and abstract the different steps and necessary operations for reusability.
Here is a small diagram which shows the connections between the classes:  

<img src="Diagram.PNG" width="500" title="Diagram">

Best Regards  
Matthias Bauer

## Package overview
###### de.exccellent.core.interfaces
1. CSVTableOperator  
Used to define methods that any class which represents a loaded CSV must offer in order to access the data inside the file.
It is used to abstract the way the data of the CSV is accessed since this can be done locally or manually.
2. FileLoader  
Used to define methods that any class which loads a file at some location must contain in order to load a specific file.
It can be used to implement classes that abstract the access of files (web or locally) while the basic access to the data stays the same.
3. FileOperation  
A special interface that defines methods for any class that accesses a file and executes different, complex operations on the data. Here, the doOperation() method defines the action that is taken on the file. The interface was chosen to reuse defined operation such as calculating the row-wise difference of column values.

###### de.exccellent.core.classes              
1. LocalFileLoader  
Abstract class that implements the LoadFile() method from the FileLoader interface in order to provide the basic functionality for local file access. 
2. CSVColumnOperation  
Class used to abstract a FileOperation which only operations on columns values of a CSV file.
It contains methods to store and prepares column data values and provide the basic mechanism which should be used by other column related operations.

###### de.exccellent.core.impl 
1. LocalCSVFile  
Inherits the LocalFileLoader class and implements the CSVTableOperator interface to load and access a locally stored CSV file.
2. MinColDiffCSVInt  
Class which implements the FileOpration inteface in order to represent a reusable FileOperation which can be execute on a local CSV file. It does calculate and return the row-wise value (only Integers) difference of two given columns  and finds the row with the minimum difference. It also supports the calculation of the absolute value.  

Tests have been added during implementation and in advance.  
Many functions using CSV data return strings so that the datavalue must be cast into the specific wanted datatype.

Further possible implementation considerations:
1. Use a different technique to open and access local files that a BufferedReader to cover more filetypes.
2. Change return values of some methods from null to other more meaningful return values.
3. Add a logger mechanism to remove the Sysout calls.
4. Account for different datatypes which are used in the rows to automatically cast the right datavalue, e.g., in case of the FileOperation interface.
5. Use a mechanism to keep the same data object for different CSV files.