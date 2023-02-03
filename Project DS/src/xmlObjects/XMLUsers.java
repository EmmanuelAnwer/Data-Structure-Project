package xmlObjects;

import XMLTreePackage.Tree;
import XMLTreePackage.TreeNode;
import java.util.ArrayList;

public class XMLUsers{
    ArrayList<User> users;

    public ArrayList<User> getUsers() { 
         return this.users; 
    }

    public void setUsers(ArrayList<User> users) { 
         this.users = users;
    }

    public int len(){
        return users.size();
    }

    public User getByIndex(int i){
        return users.get(i);
    }
    
    public static XMLUsers usersFactory(Tree tree){
        XMLUsers xmlUsers = new XMLUsers();
        ArrayList<User> users = new ArrayList<User>();
        xmlUsers.setUsers(users);
        
        ArrayList<TreeNode> usersNodes = tree.getRoot().getChildren();
        for(int userNodeIndex = 0; userNodeIndex < usersNodes.size(); userNodeIndex++){
            users.add(User.userFactory(usersNodes.get(userNodeIndex)));
        }
        
        return xmlUsers;
    }
    
    
}