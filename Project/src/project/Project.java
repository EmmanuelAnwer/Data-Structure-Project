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
        Path outFullPath = Paths.get(System.getProperty("user.dir"), "indentedOut1.xml");
        File inXml = new File(inFullPath.toString());
        File outXml = new File(outFullPath.toString());
        FileInputStream fis = new FileInputStream(inXml);
        FileOutputStream fos = new FileOutputStream(outXml);
        Scanner sc = new Scanner(fis);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        String line;
        int overAllTabs=0, rank, otag, ctag;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            rank = lineRank(line);
            otag = countOpenTag(line);
            ctag = countClosedTag(line);
            if(otag != ctag){
                overAllTabs -= ctag;
            }
            for(int i=0; i<overAllTabs-rank; i++){
                bw.write("   ");
            }
            bw.write(line);
            bw.newLine();
            if(otag != ctag){
                overAllTabs += otag;
            }
        }
        sc.close();
        bw.close();
    }
    
    public int countOpenTag(String line){
        int oCount;
        Pattern openTagPattern = Pattern.compile("<[a-zA-Z_]*>");
        Matcher openTagMatcher = openTagPattern.matcher(line);
        oCount =(int) openTagMatcher.results().count();
        return oCount;
    }
    
    public int countClosedTag(String line){
        int cCount;
        Pattern closedTagPattern = Pattern.compile("<\\/[a-zA-Z_]*>");
        Matcher closedTagMatcher = closedTagPattern.matcher(line);
        cCount =(int) closedTagMatcher.results().count();
        return cCount;
    }
    
    /* assert wellformed xml
    return 0 if this line contain root element (Ex: users)
    return 1 if it is child (Ex: user)
    return 2 if it is grandchild (Ex: likes, followers, posts)
    return 3 if it is data of childs (Ex: number of likes, specific post)
    */
    public int lineRank(String line){
        int counter;
        Pattern spaceTagPattern = Pattern.compile("(?:[ ]{3})");
        Matcher spaceTagMatcher = spaceTagPattern.matcher(line);
        counter = (int) spaceTagMatcher.results().count();
        return counter;
    }
    
    public static void main(String[] args) throws IOException {
            Project pj = new Project();
            pj.Tzbet("Sample1.xml");
    }
    
}
