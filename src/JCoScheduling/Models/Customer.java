/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import JCoScheduling.Exceptions.FormatException;
import java.util.ArrayList;


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

    public Customer(){
        this.customerID = -1;
        this.customerName = "Undefined";
        this.address = new Address();
        this.activeInd = 0;
        this.auditInfo = new AuditInfo();
        this.observers = new ArrayList<>();
    }
    
    //constuctor for when all fields are known (i.e. from database)
    public Customer(int customerID, String customerName, Address address, int activeInd,AuditInfo auditInfo) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address= address;
        this.address.registerObserver(this);
        this.activeInd = activeInd;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.observers = new ArrayList<>();
    }

    //constructor for Customer objects not created by database
    public Customer(String customerName, Address address, int activeInd, AuditInfo auditInfo) {
        this.customerName = customerName;
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

    public AddressModelInterface getAddress() {
        return address;
    }

    public int isActiveInd() {
        return activeInd;
    }

    
    public void setCustomerID(int ID){
        this.customerID = ID;
    }
    
    public void setName(String name) throws FormatException {
        if(name.length()>0 && name.length() <46){
            this.customerName = name;
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
    
    
    

    
    
    
}
