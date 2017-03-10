/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Appointment;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.Customer;
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
public class AppointmentDAOMySQL implements AppointmentDAO{

      //Database server information
    private Connection conn = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String db = "U03lv6";
    private String url = "jdbc:mysql://52.206.157.109/" + db;
    private String user = "U03lv6";
    private String pass = "53688016198";
    
    public AppointmentDAOMySQL(){
         //connect to database when instanced
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> apptList = new ArrayList<>();
        String query = "SELECT * FROM appointment";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                CustomerDAO custDAO = new CustomerDAOMySQL();
                Customer customer=null;
                try{
                    customer = custDAO.getCustomerByID(rs.getInt("customerId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
               
                Appointment appointment = new Appointment(rs.getInt("appointmentId"),
                                                 customer,
                                                 rs.getString("title"),
                                                 rs.getString("description"),
                                                 rs.getString("location"),
                                                 rs.getString("contact"),
                                                 rs.getString("url"),
                                                 rs.getTimestamp("start").toLocalDateTime().atZone(ZoneId.of("America/Montreal")), //appointments stored at EST in DB
                                                 rs.getTimestamp("end").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                apptList.add(appointment);
               
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return apptList;
    }

    @Override
    public Appointment getApptByID(int apptID) {
        Appointment appt = null;
        String query = "SELECT * FROM appointment WHERE appointmentId ="+apptID;
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                CustomerDAO custDAO = new CustomerDAOMySQL();
                Customer customer=null;
                try{
                    customer = custDAO.getCustomerByID(rs.getInt("customerId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
               
                appt = new Appointment(rs.getInt("appointmentId"),
                                                 customer,
                                                 rs.getString("title"),
                                                 rs.getString("description"),
                                                 rs.getString("location"),
                                                 rs.getString("contact"),
                                                 rs.getString("url"),
                                                 rs.getTimestamp("start").toLocalDateTime().atZone(ZoneId.of("America/Montreal")), //appointments stored at EST in DB
                                                 rs.getTimestamp("end").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
               
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return appt;
    }

    @Override
    public ArrayList<Appointment> getApptByDate(ZonedDateTime startDate, ZonedDateTime endDate) {
        ArrayList<Appointment> apptList = new ArrayList<>();
        String query = "SELECT * FROM appointment WHERE start BETWEEN '"+startDate.toLocalDateTime()+"' AND '"+endDate.toLocalDateTime()+"'";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                CustomerDAO custDAO = new CustomerDAOMySQL();
                Customer customer=null;
                try{
                    customer = custDAO.getCustomerByID(rs.getInt("customerId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
               
                Appointment appointment = new Appointment(rs.getInt("appointmentId"),
                                                 customer,
                                                 rs.getString("title"),
                                                 rs.getString("description"),
                                                 rs.getString("location"),
                                                 rs.getString("contact"),
                                                 rs.getString("url"),
                                                 rs.getTimestamp("start").toLocalDateTime().atZone(ZoneId.of("America/Montreal")), //appointments stored at EST in DB
                                                 rs.getTimestamp("end").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                apptList.add(appointment);
               
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return apptList;
    }

    @Override
    public ArrayList<Appointment> getApptByCustomer(Customer customer) {
        ArrayList<Appointment> apptList = new ArrayList<>();
        String query = "SELECT * FROM appointment WHERE customerId = "+customer.getCustomerID();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
               
                Appointment appointment = new Appointment(rs.getInt("appointmentId"),
                                                 customer,
                                                 rs.getString("title"),
                                                 rs.getString("description"),
                                                 rs.getString("location"),
                                                 rs.getString("contact"),
                                                 rs.getString("url"),
                                                 rs.getTimestamp("start").toLocalDateTime().atZone(ZoneId.of("America/Montreal")), //appointments stored at EST in DB
                                                 rs.getTimestamp("end").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                apptList.add(appointment);
               
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return apptList;
    }

    @Override
    public ArrayList<Appointment> getApptByUser(User user) {
        ArrayList<Appointment> apptList = new ArrayList<>();
        String query = "SELECT * FROM appointment WHERE createdBy = '"+user.getUsername()+"' OR lastUpdateBy = '"+user.getUsername()+"'";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                CustomerDAO custDAO = new CustomerDAOMySQL();
                Customer customer=null;
                try{
                    customer = custDAO.getCustomerByID(rs.getInt("customerId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
               
                Appointment appointment = new Appointment(rs.getInt("appointmentId"),
                                                 customer,
                                                 rs.getString("title"),
                                                 rs.getString("description"),
                                                 rs.getString("location"),
                                                 rs.getString("contact"),
                                                 rs.getString("url"),
                                                 rs.getTimestamp("start").toLocalDateTime().atZone(ZoneId.of("America/Montreal")), //appointments stored at EST in DB
                                                 rs.getTimestamp("end").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                apptList.add(appointment);
               
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return apptList;
    }

    @Override
    public ArrayList<Appointment> getApptByCreateDate(ZonedDateTime startDate, ZonedDateTime endDate) {
        ArrayList<Appointment> apptList = new ArrayList<>();
        String query = "SELECT * FROM appointment WHERE createDate BETWEEN '"+startDate.toLocalDateTime()+"' AND '"+endDate.toLocalDateTime()+"'";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            
            while(rs.next()){
                CustomerDAO custDAO = new CustomerDAOMySQL();
                Customer customer=null;
                try{
                    customer = custDAO.getCustomerByID(rs.getInt("customerId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
               
                Appointment appointment = new Appointment(rs.getInt("appointmentId"),
                                                 customer,
                                                 rs.getString("title"),
                                                 rs.getString("description"),
                                                 rs.getString("location"),
                                                 rs.getString("contact"),
                                                 rs.getString("url"),
                                                 rs.getTimestamp("start").toLocalDateTime().atZone(ZoneId.of("America/Montreal")), //appointments stored at EST in DB
                                                 rs.getTimestamp("end").toLocalDateTime().atZone(ZoneId.of("America/Montreal")),
                                                 new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                apptList.add(appointment);
               
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return apptList;
    }

    @Override
    public void createAppointment(Appointment appointment) {
        //get next available cityID from database
        int nextID=0;
        try {
            String query = "SELECT MAX(appointmentId) AS appointmentId FROM appointment";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                nextID = rs.getInt("appointmentId")+1;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        if(appointment.getCustomer().getCustomerID()==-1){ //no database ID yet assigned
            CustomerDAO custDAO = new CustomerDAOMySQL();
            custDAO.createCustomer((Customer)appointment.getCustomer());
        } else {
            String query = "INSERT INTO appointment (appointmentId, customerId,title,description, location, contact, url, start, end, createdBy, lastUpdate, lastUpdateBy) VALUES ("+
                            nextID+", "+
                            appointment.getCustomer().getCustomerID()+", '"+
                            appointment.getTitle()+"', '"+
                            appointment.getDescription()+"', '"+
                            appointment.getLocation()+"', '"+
                            appointment.getContact()+"', '"+
                            appointment.getURL()+"', '"+
                            appointment.getStartTime().toLocalDateTime()+"', '"+
                            appointment.getEndTime().toLocalDateTime()+"', '"+
                            appointment.getAuditInfo().getCreatedDate()+"', '"+
                            appointment.getAuditInfo().getCreatedBy()+"', '"+
                            appointment.getAuditInfo().getLastUpdate()+"', '"+
                            appointment.getAuditInfo().getLastUpdatedBy()+"')";
            try{
                Statement stmt = this.conn.createStatement();
                stmt.execute(query);
                appointment.setApptID(nextID); //update referenced city object with ID from database
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) {
         if(appointment.getCustomer().getCustomerID()==-1){ //no database ID yet assigned
            CustomerDAO custDAO = new CustomerDAOMySQL();
            custDAO.createCustomer((Customer)appointment.getCustomer());
        } 
        String query = "UPDATE appointment SET  customerId = "+appointment.getCustomer().getCustomerID()+", "+
                                        "title = '"+appointment.getTitle()+"', "+
                                        "description = '"+appointment.getDescription()+"', "+
                                        "location = '"+appointment.getLocation()+"', "+
                                        "contact = '"+appointment.getContact()+"', "+
                                        "url = '"+appointment.getURL()+"', "+
                                        "start = '"+appointment.getStartTime().toLocalDateTime()+"', "+
                                        "end = '"+appointment.getEndTime().toLocalDateTime()+"', "+
                                        "lastUpdate = '"+appointment.getAuditInfo().getLastUpdate()+"', "+
                                        "lastUpdateBy = '"+appointment.getAuditInfo().getLastUpdatedBy()+"' WHERE appointmentId = "+appointment.getApptID();
        
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        String query = "DELETE FROM appointment WHERE appointmentId = "+appointment.getApptID();
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAppointment(int appointmentID) {
        String query = "DELETE FROM appointment WHERE appointmentId = "+appointmentID;
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
