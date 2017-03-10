/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Views.MainWindow;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author M219663
 */
public class ApptWindow {
    private static Stage apptWindow; 
    
    public static Stage getApptWindow(){
        if(apptWindow == null){
            apptWindow = buildApptWindow();
        }
        return apptWindow;
    }
    
    private static Stage buildApptWindow(){
        Stage stage = new Stage();
        
        GridPane root = new GridPane();
        
        Label lblTitle = new Label("Appointment Maintnenance");
        lblTitle.setFont(new Font("Verdana",30));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.DARKGRAY);
        titleShadow.setOffsetX(3.0);
        titleShadow.setOffsetY(3.0);
        lblTitle.setEffect(titleShadow);
        root.add(lblTitle,0,0,6,1);
        
        Label lblApptTitle = new Label("Title");
        root.add(lblApptTitle,0,1);
        TextField txtTitle = new TextField();
        root.add(txtTitle,1,1);
        Label lblLocation = new Label("Location");
        root.add(lblLocation,2,1);
        ChoiceBox choiceLocation = new ChoiceBox(FXCollections.observableArrayList("London","New York","Phoenix"));
        root.add(choiceLocation,3,1);
        
        Label lblContact = new Label("Contact");
        root.add(lblContact,0,2);
        TextField txtContact = new TextField();
        root.add(txtContact,1,2);
        Label lblURL = new Label("URL");
        root.add(lblURL,2,2);
        TextField txtURL = new TextField();
        root.add(txtURL,3,2);
        
        Label lblApptDt = new Label("Date");
        root.add(lblApptDt,0,3);
        TextField txtApptDt = new TextField();
        root.add(txtApptDt,1,3);
        Label lblApptTime = new Label("Time");
        root.add(lblApptTime,2,3);
        TextField txtTime = new TextField();
        root.add(txtTime,3,3);
        Label lblApptLength = new Label("Length");
        root.add(lblApptLength,4,3);
        TextField txtLength = new TextField();
        root.add(txtLength,5,3);
        
        FlowPane buttonBox = new FlowPane();
        buttonBox.setHgap(5);
        
        
        Button btnSave = new Button("Save");
//        root.add(btnSave,0,4);
        Button btnClear = new Button("Clear");
//        root.add(btnClear,1,4);
        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event->{
            //return to MainWindow
            Stage mainWindow = MainWindow.getMainWindow();
            mainWindow.show();
            stage.close();
        });
//        root.add(btnCancel,2,4);
        buttonBox.getChildren().addAll(btnSave,btnClear,btnCancel);
        root.add(buttonBox, 0, 4,6,1);
        
        
        
        Scene scene = new Scene(root,800,350);
        
        stage.setTitle("Appointment Maintenance");
        stage.setScene(scene);
        
        return stage;
    }
    
}
