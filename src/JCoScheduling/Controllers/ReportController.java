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
import JCoScheduling.Models.AppointmentModelInterface;
import JCoScheduling.Models.CustomerModelInterface;
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
    
    public ReportController(){
        this.apptDAO = new AppointmentDAOMySQL();
        this.custDAO = new CustomerDAOMySQL();
    }

    @Override
    public void showReportMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showApptsByUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showApptsByCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showApptsByMonth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
