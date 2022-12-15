package com.asu.ds.project;

import java.io.BufferedReader;
import java.io.IOException;
        
public class DSProject {

    public static void main(String[] args) throws IOException {
        XMLStringFromPath xmlStringFromPath = new XMLStringFromPath();
        BufferedReader xmlString = xmlStringFromPath.getXMLString(args);
        System.out.println(xmlString);
        
//        Project pj = new Project();
//        pj.validator("Sample2.xml");
    }
}
