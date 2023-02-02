package xmlfile_operations;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;


public class Compression {
        //input = path of XML file, output = the path where you want to save the Minify XML file
    public static String Minify(String input) throws IOException {
        File file = new File(input);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuffer result = new StringBuffer();
            String line;
            while ( (line = reader.readLine() ) != null){
                result.append(line.trim());
                //System.out.println(result);
            }
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
}
