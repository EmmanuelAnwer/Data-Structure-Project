package xmlObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    HashMap <Integer,Integer> m;
    XMLUsers users;
    int[][] adjMat;

    public Graph(XMLUsers users) {
        this.users = users;
        adjMat = new int[users.len()][users.len()];
        m = new HashMap<>();
        mapCreator();
    }

    void addEdge(int x, int y){
        adjMat [x][y] = 1;
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

    ArrayList<Post> postSearch(String searchWords){
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

    /*
    static ArrayList<Post> Search(String searchWords, ArrayList<Post> postat){
        String[] searchArray = searchWords.split("[^\\w']+");

        ArrayList<Post> pf = new ArrayList<>();


            int pSize = postat.size();

            //iterate for each post
            for(int j=0; j<pSize;j++){
                Post post = postat.get(j);
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
        return pf;
    }
    */

    public static void main(String[] args) {

        /*
        Post p1 = new Post();
        p1.setBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        Post p2 = new Post();
        p2.setBody("Oneday i was young");
        Post p3 = new Post();
        p3.setBody("OneDay you will be older");
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(p1);
        posts.add(p2);
        posts.add(p3);
        ArrayList<Post> sp = Search("OneDay you will be", posts);
        for (Post p: sp){
            System.out.println(p.getBody());
        }
        */

    }
}
