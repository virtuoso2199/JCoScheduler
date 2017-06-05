/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.ReportControllerInterface;
import JCoScheduling.Models.AppointmentModelInterface;
import JCoScheduling.Models.CustomerModelInterface;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author virtu
 */
public class ReportsByCustomerView implements ReportingViewInterface{
    
    private Stage stage;
    private ReportControllerInterface controller;
    private ObservableList<AppointmentModelInterface> reportList;
    private ArrayList<CustomerModelInterface> customers;
    
    //UI Controls
    FlowPane root;
    Label lblTitle;
    ListView lvReportList;
    TableColumn tcCustID;
    TableColumn tcCustName;
    Button btnAdd;
    Button btnOK;
    Scene scene;
    
    public ReportsByCustomerView(ReportControllerInterface controller){
        this.controller = controller;
        this.customers = controller.getAllCustomers();
    }
    

    @Override
    public void show() {
        this.stage = buildView();
        this.stage.show();
    }
    
    public Stage buildView(){
        stage = new Stage();
        
        root = new FlowPane();
        root.setPrefSize(290, 790);
        
        lblTitle = new Label("Reports by Customer");
        lblTitle.setFont(new Font("Verdana",20));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.DARKGRAY);
        titleShadow.setOffsetX(3.0);
        titleShadow.setOffsetY(3.0);
        lblTitle.setEffect(titleShadow);
        root.getChildren().add(lblTitle);
        
        //get report list from controller to populate list with
        
        //add a label for each customer then a ListView containing all their appointments
        for(CustomerModelInterface customer: this.customers){
            Label lblCustomer = new Label(customer.getCustomerName());
            root.getChildren().add(lblCustomer);
            lvReportList = new ListView();
            lvReportList.setPrefSize(275, 100);
            lvReportList.setItems(controller.getApptsForCustomer(customer));
            root.getChildren().add(lvReportList);
        }
        
        Button btnOK = new Button("OK");
        btnOK.setOnAction(event ->{
            close();
        });
        
        root.getChildren().add(btnOK);
        
        scene = new Scene(root,300,800);
        stage.setScene(scene);
        stage.setTitle("Reports by Customer");
        
        
        return stage;
        
    }    

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        this.stage.close();
    }
    
}
