package com.asu.ds.project;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project {
    
    public void Prettifying(String inPath) throws FileNotFoundException, IOException{
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
            line = sc.nextLine().strip();
            openTags = countOpenTags(line);
            closeTags = countClosedTags(line);
            if(openTags == closeTags){
                /*
                if(openTags == 0){
                    //handle data case
                }
                */
                for(int i=0; i<currentRank-lineRank(line); i++){
                    bw.write("    ");
                }
            }
            else{
                for(int i=0; i<currentRank-closeTags-lineRank(line); i++){
                    bw.write("    ");
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
    
    private int countOpenTags(String line){
        int oCount;
        Pattern openTagPattern = Pattern.compile("<[a-zA-Z_]*>");
        Matcher openTagMatcher = openTagPattern.matcher(line);
        oCount =(int) openTagMatcher.results().count();
        return oCount;
    }
    
    private int countClosedTags(String line){
        int cCount;
        Pattern closeTagPattern = Pattern.compile("<\\/[a-zA-Z_]*>");
        Matcher closeTagMatcher = closeTagPattern.matcher(line);
        cCount =(int) closeTagMatcher.results().count();
        return cCount;
    }
    
    private int lineRank(String line){
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
    
    public void validator(String inpath) throws FileNotFoundException{
        Path inFullPath = Paths.get(System.getProperty("user.dir"), inpath);
        File inXml = new File(inFullPath.toString());
        FileInputStream fis = new FileInputStream(inXml);
        Scanner sc = new Scanner(fis);
        String line;
        Stack<String> tagsStack = new Stack<>();
        ArrayList<String> tagsList;
        Iterator<String> iter;
        int lineTracer=0;
        while(sc.hasNextLine()){
            lineTracer++;
            line = sc.nextLine().strip();
            tagsList = tagsName(line);
            iter = tagsList.iterator();
            while(iter.hasNext()){
                line = iter.next();
                if(!(line.substring(0,1).equals("/"))){
                    //then it is an open tag
                    tagsStack.push(line);   
                }
                else{
                    //then it is a close tag
                    if(line.substring(1).equals(tagsStack.peek())){
                        tagsStack.pop();
                    }
                    else{
                        //there is an error
                        System.out.println("Error: Opening and ending tags mismatched "+line.substring(1)+" at line"+lineTracer+" and "+tagsStack.peek());
                        while(tagsStack.isEmpty()==false){
                            tagsStack.pop();
                            if(tagsStack.peek().equals(line.substring(1))){
                                tagsStack.pop();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public ArrayList tagsName(String line){
        Pattern tagspPattern = Pattern.compile("<([^<>]*)>|<([^<>]*)>");
        //Pattern p2 = Pattern.compile(">(.*)<|^[^<]*"); //for data
        Matcher tagsMatcher = tagspPattern.matcher(line);
        ArrayList<String> tags;
        tags = new ArrayList<>(2);
        while(tagsMatcher.find()){
            for(int i=1; i<tagsMatcher.groupCount(); i++){
                tags.add(tagsMatcher.group(i));
            }
        }    
        return tags;
    }
    
    public static void main(String[] args) throws IOException {
            Project pj = new Project();
            pj.validator("Sample2.xml");
    }
}