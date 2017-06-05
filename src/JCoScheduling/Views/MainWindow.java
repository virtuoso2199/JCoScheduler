/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.AppointmentController;
import JCoScheduling.Controllers.AppointmentControllerInterface;
import JCoScheduling.Controllers.CustomerController;
import JCoScheduling.DAO.AppointmentDAO;
import JCoScheduling.DAO.AppointmentDAOMySQL;
import JCoScheduling.DAO.UserDAOMySQL;
import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.User;
import JCoScheduling.Models.UserModelInterface;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import javafx.concurrent.ScheduledService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
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
    private static UserModelInterface user;
    private static ZoneId userTimezone;
    private static AppointmentDAO apptDAO; 
    private static ArrayList<Appointment> appts;
    private ScheduledExecutorService reminderCheck;
    
    public MainWindow(UserModelInterface user, ZoneId userTz){
        this.user = user;
        this.userTimezone = userTz;
        this.apptDAO = new AppointmentDAOMySQL();
        mainWindow = buildMainWindow();
        mainWindow.show();
        reminderCheck = Executors.newScheduledThreadPool(1);
        this.appts = apptDAO.getApptByUser((User)this.user);


        //reminder check task as super fancy lambda expression :-P
        
        Runnable reminderThread = ()->{
            
            for (Appointment appt:appts){
                System.out.println("Time to appointment "+appt.getTitle() +": "+ZonedDateTime.now(userTz).until(appt.getStartTime(), ChronoUnit.MINUTES));
                if(ZonedDateTime.now(userTz).until(appt.getStartTime(), ChronoUnit.MINUTES)<=15L && ZonedDateTime.now(userTz).until(appt.getStartTime(), ChronoUnit.MINUTES)>0L){ //this will check for any appointments in 15 minutes or less that haven't already passed
                    System.out.println("Upcoming appointment!!");
                    
                    Platform.runLater(new Runnable(){
                        
                        
                        public void run(){
                            Stage reminder = new Stage();


                            GridPane root = new GridPane();
                            root.setAlignment(Pos.CENTER);

                            Label lblTitle = new Label("Appointment Reminder!");
                            lblTitle.setFont(new Font("Verdana",30));
                            DropShadow logoShadow = new DropShadow();
                            logoShadow.setColor(Color.DARKGRAY);
                            logoShadow.setOffsetX(3.0);
                            logoShadow.setOffsetY(3.0);
                            lblTitle.setEffect(logoShadow);

                            root.add(lblTitle, 0, 0,4,1);

                            Label lblApptDetail = new Label(appt.getTitle()+" at "+appt.getStartTime().withZoneSameInstant(userTz).format(DateTimeFormatter.ofPattern("hh:mm a"))+" for customer: "+appt.getCustomer().getFirstName());

                            root.add(lblApptDetail, 0, 1,4,1);

                            Button btnOK = new Button("OK");
                            btnOK.setOnAction(event->{
                                reminder.close();
                                    });
                            root.add(btnOK,4,1);
                            Scene scene = new Scene(root,500,200);

                            reminder.setTitle("Appointment Reminder");
                            reminder.setScene(scene);
                            reminder.show();
                        }
                        
                    });
                    
                }
            };
            
        
        //check for upcoming appointments today
        //Platform.runLater(reminderThread);
        };
        
        ScheduledFuture reminderResult = reminderCheck.scheduleAtFixedRate(reminderThread, 0, 15, TimeUnit.SECONDS);
    }
    
    public MainWindow(UserModelInterface user){
        this.user = user;
        this.userTimezone = ZoneId.of("America/New_York"); //default to NYC if not provided
        mainWindow = buildMainWindow();
        mainWindow.show();
    }
    
    //DEBUG ONLY UNTIL CONTROLLERS ARE ALL IN PLACE
    
    //Getters and setters for session variables
    
    public static ZoneId getTimeZone(){
        return userTimezone;
    }
    
    public static UserModelInterface getUser(){
        return user;
    }
    
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
            CustomerController cc = new CustomerController(new Customer());
            cc.showCustomerListView();
            //stage.close();
        });
        root.add(btnCustomer,0,1);
        
        Button btnAppointment = new Button("Add Appointment");
        btnAppointment.setOnAction(event->{
            //show appointment add window, hide main
            AppointmentController ac = new AppointmentController(this.user, this.userTimezone);
            ac.showAppointmentMaintenanceView();
//            Stage apptWindow = AppointmentView.getApptWindow();
//            apptWindow.show();
            //stage.close();
        });
        root.add(btnAppointment,1,1);
        
        Button btnCalendar = new Button("Calendar");
        btnCalendar.setOnAction(event->{
            AppointmentControllerInterface ac = new AppointmentController(this.user, this.userTimezone);
            ac.showCalendarWeekly();
            //stage.close();
        });
        root.add(btnCalendar,2,1);
        
        Button btnReports = new Button("Reports");
        root.add(btnReports,3,1);
        
        Button btnQuit = new Button("Quit");
        btnQuit.setOnAction(event->{
            stage.close();
            reminderCheck.shutdown();
                });
        root.add(btnQuit,4,1);
        
        Scene scene = new Scene(root,500,400);
        
        stage.setTitle("JCo Scheduling System");
        stage.setScene(scene);
        
        
        return stage;
    }
    
    public static void refreshAppts(){
        MainWindow.appts = apptDAO.getApptByUser((User)MainWindow.user);
    }
    
    
    
    
}
