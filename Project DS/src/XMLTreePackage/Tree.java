/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XMLTreePackage;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Tree {
   private TreeNode root = new TreeNode();
  
    public Tree() {

    }
    public Tree(String tag) {
        root.setTag(tag); 
        root.setDepth(-1);
        root.setChildren(new ArrayList<>()); 

    }
    
    public void add_node(TreeNode parent, TreeNode child) {
        child.setDepth(parent.getDepth() + 1); 
        child.setParent(parent); 
        parent.getChildren().add(child);
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }
    
    
    String toJson(){
        
        return "";
    }
}
