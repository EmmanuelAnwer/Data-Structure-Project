import java.io.IOException;
import javafx.application.Platform;
import xmlfile_operations.XMLStringFromPath;
        
public class testProject {

    public static void main(String[] args) throws IOException {
        XMLStringFromPath xmlStringFromPath = new XMLStringFromPath();
        xmlStringFromPath.getXMLString(args);        
    }
}
