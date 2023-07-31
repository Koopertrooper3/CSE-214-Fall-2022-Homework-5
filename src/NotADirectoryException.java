/**
* This class represents the exception thrown when a DirectoryNode is a file
* and cannot store another directory.
* @author Christian Yu 
* Recitation section 02
* ID: 114632177
*/
public class NotADirectoryException extends Exception {
    public NotADirectoryException(String errorMessage){
        super(errorMessage);
    }
}

