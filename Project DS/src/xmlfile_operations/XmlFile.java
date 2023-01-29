package xmlfile_operations;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlFile {
    private static String fullPath;
    private ArrayList<String> xmlList;


    public XmlFile(String path) throws IOException {
        XmlFile.fullPath = path;
        xmlList = xmlToList();
    }


    void setPath(String path){
        fullPath = path;
    }


    String getPath(){
        return fullPath;
    }


    ArrayList<String>getXmlList(){
        return (ArrayList<String>) xmlList.clone();
    }


    String tagType(String tagString){
        if(tagString.substring(0,1).equals("<")){
            if(tagString.substring(1,2).equals("/")){
                return "closeTag";
            }
            else{
                return "openTag";
            }
        }
        else{
            return "data";
        }
    }


    private ArrayList<String> xmlToList() throws IOException {
        Scanner sc = XmlFile.xmlScanner();
        ArrayList<String> tagsList,listOfData = new ArrayList<>();
        String line;
        while(sc.hasNextLine()) {
            line = sc.nextLine().strip();
            tagsList = tagsName(line);

            for (int i = 0; i < tagsList.size()-1; i++) {
                listOfData.add(tagsList.get(i));
            }
        }
        return listOfData;
    }


    private static Scanner xmlScanner() throws IOException{
        FileInputStream fis = new FileInputStream(new File(XmlFile.fullPath));
        Scanner sc = new Scanner(fis);
        return sc;
    }


    private ArrayList tagsName(String line){
        Pattern tagsPattern = Pattern.compile("(?:(<[^<>]*>)|([^\\n<]*)|(<[^<>]*>))");
        Matcher tagsMatcher = tagsPattern.matcher(line);
        ArrayList<String> tags = new ArrayList<>();
        while(tagsMatcher.find()){
            for(int i=1; i<tagsMatcher.groupCount(); i++){
                if(tagsMatcher.group(i)!=null)
                    tags.add(tagsMatcher.group(i));
            }
        }
        return tags;
    }


    public void printXml(){
        for(String iter : xmlList) {
            System.out.println(iter);
        }
    }


    String symbolRemover(String str){
        str = str.replaceAll("\s+","");
        if(isOpenTag(str)){
            return str.substring(1, str.length()-1);
        }
        else{
            return str.substring(2, str.length()-1);
        }
    }


    boolean isOpenTag(String str){
        return str.contains("<") && str.contains(">") && !str.contains("/");
    }


    boolean isClosedTag(String str){
        return str.contains("<") && str.contains(">") && str.contains("/");
    }


    public void prettifying() throws IOException {
    Path outFullPath = Paths.get(System.getProperty("user.dir"), "indentedOut2.xml");//get currrentpath and then join
    File outXml = new File(outFullPath.toString());
    FileOutputStream fos = new FileOutputStream(outXml);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

    int currentRank=0, level=0;
    String tag;

    for (int i = 0; i < xmlList.size(); i++) {
        tag = xmlList.get(i);

        switch (tagType(tag)) {
            case "openTag":
                currentRank = level;
                level++;
                break;
            case "closeTag":
                level--;
                currentRank = level;
                break;
            case "data":
                currentRank = level;
                break;
        }
        for (int j = 0; j < currentRank; j++)
            bw.write("    ");

        bw.write(tag+"\n");
    }
    bw.close();
    System.out.println("And now your beautiful Xml at " + outFullPath);
}


    public void validator() throws IOException{

        String line,tag,s;
        Stack<String> tagsStack = new Stack<>();
        //Stack<String> spareStack = new Stack<>();
        ArrayList<String> newXml = new ArrayList<>();
        //int lineTracer=0;
        boolean goodXml = true;


            for (int i = 0; i < xmlList.size(); i++){
                tag = xmlList.get(i);
                //System.out.println(tag);
                switch (tagType(tag)){
                    case "openTag":
                        tagsStack.push(tag);
                        newXml.add(tag);
                        break;
                    case "closeTag":

                        break;
                    case "data":
                        if(tagType(xmlList.get(i-1))=="openTag" && tagType(xmlList.get(i+1))=="closeTag"){
                            newXml.add(tag);
                        }
                        if(tagType(xmlList.get(i-1))!="openTag" && tagType(xmlList.get(i+1))=="closeTag"){
                            s = xmlList.get(i-1);
                            newXml.add(symbolRemover(s));
                            newXml.add(tag);
                        }
                        if(tagType(xmlList.get(i-1))=="openTag" && tagType(xmlList.get(i+1))!="closeTag"){
                            s = xmlList.get(i+1);
                            newXml.add(tag);
                            newXml.add(symbolRemover(s));
                        }
                        break;
                }
        }
    }


}