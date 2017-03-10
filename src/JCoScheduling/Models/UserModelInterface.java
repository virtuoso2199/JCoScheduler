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
public interface UserModelInterface {
    
    public int getUserID();

    public void setUserID(int userID);

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public int isActive();

    public void setActive(int active);

    public AuditInfoModelInterface getAuditInfo();
    
    public void setAuditInfo(AuditInfoModelInterface auditInfo);

    public void registerObserver(UserObserver o);

    public void removeObserver(UserObserver o);

    public void notifyObservers();
}
