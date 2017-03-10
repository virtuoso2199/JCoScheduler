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
public interface AddressModelInterface {
    
    public int getAddressID();

    public String getAddrLine1();

    public String getAddrLine2();

    public int getCityID();
    
    public CityModelInterface getCity();

    public String getPostCode();

    public String getPhone();
    
    public void setAddressID(int ID);
    
    public void setAddrLine1(String addrLine) throws FormatException;
    
    public void setAddrLine2(String addrLine) throws FormatException;
       
    public void setCity(CityModelInterface city);
    
    public void setPostCode(String zip) throws FormatException;
    
    public void setPhone(String phoneNum) throws FormatException;
    
    public AuditInfoModelInterface getAuditInfo();

    public void setAuditInfo(AuditInfoModelInterface auditInfo);
    
    public void registerObserver(AddressObserver o);

    public void removeObserver(AddressObserver o);

    public void notifyObservers();
}
