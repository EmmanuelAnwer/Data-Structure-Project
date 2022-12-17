package xmlfile_operations;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlFile {
    private static String fullPath;

    XmlFile(String path){XmlFile.fullPath = path;}

    void setPath(String path){fullPath = path;}

    String getPath(){return fullPath;}

    static Scanner xmlScanner() throws IOException{ // returns scanner for xml
        FileInputStream fis = new FileInputStream(new File(XmlFile.fullPath));
        Scanner sc = new Scanner(fis);
        return sc;
    }

    public void prettifying() throws IOException {
        Scanner sc = XmlFile.xmlScanner();
        Path outFullPath = Paths.get(System.getProperty("user.dir"), "indentedOut2.xml");//get currrentpath and then join
        File outXml = new File(outFullPath.toString());
        FileOutputStream fos = new FileOutputStream(outXml);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        String line;
        int nextLineRank=0, currentRank, openTags, closeTags, allTags;
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
                for(int i=0; i<currentRank-lineRank(line); i++)
                    bw.write("    ");
            }
            else{
                for(int i=0; i<currentRank-closeTags-lineRank(line); i++)
                    bw.write("    ");
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
    
    int countOpenTags(String line){
        int oCount;
        Pattern openTagPattern = Pattern.compile("<[a-zA-Z_]*>");
        Matcher openTagMatcher = openTagPattern.matcher(line);
        oCount =(int) openTagMatcher.results().count();
        return oCount;
    }
    
     int countClosedTags(String line){
        int cCount;
        Pattern closeTagPattern = Pattern.compile("<\\/[a-zA-Z_]*>");
        Matcher closeTagMatcher = closeTagPattern.matcher(line);
        cCount =(int) closeTagMatcher.results().count();
        return cCount;
    }
    
     int lineRank(String line){
        int counter;
        Pattern spaceTagPattern = Pattern.compile("(?:[ ]{3})");
        Matcher spaceTagMatcher = spaceTagPattern.matcher(line);
        counter = (int) spaceTagMatcher.results().count();
        return counter;
            /* assert (valid xml)
            return 0 if this line contain root element (Ex: users)
            return 1 if it is child (Ex: user)
            return 2 if it is grandchild (Ex: likes, followers, posts)
            return 3 if it is data of childs (Ex: number of likes, specific post)
            */
    }

        public void validator() throws IOException{
        Scanner sc = XmlFile.xmlScanner();
        String line,tag,errorslog="";
        Stack<String> tagsStack = new Stack<>();
        Stack<String> spareStack = new Stack<>();
        ArrayList<String> tagsList;
        int lineTracer=0;
        boolean goodXml = true;
        while(sc.hasNextLine()){
            line = sc.nextLine().strip();
            lineTracer++;
            tagsList = tagsName(line);
            for (int i=0; i<tagsList.size(); i++){
                tag = tagsList.get(i);

                //case it is openTag
                if(!(tag.substring(0,1).equals("/"))) {
                    tagsStack.push(tag);
                }
                //case it is closeTag
                else{
                    //closeTag has no openTag
                    if (tagsStack.empty()) {
                        errorslog = "";
                        errorslog += "Line: " + lineTracer + ": the tag " + tag.substring(1) + " has no opening tag!\n";
                        goodXml = false;
                        //System.out.println("Line: " + lineTracer + ": the tag " + tag.substring(1) + " has no opening tag!");
                        while(!spareStack.empty()){
                            tagsStack.push(spareStack.pop());
                        }
                    }
                    else{
                        //closeTag it's openTag found
                        if(tag.substring(1).equals(tagsStack.peek())) {
                            tagsStack.pop();
                        }
                        else{ //handle case 4 ("deleted random closeTags")
                            goodXml = false;
                            errorslog += "Line: " + lineTracer + ": closeTag " + tag.substring(1) + " and openTag " + tagsStack.peek() + " mismatched!\n";
                                //System.out.println("Line: " + lineTracer + ": closeTag " + tag.substring(1) + " and openTag " + tagsStack.peek() + " mismatched!");
                            spareStack.push(tagsStack.pop());
                            i--;
                        }
                    }
                }
            }
        }
        sc.close();
        if(tagsStack.empty() && goodXml) //handle case 1 ("normal case no errors")
            System.out.println("Good Xml (2BST Y3M :D)");
        else{// handle case 5 ("delete the lasts tags")
            while(!tagsStack.empty())
            System.out.println("Line: " + lineTracer + ": the tag " + tagsStack.pop() + " has no closeTag!");
        }
        System.out.println(errorslog);
    }
    
    public ArrayList tagsName(String line){
        Pattern tagsPattern = Pattern.compile("<([^<>]*)>|<([^<>]*)>");
        //Pattern p2 = Pattern.compile(">(.*)<|^[^<]*"); //for data
        Matcher tagsMatcher = tagsPattern.matcher(line);
        ArrayList<String> tags;
        tags = new ArrayList<>(2);
        while(tagsMatcher.find()){
            for(int i=1; i<tagsMatcher.groupCount(); i++){
                tags.add(tagsMatcher.group(i));
            }
        }    
        return tags;
    }
}