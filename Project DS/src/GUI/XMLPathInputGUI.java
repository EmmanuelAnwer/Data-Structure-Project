/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author user
 */
import xmlfile_operations.XMLStringFromPath;
import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

public class XMLPathInputGUI  extends Application{
    final private String XML_STAGE_TITLE = "XML path entry"; 
    final private double[] PRIMARY_STAGE_DIMENSIONS = {900, 125};
    private static String path;
    static private Stage primaryStage;
    private TextField textField;

    
    public static void setPrimaryStageScene(Scene scene) {
        XMLPathInputGUI.primaryStage.setScene(scene);
    }
    public static void close() {
        XMLPathInputGUI.primaryStage.close();
    }

    public static String getPath() {
        return XMLPathInputGUI.path;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(PRIMARY_STAGE_DIMENSIONS[0]);
        primaryStage.setHeight(PRIMARY_STAGE_DIMENSIONS[1]);
        primaryStage.setTitle(XML_STAGE_TITLE);
        primaryStage.setScene(this.XMLScene());
        primaryStage.setResizable(false);
        this.primaryStage = primaryStage;
        primaryStage.show();
    }
    public static void setPrimaryStageWidth(double width) {
        XMLPathInputGUI.primaryStage.setWidth(width);
    }
    
    public static void setPrimaryStageHeight(double height) {
        XMLPathInputGUI.primaryStage.setHeight(height);
    }
    
    
    public void XMLPreviwer(String[] args){
        launch(args);
    }
    
    private Scene XMLScene(){
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,10,10,10));
        
        
        borderPane.setLeft(leftPart());
        borderPane.setRight(rightPart());
        
        Scene scene = new Scene(borderPane);
        

        
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
        Button okayButton = new Button();
        okayButton.setText("Okay");
        okayButton.setPrefWidth(100);
        okayButton.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                onClickOkay();
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
        vbox.getChildren().add(okayButton);
        vbox.getChildren().add(browseButton);
        
        return vbox;
    }
    
    private void onClickOkay(){
        if(this.path != null && this.path.matches(".*[a-z].*")){
            XMLStringFromPath.setPath(this.path);
            primaryStage.setScene(
                new MainScreen().mainScene()
            );
            primaryStage.setWidth(500);
            primaryStage.setHeight(400);
        }
    }
    
    private void onClickBrowse(){
        JFileChooser chooser = new JFileChooser();
        chooser.showDialog(chooser, "Open");
        File xmlFile = chooser.getSelectedFile();
        this.path = xmlFile.getPath();
        textField.setText(this.path);
    }
    
    private void onTextChange(TextField textField){
        this.path = textField.getText();
    }

}
