package XMLTreePackage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class TreeNode {
   
    private String tag;
    private String value = "";;
    private String attributeName = "";
    private String attributeValue = "";
    private List<TreeNode> nodes;
    private TreeNode parent;
    private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
    private ArrayList<TreeNode> brothers = new ArrayList<TreeNode>();
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
<<<<<<< HEAD
    public void add_node() {       
        this.setDepth(this.parent.getDepth() + 1);  
        this.setParent(parent);
        this.parent.getChildren().add(this); 
    }
=======
public void add_node() {       
    this.setDepth(this.parent.getDepth() + 1);  
    this.setParent(parent);
    this.parent.getChildren().add(this); 
}
>>>>>>> 2a5b7550098f7d6d49dd063dbc0dd2fb58ca722d
    public void get_brothers() {
        for (int i = 0; i < parent.children.size(); i++) {
            brothers.add(parent.children.get(i));
        }
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

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
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

    public ArrayList<TreeNode> getBrothers() {
        return brothers;
    }

    public void setBrothers(ArrayList<TreeNode> brothers) {
        this.brothers = brothers;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    public String toJson(){
        if(this.value == ""){
            String result = "";
            for(int i =0; i < this.depth+1; i++){
                result += "\t";
            }
            result += this.tag + ": ";
            if(this.similar == true) result += "[\n";
            else result += "{\n";
            
            for(int childrenIndex = 0; childrenIndex < this.children.size(); childrenIndex++){
                result += children.get(childrenIndex).toJson();
                if(childrenIndex != this.children.size()-1) result += ",";
                result += "\n";
            }
            for(int i =0; i < this.depth+1; i++){
                result += "\t";
            }
            if(this.similar == true) result += "]";
            else result += "}";
            return result;
        }         
        else{
            String valueString = "";
            for(int i = 0; i < this.depth+1; i++){
                valueString += "\t";
            }
            valueString += this.tag + ": " + "\"" +this.value + "\"";
            return valueString;
        }
    }
}
