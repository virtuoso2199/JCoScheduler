/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import java.time.LocalDateTime;

/**
 *
 * @author M219663
 */
public interface AuditInfoModelInterface{
    
    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public LocalDateTime getCreatedDate();

    public void setCreatedDate(LocalDateTime createdDate);

    public String getLastUpdatedBy();

    public void setLastUpdatedBy(String lastUpdatedBy);

    public LocalDateTime getLastUpdate();

    public void setLastUpdate(LocalDateTime lastUpdate);
    
    public void registerObserver(AuditInfoObserver o);
    
    public void removeObserver(AuditInfoObserver o);
    
    public void notifyObservers();
}
