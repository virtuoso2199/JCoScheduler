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
import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.AppointmentModelInterface;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.CustomerModelInterface;
import JCoScheduling.Models.UserModelInterface;
import JCoScheduling.Views.AppointmentView;
import JCoScheduling.Views.AppointmentViewInterface;
import JCoScheduling.Views.MainWindow;
import JCoScheduling.Views.WkCalendarWindow;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

/**
 *
 * @author m219663
 */
public class AppointmentController implements AppointmentControllerInterface{
    
    private AppointmentDAO apptDAO;
    private CustomerDAO customerDAO;
    private AppointmentViewInterface apptView;
    private AppointmentModelInterface appt;
    private UserModelInterface user;
    private ZoneId userTimezone;
    
    public AppointmentController(UserModelInterface user, ZoneId userTz){
        this.customerDAO = new CustomerDAOMySQL();
        this.apptDAO = new AppointmentDAOMySQL();
        this.user = user;
        this.userTimezone = userTz;
    }
    
    public ArrayList<Appointment> getApptsByDate(ZonedDateTime startDt, ZonedDateTime endDt){
        return apptDAO.getApptByDate(startDt, endDt);
    }
    
    

    @Override
    public void showAppointmentMaintenanceView() {
        //when passed with no arguments, this is a new address
        this.apptView = new AppointmentView(this);
        this.apptView.show();
    }
    
    @Override
    public void showAppointmentMaintenanceView(AppointmentModelInterface appt) {
        //when passed with an appointment, this is edit mode
        this.apptView = new AppointmentView(this, appt);
        this.apptView.show();
    }

    @Override
    public void showCalendarMonthly() {
        //loads view of all appointments in current month
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showCalendarWeekly() {
        //loads view of all appointments in the current week
        apptView = new WkCalendarWindow(this.userTimezone, this,MainWindow.getUser());
        apptView.show();
    }

    @Override
    public void updateAppointment(AppointmentModelInterface appt) {
        appt.setAuditInfo(new AuditInfo(this.user.getUsername(),LocalDateTime.now(),this.user.getUsername(),LocalDateTime.now()));
        if(appt.getApptID()==-1){
            //appointment not in database, create there
            apptDAO.createAppointment((Appointment)appt);
        }else{
            //appointment has ID from database, update there
            apptDAO.updateAppointment((Appointment)appt);
        }
        this.appt = appt;
        MainWindow.refreshAppts();
    }

    @Override
    public void createAppointment(AppointmentModelInterface appt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAppointment(AppointmentModelInterface appt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<CustomerModelInterface> getCustomerList() {
        
        ObservableList<CustomerModelInterface> customers = FXCollections.observableArrayList();
        
        for(CustomerModelInterface customer: customerDAO.getAllCustomers()){
            customers.add(customer);
        }
        
        return customers;
    }

    @Override
    public Label makeApptLabel(Appointment appt) {
        String apptDt = appt.getStartTime().withZoneSameInstant(userTimezone).format(DateTimeFormatter.ofPattern("MM/dd hh:mm a"));
        Label lblAppt = new Label(appt.getTitle()+"\n"+appt.getCustomer().getFirstName()+" "+appt.getCustomer().getLastName()+"\n"+apptDt);
        lblAppt.setStyle("-fx-border-color:red; -fx-background-color: #e6ffff;");
        lblAppt.setOnMouseClicked(event->{
            if (event.getClickCount()>1){ //load appointment for editing when double clicked
                showAppointmentMaintenanceView(appt);
            }
        });
        return lblAppt;
    }
    
}
