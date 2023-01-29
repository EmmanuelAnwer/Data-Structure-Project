package xmlfile_operations;

import GUI.MainScreen;
import XMLTreePackage.Tree;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
        
public class testProject {

    public static void main(String[] args) throws IOException {
        Platform.setImplicitExit(false);
        XMLStringFromPath xmlStringFromPath = new XMLStringFromPath();
        ArrayList<String> xmlString = xmlStringFromPath.getXMLString(args);        
//        
//        TreeMaker treeMaker = new TreeMaker(xmlString);
//        treeMaker.treeCreator();
//        Tree tree = treeMaker.getTree();
//        System.out.println(tree.toJson());
    }
}
