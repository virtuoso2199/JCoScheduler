/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.AppointmentControllerInterface;
import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.AppointmentModelInterface;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.CustomerModelInterface;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author M219663
 */
public class AppointmentView implements AppointmentViewInterface{
    private Stage apptWindow; 
    private AppointmentControllerInterface controller;
    private AppointmentModelInterface appt;
 
    
    public AppointmentView(AppointmentControllerInterface controller){
        this.controller = controller;
        this.appt = new Appointment();

    }
    
    public AppointmentView(AppointmentControllerInterface controller, AppointmentModelInterface appt){
        this.controller = controller;
        this.appt = new Appointment();
        this.appt = appt;
    }
    
//    public static Stage getApptWindow(){
//        if(apptWindow == null){
//            apptWindow = buildApptWindow();
//        }
//        return apptWindow;
//    }
    
    private Stage buildApptWindow(){
        Stage stage = new Stage();
        
        GridPane root = new GridPane();
        
        Label lblTitle = new Label("Appointment Maintnenance");
        lblTitle.setFont(new Font("Verdana",30));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.DARKGRAY);
        titleShadow.setOffsetX(3.0);
        titleShadow.setOffsetY(3.0);
        lblTitle.setEffect(titleShadow);
        root.add(lblTitle,0,0,6,1);
        
        Label lblCustomer = new Label("Customer");
        root.add(lblCustomer,0,1);
        ChoiceBox choiceCustomer = new ChoiceBox();
        choiceCustomer.setItems(controller.getCustomerList());
        root.add(choiceCustomer,1,1);
        Label lblApptTitle = new Label("Title");
        root.add(lblApptTitle,2,1);
        TextField txtTitle = new TextField();
        root.add(txtTitle,3,1);
        Label lblLocation = new Label("Location");
        root.add(lblLocation,4,1);
        ChoiceBox choiceLocation = new ChoiceBox(FXCollections.observableArrayList("London","New York","Phoenix"));
        root.add(choiceLocation,5,1);
        
        Label lblContact = new Label("Contact");
        root.add(lblContact,0,2);
        TextField txtContact = new TextField();
        root.add(txtContact,1,2);
        Label lblURL = new Label("URL");
        root.add(lblURL,2,2);
        TextField txtURL = new TextField();
        root.add(txtURL,3,2);
        
        Label lblApptDt = new Label("Date");
        root.add(lblApptDt,0,3);
        TextField txtApptDt = new TextField("YYYY-MM-DD");
        root.add(txtApptDt,1,3);
        Label lblApptTime = new Label("Time");
        root.add(lblApptTime,2,3);
        TextField txtTime = new TextField("HH:MM:SS");
        root.add(txtTime,3,3);
        Label lblApptLength = new Label("Length");
        root.add(lblApptLength,4,3);
        TextField txtLength = new TextField();
        root.add(txtLength,5,3);
        TextField txtDescription = new TextField();
        root.add(txtDescription,1,4,6,1);
        
        FlowPane buttonBox = new FlowPane();
        buttonBox.setHgap(5);
        
        
        Button btnSave = new Button("Save");
        btnSave.setOnAction(event->{
            appt.setCustomer((CustomerModelInterface)choiceCustomer.getSelectionModel().getSelectedItem());
            appt.setTitle(txtTitle.getText());
            appt.setDescription(txtDescription.getText());
            appt.setLocation(choiceLocation.getSelectionModel().getSelectedItem().toString());
            appt.setContact(txtContact.getText());
            appt.setURL(txtURL.getText());
            ZoneId timezone = Appointment.getZone(choiceLocation.getSelectionModel().getSelectedItem().toString());
            ZonedDateTime startTime = ZonedDateTime.of(LocalDateTime.parse(txtApptDt.getText()+"T"+txtTime.getText(),DateTimeFormatter.ISO_LOCAL_DATE_TIME),timezone);
            appt.setStartTime(startTime); //convert to ZonedDateTime
            appt.setEndTime(startTime.plusMinutes(Long.parseLong(txtLength.getText()))); //add this many minutes to ZonedDateTime above
            controller.updateAppointment(appt);
            //new MainWindow();
            stage.close();
        });
//        root.add(btnSave,0,4);
        Button btnClear = new Button("Clear");
//        root.add(btnClear,1,4);
        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event->{
            //return to MainWindow
            //new MainWindow();
            stage.close();
        });
//        root.add(btnCancel,2,4);
        buttonBox.getChildren().addAll(btnSave,btnClear,btnCancel);
        root.add(buttonBox, 0, 5,6,1);
        
        
        
        Scene scene = new Scene(root,800,350);
        
        stage.setTitle("Appointment Maintenance");
        stage.setScene(scene);
        
        //if appointment data exists, then pre-populate fields
        if (this.appt.getApptID()!=-1){ //only run if appointment is being updated from database (i.e. does not have an ID of -1)
            choiceCustomer.getSelectionModel().select(this.appt.getCustomer()); //Should set the customer to the one in the appointment
            System.out.println("Customer choices are: "+ choiceCustomer.getItems()); //DEBUG ONLY
            txtTitle.setText(this.appt.getTitle());
            choiceLocation.getSelectionModel().select(this.appt.getLocation());
            txtContact.setText(this.appt.getContact());
            txtURL.setText(this.appt.getURL());
            txtApptDt.setText(this.appt.getStartTime().withZoneSameInstant(MainWindow.getTimeZone()).toLocalDate().format(DateTimeFormatter.ISO_DATE)); //database stores appointments in UTC
            txtTime.setText(this.appt.getStartTime().withZoneSameInstant(MainWindow.getTimeZone()).toLocalTime().format(DateTimeFormatter.ISO_TIME));
            txtLength.setText(String.valueOf(ChronoUnit.MINUTES.between(this.appt.getStartTime(),this.appt.getEndTime())));
            txtDescription.setText(this.appt.getDescription());
        }
        
        return stage;
    }


    @Override
    public void show() {
        this.apptWindow = buildApptWindow();
        this.apptWindow.show();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
