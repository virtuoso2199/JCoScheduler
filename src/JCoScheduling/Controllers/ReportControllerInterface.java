/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Controllers;

import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.AppointmentModelInterface;
import JCoScheduling.Models.CustomerModelInterface;
import JCoScheduling.Models.User;
import JCoScheduling.Models.UserModelInterface;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author virtu
 */
public interface ReportControllerInterface {
    
    public void showReportMenu();
    
    public void showApptsByUser();
    
    public void showApptsByCustomer();
    
    public void showApptsByMonth();
    
    public ObservableList<AppointmentModelInterface> getAllAppts();
    
    public ObservableList<AppointmentModelInterface> getApptsForCustomer(CustomerModelInterface customer);
    
    public ArrayList<CustomerModelInterface> getAllCustomers();
    
    public ArrayList<User> getAllUsers();
    
    public ObservableList<AppointmentModelInterface> getApptsForUser(UserModelInterface user);
    
    public ObservableList<Appointment> getApptsForMonth(ZonedDateTime month);
    
}
