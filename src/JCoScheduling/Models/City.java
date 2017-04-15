/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public class City implements CityModelInterface, CountryObserver, AuditInfoObserver{
    private int cityID;
    private String cityName;
    private CountryModelInterface country;
    private AuditInfoModelInterface auditInfo;
    private ArrayList<CityObserver> observers;
    
    public City(){
        this.cityID = -1;
        this.country = new Country();
        this.auditInfo = new AuditInfo();
        this.observers = new ArrayList<>();
    }
    
    public City(String cityName, Country country, AuditInfo auditInfo) {
        this.cityID = -1;
        this.cityName = cityName;
        this.country = country;
        this.country.registerObserver(this);
        this.auditInfo = auditInfo;
        
    }

    public City(int cityID, String cityName, Country country, AuditInfo auditInfo) {
        this.cityID = cityID;
        this.cityName = cityName;
        this.country = country;
        this.country.registerObserver(this);
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
    }
    
    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
        notifyObservers();
    }

    public String getCityName() {
        String cityName;
        if(this.cityName.indexOf(",")>0){
             cityName = this.cityName.substring(0, this.cityName.indexOf(","));
        } else {
            cityName = this.cityName;
        }
        return cityName;
    }
    
    public String getState(){
        String state; 
        
        if(this.cityName.indexOf(",")>0){
            state = this.cityName.substring(this.cityName.indexOf(",")+2, this.cityName.length());
        } else {
            state = "";
        }
        
        return state;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
        notifyObservers();
    }

    public CountryModelInterface getCountry() {
        return country;
    }
    
    public int getCountryID(){
        return this.country.getCountryID();
    }

    public void setCountry(CountryModelInterface country) {
        this.country = country;
        notifyObservers();
    }
    
    public void setCountryID(int countryID){
        this.country.setCountryID(countryID);
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
        return "City{" + "cityID=" + cityID + ", cityName=" + cityName + ", country=" + country.getCountryName() + ", auditInfo=" + auditInfo + '}';
    }

    @Override
    public void registerObserver(CityObserver o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(CityObserver o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(CityObserver o: observers){
            o.updateCity(this);
        }
    }

    @Override
    public void updateCountry(Country country) {
        this.country = country;
    }

    @Override
    public void updateAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }
    
    
    
    
}
