/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.Reminder;
import JCoScheduling.Models.User;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 *
 * @author virtu
 */
public interface ReminderDAO {
    
    public ArrayList<Reminder> getAllReminders();
    
    public Reminder getReminderByID(int reminderID);
    
    public ArrayList<Reminder> getReminderByDate(ZonedDateTime startDate, ZonedDateTime endDate);
     
    public ArrayList<Reminder> getReminderByUser(User user);
       
    public void createReminder(Reminder reminder);
    
    public void updateReminder(Reminder reminder);
    
    public void deleteReminder(Reminder reminder);
    
    public void deleteReminder(int reminderID);
    
}
