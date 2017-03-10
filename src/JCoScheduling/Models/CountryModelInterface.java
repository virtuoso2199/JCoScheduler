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
public interface CountryModelInterface {
    
    public int getCountryID();

    public void setCountryID(int countryID);

    public String getCountryName();

    public void setCountryName(String countryName);
    
    public AuditInfoModelInterface getAuditInfo();

    public void setAuditInfo(AuditInfoModelInterface auditInfo);
    
    public void updateAuditInfo(AuditInfo auditInfo);

    public void registerObserver(CountryObserver o);
    
    public void removeObserver(CountryObserver o);
    
    public void notifyObservers();
}
