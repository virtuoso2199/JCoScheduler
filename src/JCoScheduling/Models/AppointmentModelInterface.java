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
public interface AppointmentModelInterface {
    
    public int getApptID();

    public void setApptID(int apptID);

    public CustomerModelInterface getCustomer();

    public void setCustomer(CustomerModelInterface customer);

    public String getTitle();

    public void setTitle(String title);

    public String getDescription();

    public void setDescription(String description);

    public String getLocation();

    public void setLocation(String location);

    public String getContact();

    public void setContact(String contact);

    public String getURL();

    public void setURL(String URL);

    public ZonedDateTime getStartTime();

    public void setStartTime(ZonedDateTime startTime);

    public ZonedDateTime getEndTime();

    public void setEndTime(ZonedDateTime endTime);

    public AuditInfoModelInterface getAuditInfo();

    public void setAuditInfo(AuditInfoModelInterface auditInfo);
    
    public void registerObserver(AppointmentObserver o);
    
    public void removeObserver(AppointmentObserver o);
    
    public void notifyObservers();
}
