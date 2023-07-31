/**
* This class represents the exception thrown when a DirectoryNode is full and cannot
* store any more children
* @author Christian Yu 
* Recitation section 02
* ID: 114632177
*/
public class FullDirectoryException extends Exception {
    public FullDirectoryException(String errorMessage){
        super(errorMessage);
    }
}
