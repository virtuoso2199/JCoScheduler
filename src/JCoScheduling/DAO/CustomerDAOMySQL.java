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
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.CustomerModelInterface;
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
public class CustomerDAOMySQL implements CustomerDAO{
    
     //Database server information
    private Connection conn = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String db = "U03lv6";
    private String url = "jdbc:mysql://52.206.157.109/" + db;
    private String user = "U03lv6";
    private String pass = "53688016198";
    
    public CustomerDAOMySQL(){
        //connect to database when instanced
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<CustomerModelInterface> getAllCustomers() {
        ArrayList<CustomerModelInterface> customerList = new ArrayList<>();
        String qryGetAllCust = "SELECT * FROM customer";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(qryGetAllCust);
            
            
            while(rs.next()){
                AddressDAO addrDAO = new AddressDAOMySQL();
                Address address=null;
                try{
                    address = addrDAO.getAddressByID(rs.getInt("addressId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
                try{
                    Customer customer = new Customer(rs.getInt("cusomterId"),
                                                     rs.getString("customerName"),
                                                     addrDAO.getAddressByID(rs.getInt("addressId")),
                                                     rs.getInt("active"),
                                                     new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                    customerList.add(customer);
                } catch (NotFoundException ex){
                    ex.printStackTrace();
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return customerList;
    }

    @Override
    public CustomerModelInterface getCustomerByID(int customerID) throws NotFoundException {
         Customer customerFromDB = null;
        
        try{
            String query = "SELECT * FROM customer WHERE customerId ="+customerID;
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if(!rs.next()){
                throw new NotFoundException("Customer with ID "+customerID+" not found in the database!");
            } else {
                rs.beforeFirst();
            }
            
            while(rs.next()){
                //get info for Address objects from database before building Customer objects
                Address address=null;
                
                try{
                    address = new AddressDAOMySQL().getAddressByID(rs.getInt("addressId"));
                } catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
                //int addressID, String addrLine1, String addrLine2, City city, String postCode, String phone, AuditInfo auditInfo
                customerFromDB = new Customer(rs.getInt("customerId"),
                                            rs.getString("customerName"),
                                            address,
                                            rs.getInt("active"),
                                            new AuditInfo(rs.getString("createdBy"),
                                                   rs.getTimestamp("createDate").toLocalDateTime(),
                                                   rs.getString("lastUpdateBy"),
                                                   rs.getTimestamp("lastUpdate").toLocalDateTime()));
                
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return customerFromDB;
    }

    @Override
    public ArrayList<CustomerModelInterface> getCustomerByName(String customerName) {
        ArrayList<CustomerModelInterface> customerList = new ArrayList<>();
        String qryGetAllCust = "SELECT * FROM customer WHERE customerName LIKE '"+customerName+"'";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(qryGetAllCust);
            
            
            while(rs.next()){
                AddressDAO addrDAO = new AddressDAOMySQL();
                Address address=null;
                try{
                    address = addrDAO.getAddressByID(rs.getInt("addressId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
                try{
                    Customer customer = new Customer(rs.getInt("cusomterId"),
                                                     rs.getString("customerName"),
                                                     addrDAO.getAddressByID(rs.getInt("addressId")),
                                                     rs.getInt("active"),
                                                     new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                    customerList.add(customer);
                } catch (NotFoundException ex){
                    ex.printStackTrace();
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return customerList;
    }

    @Override
    public ArrayList<CustomerModelInterface> getCustomerByUser(String username) {
        ArrayList<CustomerModelInterface> customerList = new ArrayList<>();
        String qryGetAllCust = "SELECT * FROM customer WHERE createdBy = '"+username+"' OR lastUpdateBy = '"+username+"'";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(qryGetAllCust);
            
            
            while(rs.next()){
                AddressDAO addrDAO = new AddressDAOMySQL();
                Address address=null;
                try{
                    address = addrDAO.getAddressByID(rs.getInt("addressId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
                try{
                    Customer customer = new Customer(rs.getInt("cusomterId"),
                                                     rs.getString("customerName"),
                                                     addrDAO.getAddressByID(rs.getInt("addressId")),
                                                     rs.getInt("active"),
                                                     new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                    customerList.add(customer);
                } catch (NotFoundException ex){
                    ex.printStackTrace();
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return customerList;
    }

    @Override
    public ArrayList<CustomerModelInterface> getCustomerByDateCreated(LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<CustomerModelInterface> customerList = new ArrayList<>();
        String qryGetAllCust = "SELECT * FROM customer WHERE createDate BETWEEN = '"+startDate+"' AND lastUpdateBy = '"+endDate+"'";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(qryGetAllCust);
            
            
            while(rs.next()){
                AddressDAO addrDAO = new AddressDAOMySQL();
                Address address=null;
                try{
                    address = addrDAO.getAddressByID(rs.getInt("addressId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
                try{
                    Customer customer = new Customer(rs.getInt("cusomterId"),
                                                     rs.getString("customerName"),
                                                     addrDAO.getAddressByID(rs.getInt("addressId")),
                                                     rs.getInt("active"),
                                                     new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                    customerList.add(customer);
                } catch (NotFoundException ex){
                    ex.printStackTrace();
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return customerList;
    }

    @Override
    public ArrayList<CustomerModelInterface> getCustomerByDateUpdated(LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<CustomerModelInterface> customerList = new ArrayList<>();
        String qryGetAllCust = "SELECT * FROM customer WHERE lastUpdate BETWEEN = '"+startDate+"' AND lastUpdateBy = '"+endDate+"'";
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(qryGetAllCust);
            
            
            while(rs.next()){
                AddressDAO addrDAO = new AddressDAOMySQL();
                Address address=null;
                try{
                    address = addrDAO.getAddressByID(rs.getInt("addressId"));
                }catch(NotFoundException ex){
                    ex.printStackTrace();
                }
                
                try{
                    Customer customer = new Customer(rs.getInt("cusomterId"),
                                                     rs.getString("customerName"),
                                                     addrDAO.getAddressByID(rs.getInt("addressId")),
                                                     rs.getInt("active"),
                                                     new AuditInfo(rs.getString("createdBy"),rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdateBy"),rs.getTimestamp("lastUpdate").toLocalDateTime()));
                    customerList.add(customer);
                } catch (NotFoundException ex){
                    ex.printStackTrace();
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return customerList;
    }

    @Override
    public void updateCustomer(CustomerModelInterface customer) {
        if(customer.getAddress().getAddressID()==-1){ //no database ID yet assigned
            AddressDAO addrDAO = new AddressDAOMySQL();
            addrDAO.createAddress((Address)customer.getAddress());
        } 
        String query = "UPDATE customer SET  customerName = '"+customer.getCustomerName()+"', "+
                                        "addressId = "+customer.getAddress().getAddressID()+", "+
                                        "active = "+customer.isActiveInd()+", "+
                                        "lastUpdate = '"+customer.getAuditInfo().getLastUpdate()+"', "+
                                        "lastUpdateBy = '"+customer.getAuditInfo().getLastUpdatedBy()+"' WHERE customerId = "+customer.getCustomerID();
        
        try {
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void createCustomer(CustomerModelInterface customer) {
        //get next available cityID from database
        int nextID=0;
        try {
            String qryGetID = "SELECT MAX(customerId) AS customerId FROM customer";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(qryGetID);
            
            while(rs.next()){
                nextID = rs.getInt("customerId")+1;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        if(customer.getAddress().getAddressID()==-1){ //no database ID yet assigned
            AddressDAO addrDAO = new AddressDAOMySQL();
            addrDAO.createAddress((Address)customer.getAddress());
        } else {
            String query = "INSERT INTO customer (customerId, customerName, addressId,active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ("+
                            nextID+", '"+
                            customer.getCustomerName()+"', "+
                            customer.getAddress().getAddressID()+", "+
                            customer.isActiveInd()+", '"+
                            customer.getAuditInfo().getCreatedDate()+"', '"+
                            customer.getAuditInfo().getCreatedBy()+"', '"+
                            customer.getAuditInfo().getLastUpdate()+"', '"+
                            customer.getAuditInfo().getLastUpdatedBy()+"')";
            try{
                Statement stmt = this.conn.createStatement();
                stmt.execute(query);
                customer.setCustomerID(nextID); //update referenced city object with ID from database
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void deleteCustomer(CustomerModelInterface customer) {
       String query = "DELETE FROM customer WHERE customerId = "+customer.getCustomerID();
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void deleteCustomer(int customerID) throws NotFoundException {
         String query = "DELETE FROM customer WHERE customerId = "+customerID;
        try{
            Statement stmt = this.conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    
}
