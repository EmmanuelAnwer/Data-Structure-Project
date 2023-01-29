<<<<<<< HEAD
=======

/**
 *
 * @author Youssef
 */
>>>>>>> 2a5b7550098f7d6d49dd063dbc0dd2fb58ca722d
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlfile_operations;

import XMLTreePackage.Tree;
import XMLTreePackage.TreeNode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author user
 */
public class TreeMaker2 {
    private BufferedReader br;
    private Tree tree;
    
    public TreeMaker2(BufferedReader br){
        this.br = br;
    }

    public Tree getTree() {
        return tree;
    }
    
<<<<<<< HEAD
//    public BufferedReader noSpaces(BufferedReader br){
//        String xmlString = "";
//        
//        String line;
//        while((line = br.readLine()) != null){
//            if(isClosedTag(line)){
//                String newLine = symbolRemover(line);
//                if(!isClosedTag(newLine)){
//                    xmlString += line + "\n";
//                }else{
//                    String fixedLine = "";
//                    boolean openTagPassed = false;
//                    for(int i = 0; i < line.length(); i++){
//                        if(line.charAt(i) == '>'){
//                            fixedLine += '>' + "\n";
//                        }
//                        else if(line.charAt(i) == '<'){
//                            if(openTagPassed){
//                                fixedLine += "\n" + '<';
//                            }
//                        }
//                        else{
//                            fixedLine += line.charAt(i);
//                        }
//                    }
//                }
//            }  
//        }
//        
//        return newBr
//    } 
=======

>>>>>>> 2a5b7550098f7d6d49dd063dbc0dd2fb58ca722d
    
    public Tree treeCreator() throws IOException{
        String line;
        TreeNode currentNode = null;
        
        
        //TODO Add \n function to fix ID problem
        // Iterate on all lines
        while((line = br.readLine()) != null){
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
        
        return tree;
    }
    
    boolean isOpenTag(String str){
        return str.contains("<") && str.contains(">") && !str.contains("/");          
    }
    
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 2a5b7550098f7d6d49dd063dbc0dd2fb58ca722d
