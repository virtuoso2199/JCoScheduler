/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.CustomerControllerInterface;
import JCoScheduling.Exceptions.FormatException;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.CustomerModelInterface;
import JCoScheduling.Models.UserModelInterface;
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
public class CustomerAddView implements CustomerViewInterface{
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
    private Label lblCountry;
    private TextField txtCountry;
    private TextField txtPhone;
    private Button btnSave;
    private Button btnClear;
    private Button btnCancel;
    private Scene scene;
    private UserModelInterface user; 
    
    public CustomerAddView(CustomerControllerInterface controller, CustomerModelInterface customer, UserModelInterface user){
        this.controller = controller;
        this.customer = customer;
        this.customer.registerObserver(this);
        this.user = user;

    }
  
    /**
     * buildCustomerWindow returns a stage containing all the necessary elements for the Customer Maintenance Form
     * @return Stage of Customer Maintenance Form
     */
    private void buildView(){
        customerStage = new Stage();
        
        GridPane root = new GridPane();
        
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
        root.add(txtFirstName,1,1);
        
        lblLastName = new Label("Last Name");
        root.add(lblLastName,2,1);
        
        txtLastName = new TextField();
        root.add(txtLastName, 3,1);
        
        lblAddr1 = new Label("Address Line 1");
        root.add(lblAddr1,0,2);
        
        txtAddr1 = new TextField();
        root.add(txtAddr1,1,2);
        
        lblAddr2 = new Label("Address Line 2");
        root.add(lblAddr2,0,3);
        
        txtAddr2 = new TextField();
        root.add(txtAddr2,1,3);
        
        lblCity = new Label("City");
        root.add(lblCity,0,4);
        
        txtCity = new TextField();
        root.add(txtCity,1,4);
        
        lblState = new Label("State");
        root.add(lblState,2,4);
        
        txtState = new TextField();
        txtState.setPrefWidth(60);
        root.add(txtState,3,4);
        
        lblZip = new Label("Postal Code");
        root.add(lblZip,4,4);
        
        txtZip = new TextField();
        txtZip.setPrefWidth(40);
        root.add(txtZip,5,4);
        
        lblCountry = new Label("Country");
        root.add(lblCountry,0,5);
        
        txtCountry = new TextField();
        root.add(txtCountry,1,5);
        
        lblPhone = new Label("Telephone");
        root.add(lblPhone,0,6);
        
        txtPhone = new TextField();
        root.add(txtPhone,1,6);
        
        btnSave = new Button("Save");
        btnSave.setOnAction(e->{
            //build objects up dependency chain: Country, City, Address, then Customer
            this.customer.setAddress(controller.buildAddress(txtAddr1.getText(), 
                                                             txtAddr2.getText(), 
                                                             txtCity.getText(),
                                                             txtState.getText(), 
                                                             txtZip.getText(), 
                                                             txtCountry.getText(),
                                                             txtPhone.getText()));
            try{
                customer.setName(txtLastName.getText()+", "+ txtFirstName.getText());
                customer.setActive(1);
            }catch (FormatException ex){
                //handle bad formatting
            }
            System.out.println("Customer in View: "+customer); //DEBUG ONLY
            controller.createCustomer(customer);
            controller.showCustomerListView();
            customerStage.close();
        });
        root.add(btnSave,0,7);
        
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
        root.add(btnClear,1,7);
        
        btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event->{
            controller.showCustomerListView();
            customerStage.close();
        });
        
        
        
        root.add(btnCancel,2,7);
        
        scene = new Scene(root,800,350);
        
        customerStage.setScene(scene);
        customerStage.setTitle("Customer Maintenance");     

    }

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
        //no fields to update: no action required
    }
    
    public void close(){
        customerStage.close();
    }
}
