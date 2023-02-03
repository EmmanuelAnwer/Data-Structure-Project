/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import XMLTreePackage.Tree;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import xmlObjects.Graph;
import xmlObjects.Post;
import xmlObjects.XMLUsers;
import xmlfile_operations.TreeMaker;
import xmlfile_operations.XmlFile;

/**
 *
 * @author user
 */
public class SearchScreen {
        private TextField textField;
    
    public Scene XMLScene(){
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
        
        // Create and add Text Object
        hbox.getChildren().add(new Text("Keyword"));
        
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
                try {
                    onClickOkay();
                } catch (IOException ex) {
                    Logger.getLogger(SuggestScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });        
        
        // Add Okay and Browse Buttons to the Column
        vbox.getChildren().add(okayButton);        
        return vbox;
    }
    
    private void onClickOkay() throws IOException{
        XMLPathInputGUI.setPrimaryStageWidth(1000);
        
        String string = this.textField.getText();
        
        XmlFile xmlFile = new XmlFile(XMLPathInputGUI.getPath());

        TreeMaker treeMaker = new TreeMaker(xmlFile.getXmlList());
        Tree tree = treeMaker.treeCreator();
        
        XMLUsers xmlUsers = XMLUsers.usersFactory(tree);
        Graph graph = new Graph(xmlUsers);
                        
        
        ArrayList<Post> postList= graph.postSearch(string);
        
        String result = "";
        
        for(int i = 0; i < postList.size(); i++){
            result += "Post " + (i+1) + ": " + postList.get(i).getBody() + "\n";
        }
        
        
        XMLPathInputGUI.setPrimaryStageScene(new TextScreen(result.getBytes(),result.getBytes(),"txt").textScreen());

    }
}
