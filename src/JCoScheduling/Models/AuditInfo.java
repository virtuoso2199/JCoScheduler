/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * AuditInfo tracks create and update information for database objects
 * @author M219663
 */
public class AuditInfo implements AuditInfoModelInterface{
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdate;
    private ArrayList<AuditInfoObserver> observers;
    
    public AuditInfo(){
        this.createdBy = "Undefined";
        this.createdDate = LocalDateTime.now();
        this.lastUpdatedBy = "Undefined";
        this.lastUpdate = LocalDateTime.now();
        this.observers = new ArrayList<>();
    }
    
    public AuditInfo(String createdBy, LocalDateTime createdDate, String lastUpdatedBy, LocalDateTime lastUpdate){
        this.createdBy =createdBy;
        this.createdDate =createdDate;
        this.lastUpdatedBy =lastUpdatedBy;
        this.lastUpdate = lastUpdate;
        this.observers = new ArrayList<>();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        notifyObservers();
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        notifyObservers();
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        notifyObservers();
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "AuditInfo{" + "createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdate=" + lastUpdate + '}';
    }

    @Override
    public void registerObserver(AuditInfoObserver o) {
       this.observers.add(o);
    }

    @Override
    public void removeObserver(AuditInfoObserver o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(AuditInfoObserver observer:observers){
            observer.updateAuditInfo(this);
        }
    }
    
    
}
