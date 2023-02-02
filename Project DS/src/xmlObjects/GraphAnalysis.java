/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlObjects;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class GraphAnalysis {
    //returning the ids of the most followed users
    public static  ArrayList<Integer> Mostactive(int[][] matrix){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int[] count = new int[matrix.length];
        for(int i =0;i<matrix.length;i++){
          for(int j =0;j<matrix[0].length;j++){  
              if(matrix[i][j] == 1)
                 count[i] += 1;
          }
        }
        int indexMost = 0;
        for(int z =1;z<count.length;z++){
            if(count[z] > count[indexMost])
               indexMost = z;
        }
        for(int z =0;z<count.length;z++){
            if(count[indexMost] == count[z])
               result.add(z+1);
        }
        return result;
    }
    
    // returning ids of users following the most
    public static  ArrayList<Integer> Mostactive(int[][] matrix){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int[] count = new int[matrix.length];
        for(int i =0;i<matrix.length;i++){
          for(int j =0;j<matrix[0].length;j++){  
              if(matrix[i][j] == 1)
                 count[i] += 1;
          }
        }
        int indexMost = 0;
        for(int z =1;z<count.length;z++){
            if(count[z] > count[indexMost])
               indexMost = z;
        }
        for(int z =0;z<count.length;z++){
            if(count[indexMost] == count[z])
               result.add(z+1);
        }
        return result;
    }
    
    //returning the ids of mutual users between any 2 users 
     public static  ArrayList<Integer> MutualFollowers(int[][] matrix,int id1,int id2){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i =0; i<matrix.length;i++){
            if(matrix[id1-1][i] == 1 && matrix[id1-1][i]== matrix[id2-1][i] ){
                result.add(i+1);
            }  
        }
        return result;
    }

    //returning the ids of recommendations for a certain user
    public static ArrayList<Integer> suggest(int[][] matrix , int id){
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> MyFollowers = new ArrayList<Integer>();
        for(int i = 0; i<matrix.length;i++ ){
            if(matrix[id-1][i] == 1){
                MyFollowers.add(i+1);
            }
        }
        for(int j=0;j<MyFollowers.size();j++){
            for(int z =0; z<matrix.length;z++){
            if(matrix[MyFollowers.get(j)-1][z] == 1 && z != id-1 && !MyFollowers.contains(z+1) && !list.contains(z+1))
            {
                list.add(z+1);
            }
        }    
    } 
        return list;
    }
}
