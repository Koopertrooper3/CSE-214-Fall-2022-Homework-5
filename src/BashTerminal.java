/**
* This class represents the main method and contains the code that allows the user to interact with a file system
* represented by an instance of DirectoryTree.
* @author Christian Yu 
* Recitation section 02
* ID: 114632177
*/
import java.util.Scanner;
public class BashTerminal {
    /**
     * Driver code that allows a user to interact with a file system implemented by an instance of DirectoryTree
     */
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        boolean flag = true;
        String user = "114632177";
        String host = "host";
        DirectoryTree tree = new DirectoryTree();
        System.out.println("Starting bash terminal");

        while(flag){
            System.out.print("["+user+"@"+host+"]: " +"$ " );
            String input = stdin.nextLine();

            input = input.replace(" ", "");

            if(input.equals("pwd")){
                System.out.println(tree.presentWorkingDirectory());
            } else if( input.equals("ls")){
                System.out.println(tree.listDirectory());
            } else if( input.equals("ls-R")){
                tree.PrintDirectoryTree(tree.getCursor(), 0);
            } else if( input.substring(0,2).equals("cd")){
                if(input.contains("/")){
                    tree.resetCursor();
                } else if(input.contains("..")){
                    tree.moveCursorToParent();
                } else{
                    String child = input.substring(2);
                    try{
                        tree.changeDirectory(child);
                    } catch (NotADirectoryException e){
                        System.out.println("ERROR: Cannot change directory into a file.");
                    }
                }
            } else if(input.length() > 4){

                    if (input.substring(0,5).equals("mkdir")){
                        try{
                            tree.makeDirectory(input.substring(5));
                        } catch (IllegalArgumentException e){
                            System.out.println("ERROR: Invalid name");
                        } catch (FullDirectoryException e){
                            System.out.println("ERROR: Present directory is full.");
                        } catch (NotADirectoryException e){
                            System.out.println("ERROR: Cannot change directory into a file.");
                        }
                    } else if(input.substring(0, 5).equals("touch")){
                        try{
                            tree.makeFile(input.substring(5));
                        } catch(IllegalArgumentException e){
                            System.out.println("ERROR: Invalid name");
                        } catch (FullDirectoryException e){
                            System.out.println("ERROR: Present directory is full.");
                        } catch (NotADirectoryException e){
                            System.out.println("ERROR: Cannot change directory into a file.");
                        }
                    } else if(input.substring(0,4).equals("find")){
                        System.out.println(tree.findNode(tree.getRoot(), input.substring(4), ""));
                    }
                

            } else if(input.equals("exit")){
                System.out.println("Program terminating normally");
                flag = false;
            }
        }
        stdin.close();
    }
}
