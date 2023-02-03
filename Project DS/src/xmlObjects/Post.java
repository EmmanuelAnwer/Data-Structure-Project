package xmlObjects;

import XMLTreePackage.TreeNode;
import java.util.ArrayList;


public class Post
{
    private String body;
    private ArrayList<String> topics;

    public String getBody() { 
         return this.body; 
    }
    public void setBody(String body) { 
         this.body = body;
    }

    public ArrayList<String> getTopics() { 
         return this.topics;
    }
    public void setTopics(ArrayList<String> topics) { 
         this.topics = topics; 
    }
    
    public static Post postFactory(TreeNode node){
        Post post = new Post();
        
        
        ArrayList<String> topics = new ArrayList<String>();
        post.setTopics(topics);
        ArrayList<TreeNode> children = node.getChildren();
        
        for(int childIndex = 0; childIndex <children.size(); childIndex++){
            TreeNode currentChild = children.get(childIndex);
            switch (currentChild.getTag()){
                case "body":
                    post.setBody(currentChild.getValue());
                    break;
                case "topics":
                    for(int topicIndex = 0 ; topicIndex < currentChild.getChildren().size(); topicIndex++){
                        String topic = currentChild.getChildren().get(topicIndex).getValue();
                        topics.add(topic);
                    }
                    break;
            }
        }
        
        return post;
    }
    
    
    
}