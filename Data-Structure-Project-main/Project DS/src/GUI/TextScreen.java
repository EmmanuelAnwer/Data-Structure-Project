/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author user
 */
public class TextScreen {
    
    private final byte[] string;
    private String path;
    private TextField textField;
    private String type;
    
    TextScreen(byte[] string, String type){
        this.string = string;
        this.type = type;
    }
    
    public Scene textScreen(){
        
        ScrollPane scrollPane = new ScrollPane();
        
        VBox vbox = new VBox();
        
        Text text = new Text(new String(this.string, StandardCharsets.UTF_8));
//        text.
        scrollPane.setFitToWidth(true);
        
        vbox.getChildren().add(
           text
        );

//        vbox.setFitToWidth(true);
        scrollPane.setContent(
            vbox
        );
        
        Scene scene = new Scene(scrollPane);
        text.wrappingWidthProperty().bind(scene.widthProperty());
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,10,10,10));
        
        
        borderPane.setLeft(leftPart());
        borderPane.setRight(rightPart());
        vbox.getChildren().add(borderPane);
        
        
        return scene;
    } 
    
    private HBox leftPart(){
        // Create Row
        HBox hbox = new HBox();
        
        // Customize Row Alignment and spaces
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setFillHeight(true);

        // Create TextField
        textField = new TextField();
        textField.setPrefWidth(500);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            onTextChange(textField);
        });
        
        // Create and add Text Object
        hbox.getChildren().add(new Text("Enter XML Path"));
        
        // Add TextField
        hbox.getChildren().add(textField);
        
        return hbox;
    }
    
    private VBox rightPart(){
        // Create Column
        VBox vbox = new VBox();
        
        // Customize Column Alignment and spacing
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);
        
        // Create Okay Button
        Button saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setPrefWidth(100);
        saveButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                try {
                    onClickSave();
                } catch (IOException ex) {
                    Logger.getLogger(TextScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });        
        
        // Create Browse Button
        Button browseButton = new Button();
        browseButton.setText("Browse");
        browseButton.setPrefWidth(100);
        browseButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                onClickBrowse();
            }
        });     
        
        // Add Okay and Browse Buttons to the Column
        vbox.getChildren().add(saveButton);
        vbox.getChildren().add(browseButton);
        
        return vbox;
    }
    
    private void onClickSave() throws FileNotFoundException, IOException{
        Path path = Paths.get(this.path);
        byte[] op = this.string;
        Files.write(path, op);
        XMLPathInputGUI.setPrimaryStageWidth(500);
        XMLPathInputGUI.setPrimaryStageScene(new MainScreen().mainScene());
    }
    
    private void onClickBrowse(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter filter = new FileNameExtensionFilter(this.type +" file", new String[] {this.type});
        chooser.setFileFilter(filter);
        chooser.showDialog(chooser, "Select");
        File xmlFile = chooser.getSelectedFile();
        this.path = xmlFile.getPath();
        if(!this.path.endsWith("."+this.type)){
            this.path = this.path + "." + this.type;
        }
        textField.setText(this.path);
    }
    
    private void onTextChange(TextField textField){
        this.path = textField.getText();
    }
    
}
