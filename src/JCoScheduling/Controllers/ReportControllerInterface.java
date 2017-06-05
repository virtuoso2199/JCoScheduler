/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Controllers;

import JCoScheduling.Models.AppointmentModelInterface;
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
    
}
