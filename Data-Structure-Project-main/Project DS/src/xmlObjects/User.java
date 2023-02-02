package xmlObjects;

import java.util.ArrayList;

public class User
{
    private int id;
    private String name;
    private ArrayList<Post> posts;
    private ArrayList<Follower> followers;

    public int getId() { 
         return this.id;
    }
    public void setId(int id) { 
         this.id = id;
    }
    
    public String getName() { 
         return this.name;
    }
    public void setName(String name) { 
         this.name = name;
    }

    public ArrayList<Post> getPosts() { 
         return this.posts; 
    }
    public void setPosts(ArrayList<Post> posts) { 
         this.posts = posts; 
    }

    public ArrayList<Follower> getFollowers() { 
         return this.followers; 
    }
    public void setFollowers(ArrayList<Follower> followers) { 
         this.followers = followers; 
    }
}
