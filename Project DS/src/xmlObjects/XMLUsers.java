package xmlObjects;

import java.util.ArrayList;

public class XMLUsers{
    ArrayList<User> users;

    public ArrayList<User> getUsers() { 
         return this.users; 
    }
    public void setUsers(ArrayList<User> users) { 
         this.users = users;
    }
    
    public int getid(User u){
        
        return u.getId();
        
    }
    
    public ArrayList<Follower> getFollowers(User u){
        
        return u.getFollowers();
    }
    
}