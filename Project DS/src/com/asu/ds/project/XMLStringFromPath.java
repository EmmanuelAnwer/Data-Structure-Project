/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asu.ds.project;

import GUI.XMLPathInputGUI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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
    
    
    
    String getXMLString(String[] args) throws IOException{
        try{
            XMLPathInputGUI xmlPathInputGUI = new XMLPathInputGUI();
            xmlPathInputGUI.XMLPreviwer(args);
            BufferedReader br=new BufferedReader(new FileReader(path));
            String line;
            while((line=br.readLine()) != null){
                xmlString += line + "\n";
            }
            return xmlString;
        }
        catch (FileNotFoundException ex) {
            return getXMLString(args);
        }
    }
}
