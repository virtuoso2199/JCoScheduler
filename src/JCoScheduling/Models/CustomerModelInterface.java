/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import JCoScheduling.Exceptions.FormatException;

/**
 *
 * @author M219663
 */
public interface CustomerModelInterface {
    
     public int getCustomerID();

    public String getCustomerName();

    public AddressModelInterface getAddress();

    public int isActiveInd();
    
    public void setCustomerID(int ID);
    
    public void setName(String name) throws FormatException;
    
    public void setAddress(AddressModelInterface address);
    
    public void setActive(int active);

    public AuditInfoModelInterface getAuditInfo();

    public void setAuditInfo(AuditInfoModelInterface auditInfo);
    
    public void registerObserver(CustomerObserver o);
    
    public void removeObserver(CustomerObserver o);
    
    public void notifyObservers();
    
}
