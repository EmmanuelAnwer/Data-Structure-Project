/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlfile_operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author user
 */
public class XMLChecker {
    BufferedReader buffer;
    public XMLChecker(BufferedReader buffer){
        this.buffer = buffer;
    }
    
    void xmlGetErrors() throws IOException{
        String line;
        Vector<String> stack = new Vector<String>();
        while((line = buffer.readLine()) != null){
            if(isOpenTag(line)) stack.addElement(line);
            if(isClosedTag(line)){
                if(stack.lastElement() == symbolRemover(line)){
                    int lastElementIndex = stack.size()-1;
                    stack.remove(lastElementIndex);
                }else{
                    System.out.println("");
                    int lastElementIndex = stack.size()-1;
                    stack.remove(lastElementIndex);
                }
            }
        }
    }
    
    boolean isOpenTag(String str){
        return str.contains("<") && str.contains(">") && !str.contains("/");          
    }
    
    boolean isClosedTag(String str){
        return str.contains("<") && str.contains(">") && str.contains("/");          
    }
    
    String symbolRemover(String str){
        if(isOpenTag(str)){
            return str.substring(1, str.length()-1);
        }
        else{
            return str.substring(2, str.length()-1);
        }
    }
    
}
