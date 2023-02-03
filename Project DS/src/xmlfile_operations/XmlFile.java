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
    private ArrayList<String> newList;


    public XmlFile(String path) throws IOException {
        XmlFile.fullPath = path;
        xmlList = xmlToList();
    }


    void setPath(String path){
        fullPath = path;
    }

    public void setXmlList(ArrayList<String> xmlList) {
        this.xmlList = xmlList;
    }

    public void setNewList(ArrayList<String> newList) {
        this.newList = newList;
    }

    public ArrayList<String> getNewList() {
        return newList;
    }


    String getPath(){
        return fullPath;
    }


    public ArrayList<String>getXmlList(){
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


    boolean isOpenTag(String str){
        return str.contains("<") && str.contains(">") && !str.contains("/");
    }

    boolean isClosedTag(String str){
        return str.contains("<") && str.contains(">") && str.contains("/");
    }

    String symbolRemover(String str){
        str = str.replaceAll("\\s+","");
        if(isOpenTag(str)){
            return str.substring(1, str.length()-1);
        }
        else{
            return str.substring(2, str.length()-1);
        }
    }

    String toClose(String str){
        String s ="";
        s += "</" + str + '>';
        return s;
    }

    String toOpen(String str){
        String s ="";
        s += "<" + str + '>';
        return s;
    }

    public String prettifying(){
        
    int currentRank=0, level=0;
    String tag;

    String prettifiedXml = "";
    
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
            prettifiedXml += "    ";

        prettifiedXml += tag+"\n";
    }
    return prettifiedXml;
}
    public String validator() throws IOException{

        String line,tag,s;
        Stack<String> tagsStack = new Stack<>();
        ArrayList<String> newXml = new ArrayList<>();
        ArrayList<String> compXml = new ArrayList<>();
        //boolean goodXml = true;

            for (int i = 0; i < xmlList.size(); i++){
                tag = xmlList.get(i);
                switch (tagType(tag)){
                    case "openTag":
                        newXml.add(tag);
                        tagsStack.push(tag);
                        break;
                    case "closeTag":
                        if(tagsStack.empty()){
                            newXml.add(xmlList.size()-i-1, toOpen(symbolRemover(tag)));
                            newXml.add(tag);
                        }
                        else {
                            if (symbolRemover(tag).equals(symbolRemover(tagsStack.peek()))) {
                                newXml.add(tag);
                                tagsStack.pop();
                            } else {
                                //newXml.add(xmlList.size()-i-1, toOpen(symbolRemover(tag)));
                                //newXml.add(tag);
                            }
                        }
                        break;
                    case "data":
                        if(symbolRemover(xmlList.get(i-1)).equals(symbolRemover(xmlList.get(i+1))) && tagType(xmlList.get(i-1)).equals("openTag")){
                            newXml.add(tag);
                            newXml.add(xmlList.get(i+1));
                            tagsStack.pop();
                            i++;
                        }
                        else if (!(symbolRemover(xmlList.get(i-1)).equals(symbolRemover(xmlList.get(i+1)))) && tagType(xmlList.get(i+1)).equals("closeTag")) {
                            newXml.add(toOpen(symbolRemover(xmlList.get(i+1))));
                            newXml.add(tag);
                            newXml.add(xmlList.get(i+1));
                            i++;
                        }
                        else {
                            //raise error
                            newXml.add(tag);
                            newXml.add(toClose(symbolRemover(tagsStack.peek())));
                            tagsStack.pop();
                        }
                        break;
                }
        }
            while(!tagsStack.empty()){
                newXml.add(toClose(symbolRemover(tagsStack.pop())));
            }
            //this.xmlList = newXml;
        //compare

        int offset = 0;
            int maxOffset = newXml.size() - xmlList.size();
        for(int i=0; i<newXml.size(); i++){
            if(xmlList.get(i-offset).equals(newXml.get(i))){
                compXml.add(i, newXml.get(i));
                continue;
            }
            if(offset < maxOffset)
                offset++;
            compXml.add(i, newXml.get(i)+ "\t<--");
        }

        this.xmlList = compXml;
        this.newList = newXml;
        return this.prettifying();
    }
    public static void main(String[] args) throws IOException {
        XmlFile xf = new XmlFile("D:\\My-Github\\Data-Structure-Project\\indentedOut2.xml");
        xf.validator();
        xf.printXml();
    }

}