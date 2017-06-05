/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.ReportControllerInterface;
import JCoScheduling.Models.Appointment;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author virtu
 */
public class ReportsByMonthView implements ReportingViewInterface{
    
    private ReportControllerInterface controller;
    private Stage stage;
    
    public ReportsByMonthView(ReportControllerInterface controller){
        this.controller = controller;
    }
    
    public Stage buildView(){
        Stage stage = new Stage();
        
        GridPane root = new GridPane();
        root.setPrefSize(800, 600);
        
        Label lblTitle = new Label("Reports by User");
        lblTitle.setFont(new Font("Verdana",20));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.DARKGRAY);
        titleShadow.setOffsetX(3.0);
        titleShadow.setOffsetY(3.0);
        lblTitle.setEffect(titleShadow);
        root.add(lblTitle,0,0,4,1);
        
        //each month will have a label and a listview to display appointments 
        Label lblJanuary = new Label("January");
        root.add(lblJanuary, 0, 1);
        ListView lvJanuary = new ListView();
        root.add(lvJanuary,0,2);
        
        Label lblFebruary = new Label("February");
        root.add(lblFebruary, 0, 3);
        ListView lvFebruary = new ListView();
        root.add(lvFebruary,0,4);
        
        Label lblMarch = new Label("March");
        root.add(lblMarch, 0, 5);
        ListView lvMarch = new ListView();
        root.add(lvMarch,0,6);
        
        Label lblApril = new Label("April");
        root.add(lblApril, 1, 1);
        ListView lvApril = new ListView();
        root.add(lvApril,1,2);
        
        Label lblMay= new Label("May");
        root.add(lblMay, 1, 3);
        ListView lvMay = new ListView();
        root.add(lvMay,1,4);
        
        Label lblJune= new Label("June");
        root.add(lblJune, 1, 5);
        ListView lvJune = new ListView();
        root.add(lvJune,1,6);
        
        Label lblJuly= new Label("July");
        root.add(lblJuly, 2, 1);
        ListView lvJuly = new ListView();
        root.add(lvJuly,2,2);
        
        Label lblAugust= new Label("August");
        root.add(lblAugust, 2, 3);
        ListView lvAugust = new ListView();
        root.add(lvAugust,2,4);
        
        Label lblSeptember= new Label("September");
        root.add(lblSeptember, 2, 5);
        ListView lvSeptember = new ListView();
        root.add(lvSeptember,2,6);
        
        Label lblOctober= new Label("October");
        root.add(lblOctober, 3, 1);
        ListView lvOctober = new ListView();
        root.add(lvOctober,3,2);
        
        Label lblNovember= new Label("November");
        root.add(lblNovember, 3, 3);
        ListView lvNovember = new ListView();
        root.add(lvNovember,3,4);
        
        Label lblDecember= new Label("December");
        root.add(lblDecember, 3, 5);
        ListView lvDecember = new ListView();
        root.add(lvDecember,3,6);
        
        Button btnOK = new Button("OK");
        btnOK.setOnAction(event->{
            close();
        });
        root.add(btnOK,0,7,4,1);
        
        ZonedDateTime currentMonth = ZonedDateTime.now().with(TemporalAdjusters.firstDayOfYear()); //start getting appointment data at beginning of current year
        ZonedDateTime yrEnd = ZonedDateTime.now().with(TemporalAdjusters.lastDayOfYear());
        
        while(currentMonth.until(yrEnd, ChronoUnit.MONTHS)>=0){
            ObservableList<Appointment> apptsThisMonth = FXCollections.observableArrayList();
            
            for(Appointment appt:controller.getApptsForMonth(currentMonth)){
                apptsThisMonth.add(appt);
            }
            
            switch(currentMonth.getMonthValue()){
                case 1:
                    lvJanuary.setItems(apptsThisMonth);
                    break;
                case 2:
                    lvFebruary.setItems(apptsThisMonth);
                    break;
                case 3:
                    lvMarch.setItems(apptsThisMonth);
                    break;
                case 4:
                    lvApril.setItems(apptsThisMonth);
                    break;
                case 5:
                    lvMay.setItems(apptsThisMonth);
                    break;
                case 6:
                    lvJune.setItems(apptsThisMonth);
                    break;
                case 7:
                    lvJuly.setItems(apptsThisMonth);
                    break;
                case 8:
                    lvAugust.setItems(apptsThisMonth);
                    break;
                case 9:
                    lvSeptember.setItems(apptsThisMonth);
                    break;
                case 10:
                    lvOctober.setItems(apptsThisMonth);
                    break;
                case 11:
                    lvNovember.setItems(apptsThisMonth);
                    break;
                case 12:
                    lvDecember.setItems(apptsThisMonth);
                    break;
            }
            currentMonth = currentMonth.plusMonths(1L);
        }
        
        Scene scene = new Scene(root,900,650);
        stage.setScene(scene);
        stage.setTitle("Appointments by Month");
        
        return stage;
    }

    @Override
    public void show() {
        this.stage = buildView();
        this.stage.show();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        this.stage.close();
    }
    
}
