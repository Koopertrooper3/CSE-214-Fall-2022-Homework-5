/**
* This class represents the directory tree, a ternary (3-child) tree of DirectoryNodes. 
* The class contain a reference to the root of the tree, a cursor for the present working directory, 
* and methods to manipulate the tree, the cursor, and find nodes in the tree.
* @author Christian Yu 
* Recitation section 02
* ID: 114632177
*/
public class DirectoryTree {
    private DirectoryNode root;
    private DirectoryNode cursor;
    private String workingDirectory;

    /**
     * Creates a DirectoryTree object which implements a ternary tree of DirectoryNodes and methods to
     * manipulate it.
     */
    public DirectoryTree(){
        this.root = new DirectoryNode("root",false);
        this.cursor = root;
        workingDirectory = cursor.getName();
    }

    /**
     * Gets the current cursor of the tree
     * @return
     * Returns the directory node that is the cursor of this tree
     */
    public DirectoryNode getCursor() {
        return cursor;
    }

    /**
     * Gets the root of this tree
     * @return
     * Gets the root directory node that is the root of this tree
     */
    public DirectoryNode getRoot(){
        return root;
    }

    /**
     * Resets the cursor of this tree to the root node
     */
    public void resetCursor(){
        cursor = root;
        workingDirectory = cursor.getName();
    }

    /**
     * Moves the cursor to the directory indicated in the string parameter name
     * @param name
     * The name of the child directorty that the cursor is to be moved to
     * @throws NotADirectoryException
     * Thrown when there is no child node with the paramemter name to move the cursor to
     */
    public void changeDirectory(String name) throws NotADirectoryException{
        
        if(cursor.getLeft() != null && cursor.getLeft().getName().equals(name)){
            if(cursor.getLeft().isFile() == false){
                cursor = cursor.getLeft();
                workingDirectory += "/" + name;
            } else {
                throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
            }
        } else if(cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name)){
            if(cursor.getMiddle().isFile() == false){
                cursor = cursor.getMiddle();
                workingDirectory += "/" + name;
            } else {
                throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
            }
        } else if(cursor.getRight() != null && cursor.getRight().getName().equals(name)){
            if(cursor.getMiddle().isFile() == false){
                cursor = cursor.getRight();
                workingDirectory += "/" + name;
            } else {
                throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
            }
        } else {
            System.out.println("ERROR: No such directory named '"+name+"'.");
        }
    }

    /**
     * Returns a String containing the path of directory names from the root node of the tree to the cursor 
     * @return
     * Returns the String workingDirectory which contains the path of the cursor from the root
     */
    public String presentWorkingDirectory(){
        return workingDirectory;

    }

    /**
     * Returns a String containing a space-separated list of names of all the child directories or files of the cursor.
     * @return
     * Returns a String with the names of all the children of the cursor
     */
    public String listDirectory(){
        String result="";
        if(cursor.getLeft() != null){
            result += cursor.getLeft().getName()+ " ";
        }
        if(cursor.getMiddle() != null){
            result += cursor.getMiddle().getName()+ " ";
        }
        if(cursor.getRight() != null){
            result += cursor.getRight().getName()+ " ";
        }
        return result;
    }

    /**
     * Prints a formatted nested list of names of all the nodes in the directory tree, starting from the cursor.
     * @param root
     * The root of the tree or subtree
     * @param depth
     * The depth of the tree starting from the root of the tree or subtree
     */
    public void PrintDirectoryTree(DirectoryNode root,int depth){
        if(root == null){
            return;
        }

        if(root.isFile() == true){
            String output = "|- "+root.getName();
            System.out.print(output.indent(4*depth));
        } else{
            String output = "- "+root.getName();
            System.out.print(output.indent(4*depth));
        }
        if(root.getLeft() != null)
            PrintDirectoryTree(root.getLeft(), depth+1);
        if(root.getMiddle() != null)
            PrintDirectoryTree(root.getMiddle(), depth+1);
        if(root.getRight() != null)    
            PrintDirectoryTree(root.getRight(), depth+1);
        
    }

    /**
     * Creates a new directory with the indicated name and adds it as a child of the cursor node
     * @param name
     * The name of the new directory being added
     * @throws IllegalArgumentException
     * Thrown if the name is invalid, as in when it contains a " " or "/"
     * @throws FullDirectoryException
     * Thrown if all child references of the cursor directory are occupied.
     * @throws NotADirectoryException
     * Thrown if the node the cursor is referring to is a file, which cannot be used store a directory
     */
    public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException{
        if(name.contains(" ") || name.contains("/")){
            throw new IllegalArgumentException("");
        } else{
            DirectoryNode newDirectoryNode = new DirectoryNode(name, false, cursor);
            try{
                cursor.addChild(newDirectoryNode);
            } catch (FullDirectoryException e){
                throw new FullDirectoryException("ERROR: Present directory is full.");
            } catch (NotADirectoryException e){
                throw new NotADirectoryException("ERROR: Cannot change directory into a file.");
            }
        }
    }

    /**
     * Creates a file with the indicated name and adds it to the children of the cursor node.
     * @param name
     * The name of the file being created
     * @throws IllegalArgumentException
     * Thrown if the name is invalid, as in when it contains a " " or "/"
     * @throws FullDirectoryException
     * Thrown if all child references of the cursor directory are occupied.
     * @throws NotADirectoryException
     * Thrown if the node the cursor is referring to is a file, which cannot be used to store a file
     */
    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException{
        if(name.contains(" ") || name.contains("/")){
            throw new IllegalArgumentException();
        } else{
            DirectoryNode newFile = new DirectoryNode(name, true, cursor);
            try{
                cursor.addChild(newFile);
            } catch (FullDirectoryException e){
                throw new FullDirectoryException("ERROR: Present directory is full.");
            } catch (NotADirectoryException e){
                throw new NotADirectoryException("ERROR: Cannot change directory into a file.");
            }
        }
    }

    /**
     * Moves the cursor to its parent node and remove the name of the cursor node from the String workingDirectory.
     */
    public void moveCursorToParent(){
        cursor = cursor.getParent();
        workingDirectory = workingDirectory.substring(0, workingDirectory.lastIndexOf("/"));
    } 

    /**
     * Conducts a preorder traversal of the whole tree to find the node with the name of the String variable target
     * @param root
     * The root of the tree being searched
     * @param target
     * The name of the node which we want to find
     * @param result
     * The path of the node of which we want to find starting from the root of the tree. Returns "ERROR: No such file exits." when no such directory exists in the tree
     * @return
     * Returns the path of the target node from the cursor. Returns "ERROR: No such file exits." when no directory with the name of the variable target exists.
     */
    public String findNode(DirectoryNode root, String target, String result){
        if(root == null){
            return "ERROR: No such file exits.";
        }

        result += "/"+root.getName();

        if(!root.getName().equals(target)){
            String left = findNode(root.getLeft(), target, result);
            if(!left.equals("ERROR: No such file exits.")){
                return left;
            }
            String middle = findNode(root.getMiddle(), target, result);
            if(!middle.equals("ERROR: No such file exits.")){
                return middle;
            }

            String right = findNode(root.getRight(), target, result);
            if(!right.equals("ERROR: No such file exits.")){
                return right;
            }
            return "ERROR: No such file exits.";
        } else{
            return result;
        }
    }
}
