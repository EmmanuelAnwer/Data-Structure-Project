package XMLTreePackage;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Tree {
   private TreeNode root = new TreeNode();
    public String JSONified = "";
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
    
    public String toJson() {
        return root.toJson();
    }
        
//        TreeNode lastNode;
//        boolean last = false;
//
//        // first node (root)
//        JSONified += "{\n";
//        JSONified += "\t\"" + this.getRoot().getTag() + "\": {\n";
//
//        if (this.getRoot().getValue().compareTo("") != 0) {
//            JSONified += "\"" + this.getRoot().getValue() + "\"";
//        }
//
//        for (int i = 0; i < this.getRoot().getChildren().size(); i++) {
//            // Making last node is the parent for the first ,dd else the lastest brother
//            if (i == 0) {
//                lastNode = this.getRoot();
//            } else {
//                lastNode = this.getRoot().getChildren().get(i - 1);
//            }
//            // flag the last node for ","
//            if (i == this.getRoot().getChildren().size() - 1) {
//                last = true;
//            }
//            JSONify_traversal(this.getRoot().getChildren().get(i), lastNode, last, i);
//        }
//       
//        JSONified += "\t}\n";
//        JSONified += "\n}";
//        return JSONified;
//}
    
    public void JSONify_traversal(TreeNode node, TreeNode lastNode, boolean last, int order) {
        boolean square = false;

        // If comment, neglect it
        if (node.getTag().charAt(0) == '<' && node.getTag().charAt(1) == '!') {
            // JSONified += "//" + node.tag.substring(2, node.tag.length() - 2) + "\n";
            return;
        }

        // if the node's tag equals lastest brother's tag (twin)
        if (node.getTag().compareTo(lastNode.getTag()) == 0) {
            // if it has children
            if (!node.getChildren().isEmpty()) {
                for (int i = 0; i < node.getDepth() + 2; i++) {
                    JSONified += "\t";
                }
                JSONified += "{\n";
                for (int i = 0; i < node.getChildren().size(); i++) {
                    if (i == 0) {
                        lastNode = node;
                    } else {
                        lastNode = node.getChildren().get(i - 1);
                    }
                    if (i == node.getChildren().size() - 1) {
                        last = true;
                    } else {
                        last = false;
                    }
                    JSONify_traversal(node.getChildren().get(i), lastNode, last, i);
                }
                for (int i = 0; i < node.getDepth() + 2; i++) {
                    JSONified += "\t";
                }
                JSONified += "}";
            }
            // if it's a leaf
            else {
                for (int i = 0; i < node.getDepth() + 2; i++) {
                    JSONified += "\t";
                }
                JSONified += "\"" + node.getValue() + "\"";
            }
            JSONified += '\n';
            TreeNode parent = node.getParent();
            if (order == parent.getChildren().size() - 1) {
                // indentation
                for (int i = 0; i < node.getDepth() + 1; i++) {
                    JSONified += "\t";
                }
                JSONified += "]";

                if (!last) {
                    JSONified += ",";
                }
                JSONified += '\n';
            }
            boolean lastone = false;
            try {
                parent.getChildren().get(order + 1);
            } catch (Exception e) {
                lastone = true;
            }
            if (!lastone) {
                if (parent.getChildren().get(order + 1).getTag().compareTo(node.getTag()) != 0) {
                    // indentation
                    for (int i = 0; i < node.getDepth() + 1; i++) {
                        JSONified += "\t";
                    }
                    JSONified += "]";

                    if (!last) {
                        JSONified += ",";
                    }
                    JSONified += '\n';
                }
            }
            return;

        }

        // First child: check if there're twin brothers
        node.getBrothers();
        for (int i = 0; i < node.getBrothers().size(); i++) {
            if (node.getBrothers().get(i).getTag().compareTo(node.getTag()) == 0 && i != order) {
                square = true;
                break;
            }
        }
        //add if condition
        if (square) {
            // indentation
            for (int i = 0; i < node.getDepth() + 1; i++) {
                JSONified += "\t";
            }
            JSONified += "\"" + node.getTag() + "\": [\n";
            // check if it has children
            if (!node.getChildren().isEmpty()) {
                // indentation
                for (int i = 0; i < node.getDepth() + 2; i++) {
                    JSONified += "\t";
                }
                // adding the "{", calling the getChildren()
                JSONified += "{\n";
                for (int i = 0; i < node.getChildren().size(); i++) {
                    if (i == 0) {
                        lastNode = node;
                    } else {
                        lastNode = node.getChildren().get(i - 1);
                    }
                    if (i == node.getChildren().size() - 1) {
                        last = true;
                    } else {
                        last = false;
                    }

                    JSONify_traversal(node.getChildren().get(i), lastNode, last, i);
                }
                // closing
                for (int i = 0; i < node.getDepth() + 2; i++) {
                    JSONified += "\t";
                }
                JSONified += "},";
            } else {
                // indentation
                for (int i = 0; i < node.getDepth() + 2; i++) {
                    JSONified += "\t";
                }
                JSONified += "\"" + node.getValue() + "\"";
            }
            if (!last) {
                JSONified += ",";
            }
            JSONified += '\n';
            square = false;
            return;
        }

        // no twins
        else {
            // check if it has children
            if (!node.getChildren().isEmpty()) {
                // indentation
                for (int i = 0; i < node.getDepth() + 1; i++) {
                    JSONified += "\t";
                }
                // adding the "{", calling the getChildren()
                JSONified += "\"" + node.getTag() + "\": {\n";
                for (int i = 0; i < node.getChildren().size(); i++) {
                    if (i == 0) {
                        lastNode = node;
                    } else {
                        lastNode = node.getChildren().get(i - 1);
                    }
                    if(i == node.getChildren().size() - 1) {
                        last = true;
                    } else {
                        last = false;
                    }
                    JSONify_traversal(node.getChildren().get(i), lastNode, last, i);
                }
                // closing
                for (int i = 0; i < node.getDepth() + 2; i++) {
                    JSONified += "\t";
                }
                JSONified += "}";
            } else {
                // indentation
                for (int i = 0; i < node.getDepth() + 1; i++) {
                    JSONified += "\t";
                }
                JSONified += "\"" + node.getTag() + "\": ";
                JSONified += "\"" + node.getValue() + "\"";
            }
            if (!last) {
                JSONified += ",";
            }
            JSONified += '\n';
        }

    }
}