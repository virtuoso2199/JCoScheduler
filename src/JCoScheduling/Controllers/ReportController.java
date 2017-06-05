/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Controllers;

import JCoScheduling.DAO.AppointmentDAO;
import JCoScheduling.DAO.AppointmentDAOMySQL;
import JCoScheduling.DAO.CustomerDAO;
import JCoScheduling.DAO.CustomerDAOMySQL;
import JCoScheduling.DAO.UserDAO;
import JCoScheduling.DAO.UserDAOMySQL;
import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.AppointmentModelInterface;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.CustomerModelInterface;
import JCoScheduling.Models.User;
import JCoScheduling.Models.UserModelInterface;
import JCoScheduling.Views.ReportMenuView;
import JCoScheduling.Views.ReportingViewInterface;
import JCoScheduling.Views.ReportsByCustomerView;
import JCoScheduling.Views.ReportsByMonthView;
import JCoScheduling.Views.ReportsByUserView;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author virtu
 */
public class ReportController implements ReportControllerInterface{
    
    private AppointmentDAO apptDAO;
    private CustomerDAO custDAO;
    private UserDAO userDAO;
    private ReportingViewInterface rptView;
    
    public ReportController(){
        this.apptDAO = new AppointmentDAOMySQL();
        this.custDAO = new CustomerDAOMySQL();
        this.userDAO = new UserDAOMySQL();
    }

    @Override
    public void showReportMenu() {
        this.rptView = new ReportMenuView(this);
        this.rptView.show();
    }

    @Override
    public void showApptsByUser() {
        this.rptView = new ReportsByUserView(this);
        this.rptView.show();
    }

    @Override
    public void showApptsByCustomer() {
        this.rptView = new ReportsByCustomerView(this);
        this.rptView.show();
    }

    @Override
    public void showApptsByMonth() {
        this.rptView = new ReportsByMonthView(this);
        this.rptView.show();
    }
    
    public ObservableList<AppointmentModelInterface> getAllAppts(){
        ObservableList<AppointmentModelInterface> appts = FXCollections.observableArrayList();
        
        for(AppointmentModelInterface appt: apptDAO.getAllAppointments()){
            appts.add(appt);
        }
        
        return appts;
    }

    @Override
    public ArrayList<CustomerModelInterface> getAllCustomers() {
        return custDAO.getAllCustomers();
    }
    
    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    
    public ObservableList<AppointmentModelInterface> getApptsForCustomer(CustomerModelInterface customer){
        ObservableList<AppointmentModelInterface> appts = FXCollections.observableArrayList();
        
        for(AppointmentModelInterface appt: apptDAO.getApptByCustomer((Customer)customer)){
            appts.add(appt);
        }
        
        return appts;
    }
    
    public ObservableList<AppointmentModelInterface> getApptsForUser(UserModelInterface user){
        ObservableList<AppointmentModelInterface> appts = FXCollections.observableArrayList();
        
        for(AppointmentModelInterface appt: apptDAO.getApptByUser((User)user)){
            appts.add(appt);
        }
        
        return appts;
    }

    @Override
    public ObservableList<Appointment> getApptsForMonth(ZonedDateTime month) {
        ObservableList<Appointment> appts = FXCollections.observableArrayList();
        for (Appointment appt:apptDAO.getApptByDate(month, month.with(TemporalAdjusters.lastDayOfMonth()))){
            appts.add(appt);
        }
        
        return appts;
    }
    
}
