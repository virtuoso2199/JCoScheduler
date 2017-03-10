/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

/**
 *
 * @author M219663
 */
public interface CityModelInterface {
    
    public int getCityID();

    public void setCityID(int cityID);

    public String getCityName();

    public void setCityName(String cityName);

    public CountryModelInterface getCountry();
    
    public int getCountryID();

    public void setCountry(CountryModelInterface country);
    
    public void setCountryID(int countryID);

    public AuditInfoModelInterface getAuditInfo();

    public void setAuditInfo(AuditInfoModelInterface auditInfo);
    
    public void registerObserver(CityObserver o);
    
    public void removeObserver(CityObserver o);
    
    public void notifyObservers();
    
}
