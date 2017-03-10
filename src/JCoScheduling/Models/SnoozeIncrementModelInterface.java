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
public interface SnoozeIncrementModelInterface {
    
    public int getIncrementID();

    public void setIncrementID(int incrementID);

    public String getDescription();

    public void setDescription(String description);
    
    public void registerObserver(SnoozeIncrementObserver o);
    
    public void removeObserver(SnoozeIncrementObserver o);
    
    public void notifyObservers();
}
