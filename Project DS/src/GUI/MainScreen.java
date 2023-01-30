/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import XMLTreePackage.Tree;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import xmlfile_operations.Compression;
import xmlfile_operations.TreeMaker;
import xmlfile_operations.XmlFile;

/**
 *
 * @author user
 */
public class MainScreen{
    final private double[] PRIMARY_STAGE_DIMENSIONS = {500, 720};
    final private String XML_STAGE_TITLE = "Main Screen"; 

    
    public Scene mainScene(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        
        vbox.getChildren().add(topButtons());
        vbox.getChildren().add(midButtons());
        vbox.getChildren().add(buttomButtons());
        
        vbox.setSpacing(20);

        
        
        Scene scene = new Scene(vbox);
        
        return scene;
    }
    
    private BorderPane topButtons(){
        
        BorderPane hbox = new BorderPane();
        
        Button correctButton = new Button();
        correctButton.setText("Correct");
        correctButton.setPrefWidth(100);
        correctButton.setPrefHeight(100);

        correctButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                try {
                    onClickCorrect();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

        
        Button minifiyButton = new Button();
        minifiyButton.setText("Minify");
        minifiyButton.setPrefWidth(100);
        minifiyButton.setPrefHeight(100);

        minifiyButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                try {
                    onClickMinifiy();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });   
        

        hbox.setLeft(correctButton);
        hbox.setRight(minifiyButton);
        
        return hbox;
    }
    
    private BorderPane midButtons(){
        
        BorderPane hbox = new BorderPane();
        
        Button pretifyButton = new Button();
        pretifyButton.setText("Pretify");
        pretifyButton.setPrefWidth(100);
        pretifyButton.setPrefHeight(100);

        pretifyButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                try {
                    onClickPretify();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

        
        Button toJsonButton = new Button();
        toJsonButton.setText("To Json");
        toJsonButton.setPrefWidth(100);
        toJsonButton.setPrefHeight(100);

        toJsonButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                try {
                    onClickToJson();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });   
        
        hbox.setLeft(pretifyButton);
        hbox.setRight(toJsonButton);
        
//        hbox.getChildren().add(correctButton);
//        hbox.getChildren().add(minifiyButton);
        
        return hbox;
    }

    private BorderPane buttomButtons(){
        
        BorderPane hbox = new BorderPane();
        
        Button compressionButton = new Button();
        compressionButton.setText("Compression");
        compressionButton.setPrefWidth(100);
        compressionButton.setPrefHeight(100);

        compressionButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                try {
                    onClickCompression();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

        
        Button decompressionButton = new Button();
        decompressionButton.setText("Decompression");
        decompressionButton.setPrefWidth(100);
        decompressionButton.setPrefHeight(100);

        decompressionButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                try {
                    onClickDecompression();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });   
        
        hbox.setLeft(compressionButton);
        hbox.setRight(decompressionButton);
        
//        hbox.getChildren().add(correctButton);
//        hbox.getChildren().add(minifiyButton);
        
        return hbox;
    }
    
    void onClickMinifiy() throws IOException{
        XMLPathInputGUI.setPrimaryStageWidth(1200);
        
        String path = XMLPathInputGUI.getPath();
        String minifiedString = Compression.Minify(path);
        
        
        XMLPathInputGUI.setPrimaryStageScene(new TextScreen(minifiedString.getBytes(), "xml").textScreen());
    }
    
    void onClickPretify() throws IOException{
        XMLPathInputGUI.setPrimaryStageWidth(1200);

        XmlFile xmlFile = new XmlFile(XMLPathInputGUI.getPath());
        String prettifiedString = xmlFile.prettifying();
        
        XMLPathInputGUI.setPrimaryStageScene(new TextScreen(prettifiedString.getBytes(), "xml").textScreen());
    }
    
    void onClickCompression() throws FileNotFoundException, IOException{
        XMLPathInputGUI.setPrimaryStageWidth(1200);
        
        
        Path path = Paths.get(XMLPathInputGUI.getPath());
        byte[] op = Files.readAllBytes(path);
        byte[] org = Compression.compress(op);
        XMLPathInputGUI.setPrimaryStageScene(new TextScreen(org, "cxml").textScreen());
    }
    
    void onClickDecompression() throws FileNotFoundException, IOException{
        XMLPathInputGUI.setPrimaryStageWidth(1200);
        
        
        Path path = Paths.get(XMLPathInputGUI.getPath());
        byte[] op = Files.readAllBytes(path);
        byte[] org = Compression.decompress(op);
        XMLPathInputGUI.setPrimaryStageScene(new TextScreen(org, "xml").textScreen());
    }

    void onClickToJson() throws IOException{
        XMLPathInputGUI.setPrimaryStageWidth(1200);
        
        XmlFile xmlFile = new XmlFile(XMLPathInputGUI.getPath());

        TreeMaker treeMaker = new TreeMaker(xmlFile.getXmlList());
        Tree tree = treeMaker.treeCreator();
        String jsonString = tree.toJson();
        
        XMLPathInputGUI.setPrimaryStageScene(new TextScreen(jsonString.getBytes(), "json").textScreen());
    }
    
    void onClickCorrect() throws IOException{
       XMLPathInputGUI.setPrimaryStageWidth(1200);

       XmlFile xmlFile = new XmlFile(XMLPathInputGUI.getPath()); 
       String xmlString = xmlFile.validator();

       XMLPathInputGUI.setPrimaryStageScene(new TextScreen(xmlString.getBytes(), "xml").textScreen());
    }
    
}
