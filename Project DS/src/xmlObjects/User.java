package xmlObjects;

import XMLTreePackage.TreeNode;
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
    
    public static User userFactory(TreeNode treeNode){
        User user = new User();
        ArrayList<Post> posts = new ArrayList<Post>();
        user.setPosts(posts);
        ArrayList<Follower> followers = new ArrayList<Follower>();
        user.setFollowers(followers);

        
        
        ArrayList<TreeNode> children = treeNode.getChildren();
        
        for(int childIndex = 0; childIndex <children.size(); childIndex++){
            TreeNode currentChild = children.get(childIndex);
            switch (currentChild.getTag()){
                case "id":
                    user.setId(Integer.parseInt(currentChild.getValue()));
                    break;
                case "name":
                    user.setName(currentChild.getValue());
                    break;
                case "posts":
                    for(int postChildIndex = 0; postChildIndex < currentChild.getChildren().size(); postChildIndex++){
                        TreeNode postNode = currentChild.getChildren().get(postChildIndex);
                        Post post = Post.postFactory(postNode);
                        posts.add(post);
                    }
                    break;
                case "followers":
                    for(int followerChildIndex = 0; followerChildIndex < currentChild.getChildren().size(); followerChildIndex++){
                        TreeNode followerNode = currentChild.getChildren().get(followerChildIndex);
                        Follower follower = Follower.followerFactory(followerNode);
                        followers.add(follower);
                    }       
            }
        }
        
        
        return user;
    }
}
