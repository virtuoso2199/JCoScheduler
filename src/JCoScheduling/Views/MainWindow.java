/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.CustomerController;
import JCoScheduling.DAO.UserDAOMySQL;
import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.UserModelInterface;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * MainWindow is a controller and view all in one simply because it is just a launchpad to other parts of the program
 *
 * @author M219663
 */
public class MainWindow {
    private Stage mainWindow;
    private UserModelInterface user;
    
    public MainWindow(UserModelInterface user){
        this.user = user;
        mainWindow = buildMainWindow();
        mainWindow.show();
    }
    
    //DEBUG ONLY UNTIL CONTROLLERS ARE ALL IN PLACE
    
    public MainWindow(){
        try{
            this.user = new UserDAOMySQL().getUserByName("jbowley");
        } catch (NotFoundException ex){
            System.out.println("User not found?!");
        }
        
        mainWindow = buildMainWindow();
        mainWindow.show();
    }
    
        
    private Stage buildMainWindow(){
        Stage stage = new Stage();
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        
        Label lblTitle = new Label("JCo Scheduling");
        lblTitle.setFont(new Font("Verdana",30));
        DropShadow logoShadow = new DropShadow();
        logoShadow.setColor(Color.DARKGRAY);
        logoShadow.setOffsetX(3.0);
        logoShadow.setOffsetY(3.0);
        lblTitle.setEffect(logoShadow);
        
        root.add(lblTitle, 0, 0,4,1);
        
        Button btnCustomer = new Button("Customers");
        btnCustomer.setOnAction(event->{
            CustomerController cc = new CustomerController(new Customer(), this.user);
            cc.showCustomerListView();
            stage.close();
        });
        root.add(btnCustomer,0,1);
        
        Button btnAppointment = new Button("Appointments");
        btnAppointment.setOnAction(event->{
            //show appointment window, hide main
            Stage apptWindow = AppointmentView.getApptWindow();
            apptWindow.show();
            stage.close();
        });
        root.add(btnAppointment,1,1);
        
        Button btnCalendar = new Button("Calendar");
        btnCalendar.setOnAction(event->{
            Stage calendarWindow = WkCalendarWindow.getWkCalWindow();
            calendarWindow.show();
            stage.close();
        });
        root.add(btnCalendar,2,1);
        
        Button btnReports = new Button("Reports");
        root.add(btnReports,3,1);
        
        Button btnQuit = new Button("Quit");
        btnQuit.setOnAction(event->stage.close());
        root.add(btnQuit,4,1);
        
        Scene scene = new Scene(root,500,400);
        
        stage.setTitle("JCo Scheduling System");
        stage.setScene(scene);
        
        
        return stage;
    }
    
    
    
    
}
