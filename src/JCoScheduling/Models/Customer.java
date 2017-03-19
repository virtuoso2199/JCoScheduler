/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import JCoScheduling.Exceptions.FormatException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author M219663
 */
public class Customer implements CustomerModelInterface, AddressObserver, AuditInfoObserver{
    private int customerID;
    private String customerName;
    private AddressModelInterface address;
    private int activeInd;
    private AuditInfoModelInterface auditInfo;
    private ArrayList<CustomerObserver> observers;
    
    //Property values for UI Controls
    private final SimpleStringProperty prpCustomerName;
    private final SimpleStringProperty propCustomerID;

    public Customer(){
        this.customerID = -1;
        this.propCustomerID = new SimpleStringProperty(String.valueOf(this.customerID));
        this.customerName = "Undefined";
        this.prpCustomerName = new SimpleStringProperty(this.customerName);
        this.address = new Address();
        this.activeInd = 0;
        this.auditInfo = new AuditInfo();
        this.observers = new ArrayList<>();
    }
    
    //constuctor for when all fields are known (i.e. from database)
    public Customer(int customerID, String customerName, Address address, int activeInd,AuditInfo auditInfo) {
        this.customerID = customerID;
        this.propCustomerID= new SimpleStringProperty(String.valueOf(this.customerID));
        this.customerName = customerName;
        this.prpCustomerName= new SimpleStringProperty(customerName);
        this.address= address;
        this.address.registerObserver(this);
        this.activeInd = activeInd;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.observers = new ArrayList<>();
    }

    //constructor for Customer objects not created by database
    public Customer(String customerName, Address address, int activeInd, AuditInfo auditInfo) {
        this.customerID = -1;
        this.propCustomerID = new SimpleStringProperty(String.valueOf(this.customerID));
        this.customerName = customerName;
        this.prpCustomerName = new SimpleStringProperty(customerName);
        this.address = address;
        this.address.registerObserver(this);
        this.activeInd = activeInd;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.observers = new ArrayList<>();
    }

    
    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }
    
    public String getFirstName(){
        String firstName;
        
        
        if(this.customerName.indexOf(",")>0){
            firstName = this.customerName.substring(this.customerName.indexOf(",")+1, this.customerName.length()-1);
        } else {
            firstName = this.customerName;
        }
        return firstName;
    }
    
    public String getLastName(){
        String lastName;
        
        //test for presence of comma; undefined customer objects won't have one
        if(this.customerName.indexOf(",")>0){
            lastName = this.customerName.substring(0, this.customerName.indexOf(","));
        } else {
            lastName = "";
        }
        
        return lastName;
        
    }
    
    public String getPrpCustomerName(){
        return this.prpCustomerName.get();
    }
    
//    public StringProperty getPrpCustomerName(){
//        return this.prpCustomerName;
//    }

    public AddressModelInterface getAddress() {
        return address;
    }

    public int isActiveInd() {
        return activeInd;
    }

    
    public void setCustomerID(int ID){
        this.customerID = ID;
        this.propCustomerID.set(String.valueOf(customerID));
    }
    
    public void setName(String name) throws FormatException {
        if(name.length()>0 && name.length() <46){
            this.customerName = name;
            this.prpCustomerName.set(customerName);
        } else {
            throw new FormatException("Name must be between 1 and 45 characters");
        }
        
    }
    
    public void setAddress(AddressModelInterface address){
        this.address = address;
    }
    
    public void setActive(int active){
        this.activeInd = active;
    }

    public AuditInfoModelInterface getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfoModelInterface auditInfo) {
        this.auditInfo = auditInfo;
    }

    @Override
    public void registerObserver(CustomerObserver o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(CustomerObserver o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(CustomerObserver o:observers){
            o.updateCustomer(this);
        }
    }

    @Override
    public void updateAddress(Address address) {
        this.address = address;
        notifyObservers();
    }

    @Override
    public void updateAuditInfo(AuditInfo auditInfo) {
        this.auditInfo=auditInfo;
        notifyObservers();
    }

    @Override
    public String toString() {
        return customerName + " ("+customerID +")";
    }
    
    
    

    
    
    
}
