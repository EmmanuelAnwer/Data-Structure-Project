package xmlObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private HashMap <Integer,Integer> m;
    private XMLUsers users;
    private int[][] adjMat;

    public Graph(XMLUsers users) {
        this.users = users;
        adjMat = new int[users.len()][users.len()];
        m = new HashMap<>();
        mapCreator();
        arrayMaker(users.getUsers());
    }

    public HashMap<Integer, Integer> getM() {
        return m;
    }

    void addEdge(int x, int y){
        adjMat [x][y] = 1;
    }

    public int[][] getAdjMat() {
        return adjMat;
    }
    
    void arrayMaker (ArrayList<User> users){
        
        for(int i=0; i<users.size();i++){
            
            for(int j=0; j<users.get(i).getFollowers().size();j++){
                
                adjMat [m.get(users.get(i).getId())] [m.get(users.get(i).getFollowers().get(j).getId())]=1;
                
            }
        }
    } 
   
    void mapCreator(){
        for(int i=0; i<users.len();i++){
            this.m.put(users.getByIndex(i).getId(), i);
        }
    }

    public ArrayList<Post> postSearch(String searchWords){
        String[] searchArray = searchWords.split("[^\\w']+");

        ArrayList<Post> pf = new ArrayList<>();
        int uSize = this.users.len();

        // iterate for each user
        for(int i=0; i<uSize; i++){
            User user = users.getByIndex(i);
            int pSize = user.getPosts().size();

            //iterate for each post
            for(int j=0; j<pSize;j++){
                Post post = user.getPosts().get(j);
                String[] bodyWords = post.getBody().split("[^\\w']+"); // split post into array of words

                //iterate for each word
                for(int k=0; k< bodyWords.length; k++){
                    boolean matched = false;

                   for(int h=0; h<searchArray.length; h++){
                       if(searchArray[h].equalsIgnoreCase(bodyWords[k+h])){
                           matched = true;
                       }
                       else{
                           matched = false;
                           break;
                       }
                   }
                   if(matched == true)
                       pf.add(post);
                }
            }
        }
        return pf;
    }
}
