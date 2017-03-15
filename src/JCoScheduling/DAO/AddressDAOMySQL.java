/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.FormatException;
import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Address;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.City;
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
public class AddressDAOMySQL implements AddressDAO{
    
    //Database server information
    private Connection conn = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String db = "U03lv6";
    private String url = "jdbc:mysql://52.206.157.109/" + db;
    private String user = "U03lv6";
    private String pass = "53688016198";
    
    public AddressDAOMySQL(){
        //connect to database when instanced
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Address> getAllAddresses() {
        ArrayList<Address> addressList = new ArrayList<>();
        String qryGetAllAddr = "SELECT * FROM address";
        try {
            Statement stmtGetAddr = this.conn.createStatement();
            ResultSet rsGetAddr = stmtGetAddr.executeQuery(qryGetAllAddr);
            
            
            while(rsGetAddr.next()){
                CityDAO cityDAO = new CityDAOMySQL();
                City city=new City();
                try{
                    city = cityDAO.getCityByID(rsGetAddr.getInt("cityId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                

                Address address = new Address(rsGetAddr.getInt("addressId"),
                                              rsGetAddr.getString("address"),
                                              rsGetAddr.getString("address2"),
                                              city,
                                              rsGetAddr.getString("postalCode"),
                                              rsGetAddr.getString("phone"),
                                              new AuditInfo(rsGetAddr.getString("createdBy"),rsGetAddr.getTimestamp("createDate").toLocalDateTime(),rsGetAddr.getString("lastUpdateBy"),rsGetAddr.getTimestamp("lastUpdate").toLocalDateTime()));
                addressList.add(address);
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return addressList;
    }
    
      @Override
    public Address getAddressByID(int addressID) throws NotFoundException{
        Address addressFromDB = null;
        
        try{
            String query = "SELECT * FROM address WHERE addressId ="+addressID;
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if(!rs.next()){
                throw new NotFoundException("Address with ID "+addressID+" not found in the database!");
            } else {
                rs.beforeFirst();
            }
            
            while(rs.next()){
                //get info for City objects from database before building Address objects
                City city=null;
                
                try{
                    city = new CityDAOMySQL().getCityByID(rs.getInt("cityId"));
                } catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
                //int addressID, String addrLine1, String addrLine2, City city, String postCode, String phone, AuditInfo auditInfo
                addressFromDB = new Address(rs.getInt("addressId"),
                                            rs.getString("address"),
                                            rs.getString("address2"),
                                            city,
                                            rs.getString("postalCode"),
                                            rs.getString("phone"),
                                            new AuditInfo(rs.getString("createdBy"),
                                                   rs.getTimestamp("createDate").toLocalDateTime(),
                                                   rs.getString("lastUpdateBy"),
                                                   rs.getTimestamp("lastUpdate").toLocalDateTime()));
                
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return addressFromDB;
    }


    @Override
    public ArrayList<Address> getAddressesByUser(String username) {
        ArrayList<Address> addressList = new ArrayList<>();
        
        String qryGetAllAddr = "SELECT * FROM address WHERE createdBy ='"+username+"' OR lastUpdateBy = '"+username+"'";
        try {
            Statement stmtGetAddr = this.conn.createStatement();
            ResultSet rsGetAddr = stmtGetAddr.executeQuery(qryGetAllAddr);
            
            while(rsGetAddr.next()){
                 //get info for City objects from database before building Address objects
                City city=null;
                
                try{
                    city = new CityDAOMySQL().getCityByID(rsGetAddr.getInt("cityId"));
                } catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                

                Address address = new Address(rsGetAddr.getInt("addressId"),
                                              rsGetAddr.getString("address"),
                                              rsGetAddr.getString("address2"),
                                              city,
                                              rsGetAddr.getString("postalCode"),
                                              rsGetAddr.getString("phone"),
                                              new AuditInfo(rsGetAddr.getString("createdBy"),rsGetAddr.getTimestamp("createDate").toLocalDateTime(),rsGetAddr.getString("lastUpdateBy"),rsGetAddr.getTimestamp("lastUpdate").toLocalDateTime()));
                addressList.add(address);

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return addressList;
    }

    @Override
    public ArrayList<Address> getAddressesByDateCreated(LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<Address> addressList = new ArrayList<>();
        String qryGetAllAddr = "SELECT * FROM address WHERE createDate BETWEEN ='"+startDate+"' AND '"+endDate+"'";
        try {
            Statement stmtGetAddr = this.conn.createStatement();
            ResultSet rsGetAddr = stmtGetAddr.executeQuery(qryGetAllAddr);
            
            while(rsGetAddr.next()){
                //get info for City objects from database before building Address objects
                City city=null;
                
                try{
                    city = new CityDAOMySQL().getCityByID(rsGetAddr.getInt("cityId"));
                } catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                    
                Address address = new Address(rsGetAddr.getInt("addressId"),
                                              rsGetAddr.getString("address"),
                                              rsGetAddr.getString("address2"),
                                              city,
                                              rsGetAddr.getString("postalCode"),
                                              rsGetAddr.getString("phone"),
                                              new AuditInfo(rsGetAddr.getString("createdBy"),rsGetAddr.getTimestamp("createDate").toLocalDateTime(),rsGetAddr.getString("lastUpdateBy"),rsGetAddr.getTimestamp("lastUpdate").toLocalDateTime()));
                addressList.add(address);

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return addressList;
    }

    @Override
    public ArrayList<Address> getAddressesByDateUpdated(LocalDateTime startDate, LocalDateTime endDate) {
         ArrayList<Address> addressList = new ArrayList<>();
        String qryGetAllAddr = "SELECT * FROM address WHERE lastUpdate BETWEEN ='"+startDate+"' AND '"+endDate+"'";
        try {
            Statement stmtGetAddr = this.conn.createStatement();
            ResultSet rsGetAddr = stmtGetAddr.executeQuery(qryGetAllAddr);
            
            while(rsGetAddr.next()){
                //get info for City objects from database before building Address objects
                City city=null;
                
                try{
                    city = new CityDAOMySQL().getCityByID(rsGetAddr.getInt("cityId"));
                } catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                

                Address address = new Address(rsGetAddr.getInt("addressId"), 
                                              rsGetAddr.getString("address"),
                                              rsGetAddr.getString("address2"),
                                              city,
                                              rsGetAddr.getString("postalCode"),
                                              rsGetAddr.getString("phone"),
                                              new AuditInfo(rsGetAddr.getString("createdBy"),rsGetAddr.getTimestamp("createDate").toLocalDateTime(),rsGetAddr.getString("lastUpdateBy"),rsGetAddr.getTimestamp("lastUpdate").toLocalDateTime()));
                addressList.add(address);

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return addressList;
    }
    

    @Override
    public void createAddress(Address address) {
        //get next available cityID from database
        int nextID=0;
        try {
            String qryGetID = "SELECT MAX(addressId) AS addressId FROM address";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(qryGetID);
            
            while(rs.next()){
                nextID = rs.getInt("addressId")+1;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        if(address.getCityID()==-1){ //no database ID yet assigned
            CityDAO cityDAO = new CityDAOMySQL();
            cityDAO.createCity((City)address.getCity());
        } 
        
        String query = "INSERT INTO address (addressId, address, address2,cityId,postalCode,phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ("+
                        nextID+", '"+
                        address.getAddrLine1()+"', '"+
                        address.getAddrLine2()+"', "+
                        address.getCityID()+", '"+
                        address.getPostCode()+"', '"+
                        address.getPhone()+"', '"+
                        address.getAuditInfo().getCreatedDate()+"', '"+
                        address.getAuditInfo().getCreatedBy()+"', '"+
                        address.getAuditInfo().getLastUpdate()+"', '"+
                        address.getAuditInfo().getLastUpdatedBy()+"')";
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
            address.setAddressID(nextID); //update referenced city object with ID from database
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

  
    @Override
    public void updateAddress(Address address) {
        if(address.getCityID()==-1){ //no database ID yet assigned
            CityDAO cityDAO = new CityDAOMySQL();
            cityDAO.createCity((City)address.getCity());
        } 
        String query = "UPDATE address SET  address = '"+address.getAddrLine1()+"', "+
                                        "address2 = '"+address.getAddrLine2()+"', "+
                                        "cityId = "+address.getCityID()+", "+
                                        "postalCode = '"+address.getPostCode()+"', "+
                                        "phone = '"+address.getPhone()+"', "+
                                        "lastUpdate = '"+address.getAuditInfo().getLastUpdate()+"', "+
                                        "lastUpdateBy = '"+address.getAuditInfo().getLastUpdatedBy()+"' WHERE addressId = "+address.getAddressID();
        
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAddress(Address address) throws NotFoundException{
        String query = "DELETE FROM address WHERE addressId = "+address.getAddressID();
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void deleteAddress(int addressID) throws NotFoundException{
        String query = "DELETE FROM address WHERE addressId = "+addressID;
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
