/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import JCoScheduling.DAO.AddressDAO;
import JCoScheduling.DAO.AddressDAOMySQL;
import JCoScheduling.Exceptions.FormatException;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public class Address implements AddressModelInterface, AuditInfoObserver, CityObserver{
    private int addressID;
    private String addrLine1;
    private String addrLine2;
    private CityModelInterface city;
    private String postCode;
    private String phone;
    private AuditInfoModelInterface auditInfo;
    private ArrayList<AddressObserver> observers; //other objects interested in state changes to this object

    public Address(){
        this.addressID = -1;
        this.addrLine1 = "Undefined";
        this.addrLine2 = "";
        this.city = new City();
        this.postCode = "";
        this.phone = "";
        this.auditInfo = new AuditInfo();
        this.observers = new ArrayList();
    }

    public Address(int addressID, String addrLine1, String addrLine2, City city, String postCode, String phone, AuditInfo auditInfo) {
        this.addressID = addressID;
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
        this.city = city;
        this.city.registerObserver(this);
        this.postCode = postCode;
        this.phone = phone;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.observers = new ArrayList<>();

    }

    public Address(String addrLine1, String addrLine2, City city, String postCode, String phone, AuditInfo auditInfo) {
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
        this.city = city;
        this.city.registerObserver(this);
        this.postCode = postCode;
        this.phone = phone;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.observers = new ArrayList<>();
    }

    @Override
     public int getAddressID() {
        return addressID;
    }

    @Override
    public String getAddrLine1() {
        return addrLine1;
    }

    @Override
    public String getAddrLine2() {
        return addrLine2;
    }

    @Override
    public int getCityID() {
        return this.city.getCityID();
    }
    
    @Override
    public CityModelInterface getCity(){
        return this.city;
    }

    @Override
    public String getPostCode() {
        return postCode;
    }

    @Override
    public String getPhone() {
        return phone;
    }

        
    @Override
    public void setAddressID(int ID){
        this.addressID = ID;
        notifyObservers();
    }
    
    @Override
    public void setAddrLine1(String addrLine) throws FormatException{
        if (addrLine.length()>0 && addrLine.length()<51){
            this.addrLine1 = addrLine;
            notifyObservers();
        } else {
            throw new FormatException("Address Line 1 is not between 1 & 50 characters");
        }
    }
    
    @Override
    public void setAddrLine2(String addrLine) throws FormatException{
        if (addrLine.length()<51){
            this.addrLine2 = addrLine;
            notifyObservers();
        } else {
            throw new FormatException("Address Line 2 must be less than 50 characters");
        }
    }
    
    @Override
    public void setCity(CityModelInterface city){
        this.city = city;
        notifyObservers();
    }
    
    @Override
    public void setPostCode(String zip) throws FormatException{
        if(zip.length()>0 && zip.length()<11){
            this.postCode = zip;
            notifyObservers();
        } else {
            throw new FormatException("Postal code must be between 1 & 10 characters");
        }
    }
    
    @Override
    public void setPhone(String phoneNum) throws FormatException{
        if(phoneNum.length()>6 && phoneNum.length()<21){
            this.phone = phoneNum;
            notifyObservers();
        } else {
            throw new FormatException("Phone numbers must be between 7 & 20 characters");
        }
    }
    
    @Override
    public AuditInfoModelInterface getAuditInfo() {
        return auditInfo;
    }

    @Override
    public void setAuditInfo(AuditInfoModelInterface auditInfo) {
        this.auditInfo = auditInfo;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Address{" + "addressID=" + addressID + ", addrLine1=" + addrLine1 + ", addrLine2=" + addrLine2 + ", city=" + this.city.getCityName() + ", postCode=" + postCode + ", phone=" + phone + ", auditInfo=" + auditInfo + '}';
    }


    public void registerObserver(AddressObserver o) {
        observers.add(o);
    }


    public void removeObserver(AddressObserver o) {
        observers.remove(o);
    }


    public void notifyObservers() { //alerts anything interested in this object that its state has changed
        for(AddressObserver o:observers){
            o.updateAddress(this);
        }
    }

    @Override
    public void updateAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    @Override
    public void updateCity(City city) {
       this.city = city;
    }
    
    
    
    
    
}
