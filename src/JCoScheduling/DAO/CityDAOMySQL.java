/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.City;
import JCoScheduling.Models.Country;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author virtu
 */
public class CityDAOMySQL implements CityDAO{
    
    //Database server information
    private Connection conn = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String db = "U03lv6";
    private String url = "jdbc:mysql://52.206.157.109/" + db;
    private String user = "U03lv6";
    private String pass = "53688016198";

    public CityDAOMySQL(){
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<City> getAllCities() {
        ArrayList<City> cityList = new ArrayList<>();
        
        try{
            String query = "SELECT * FROM city";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if(!rs.next()){
                System.out.println("No cities found in database!");
            } else {
                rs.beforeFirst();
            }
            
            while(rs.next()){
                //get info for Country objects from database before building City objects
                Country country=new Country();
                
                try{
                    country = new CountryDAOMySQL().getCountryByID(rs.getInt("countryId"));
                } catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                City city = new City(rs.getInt("cityId"),
                                     rs.getString("city"),
                                     country,
                                     new AuditInfo(rs.getString("createdBy"),
                                                   rs.getTimestamp("createDate").toLocalDateTime(),
                                                   rs.getString("lastUpdateBy"),
                                                   rs.getTimestamp("lastUpdate").toLocalDateTime()));
                cityList.add(city);
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return cityList;
    }

    @Override
    public City getCityByID(int ID) throws NotFoundException {
         City cityFromDB = null;
        
        try{
            String query = "SELECT * FROM city WHERE cityId ="+ID;
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if(!rs.next()){
                throw new NotFoundException("City with ID "+ID+" not found in the database!");
            } else {
                rs.beforeFirst();
            }
            
            while(rs.next()){
                //get info for Country objects from database before building City objects
                Country country=null;
                
                try{
                    country = new CountryDAOMySQL().getCountryByID(rs.getInt("countryId"));
                } catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                cityFromDB = new City(rs.getInt("cityId"),
                                     rs.getString("city"),
                                     country,
                                     new AuditInfo(rs.getString("createdBy"),
                                                   rs.getTimestamp("createDate").toLocalDateTime(),
                                                   rs.getString("lastUpdateBy"),
                                                   rs.getTimestamp("lastUpdate").toLocalDateTime()));
                
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return cityFromDB;
    }

    @Override
    public ArrayList<City> getCityByName(String cityName) throws NotFoundException {
        ArrayList<City> cityList = new ArrayList<>();
        
        try{
            String query = "SELECT * FROM city WHERE city ='"+cityName+"'";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if(!rs.next()){
                throw new NotFoundException(cityName+" not found in the database!");
            } else {
                rs.beforeFirst();
            }
            
            while(rs.next()){
                //get info for Country objects from database before building City objects
                Country country=null;
                
                try{
                    country = new CountryDAOMySQL().getCountryByID(rs.getInt("countryId"));
                } catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                City city = new City(rs.getInt("cityId"),
                                     rs.getString("city"),
                                     country,
                                     new AuditInfo(rs.getString("createdBy"),
                                                   rs.getTimestamp("createDate").toLocalDateTime(),
                                                   rs.getString("lastUpdateBy"),
                                                   rs.getTimestamp("lastUpdate").toLocalDateTime()));
                cityList.add(city);
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return cityList;
    }

    @Override
    public void createCity(City city) {
        
        //get next available cityID from database
        int nextID=0;
        try {
            String qryGetID = "SELECT MAX(cityId) AS cityId FROM city";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(qryGetID);
            
            while(rs.next()){
                nextID = rs.getInt("cityId")+1;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        if(city.getCountryID()==-1){ //no database ID yet assigned
            CountryDAO countryDAO = new CountryDAOMySQL();
            countryDAO.createCountry((Country)city.getCountry());
        } 
        
        String query = "INSERT INTO city (cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ("+
                        nextID+", '"+
                        city.getCityName()+"', "+
                        city.getCountryID()+", '"+
                        city.getAuditInfo().getCreatedDate()+"', '"+
                        city.getAuditInfo().getCreatedBy()+"', '"+
                        city.getAuditInfo().getLastUpdate()+"', '"+
                        city.getAuditInfo().getLastUpdatedBy()+"')";
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
            city.setCityID(nextID); //update referenced city object with ID from database
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    @Override
    public void updateCity(City city) {
        String query = "UPDATE city SET city = '"+city.getCityName()+"', "+
                                        "countryId = "+city.getCountryID()+", "+
                                        "lastUpdate = '"+city.getAuditInfo().getLastUpdate()+"', "+
                                        "lastUpdateBy = '"+city.getAuditInfo().getLastUpdatedBy()+"' WHERE cityId = "+city.getCityID();
        
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    @Override
    public void deleteCity(City city) {
        String query = "DELETE FROM city WHERE cityId = "+city.getCityID();
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void deleteCity(int cityID) {
        String query = "DELETE FROM city WHERE cityId = "+cityID;
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
