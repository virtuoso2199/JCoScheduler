/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.Country;
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
public class CountryDAOMySQL implements CountryDAO{
    
    //Database server information
    private Connection conn = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String db = "U03lv6";
    private String url = "jdbc:mysql://52.206.157.109/" + db;
    private String user = "U03lv6";
    private String pass = "53688016198";
    
    public CountryDAOMySQL(){
         //connect to database when instanced
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Country> getAllCountries() {
        
        ArrayList<Country> countryList = new ArrayList<>();
        String query = "SELECT * FROM country";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Country country = new Country(rs.getInt("countryId"),
                                              rs.getString("country"),
                                              new AuditInfo(rs.getString("createdBy"),
                                                            rs.getTimestamp("createDate").toLocalDateTime(),
                                                            rs.getString("lastUpdateBy"),
                                                            rs.getTimestamp("lastUpdate").toLocalDateTime()));
                countryList.add(country);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return countryList;
    }

    @Override
    public Country getCountryByID(int countryId) throws NotFoundException {
        Country country=null;
        String query = "SELECT * FROM country WHERE countryId = "+countryId;
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()){
                throw new NotFoundException("Country ID "+countryId+ " not found in database");
            } else {
                //reset cursor position after test for results above
                rs.beforeFirst();
            }
            
            while(rs.next()){
                country = new Country(rs.getInt("countryId"),
                                              rs.getString("country"),
                                              new AuditInfo(rs.getString("createdBy"),
                                                            rs.getTimestamp("createDate").toLocalDateTime(),
                                                            rs.getString("lastUpdateBy"),
                                                            rs.getTimestamp("lastUpdate").toLocalDateTime()));
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return country;
    }

    @Override
    public ArrayList<Country> getCountryByName(String countryName) throws NotFoundException {
        ArrayList<Country> countryList = new ArrayList<>();
        
        String query = "SELECT * FROM country WHERE country = '"+countryName+"'";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()){
                throw new NotFoundException("Counrty "+countryName+ " not found in database");
            } else {
                //reset cursor position after test for results above
                rs.beforeFirst();
            }
            while(rs.next()){
                Country country = new Country(rs.getInt("countryId"),
                                              rs.getString("country"),
                                              new AuditInfo(rs.getString("createdBy"),
                                                            rs.getTimestamp("createDate").toLocalDateTime(),
                                                            rs.getString("lastUpdateBy"),
                                                            rs.getTimestamp("lastUpdate").toLocalDateTime()));
                countryList.add(country);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return countryList;
    }

    @Override
    public void createCountry(Country country) {
        //get next available ID
        int nextNum=0;
        String qryNextNum = "SELECT MAX(countryId) AS countryId FROM country";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(qryNextNum);
            
            while(rs.next()){
                nextNum = rs.getInt("countryId")+1;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        
        String query = "INSERT INTO country (countryId, country, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ("+
                                            nextNum+", '"+
                                            country.getCountryName()+"', '"+
                                            country.getAuditInfo().getCreatedDate()+"', '"+
                                            country.getAuditInfo().getCreatedBy()+"', '"+
                                            country.getAuditInfo().getLastUpdate()+"', '"+
                                            country.getAuditInfo().getLastUpdatedBy()+"')";
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
            country.setCountryID(nextNum); //update object with ID from database
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCountry(Country country){
        

        String query = "UPDATE country SET  country = '"+country.getCountryName()+"', "+
                                            "lastUpdate = '"+LocalDateTime.now()+"', "+
                                            "lastUpdateBy = '"+country.getAuditInfo().getLastUpdatedBy()+"' WHERE countryId = "+ country.getCountryID();
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteCountry(Country country) {
        String query = "DELETE FROM country WHERE countryId= "+country.getCountryID();
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void deleteCountry(int countryID) {
        String query = "DELETE FROM country WHERE countryId = "+countryID;
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
