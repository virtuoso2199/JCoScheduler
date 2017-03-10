/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.User;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public interface AppointmentDAO {
    
    public ArrayList<Appointment> getAllAppointments();
    
    public Appointment getApptByID(int apptID);
    
    public ArrayList<Appointment> getApptByDate(ZonedDateTime startDate, ZonedDateTime endDate);
    
    public ArrayList<Appointment> getApptByCustomer(Customer customer);
    
    public ArrayList<Appointment> getApptByUser(User user);
    
    public ArrayList<Appointment> getApptByCreateDate(ZonedDateTime startDate,ZonedDateTime endDate);
    
    public void createAppointment(Appointment appointment);
    
    public void updateAppointment(Appointment appointment);
    
    public void deleteAppointment(Appointment appointment);
    
    public void deleteAppointment(int appointmentID);
    
}
