/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling;

import JCoScheduling.Controllers.UserController;
import JCoScheduling.Controllers.UserControllerInterface;
import JCoScheduling.DAO.UserDAOMySQL;
import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.User;
import JCoScheduling.Models.UserModelInterface;
import JCoScheduling.Models.UserObserver;
import JCoScheduling.Views.MainWindow;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author M219663
 */
public class C195Final2 extends Application implements UserObserver{
    //create UserController to control the login screen and UserDAO to talk to database for authentication
    private UserModelInterface user;        
    private UserControllerInterface userController; //to be assigned later when User object can be built from form fields
    private ZoneId userTimezone; 
    Locale userLocale;
    ResourceBundle rb;
    
    
    @Override
    public void start(Stage primaryStage) {

        user = new User();
        user.registerObserver((UserObserver)this);
        userController = new UserController(new UserDAOMySQL(),primaryStage,user);
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("LoginFields",Locale.getDefault());
        this.userTimezone = ZoneId.systemDefault();
              
        //create a GridPane for form layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10.0);
        root.setVgap(10.0);
        
        //create all login form controls
        Label lblLogo = new Label("JCo Login");
        lblLogo.setFont(new Font("Verdana",30));
        DropShadow logoShadow = new DropShadow();
        logoShadow.setColor(Color.DARKGRAY);
        logoShadow.setOffsetX(3.0);
        logoShadow.setOffsetY(3.0);
        lblLogo.setEffect(logoShadow);
        root.add(lblLogo, 0, 0,2,1);
        
//        HBox hboxUsername = new HBox();
        Label lblUsername = new Label("Username:");
//        lblUsername.setPrefSize(40,20);
        root.add(lblUsername,0,1);
        TextField txtUsername = new TextField();
//        txtUsername.setPrefSize(40,20);
        root.add(txtUsername,1,1);
//        hboxUsername.getChildren().addAll(lblUsername,txtUsername);
//        hboxUsername.setSpacing(20);

        
        
//        HBox hboxPassword = new HBox();
        Label lblPassword = new Label("Password:");
//        lblPassword.setPrefSize(40,20);
        root.add(lblPassword,0,2);
        PasswordField txtPassword = new PasswordField();
//        txtPassword.setPrefSize(40,20);
        root.add(txtPassword,1,2);
//        hboxPassword.getChildren().addAll(lblPassword,txtPassword);
//        hboxPassword.setSpacing(20);
        
       

//        HBox hboxLanguage = new HBox();
//        Label lblLanguage = new Label("Language:");
////        lblLanguage.setPrefSize(40,20);
//        root.add(lblLanguage,0,3);
//        ChoiceBox choiceLanguage = new ChoiceBox(FXCollections.observableArrayList("English","Français","Español")); 
////        choiceLanguage.setPrefSize(120,20);
//        choiceLanguage.setTooltip(new Tooltip("Select a language")); 
        Label lblLocation = new Label("Location:"); //when this changes, change the timezone of the program
//        choiceLanguage.setOnAction(event -> {//when this changes, change the language of the fields on the form
//                //System.out.println(choiceLanguage.getSelectionModel().getSelectedItem()); //DEBUG ONLY
//                if(choiceLanguage.getSelectionModel().getSelectedItem()=="English"){
//                    Locale.setDefault(new Locale("en"));
//                    this.rb = ResourceBundle.getBundle("LoginFields",Locale.getDefault());
//                } else if(choiceLanguage.getSelectionModel().getSelectedItem()=="Français"){
//                    Locale.setDefault(new Locale("fr"));
//                    this.rb = ResourceBundle.getBundle("LoginFields",Locale.getDefault());
//                } else if(choiceLanguage.getSelectionModel().getSelectedItem()=="Español"){
//                    Locale.setDefault(new Locale("es"));
//                    this.rb = ResourceBundle.getBundle("LoginFields",Locale.getDefault());
//                }
//                //reload fields on change of language
//                lblUsername.setText(this.rb.getString("username"));
//                lblPassword.setText(this.rb.getString("password"));
//                lblLanguage.setText(this.rb.getString("language"));
//                lblLocation.setText(this.rb.getString("location"));
//            });
//        
//        root.add(choiceLanguage,1,3);
        
        //sets fields to system default language 
        lblUsername.setText(this.rb.getString("username"));
        lblPassword.setText(this.rb.getString("password"));
//        lblLanguage.setText(this.rb.getString("language"));
        lblLocation.setText(this.rb.getString("location"));
        
        //set language to default for locale if applicable. Otherwise, default to English
//        if(this.userLocale.equals(new Locale("fr").getLanguage())){
//            //default system locale uses French language
//            choiceLanguage.getSelectionModel().select("Français");
//        } else if (this.userLocale.equals(new Locale("es").getLanguage())){
//            //default system locale uses Spanish language
//            choiceLanguage.getSelectionModel().select("Español");
//        } else {
//            //default to English if other languags are unavailable
//            choiceLanguage.getSelectionModel().select("English");
//        }
        
        
//        hboxLanguage.getChildren().addAll(lblLanguage,choiceLanguage);
//        hboxLanguage.setSpacing(10);
        
//        HBox hboxLocation = new HBox();

//        lblLocation.setPrefSize(40,20);
        root.add(lblLocation,0,4);
        ChoiceBox choiceLocation = new ChoiceBox(FXCollections.observableArrayList("Phoenix","New York","London"));
//        choiceLocation.setPrefSize(40,20);
        choiceLocation.setTooltip(new Tooltip("Select a location"));
        choiceLocation.setOnAction(event->{
            
            userTimezone = Appointment.getZone(choiceLocation.getSelectionModel().getSelectedItem().toString()); //set timezone to display appointments in, defaulting to New York's if none selected
        });
        root.add(choiceLocation,1,4);
        
        if(this.userTimezone.normalized().equals(Appointment.getZone("London").normalized())){
            //system default timezone is the same as London so set that in the choiceLocation dropdown
            choiceLocation.getSelectionModel().select("London");
        } else if (this.userTimezone.normalized().equals(Appointment.getZone("Phoenix").normalized())){
            //system default timezone is the same as Phoenix, AZ so set that in the choiceLocation dropdown
            choiceLocation.getSelectionModel().select("Phoenix");
        } else if (this.userTimezone.normalized().equals(Appointment.getZone("New York").normalized())){
            //system default timezone is the same as NYC so set that in the choiceLocation dropdown
            choiceLocation.getSelectionModel().select("New York");
        } //if user timezone does not match one of the default locations, times will default to system default though no location will be selected
        
        
//        hboxLocation.getChildren().addAll(lblLocation,choiceLocation);
//        hboxLocation.setSpacing(20);
        final Label lblInfo = new Label(); //used to display information to the user
        root.add(lblInfo,0,6,2,1);
//        HBox hboxButtons = new HBox();
        Button btnOK = new Button("OK");
//        btnOK.setPrefSize(40,20);
        btnOK.setOnAction((ActionEvent event)->{
                if (userTimezone==null){
                    userTimezone = Appointment.getZone("New York");
                }
                this.user.setUsername(txtUsername.getText());
                this.user.setPassword(txtPassword.getText());
                if(userController.validateLogin()){ //user authenticated
                    new MainWindow(this.user,userTimezone);
                    primaryStage.close();
                } else {
                    //user could not be authenticated, show error
                    lblInfo.setText(this.rb.getString("loginFail"));
                    System.out.println(txtUsername.getText()+":"+txtPassword.getText());
                    txtUsername.clear();
                    txtPassword.clear();
                }
                
            });
        root.add(btnOK,0,5);
        Button btnQuit = new Button("Quit");
        btnQuit.setOnAction(event->primaryStage.close());
//        btnQuit.setPrefSize(40,20);
        root.add(btnQuit,1,5);
//        hboxButtons.getChildren().addAll(btnOK,btnQuit);
//        hboxButtons.setSpacing(20);

        
                
        Scene scene = new Scene(root, 400, 400);
        
        root.setOnKeyPressed((event)-> { //should be put into a method and called, but lambdas are required for C195, soooooo....
                if (event.getCode() == KeyCode.ENTER){
                     this.user.setUsername(txtUsername.getText());
                     this.user.setPassword(txtPassword.getText());
                     if(userController.validateLogin()){ //user authenticated
                         JCoLogger.recordLogin(this.user.getUsername());
                         new MainWindow(this.user,userTimezone);
                         primaryStage.close();
                     } else {
                         //user could not be authenticated, show error
                         lblInfo.setText("Authentication failed for user "+ user.getUsername()+"! Please try again");
                         System.out.println(txtUsername.getText()+":"+txtPassword.getText());
                         txtUsername.clear();
                         txtPassword.clear();
                     } 
                }
                    });
        
        primaryStage.setTitle("JCo Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void updateUser(User u) {
        this.user = u;
    }
    
    public void localizeLogin(){
        this.userLocale = Locale.getDefault();
        //FINISH THIS PART LATER
    }
    
}
