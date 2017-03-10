/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.CustomerControllerInterface;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.CustomerModelInterface;
import JCoScheduling.Models.CustomerObserver;
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
    private Scene scene;
    
    public CustomerEditView(CustomerControllerInterface controller, CustomerModelInterface customer){
        this.controller = controller;
        this.customer = customer;
        this.customer.registerObserver(this);

    }
    
    /**
     * showAddView shows Stage used to add a new customer to the database
     */
    
//    public void showAddView(){
//        customerStage.show();
//    }
//    
//    public void showEditView(){
//        //uses same view but populates field with customer data
//
//    }
//    
//    public void showListView(){
//        
//    }
    
    /**
     * buildCustomerWindow returns a stage containing all the necessary elements for the Customer Maintenance Form
     * @return Stage of Customer Maintenance Form
     */
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
        txtFirstName.setText(customer.getCustomerName().trim().substring(0, customer.getCustomerName().indexOf(" "))); //gets customer name up to space
        root.add(txtFirstName,1,1);
        
        lblLastName = new Label("Last Name");
        root.add(lblLastName,2,1);
        
        txtLastName = new TextField();
        txtLastName.setText(customer.getCustomerName().trim().substring(customer.getCustomerName().lastIndexOf(" "),customer.getCustomerName().length()-1));
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
        txtCity.setText(customer.getAddress().getCity().getCityName().trim().substring(0, customer.getAddress().getCity().getCityName().indexOf(",")));
        root.add(txtCity,1,4);
        
        lblState = new Label("State");
        root.add(lblState,2,4);
        
        txtState = new TextField();
        txtState.setText(customer.getAddress().getCity().getCityName().trim().substring(customer.getAddress().getCity().getCityName().indexOf(",")+1,customer.getAddress().getCity().getCityName().length()-1));
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
            Stage mainWindow = MainWindow.getMainWindow();
            mainWindow.show();
            customerStage.close();
        });
        
        
        
        root.add(btnCancel,2,6);
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
