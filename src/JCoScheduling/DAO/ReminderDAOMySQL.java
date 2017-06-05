/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.Reminder;
import JCoScheduling.Models.SnoozeIncrement;
import JCoScheduling.Models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 *
 * @author virtu
 */
public class ReminderDAOMySQL implements ReminderDAO{
    
    private static Connection conn= null;
    
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.0.0.194/U03lv6", "jbowley", "Paw52beh!");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
      //Database server information
//    private Connection conn = null;
//    private String driver = "com.mysql.jdbc.Driver";
//    private String db = "U03lv6";
//    private String url = "jdbc:mysql://10.0.0.194/" + db;
//    private String user = "jbowley";
//    private String pass = "Paw52beh!";
    
    public ReminderDAOMySQL(){
         //connect to database when instanced
//        try {
//            Class.forName(driver);
//            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
//        } catch(Exception ex){
//            ex.printStackTrace();
//        } 
    }
    
//     public void finalize(){
//        try{
//            conn.close();
//        }catch(SQLException ex){
//            System.out.println("Unable to close database connection.\n");
//            ex.printStackTrace();
//        }
//    }

    @Override
    public ArrayList<Reminder> getAllReminders() {
        ArrayList<Reminder> remList = new ArrayList<>();
        String query = "SELECT * FROM reminder";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                //get data about Appointment and SnoozeIncrement objects from database
                AppointmentDAO apptDAO = new AppointmentDAOMySQL();
                Appointment appointment=new Appointment();
                SnoozeIncrementDAO incrementDAO = new SnoozeIncrementDAOMySQL();
                SnoozeIncrement increment = new SnoozeIncrement();
                
                try{
                    appointment = apptDAO.getApptByID(rs.getInt("appointmentId"));
                    increment = incrementDAO.getIncrementByID(rs.getInt("snoozeIncrementTypeId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
               
                Reminder reminder = new Reminder(rs.getInt("reminderId"),
                                                 rs.getTimestamp("reminderDate").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 increment,
                                                 appointment,
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime()), //lastUpdate information not stored for reminders so fudge it
                                                 rs.getString("reminderCol"));
                remList.add(reminder);
               
            }
            
            stmt.closeOnCompletion();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return remList;
    }

    @Override
    public Reminder getReminderByID(int reminderID) {
        Reminder reminder = null;
        String query = "SELECT * FROM reminder WHERE remidnerId = "+reminderID;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                //get data about Appointment and SnoozeIncrement objects from database
                AppointmentDAO apptDAO = new AppointmentDAOMySQL();
                Appointment appointment=new Appointment();
                SnoozeIncrementDAO incrementDAO = new SnoozeIncrementDAOMySQL();
                SnoozeIncrement increment = new SnoozeIncrement();
                
                try{
                    appointment = apptDAO.getApptByID(rs.getInt("appointmentId"));
                    increment = incrementDAO.getIncrementByID(rs.getInt("snoozeIncrementTypeId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
               
                reminder = new Reminder(rs.getInt("reminderId"),
                                                 rs.getTimestamp("reminderDate").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 increment,
                                                 appointment,
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime()), //lastUpdate information not stored for reminders so fudge it
                                                 rs.getString("reminderCol"));
               
            }
            
            stmt.closeOnCompletion();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return reminder;
    }

    @Override
    public ArrayList<Reminder> getReminderByDate(ZonedDateTime startDate, ZonedDateTime endDate) {
        ArrayList<Reminder> remList = new ArrayList<>();
        String query = "SELECT * FROM reminder WHERE reminderDate BETWEEN '"+startDate.toLocalDateTime()+"' AND '"+endDate.toLocalDateTime()+"'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                //get data about Appointment and SnoozeIncrement objects from database
                AppointmentDAO apptDAO = new AppointmentDAOMySQL();
                Appointment appointment=null;
                SnoozeIncrementDAO incrementDAO = new SnoozeIncrementDAOMySQL();
                SnoozeIncrement increment = null;
                
                try{
                    appointment = apptDAO.getApptByID(rs.getInt("appointmentId"));
                    increment = incrementDAO.getIncrementByID(rs.getInt("snoozeIncrementTypeId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
               
                Reminder reminder = new Reminder(rs.getInt("reminderId"),
                                                 rs.getTimestamp("reminderDate").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 increment,
                                                 appointment,
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime()), //lastUpdate information not stored for reminders so fudge it
                                                 rs.getString("reminderCol"));
                remList.add(reminder);
               
            }
            
            stmt.closeOnCompletion();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return remList;
    }

    @Override
    public ArrayList<Reminder> getReminderByUser(User user) {
        ArrayList<Reminder> remList = new ArrayList<>();
        String query = "SELECT * FROM reminder WHERE createdBy = '"+user.getUsername()+"'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                //get data about Appointment and SnoozeIncrement objects from database
                AppointmentDAO apptDAO = new AppointmentDAOMySQL();
                Appointment appointment=null;
                SnoozeIncrementDAO incrementDAO = new SnoozeIncrementDAOMySQL();
                SnoozeIncrement increment = null;
                
                try{
                    appointment = apptDAO.getApptByID(rs.getInt("appointmentId"));
                    increment = incrementDAO.getIncrementByID(rs.getInt("snoozeIncrementTypeId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
               
                Reminder reminder = new Reminder(rs.getInt("reminderId"),
                                                 rs.getTimestamp("reminderDate").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 increment,
                                                 appointment,
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime()), //lastUpdate information not stored for reminders so fudge it
                                                 rs.getString("reminderCol"));
                remList.add(reminder);
               
            }
            
            stmt.closeOnCompletion();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return remList;
    }

    @Override
    public void createReminder(Reminder reminder) {
        //get next available cityID from database
        int nextID=0;
        try {
            String query = "SELECT MAX(reminderId) AS reminderId FROM reminder";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                nextID = rs.getInt("reminderId")+1;
            }
            
            stmt.closeOnCompletion();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        if(reminder.getSnoozeIncrement().getIncrementID()==-1){ //no database ID yet assigned
            SnoozeIncrementDAO incrementDAO = new SnoozeIncrementDAOMySQL();
            incrementDAO.createIncrement((SnoozeIncrement)reminder.getSnoozeIncrement());
        } else {
            String query = "INSERT INTO reminder (reminderId, reminderDate ,snoozeIncrement,snoozeIncrementTypeId, appointmentId, createdBy, createDate, remindercol) VALUES ("+
                            nextID+", '"+
                            reminder.getReminderDate()+", '"+
                            reminder.getSnoozeIncrement().getDescription()+"', "+
                            reminder.getSnoozeIncrement().getIncrementID()+", '"+
                            reminder.getAppointment().getApptID()+", '"+
                            reminder.getAuditInfo().getCreatedBy()+"', '"+
                            reminder.getAuditInfo().getCreatedDate()+"', '"+
                            reminder.getReminderCol()+"')";
            try{
                Statement stmt = conn.createStatement();
                stmt.execute(query);
                reminder.setReminderID(nextID); //update referenced Reminder object with ID from database
                stmt.closeOnCompletion();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void updateReminder(Reminder reminder) {
        String query = "UPDATE reminder SET  reminderDate = '"+reminder.getReminderDate()+"', "+
                                        "snoozeIncrement = '"+reminder.getSnoozeIncrement().getDescription()+"', "+
                                        "snoozeIncrementTypeId = "+reminder.getSnoozeIncrement().getIncrementID()+", "+
                                        "appointmentId = "+reminder.getAppointment().getApptID()+", "+
                                        "reminderCol = '"+reminder.getReminderCol()+"', "+"' WHERE reminderId = "+reminder.getReminderID();
        
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            stmt.closeOnCompletion();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteReminder(Reminder reminder) {
        String query = "DELETE FROM reminder WHERE reminderId = "+reminder.getReminderID();
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            stmt.closeOnCompletion();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteReminder(int reminderID) {
        String query = "DELETE FROM reminder WHERE reminderId = "+reminderID;
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            stmt.closeOnCompletion();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
