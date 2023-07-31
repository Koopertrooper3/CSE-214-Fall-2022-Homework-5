/**
* This class represents a directory in the form of a node. It contains a string containing its name, 
* a boolean that ndicates whether it is a file or not, a reference to its three children 
* and the parent node.
* @author Christian Yu 
* Recitation section 02
* ID: 114632177
*/
public class DirectoryNode {
    private String name;
    private DirectoryNode left;
    private DirectoryNode middle;
    private DirectoryNode right;
    private boolean isFile;
    private DirectoryNode parent;
    

    /**
     * Creates a directory node with a name and a boolean that indicates whether it is a file
     * or not
     * @param name
     * The name of the directory
     * @param isFile
     * Boolean that indicates whether this directory is a file. True when directory is a file, false
     * when otherwise
     */
    public DirectoryNode(String name, boolean isFile){
        this.isFile = isFile;
        this.name = name;
    }

    /**
     * Creates a directory node with a name, a boolean that indicates whether it is a file
     * or not, and a reference to its parent node.
     * @param name
     * The name of the directory
     * @param isFile
     * Boolean that indicates whether this directory is a file. True when directory is a file, false
     * when otherwise
     * @param root
     * The parent of the node being created
     */
    public DirectoryNode(String name, boolean isFile, DirectoryNode parent){
        this.isFile = isFile;
        this.name = name;
        this.parent = parent;
    }

    /**
     * Gets the name of this node
     * @return
     * Returns the name of the node according to the string variable name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the left child of this node
     * @return
     * Returns the left child of this node referenced in the DirectryNode variable left
     */
    public DirectoryNode getLeft() {
        return left;
    }

    /**
     * Gets the middle child of this node
     * @return
     * Returns the middle child of this node referenced in the DirectoryNode variable middle
     */
    public DirectoryNode getMiddle() {
        return middle;
    }

    /**
     * Gets the right child of this node
     * @return
     * Returns the right child of this node referenced in the DirectoryNode variable right
     */
    public DirectoryNode getRight() {
        return right;
    }

    /**
     * Gets the boolean that indicates whether this node is a file or not
     * @return
     * Returns the boolean isFile of this node, true if this node is a file, false otherwise
     */
    public boolean isFile() {
        return isFile;
    }

    /**
     * Gets the parent node of this node
     * @return
     * Returns the parent node of this node referenced in the DirectoryNode variable parent
     */
    public DirectoryNode getParent() {
        return parent;
    }

    /**
     * Adds the child newChild to any of the open child positions of this node (left, middle, or right), starting from left to right.
     * @param newChild
     * The node being added as a child of this node
     * @throws FullDirectoryException
     * Thrown if all child references of this directory are occupied
     * @throws NotADirectoryException
     * Thrown if the current node is a file, as files cannot contain DirectoryNode references
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException{
        if(isFile == true){
            throw new NotADirectoryException("ERROR: Cannot change directory into a file.");
        }

        if(left == null){
            this.left = newChild;
        } else if(middle == null){
            this.middle = newChild;
        } else if(right == null){
            this.right = newChild;
        } else{
            throw new FullDirectoryException("ERROR: Present directory is full.");
        }
    }

}   
