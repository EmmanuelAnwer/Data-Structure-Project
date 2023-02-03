package xmlObjects;

import XMLTreePackage.TreeNode;

public class Follower
{
    private int id;

    public int getId() { 
         return this.id ;
    }
    public void setId(int id) { 
         this.id = id;
    }
    
    public static Follower followerFactory(TreeNode node){
        Follower follower = new Follower();
        String idString = node.getChildren().get(0).getValue();
        follower.setId(Integer.parseInt(idString));
        
        return follower;
    }

}