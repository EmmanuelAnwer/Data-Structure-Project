
/**
 *
 * @author Youssef
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlfile_operations;

import XMLTreePackage.Tree;
import XMLTreePackage.TreeNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class TreeMaker {
    private final ArrayList<String> br;
    private Tree tree;
    
    public TreeMaker(ArrayList<String> br){
        this.br = br;
    }

    public Tree getTree() {
        return tree;
    }
    

    //creating the tree
    public Tree treeCreator() throws IOException{
        TreeNode currentNode = null;
        HashMap<String,Boolean> similarMap = new HashMap<String,Boolean>(); 
        
        //TODO Add \n function to fix ID problem
        // Iterate on all lines
        for(String line : br){
            // Open Tag Case
            if(isOpenTag(line)){
                String tagName = this.symbolRemover(line);
                
                // optimum case
                if(currentNode != null){
                    TreeNode parentNode = currentNode;
                    currentNode = new TreeNode(
                    tagName
                    );
                    currentNode.setParent(parentNode);
                    currentNode.add_node();
                    if(parentNode.getChildren().size() > 1){
                        // Check the first two children are the same tag
                        if(parentNode.getChildren().get(0).getTag().equals(parentNode.getChildren().get(1).getTag())){
                            similarMap.put(parentNode.getTag(), true);
                        }
                    }
                }
                // root case
                else{
                    currentNode = new TreeNode(
                        tagName
                    );
                    currentNode.setDepth(-1);
                    tree = new Tree();
                    tree.setRoot(currentNode);
                }
            }
            // Closed Tag Case
            else if(isClosedTag(line)){
                if(currentNode == null) continue;
                currentNode = currentNode.getParent();
            }
            else if(line == "\n") continue;
            // Leaf Case
            else{
                currentNode.setValue(line);
            }
        }
        
        setSimilarProperty(tree.getRoot(), similarMap);
        
        
        return tree;
    }
    
    
    void setSimilarProperty(TreeNode root, HashMap<String,Boolean> similarMap){
        if(similarMap.containsKey(root.getTag())){
            root.setSimilar(true);
        }
        
        for(int i = 0; i < root.getChildren().size(); i++){
            setSimilarProperty(root.getChildren().get(i), similarMap);
        }
        
    }
    
    //checking if it is an open tag 
    boolean isOpenTag(String str){
        return str.contains("<") && str.contains(">") && !str.contains("/");          
    }
    
    //checking if it is a closed tag 
    boolean isClosedTag(String str){
        return str.contains("<") && str.contains(">") && str.contains("/");          
    }
    
    
    String symbolRemover(String str){
        str = str.replaceAll("\\s+","");
        if(isOpenTag(str)){
            return str.substring(1, str.length()-1);
        }
        else{
            return str.substring(2, str.length()-1);
        }
    }
}
