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
    
     public TreeNode() {

    }

    public TreeNode(String tag) {
        this.tag = tag;
    }

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
    
    String toJson(){
        
        return "";
    }
}
