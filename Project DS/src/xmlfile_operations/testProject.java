package xmlfile_operations;

import java.io.BufferedReader;
import java.io.IOException;
        
public class testProject {

    public static void main(String[] args) throws IOException {
        //XMLStringFromPath xmlStringFromPath = new XMLStringFromPath();
        //BufferedReader xmlString = xmlStringFromPath.getXMLString(args);
        //System.out.println(xmlString);
        XmlFile x = new XmlFile("D:\\My-Github\\Project\\indentedOut1.xml");
        x.validator();
//        Project pj = new Project();
//        pj.validator("Sample2.xml");
    }
}
