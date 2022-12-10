package project;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Stack;

public class Project {

    void Prettifying(String inPath) throws FileNotFoundException, IOException{
        Path inFullPath = Paths.get(System.getProperty("user.dir"), inPath); //get currrentpath and then join
        Path outFullPath = Paths.get(System.getProperty("user.dir"), "indentedOut2.xml");
        
        File inXml = new File(inFullPath.toString());
        File outXml = new File(outFullPath.toString());
        
        FileInputStream fis = new FileInputStream(inXml);
        FileOutputStream fos = new FileOutputStream(outXml);
        
        Scanner sc = new Scanner(fis);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        
        String line;
        int nextLineRank=0, currentRank=0, openTags, closeTags, allTags;
        Stack<Integer> rankStack = new Stack<>();
        rankStack.push(nextLineRank);
        while(sc.hasNextLine()){
            currentRank = rankStack.pop();
            line = sc.nextLine();
            openTags = countOpenTags(line);
            closeTags = countClosedTags(line);
            if(openTags == closeTags){
                /*
                if(openTags == 0){
                    //handle data case
                }
                */
                for(int i=0; i<currentRank-lineRank(line); i++){
                    bw.write("   ");
                }
            }
            else{
                for(int i=0; i<currentRank-closeTags-lineRank(line); i++){
                    bw.write("   ");
                }
            }
            bw.write(line);
            bw.newLine();
            allTags = openTags - closeTags;
            nextLineRank = currentRank + allTags;
            rankStack.push(nextLineRank);
        }
        sc.close();
        bw.close();
    }
    
    public int countOpenTags(String line){
        int oCount;
        Pattern openTagPattern = Pattern.compile("<[a-zA-Z_]*>");
        Matcher openTagMatcher = openTagPattern.matcher(line);
        oCount =(int) openTagMatcher.results().count();
        return oCount;
    }
    
    public int countClosedTags(String line){
        int cCount;
        Pattern closedTagPattern = Pattern.compile("<\\/[a-zA-Z_]*>");
        Matcher closedTagMatcher = closedTagPattern.matcher(line);
        cCount =(int) closedTagMatcher.results().count();
        return cCount;
    }
    
    public int lineRank(String line){
        int counter;
        Pattern spaceTagPattern = Pattern.compile("(?:[ ]{3})");
        Matcher spaceTagMatcher = spaceTagPattern.matcher(line);
        counter = (int) spaceTagMatcher.results().count();
        return counter;
            /* assert (wellformed xml)
            return 0 if this line contain root element (Ex: users)
            return 1 if it is child (Ex: user)
            return 2 if it is grandchild (Ex: likes, followers, posts)
            return 3 if it is data of childs (Ex: number of likes, specific post)
            */
    }
    
    public static void main(String[] args) throws IOException {
            Project pj = new Project();
            pj.Prettifying("Sample2.xml");
    }
}
