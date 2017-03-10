/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public class Country implements CountryModelInterface, AuditInfoObserver{
    
    private int countryID;
    private String countryName;
    private AuditInfoModelInterface auditInfo;
    private ArrayList<CountryObserver> observers;
    

    public Country(){
        this.countryID = -1;
        this.countryName = "Undefined";
        this.auditInfo = new AuditInfo("Undefined", LocalDateTime.now(),"Undefined",LocalDateTime.now());
        this.observers = new ArrayList<>();
    }
    
    public Country(String countryName, AuditInfo auditInfo){
        this.setCountryName(countryName);
        this.countryID = -1;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.observers = new ArrayList<>();

    }

    public Country(int countryID, String countryName, AuditInfo auditInfo) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.observers = new ArrayList<>();
    }
    
    

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
        notifyObservers();
        
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
        notifyObservers();
    }

    public AuditInfoModelInterface getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfoModelInterface auditInfo) {
        this.auditInfo = auditInfo;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Country{" + "countryID=" + countryID + ", countryName=" + countryName + ", auditInfo=" + auditInfo + '}';
    }

    @Override
    public void updateAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    @Override
    public void registerObserver(CountryObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(CountryObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(CountryObserver o:observers){
            o.updateCountry(this);
        }
    }
    
    

    
    
    
}
