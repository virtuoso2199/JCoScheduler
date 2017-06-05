/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Controllers.ReportControllerInterface;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author M219663
 */
public class ReportMenuView implements ReportingViewInterface{
    
    private ReportControllerInterface controller;
    private Stage stage;
    
    public ReportMenuView(ReportControllerInterface controller){
        this.controller = controller;
        
    }

    private Stage buildView(){
        Stage stage = new Stage();
        
        FlowPane root = new FlowPane();
        root.setPrefSize(300, 300);
        
        Button btnRptCustomer = new Button("Appointments by Customer");
        btnRptCustomer.setPrefSize(280, 50);
        btnRptCustomer.setOnAction(event-> {
            controller.showApptsByCustomer();
        });
        root.getChildren().add(btnRptCustomer);
        
        Button btnRptUser = new Button("Appointments by User");
        btnRptUser.setPrefSize(280, 50);
        btnRptUser.setOnAction(event->{
            controller.showApptsByUser();
        });
        root.getChildren().add(btnRptUser);
        
        Button btnRptMonthly = new Button("Appointments by Month");
        btnRptMonthly.setPrefSize(280, 50);
        btnRptMonthly.setOnAction(event -> {
            controller.showApptsByMonth();
        });
        root.getChildren().add(btnRptMonthly);
        
        Button btnClose = new Button("Close");
        btnClose.setPrefSize(280, 50);
        btnClose.setOnAction(event ->{
            close();
        });
        root.getChildren().add(btnClose);
        
        Scene scene = new Scene(root,350,350);
        stage.setScene(scene);
        stage.setTitle("Reporting Menu");
        
        
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
