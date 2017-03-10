/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

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
public class WkCalendarWindow {
    private static Stage wkCalWindow;
    
    public static Stage getWkCalWindow(){
        if(wkCalWindow==null){
            wkCalWindow = buildWkCalWindow();
        }
        
        return wkCalWindow;
    }
    
    private static Stage buildWkCalWindow(){
        Stage stage = new Stage();
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        
        //calendar controls
        Button btnBack= new Button("Back");
        GridPane.setHalignment(btnBack, HPos.CENTER);
        root.add(btnBack, 0, 0);
        Label lblTitle = new Label("Weekly Calendar");
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
        
        //days of week columns
        FlowPane mondayFlow = new FlowPane();
        mondayFlow.setPrefWidth(100);
        mondayFlow.setPrefHeight(700);
        mondayFlow.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
        Label lblMonday = new Label("Monday");
        mondayFlow.setAlignment(Pos.TOP_CENTER);
        mondayFlow.getChildren().add(lblMonday);
        root.add(mondayFlow,0,1);
        FlowPane tuesdayFlow = new FlowPane();
        tuesdayFlow.setPrefWidth(100);
        tuesdayFlow.setPrefHeight(700);
        tuesdayFlow.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
        Label lblTuesday = new Label("Tuesday");
        tuesdayFlow.setAlignment(Pos.TOP_CENTER);
        tuesdayFlow.getChildren().add(lblTuesday);
        root.add(tuesdayFlow,1,1);
        FlowPane wednesdayFlow = new FlowPane();
        wednesdayFlow.setPrefWidth(100);
        wednesdayFlow.setPrefHeight(700);
        wednesdayFlow.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
        Label lblWednesday = new Label("Wednesday");
        wednesdayFlow.setAlignment(Pos.TOP_CENTER);
        wednesdayFlow.getChildren().add(lblWednesday);
        root.add(wednesdayFlow,2,1);
        FlowPane thursdayFlow = new FlowPane();
        thursdayFlow.setPrefWidth(100);
        thursdayFlow.setPrefHeight(700);
        thursdayFlow.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
        Label lblThursday = new Label("Thursday");
        thursdayFlow.setAlignment(Pos.TOP_CENTER);
        thursdayFlow.getChildren().add(lblThursday);
        root.add(thursdayFlow,3,1);
        FlowPane fridayFlow = new FlowPane();
        fridayFlow.setPrefWidth(100);
        fridayFlow.setPrefHeight(700);
        fridayFlow.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
        Label lblFriday = new Label("Friday");
        fridayFlow.setAlignment(Pos.TOP_CENTER);
        fridayFlow.getChildren().add(lblFriday);
        root.add(fridayFlow,4,1);
        FlowPane saturdayFlow = new FlowPane();
        saturdayFlow.setPrefWidth(100);
        saturdayFlow.setPrefHeight(700);
        saturdayFlow.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
        Label lblSaturday = new Label("Saturday");
        saturdayFlow.setAlignment(Pos.TOP_CENTER);
        saturdayFlow.getChildren().add(lblSaturday);
        root.add(saturdayFlow,5,1);
        FlowPane sundayFlow = new FlowPane();
        sundayFlow.setPrefWidth(100);
        sundayFlow.setPrefHeight(700);
        sundayFlow.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 1px;");
        Label lblSunday = new Label("Sunday");
        sundayFlow.setAlignment(Pos.TOP_CENTER);
        sundayFlow.getChildren().add(lblSunday);
        root.add(sundayFlow,6,1);
        
        Button btnMonth = new Button("Month");
        GridPane.setHalignment(btnMonth, HPos.CENTER);
        btnMonth.setOnAction(event->{
            Stage monthView = MonthCalendarWindow.getMonthCalWindow();
            monthView.show();
            stage.close();
        });
        root.add(btnMonth,0,2);
        Button btnOK = new Button("OK");
        btnOK.setOnAction(event->{
            Stage mainWindow = MainWindow.getMainWindow();
            mainWindow.show();
            stage.close();
        });
        GridPane.setHalignment(btnOK, HPos.CENTER);
        root.add(btnOK,6,2);
        
        
        
        Scene scene = new Scene(root,800,800);
        stage.setTitle("Weekly Calendar");
        stage.setScene(scene);
        
        
        return stage;
    }
}
