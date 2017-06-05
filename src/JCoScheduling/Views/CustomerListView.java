/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.CustomerControllerInterface;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.CustomerModelInterface;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author virtu
 */
public class CustomerListView implements CustomerViewInterface{
    
    private Stage customerListView;
    private CustomerControllerInterface controller;
    private final ObservableList<CustomerModelInterface> customerList;
    
    //UI Controls
    GridPane root;
    Label lblTitle;
    ListView lvCustomerList;
    TableColumn tcCustID;
    TableColumn tcCustName;
    Button btnAdd;
    Button btnOK;
    Scene scene;
    
    public CustomerListView(CustomerControllerInterface controller){
        
        this.controller = controller;
        this.customerList = controller.getAllCustomers();
    }
    
    public void buildView(){
        customerListView = new Stage();
        
        root = new GridPane();
        
        lblTitle = new Label("Customer Maintenance");
        lblTitle.setFont(new Font("Verdana",30));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.DARKGRAY);
        titleShadow.setOffsetX(3.0);
        titleShadow.setOffsetY(3.0);
        lblTitle.setEffect(titleShadow);
        root.add(lblTitle, 0, 0,5,1);
        
        //get customer list from controller to populate list with
        
        lvCustomerList = new ListView();
        lvCustomerList.setOnMouseClicked(e->{
                if (e.getClickCount()>1){ //load customer for editing when double clicked
                    controller.showCustomerEditView((CustomerModelInterface)lvCustomerList.getSelectionModel().getSelectedItem());
                    customerListView.close();
                }
            });
        lvCustomerList.setItems(this.customerList);
       
        
        root.add(lvCustomerList, 0, 1,5,10);
        
        btnAdd = new Button("Add");
        btnAdd.setOnAction(e->{
            controller.showCustomerEntryView();
            customerListView.close();
            
        });
        
        root.add(btnAdd,0,11);
        
        btnOK = new Button("OK");
        btnOK.setOnAction(e->{
            //new MainWindow(controller.getUser());
            customerListView.close();
        });
        
        root.add(btnOK,4,11);
        
        scene = new Scene(root,500,800);
        customerListView.setScene(scene);
        customerListView.setTitle("Reports by Customer");
        
        
    }

    @Override
    public void show() {
        buildView();
        customerListView.show();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void close(){
        customerListView.close();
    }


}
