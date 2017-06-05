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
    private final ObservableList<AppointmentModelInterface> reportList;
    private ArrayList<CustomerModelInterface> customers;
    
    //UI Controls
    GridPane root;
    Label lblTitle;
    ListView lvReportList;
    TableColumn tcCustID;
    TableColumn tcCustName;
    Button btnAdd;
    Button btnOK;
    Scene scene;
    
    public ReportsByCustomerView(ReportControllerInterface controller){
        this.controller = controller;
        this.reportList = controller.getAllAppts();
        this.customers = controller.getAllCustomers();
    }
    

    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void buildView(){
        stage = new Stage();
        
        root = new GridPane();
        
        lblTitle = new Label("Reports by Customer");
        lblTitle.setFont(new Font("Verdana",30));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.DARKGRAY);
        titleShadow.setOffsetX(3.0);
        titleShadow.setOffsetY(3.0);
        lblTitle.setEffect(titleShadow);
        root.add(lblTitle, 0, 0,5,1);
        
        //get report list from controller to populate list with
        
        lvReportList = new ListView();
        lvReportList.setItems(this.reportList);
       
        
        root.add(lvReportList, 0, 1,5,10);
        
    }    

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
