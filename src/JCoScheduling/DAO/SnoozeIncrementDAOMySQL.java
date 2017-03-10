/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.SnoozeIncrement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author virtu
 */
public class SnoozeIncrementDAOMySQL implements SnoozeIncrementDAO{
    
     //Database server information
    private Connection conn = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String db = "U03lv6";
    private String url = "jdbc:mysql://52.206.157.109/" + db;
    private String user = "U03lv6";
    private String pass = "53688016198";
    
    public SnoozeIncrementDAOMySQL(){
         //connect to database when instanced
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<SnoozeIncrement> getAllIncrements() {
        ArrayList<SnoozeIncrement> incrementList = new ArrayList<>();
        String query = "SELECT * FROM incrementtypes";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                SnoozeIncrement increment = new SnoozeIncrement(rs.getInt("incrementTypeId"),
                                                                rs.getString("description"));
                incrementList.add(increment);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return incrementList;
    }

    @Override
    public SnoozeIncrement getIncrementByID(int incrementID) throws NotFoundException {
        SnoozeIncrement increment = null;
        String query = "SELECT * FROM incrementtypes WHERE incrementTypeId = "+incrementID;
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                increment = new SnoozeIncrement(rs.getInt("incrementTypeId"),
                                                rs.getString("description"));

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return increment;
    }

    @Override
    public ArrayList<SnoozeIncrement> getIncrementByDesc(String incrementDesc) throws NotFoundException {
       ArrayList<SnoozeIncrement> incrementList = new ArrayList<>();
        String query = "SELECT * FROM incrementtypes WHERE incrementTypeDescription LIKE '"+incrementDesc+"'";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                SnoozeIncrement increment = new SnoozeIncrement(rs.getInt("incrementTypeId"),
                                                                rs.getString("description"));
                incrementList.add(increment);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return incrementList;
    }

    @Override
    public void createIncrement(SnoozeIncrement increment) {
         //get next available ID
        int nextNum=0;
        String queryID = "SELECT MAX(incrementTypeId) AS incrementTypeId FROM incrementtypes";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryID);
            
            while(rs.next()){
                nextNum = rs.getInt("incrementTypeId")+1;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        
        String query = "INSERT INTO incrementtypes (incrementTypeId, incrementTypeDescription) VALUES ("+
                                            nextNum+", '"+
                                            increment.getDescription()+"')";
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
            increment.setIncrementID(nextNum); //update object with ID from database
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateIncrement(SnoozeIncrement increment) {
        String query = "UPDATE incrementtypes SET  incrementTypeDescription = '"+increment.getDescription()+"'";
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteIncrement(SnoozeIncrement increment) {
        String query = "DELETE FROM incrementtypes WHERE incrementTypeId= "+increment.getIncrementID();
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteIncrement(int incrementID) {
        String query = "DELETE FROM incrementtypes WHERE incrementTypeId= "+incrementID;
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
