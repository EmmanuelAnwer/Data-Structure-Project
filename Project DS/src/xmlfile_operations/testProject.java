package xmlfile_operations;

import GUI.MainScreen;
import XMLTreePackage.Tree;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
        
public class testProject {

    public static void main(String[] args) throws IOException {
<<<<<<< HEAD
        Platform.setImplicitExit(false);
        XMLStringFromPath xmlStringFromPath = new XMLStringFromPath();
        ArrayList<String> xmlString = xmlStringFromPath.getXMLString(args);        
//        
//        TreeMaker treeMaker = new TreeMaker(xmlString);
//        treeMaker.treeCreator();
//        Tree tree = treeMaker.getTree();
//        System.out.println(tree.toJson());
=======
        //XMLStringFromPath xmlStringFromPath = new XMLStringFromPath();
        //BufferedReader xmlString = xmlStringFromPath.getXMLString(args);
        //System.out.println(xmlString);
        XmlFile x = new XmlFile("D:\\My-Github\\Project\\Sample1.xml");

//        Project pj = new Project();
//        pj.validator("Sample2.xml");
>>>>>>> 2a5b7550098f7d6d49dd063dbc0dd2fb58ca722d
    }
}
