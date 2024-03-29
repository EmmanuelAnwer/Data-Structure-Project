package XMLTreePackage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class TreeNode {
   
    private String tag;
    private String value = "";
    private List<TreeNode> nodes;
    private TreeNode parent;
    private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
    private int depth;
    private boolean similar = false;

    public boolean isSimilar() {
        return similar;
    }

    public void setSimilar(boolean similar) {
        this.similar = similar;
    }
    
     public TreeNode() {

    }

    public TreeNode(String tag) {
        this.tag = tag;
    }
    public void add_node() {       
        this.setDepth(this.parent.getDepth() + 1);  
        this.setParent(parent);
        this.parent.getChildren().add(this); 
    }
    
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<TreeNode> children) {
        this.children = children;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
   
   // converting the tree to JSON 
    public String toJson(){
        if("".equals(this.value)){
            String result = "";
            for(int i =0; i < this.depth+2; i++){
                result += "\t";
            }
            if(this.parent != null && !this.parent.similar) result += '"' + this.tag + '"' + ": ";
            else if(this.parent == null) result += '"' + this.tag + '"' + ": ";
            if(this.similar == true) result += "[\n";
            else{ 
                result += "{\n";
            }
            
            for(int childrenIndex = 0; childrenIndex < this.children.size(); childrenIndex++){
                result += children.get(childrenIndex).toJson();
                if(childrenIndex != this.children.size()-1) result += ",";
                result += "\n";
            }
            for(int i =0; i < this.depth+2; i++){
                result += "\t";
            }
            if(this.similar == true) result += "]";
            else result += "}";
            return result;
        }         
        else{
            String valueString = "";
            for(int i = 0; i < this.depth+2; i++){
                valueString += "\t";
            }
            if(this.parent.similar){
             valueString += "\"" +this.value + "\"";
            }
            else valueString += '"' + this.tag +'"' + ": " + "\"" +this.value + "\"";
            return valueString;
        }
    }
}
