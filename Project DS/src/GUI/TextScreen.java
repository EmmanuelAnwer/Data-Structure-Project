/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author user
 */
public class TextScreen {
    
    private final String string;
   
    
    TextScreen(String string){
        this.string = string;
    }
    
    public Scene textScreen(){
        VBox vbox = new VBox();
        ScrollPane root = new ScrollPane();
        Text text = new Text(string);        
        
        
        Scene scene = new Scene(vbox);
        text.wrappingWidthProperty().bind(scene.widthProperty());
        root.setFitToWidth(true);
        root.setContent(text);
        
        
        vbox.getChildren().add(root);
        
        return scene;
    } 
}
