/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
                System.out.println("Clicked");
            }
        });
        

        
        Button minifiyButton = new Button();
        minifiyButton.setText("Minify");
        minifiyButton.setPrefWidth(100);
        minifiyButton.setPrefHeight(100);

        minifiyButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                XMLPathInputGUI.setPrimaryStageScene(new TextScreen("The look and feel of JavaFX applications "
                + "can be customized. Cascading Style Sheets (CSS) separate "
                + "appearance and style from implementation so that developers can "
                + "concentrate on coding. Graphic designers can easily "
                + "customize the appearance and style of the application "
                + "through the CSS. If you have a web design background,"
                + " or if you would like to separate the user interface (UI) "
                + "and the back-end logic, then you can develop the presentation"
                + " aspects of the UI in the FXML scripting language and use Java "
                + "code for the application logic. If you prefer to design UIs "
                + "without writing code, then use JavaFX Scene Builder. As you design the UI, "
                + "Scene Builder creates FXML markup that can be ported to an Integrated Development "
                + "Environment (IDE) so that developers can add the business logic.").textScreen());
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
                System.out.println("Clicked");
            }
        });
        

        
        Button toJsonButton = new Button();
        toJsonButton.setText("To Json");
        toJsonButton.setPrefWidth(100);
        toJsonButton.setPrefHeight(100);

        toJsonButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Clicked");
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
                System.out.println("Clicked");
            }
        });
        

        
        Button decompressionButton = new Button();
        decompressionButton.setText("Decompression");
        decompressionButton.setPrefWidth(100);
        decompressionButton.setPrefHeight(100);

        decompressionButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Clicked");
            }
        });   
        
        hbox.setLeft(compressionButton);
        hbox.setRight(decompressionButton);
        
//        hbox.getChildren().add(correctButton);
//        hbox.getChildren().add(minifiyButton);
        
        return hbox;
    }
}
