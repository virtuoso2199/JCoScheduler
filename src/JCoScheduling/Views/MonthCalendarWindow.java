/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Views.WkCalendarWindow;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class MonthCalendarWindow {
    private static Stage monthCalWindow;
    
    public static Stage getMonthCalWindow(){
        if(monthCalWindow == null){
            monthCalWindow = buildMonthCalWindow();
        }
        
        
        return monthCalWindow;
    }
    
    private static Stage buildMonthCalWindow(){
        Stage stage = new Stage();
        
        GridPane root = new GridPane();
        
        //title and navigation similar to week view
        Button btnBack= new Button("Back");
        GridPane.setHalignment(btnBack, HPos.CENTER);
        root.add(btnBack, 0, 0);
        Label lblTitle = new Label("Month Calendar");
        lblTitle.setFont(new Font("Verdana",30));
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.DARKGRAY);
        titleShadow.setOffsetY(3.0);
        titleShadow.setOffsetX(3.0);
        lblTitle.setEffect(titleShadow);
        GridPane.setHalignment(lblTitle, HPos.CENTER);
        root.add(lblTitle,1,0,5,1);
        Button btnForward = new Button("Forward");
        GridPane.setHalignment(btnForward, HPos.CENTER);
        root.add(btnForward,6,0);
        
        //Create a 7x5 grid to hold all the days of the month
        String[] days = {"Monday","Tuesday", "Wednesday","Thursday","Friday","Saturday","Sunday"};
        for (int row=0;row<5;row++){
            FlowPane weekPane = new FlowPane();
            weekPane.setAlignment(Pos.TOP_CENTER);
            weekPane.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
            weekPane.setPrefWidth(720);
            weekPane.setPrefHeight(120);
            for(int day=0;day<7;day++){
                FlowPane dayPane = new FlowPane();
                dayPane.setAlignment(Pos.TOP_CENTER);
                dayPane.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
                dayPane.setPrefWidth(100);
                dayPane.setPrefHeight(100);
                weekPane.getChildren().add(dayPane);
            }
            
            root.add(weekPane, 0, row+1,7,1); //row+1 accounts for title bar row
        }
        
        Button btnWeek = new Button("Week");
        btnWeek.setOnAction(event->{
            Stage weekView = WkCalendarWindow.getWkCalWindow();
            weekView.show();
            stage.close();
        });
        root.add(btnWeek,0,6);
        
        Button btnOK = new Button("OK");
        btnOK.setOnAction(event->{
            new MainWindow();
            stage.close();
        });
        root.add(btnOK,6,6);
        
        Scene scene = new Scene(root,800,800);
        stage.setTitle("Month Calendar View");
        stage.setScene(scene);
        
        return stage;
        
    }
    
}
