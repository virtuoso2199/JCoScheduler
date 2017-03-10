/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.SnoozeIncrement;
import java.util.ArrayList;

/**
 *
 * @author virtu
 */
public interface SnoozeIncrementDAO {
    
    public ArrayList<SnoozeIncrement> getAllIncrements();
    
    public SnoozeIncrement getIncrementByID(int incrementID) throws NotFoundException;
    
    public ArrayList<SnoozeIncrement> getIncrementByDesc(String incrementDesc) throws NotFoundException; //country names aren't necessarily unique so an ArrayList is returned
    
    public void createIncrement(SnoozeIncrement increment);
    
    public void updateIncrement(SnoozeIncrement increment);
    
    public void deleteIncrement(SnoozeIncrement increment);
    
    public void deleteIncrement(int incrementID);
    
}
