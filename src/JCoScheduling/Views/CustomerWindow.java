/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Views.MainWindow;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *CustomerWindow contains the presentation logic for the customer maintenance window
 * used to add/edit customer entries in the database
 * 
 * @author M219663
 */
public class CustomerWindow {
    private static Stage custMaintInstance;
    
    public static Stage getCustomerWindow(){
        if(custMaintInstance==null){
            custMaintInstance = buildCustomerWindow();
        }
        return custMaintInstance;
    }
    
    
    /**
     * buildCustomerWindow returns a stage containing all the necessary elements for the Customer Maintenance Form
     * @return Stage of Customer Maintenance Form
     */
    private static Stage buildCustomerWindow(){
        Stage stage = new Stage();
        
        GridPane root = new GridPane();
        
        Label lblTitle = new Label("Customer Maintenance");
        lblTitle.setFont(new Font("Verdana",30));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.DARKGRAY);
        titleShadow.setOffsetX(3.0);
        titleShadow.setOffsetY(3.0);
        lblTitle.setEffect(titleShadow);
        root.add(lblTitle, 0, 0,5,1);
        
        Label lblFirstName = new Label("First Name");
        root.add(lblFirstName,0,1);
        
        TextField txtFirstName = new TextField();
        root.add(txtFirstName,1,1);
        
        Label lblLastName = new Label("Last Name");
        root.add(lblLastName,2,1);
        
        TextField txtLastName = new TextField();
        root.add(txtLastName, 3,1);
        
        Label lblAddr1 = new Label("Address Line 1");
        root.add(lblAddr1,0,2);
        
        TextField txtAddr1 = new TextField();
        root.add(txtAddr1,1,2);
        
        Label lblAddr2 = new Label("Address Line 2");
        root.add(lblAddr2,0,3);
        
        TextField txtAddr2 = new TextField();
        root.add(txtAddr2,1,3);
        
        Label lblCity = new Label("City");
        root.add(lblCity,0,4);
        
        TextField txtCity = new TextField();
        root.add(txtCity,1,4);
        
        Label lblState = new Label("State");
        root.add(lblState,2,4);
        
        TextField txtState = new TextField();
        txtState.setPrefWidth(60);
        root.add(txtState,3,4);
        
        Label lblZip = new Label("Postal Code");
        root.add(lblZip,4,4);
        
        TextField txtZip = new TextField();
        txtZip.setPrefWidth(40);
        root.add(txtZip,5,4);
        
        Label lblPhone = new Label("Telephone");
        root.add(lblPhone,0,5);
        
        TextField txtPhone = new TextField();
        root.add(txtPhone,1,5);
        
        Button btnSave = new Button("Save");
        root.add(btnSave,0,6);
        
        Button btnClear = new Button("Clear");
        root.add(btnClear,1,6);
        
        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event->{
            new MainWindow();
            stage.close();
        });
        
        
        
        root.add(btnCancel,2,6);
        
        Scene scene = new Scene(root,800,350);
        
        stage.setScene(scene);
        stage.setTitle("Customer Maintenance");     
        
        
        return stage;
    }
}
