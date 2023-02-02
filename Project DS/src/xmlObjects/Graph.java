/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlObjects;


import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author Youssef
 */
public class Graph {
    
    HashMap <Integer,Integer> m = new HashMap<>();
    XMLUsers users ;
    
    int[][] array = new int[users.users.size()][users.users.size()];
    
    
    
    
    public Graph(XMLUsers users) {
        this.users = users;
        
        
    }
    
    
    
    void addEdge(int x, int y){
        
        array [x][y] = 1; 
        
    }
    
    void arrayMaker (ArrayList<User> users){
        
        for(int i=0; i<users.size();i++){
            
            for(int j=0; j<users.get(i).getFollowers().size();j++){
                
                array [users.get(i).getId()] [users.get(i).getFollowers().get(j).getId()]=1;
                
            }
            
        }
        
    } 
   
    HashMap GraphCreator(ArrayList<User> users){
        
       
        
        for(int i=0; i<users.size();i++){
            
            m.put(users.get(i).getId(),i );
            
        }
        
      return m;
        
    }
    
    
    
    
    
}
