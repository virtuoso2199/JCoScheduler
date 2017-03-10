/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import java.time.ZonedDateTime;

/**
 *
 * @author M219663
 */
public interface ReminderModelInterface {
    
    public int getReminderID();

    public void setReminderID(int reminderID);

    public ZonedDateTime getReminderDate();

    public void setReminderDate(ZonedDateTime reminderDate);

    public SnoozeIncrementModelInterface getSnoozeIncrement();

    public void setSnoozeIncrement(SnoozeIncrementModelInterface snoozeIncrement);

    public AppointmentModelInterface getAppointment();

    public void setAppointment(AppointmentModelInterface appointment);

    public String getReminderCol();

    public void setReminderCol(String reminderCol);

    public AuditInfoModelInterface getAuditInfo();

    public void setAuditInfo(AuditInfoModelInterface auditInfo);
    
    public void registerObserver(ReminderObserver o);
    
    public void removeObserver(ReminderObserver o);
    
    public void notifyObservers();
    
}
