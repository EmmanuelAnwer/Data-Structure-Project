package GUI;

import XMLTreePackage.Tree;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import xmlObjects.GraphAnalysis;
import xmlObjects.XMLUsers;
import xmlfile_operations.TreeMaker;
import xmlfile_operations.XmlFile;

public class SuggestScreen {
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
        textField.setPrefWidth(50);
        
        // Create and add Text Object
        hbox.getChildren().add(new Text("ID"));
        
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
        
        String idString = this.textField.getText();
        
        XmlFile xmlFile = new XmlFile(XMLPathInputGUI.getPath());

        TreeMaker treeMaker = new TreeMaker(xmlFile.getXmlList());
        Tree tree = treeMaker.treeCreator();
        
        XMLUsers xmlUsers = XMLUsers.usersFactory(tree);
        Graph graph = new Graph(xmlUsers);
        
        int[][] adjacencyMatrix = graph.getAdjMat();
        
        HashMap<Integer,Integer> map = graph.getM();
        
        int index = map.get(Integer.parseInt(idString));
        
        ArrayList<Integer> mostInfluncers= GraphAnalysis.suggest(adjacencyMatrix,index+1);
        ArrayList<Integer> ids = new ArrayList<Integer>();
        
        
        for(int i = 0; i < mostInfluncers.size(); i++){
            ids.add(xmlUsers.getByIndex(mostInfluncers.get(i)-1).getId());
        }
        
        
        XMLPathInputGUI.setPrimaryStageScene(new TextScreen(ids.toString().getBytes(),ids.toString().getBytes(),"txt").textScreen());

    }
    
}
