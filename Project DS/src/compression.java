<<<<<<< HEAD
<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectds;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;
import xmlfile_operations.XmlFile;
/**
 *
 * @author HP
 */
public class compression {
    //input = path of XML file, output = the path where you want to save the Minify XML file
public static String Minify(String input, String output) throws IOException {
    File file = new File(input);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuffer result = new StringBuffer();
        String line;
        while ( (line = reader.readLine() ) != null){
            result.append(line.trim());
            //System.out.println(result);
        }
        Path path = Paths.get(output);
        byte[] arr = (result.toString()).getBytes();
        Files.write(path, arr);
        return result.toString();
    
}
// Method DeflatorOutputStream
    // input = output from Minify function
public static byte[] compress(byte [] input) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();   
    try(DeflaterOutputStream dos = new DeflaterOutputStream(os)){
        dos.write(input);  
    }
    
    return os.toByteArray();
}
    // input = output from Compress function
public static byte[] decompress(byte[] input) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();    
    try (OutputStream ios = new InflaterOutputStream(os)) {
        ios.write(input);   
    }
 
    return os.toByteArray();
}
public static void main(String[] args) throws FileNotFoundException, IOException
    {
//    String ss = Minify("C:\\Users\\user\\Downloads\\sample (2).xml","C:\\Users\\user\\Downloads\\sample (3).xml");
//        byte[] input = ss.getBytes();
//        byte[] op = compress(input);
//        Path path = Paths.get("C:\\Users\\user\\Downloads\\sample (4).xml");
//        byte[] op = Files.readAllBytes(path);
//        System.out.println("original data length " + input.length + ",  compressed data lenght "+ op.length);
 
//        byte[] org = decompress(op);
//        Path path2 = Paths.get("C:\\Users\\user\\Downloads\\sample (5).xml");
//        Files.write(path2, org);
        XmlFile xmlFile = new XmlFile("C:\\Users\\user\\Downloads\\sample (5).xml");
        xmlFile.prettifying();
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectds;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;
/**
 *
 * @author HP
 */
public class compression {
    //input = path of XML file, output = the path where you want to save the Minify XML file
public static String Minify(String input, String output) throws IOException {
    File file = new File(input);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuffer result = new StringBuffer();
        String line;
        while ( (line = reader.readLine() ) != null){
            result.append(line.trim());
            //System.out.println(result);
        }
        Path path = Paths.get(output);
        byte[] arr = (result.toString()).getBytes();
        Files.write(path, arr);
        return result.toString();
    
}
// Method DeflatorOutputStream
    // input = output from Minify function
public static byte[] compress(byte [] input) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();   
    try(DeflaterOutputStream dos = new DeflaterOutputStream(os)){
        dos.write(input);  
    }
    
    return os.toByteArray();
}
    // input = output from Compress function
public static byte[] decompress(byte[] input) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();    
    try (OutputStream ios = new InflaterOutputStream(os)) {
        ios.write(input);   
    }
 
    return os.toByteArray();
}
public static void main(String[] args) throws FileNotFoundException, IOException
    {
    String ss = Minify("D:\\Data Structure\\sample.xml","D:\\Data Structure\\mm1.xml");
        byte[] input = ss.getBytes();
        byte[] op = compress(input);
        Path path = Paths.get("D:\\Data Structure\\mm2.xml");
        Files.write(path, op);
        System.out.println("original data length " + input.length + ",  compressed data lenght "+ op.length);
 
        byte[] org = decompress(op);
        Path path2 = Paths.get("D:\\Data Structure\\mm3.xml");
        Files.write(path2, org);
    }
    
}
>>>>>>> 2a5b7550098f7d6d49dd063dbc0dd2fb58ca722d

=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectds;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;
/**
 *
 * @author HP
 */
public class compression {
    //input = path of XML file, output = the path where you want to save the Minify XML file
public static String Minify(String input, String output) throws IOException {
    File file = new File(input);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuffer result = new StringBuffer();
        String line;
        while ( (line = reader.readLine() ) != null){
            result.append(line.trim());
            //System.out.println(result);
        }
        Path path = Paths.get(output);
        byte[] arr = (result.toString()).getBytes();
        Files.write(path, arr);
        return result.toString();
    
}
// Method DeflatorOutputStream
    // input = output from Minify function
public static byte[] compress(byte [] input) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();   
    try(DeflaterOutputStream dos = new DeflaterOutputStream(os)){
        dos.write(input);  
    }
    
    return os.toByteArray();
}
    // input = output from Compress function
public static byte[] decompress(byte[] input) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();    
    try (OutputStream ios = new InflaterOutputStream(os)) {
        ios.write(input);   
    }
 
    return os.toByteArray();
}
public static void main(String[] args) throws FileNotFoundException, IOException
    {
    String ss = Minify("D:\\Data Structure\\sample.xml","D:\\Data Structure\\mm1.xml");
        byte[] input = ss.getBytes();
        byte[] op = compress(input);
        Path path = Paths.get("D:\\Data Structure\\mm2.xml");
        Files.write(path, op);
        System.out.println("original data length " + input.length + ",  compressed data lenght "+ op.length);
 
        byte[] org = decompress(op);
        Path path2 = Paths.get("D:\\Data Structure\\mm3.xml");
        Files.write(path2, org);
    }
    
}
>>>>>>> 2a5b7550098f7d6d49dd063dbc0dd2fb58ca722d
