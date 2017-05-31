/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.CustomerControllerInterface;
import JCoScheduling.Exceptions.FormatException;
import JCoScheduling.Models.Address;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.CustomerModelInterface;
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
 *CustomerView contains the presentation logic for the customer maintenance window
 used to add/edit customer entries in the database
 * 
 * @author M219663
 */
public class CustomerEditView implements CustomerViewInterface{
    private Stage customerStage;
    private CustomerModelInterface customer;
    private CustomerControllerInterface controller;
    
    //UI controls
    
    private GridPane root = new GridPane();
    private Label lblTitle;
    private DropShadow titleShadow;
    private Label lblFirstName;
    private TextField txtFirstName;
    private Label lblLastName;
    private TextField txtLastName;
    private Label lblAddr1;
    private TextField txtAddr1;
    private Label lblAddr2;
    private TextField txtAddr2;
    private Label lblCity;
    private TextField txtCity;
    private Label lblState;
    private TextField txtState;
    private Label lblZip;
    private TextField txtZip;
    private Label lblPhone;
    private TextField txtPhone;
    private Button btnSave;
    private Button btnClear;
    private Button btnCancel;
    private Button btnDelete;
    private Scene scene;
    
    public CustomerEditView(CustomerControllerInterface controller, CustomerModelInterface customer){
        this.controller = controller;
        this.customer = customer;
        this.customer.registerObserver(this);

    }

    private void buildView(){
        customerStage = new Stage();
        
        root = new GridPane();
        
        lblTitle = new Label("Customer Maintenance");
        lblTitle.setFont(new Font("Verdana",30));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.DARKGRAY);
        titleShadow.setOffsetX(3.0);
        titleShadow.setOffsetY(3.0);
        lblTitle.setEffect(titleShadow);
        root.add(lblTitle, 0, 0,5,1);
        
        lblFirstName = new Label("First Name");
        root.add(lblFirstName,0,1);
        
        txtFirstName = new TextField();
        txtFirstName.setText(customer.getFirstName()); //gets customer name up to space
        root.add(txtFirstName,1,1);
        
        lblLastName = new Label("Last Name");
        root.add(lblLastName,2,1);
        
        txtLastName = new TextField();
        txtLastName.setText(customer.getLastName());
        root.add(txtLastName, 3,1);
        
        lblAddr1 = new Label("Address Line 1");
        root.add(lblAddr1,0,2);
        
        txtAddr1 = new TextField();
        txtAddr1.setText(customer.getAddress().getAddrLine1());
        root.add(txtAddr1,1,2);
        
        lblAddr2 = new Label("Address Line 2");
        root.add(lblAddr2,0,3);
        
        txtAddr2 = new TextField();
        txtAddr2.setText(customer.getAddress().getAddrLine2());
        root.add(txtAddr2,1,3);
        
        lblCity = new Label("City");
        root.add(lblCity,0,4);
        
        txtCity = new TextField();
        txtCity.setText(customer.getAddress().getCity().getCityName());
        root.add(txtCity,1,4);
        
        lblState = new Label("State");
        root.add(lblState,2,4);
        
        txtState = new TextField();
        txtState.setText(customer.getAddress().getCity().getState());
        txtState.setPrefWidth(60);
        root.add(txtState,3,4);
        
        lblZip = new Label("Postal Code");
        root.add(lblZip,4,4);
        
        txtZip = new TextField();
        txtZip.setText(customer.getAddress().getPostCode());
        txtZip.setPrefWidth(40);
        root.add(txtZip,5,4);
        
        lblPhone = new Label("Telephone");
        root.add(lblPhone,0,5);
        
        txtPhone = new TextField();
        txtPhone.setText(customer.getAddress().getPhone());
        root.add(txtPhone,1,5);
        
        btnSave = new Button("Save");
        btnSave.setOnAction(event->{
            try{
                customer.setName(txtLastName.getText()+", "+txtFirstName.getText());
                customer.setAddress(controller.buildAddress(txtAddr1.getText(),txtAddr2.getText(),txtCity.getText(),txtState.getText(),txtZip.getText(),"USA",txtPhone.getText()));
                
            } catch(FormatException ex){
                
            }
            controller.updateCustomer(customer);
            new MainWindow(controller.getUser());
            customerStage.close();
        });
        root.add(btnSave,0,6);
        
        btnClear = new Button("Clear");
        btnClear.setOnAction(event->{
            txtFirstName.clear();
            txtLastName.clear();
            txtAddr1.clear();
            txtAddr2.clear();
            txtCity.clear();
            txtState.clear();
            txtZip.clear();
            txtPhone.clear();
        });
        root.add(btnClear,1,6);
        
        btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event->{
            new MainWindow(controller.getUser());
            customerStage.close();
        });
        
        
        
        root.add(btnCancel,2,6);
        
        btnDelete = new Button("Delete");
        btnDelete.setOnAction(event->{
            controller.deleteCustomer(customer);
        });
        
        root.add(btnDelete,3,6);
        
        
        
        scene = new Scene(root,800,350);
        
        customerStage.setScene(scene);
        customerStage.setTitle("Customer Maintenance");     

    }

    @Override
    public void updateCustomer(Customer customer) {
        this.customer = customer;
        update();
        //update display?
    }

    @Override
    public void show() {
        buildView();
        customerStage.show();
    }

    @Override
    public void update() {
        //Refresh data fields?
    }
    
    public void close(){
        customerStage.close();
    }
}
