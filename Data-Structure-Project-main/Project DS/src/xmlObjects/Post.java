package xmlObjects;

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
}