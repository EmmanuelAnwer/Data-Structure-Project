package project;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project {

    void Tzbet(String inPath) throws FileNotFoundException, IOException{
        Path inFullPath = Paths.get(System.getProperty("user.dir"), inPath); //get currrentpath and then join
        Path outFullPath = Paths.get(System.getProperty("user.dir"), "indentedOut.xml");
        File inXml = new File(inFullPath.toString());
        File outXml = new File(outFullPath.toString());
        FileInputStream fis = new FileInputStream(inXml);
        FileOutputStream fos = new FileOutputStream(outXml);
        Scanner sc = new Scanner(fis);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        String line;
        //int tagsDiff;
        int overAllTabs=0;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            //System.out.println(line);
            //tagsDiff = isContainOpenTag(line) - isContainClosedTag(line);
            if(isContainClosedTag(line) != isContainOpenTag(line)){
                overAllTabs -= isContainClosedTag(line);
            }
            for(int i=0; i<overAllTabs; i++){
                bw.write("   ");
            }
            bw.write(line);
            bw.newLine();
            if(isContainClosedTag(line) != isContainOpenTag(line)){
                overAllTabs += isContainOpenTag(line);
            }
        }
        sc.close();
        bw.close();
}
    public int isContainOpenTag(String line){
        int oCount;
        Pattern openTagPattern = Pattern.compile("<[a-zA-Z_]*>");
        Matcher openTagMatcher = openTagPattern.matcher(line);
        oCount =(int) openTagMatcher.results().count();
        //System.out.println(oCount);
        return oCount;
    }
    public int isContainClosedTag(String line){
        int cCount;
        Pattern closedTagPattern = Pattern.compile("<\\/[a-zA-Z_]*>");
        Matcher closedTagMatcher = closedTagPattern.matcher(line);
        cCount =(int) closedTagMatcher.results().count();
        System.out.println(cCount);
        return cCount;
    }
    
    public static void main(String[] args) throws IOException {
            // TODO code application logic here
            Project pj = new Project();
            pj.Tzbet("Sample1.xml");
    }
    
}
