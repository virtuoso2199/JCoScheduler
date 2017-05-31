/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Controllers;

import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.AppointmentModelInterface;
import JCoScheduling.Models.CustomerModelInterface;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

/**
 *
 * @author m219663
 */
public interface AppointmentControllerInterface {
    
    public void showAppointmentMaintenanceView();
    
    public void showAppointmentMaintenanceView(AppointmentModelInterface appt);
    
    public void showCalendarMonthly();
    
    public void showCalendarWeekly();
    
    public ArrayList<Appointment> getApptsByDate(ZonedDateTime startDt, ZonedDateTime endDt);
    
    public void updateAppointment(AppointmentModelInterface appt);
    
    public void createAppointment(AppointmentModelInterface appt);
    
    public void deleteAppointment(AppointmentModelInterface appt);
    
    public ObservableList<CustomerModelInterface> getCustomerList();
    
    public Label makeApptLabel(Appointment appt);
    
}
