package xmlfile_operations;

import GUI.XMLPathInputGUI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author user
 */
public class XMLStringFromPath {
    private String xmlString;
    private static String path;
    
    public XMLStringFromPath(){
        xmlString = "";
    }
    
    public static void setPath(String path) {
        XMLStringFromPath.path = path;
    }



    static ArrayList<String> getXMLString(String[] args) throws IOException{
        try{
            XMLPathInputGUI xmlPathInputGUI = new XMLPathInputGUI();
            xmlPathInputGUI.XMLPreviwer(args);
            XmlFile xmlFile = new XmlFile(path);
            return xmlFile.getXmlList();
//            BufferedReader br=new BufferedReader(new FileReader(path));
            
//            return br;
        }
        catch (FileNotFoundException ex) {
            return getXMLString(args);
        }
    }
}
