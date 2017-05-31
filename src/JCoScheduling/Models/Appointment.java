/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public class Appointment implements AppointmentModelInterface, CustomerObserver,AuditInfoObserver, Comparable<Appointment>{
    
    private int apptID;
    private CustomerModelInterface customer;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String URL;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private AuditInfoModelInterface auditInfo;
    private ArrayList<AppointmentObserver> observers;
    
    public Appointment(){
        this.apptID = -1;
        this.customer = new Customer();
        this.title = "";
        this.description = "";
        this.location = "";
        this.contact = "";
        this.URL = "";
        this.startTime = ZonedDateTime.now();
        this.endTime = ZonedDateTime.now().plusMinutes(30L); //default end time is 30 minutes in the future
        this.auditInfo = new AuditInfo();
        this.observers = new ArrayList<>();
    }

    //constructor for when all fields are known (e.g loading from DB)
    public Appointment(int apptID, CustomerModelInterface customer, String title, String description, String location, String contact, String URL, ZonedDateTime startTime, ZonedDateTime endTime, AuditInfo auditInfo) {
        this.apptID = apptID;
        this.customer = customer;
        this.customer.registerObserver(this);
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.URL = URL;
        this.startTime = startTime;
        this.endTime = endTime;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.observers = new ArrayList<>();
    }

    //constructor for when database ID is unknown
    public Appointment(CustomerModelInterface customer, String title, String description, String location, String contact, String URL, ZonedDateTime startTime, ZonedDateTime endTime, AuditInfo auditInfo) {
        this.customer = customer;
        this.customer.registerObserver(this);
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.URL = URL;
        this.startTime = startTime;
        this.endTime = endTime;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.observers = new ArrayList<>();
    }
    
    

    public int getApptID() {
        return apptID;
    }
    
    public static ZoneId getZone(String location){
        
        ZoneId zone;
        
        if (location == "London"){
                zone = ZoneId.of("Europe/London");
            }else if (location=="Phoenix"){
                zone = ZoneId.of("America/Phoenix");
            }else { //default location is New York
                zone = ZoneId.of("America/New_York");
            }
        
        return zone;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
        notifyObservers();
    }

    public CustomerModelInterface getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModelInterface customer) {
        this.customer = customer;
        notifyObservers();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyObservers();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
        notifyObservers();
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
        notifyObservers();
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }
    
    public ZonedDateTime getStartTimeUTC() {
        return startTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
        notifyObservers();
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }
    
    public ZonedDateTime getEndTimeUTC() {
        return endTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
        notifyObservers();
    }

    public AuditInfoModelInterface getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfoModelInterface auditInfo) {
        this.auditInfo = auditInfo;
        notifyObservers();
    }

    @Override
    public void registerObserver(AppointmentObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(AppointmentObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(AppointmentObserver o:observers){
            o.updateAppointment(this);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void updateAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }
    
    public int compareTo(Appointment appointment){
        return this.getStartTime().compareTo(appointment.getStartTime());
    }


}
