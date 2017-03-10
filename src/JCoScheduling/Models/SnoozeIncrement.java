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
public class SnoozeIncrement implements SnoozeIncrementModelInterface{
    
    private int incrementID;
    private String description;
    private ArrayList<SnoozeIncrementObserver> observers;
    
    public SnoozeIncrement(){
        this.incrementID=-1;
        this.description = "Undefined";
        this.observers = new ArrayList<>();
    }
    
    public SnoozeIncrement(int incrementID, String description){
        this.setDescription(description);
        this.incrementID = incrementID;
        this.observers = new ArrayList<>();
    }
    
    public SnoozeIncrement(String description){
        this.setDescription(description);
        this.incrementID = -1;
        this.observers = new ArrayList<>();
    }

    public int getIncrementID() {
        return incrementID;
    }

    public void setIncrementID(int incrementID) {
        this.incrementID = incrementID;
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    @Override
    public void registerObserver(SnoozeIncrementObserver o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(SnoozeIncrementObserver o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(SnoozeIncrementObserver o:observers){
            o.updateSnoozeIncrement(this);
        }
    }
    
}
