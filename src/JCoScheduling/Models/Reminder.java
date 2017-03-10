/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public class Reminder implements ReminderModelInterface, AppointmentObserver, SnoozeIncrementObserver, AuditInfoObserver{
    
    private int reminderID;
    private ZonedDateTime reminderDate;
    private SnoozeIncrementModelInterface snoozeIncrement;
    private AppointmentModelInterface appointment;
    private AuditInfoModelInterface auditInfo;
    private String reminderCol;
    private ArrayList<ReminderObserver> observers;
    
    public Reminder(){
        this.reminderID = -1;
        this.reminderDate = ZonedDateTime.now();
        this.snoozeIncrement = new SnoozeIncrement();
        this.appointment = new Appointment();
        this.auditInfo = new AuditInfo();
        this.reminderCol = "";
        this.observers = new ArrayList<>();
    }

    //contructor for when all fields are known for reminder (e.g. loading from database)
    public Reminder(int reminderID, ZonedDateTime reminderDate, SnoozeIncrement snoozeIncrement, Appointment appointment, AuditInfo auditInfo, String reminderCol) {
        this.reminderID = reminderID;
        this.reminderDate = reminderDate;
        this.snoozeIncrement = snoozeIncrement;
        this.snoozeIncrement.registerObserver(this);
        this.appointment = appointment;
        this.appointment.registerObserver(this);
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.reminderCol = reminderCol;
        this.observers = new ArrayList<>();
    }

    //constructor for reminders when id is not yet known
    public Reminder(ZonedDateTime reminderDate, SnoozeIncrement snoozeIncrement, Appointment appointment, AuditInfo auditInfo, String reminderCol) {
        this.reminderDate = reminderDate;
        this.snoozeIncrement = snoozeIncrement;
        this.snoozeIncrement.registerObserver(this);
        this.appointment = appointment;
        this.appointment.registerObserver(this);
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        this.reminderCol = reminderCol;
        this.setReminderID(-1);
        this.observers = new ArrayList<>();
    }
    
    

    public int getReminderID() {
        return reminderID;
    }

    public void setReminderID(int reminderID) {
        this.reminderID = reminderID;
        notifyObservers();
    }

    public ZonedDateTime getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(ZonedDateTime reminderDate) {
        this.reminderDate = reminderDate;
        notifyObservers();
    }

    public SnoozeIncrementModelInterface getSnoozeIncrement() {
        return snoozeIncrement;
    }

    public void setSnoozeIncrement(SnoozeIncrementModelInterface snoozeIncrement) {
        this.snoozeIncrement = snoozeIncrement;
        notifyObservers();
    }

    public AppointmentModelInterface getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentModelInterface appointment) {
        this.appointment = appointment;
        notifyObservers();
    }

    public String getReminderCol() {
        return reminderCol;
    }

    public void setReminderCol(String reminderCol) {
        this.reminderCol = reminderCol;
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
    public void registerObserver(ReminderObserver o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(ReminderObserver o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(ReminderObserver o:observers){
            o.updateReminder(this);
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        this.appointment=appointment;
    }

    @Override
    public void updateAuditInfo(AuditInfo auditInfo) {
        this.auditInfo=auditInfo;
    }

    @Override
    public void updateSnoozeIncrement(SnoozeIncrement increment) {
        this.snoozeIncrement = increment;
    }
    
    
    
    
    
}
