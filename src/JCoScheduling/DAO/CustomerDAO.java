/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Customer;
import JCoScheduling.Models.CustomerModelInterface;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * CustomerDAO is the interface used to define database operations for Customer objects
 *
 * @author M219663
 */
public interface CustomerDAO {
    
    public ArrayList<CustomerModelInterface> getAllCustomers();
    
    public CustomerModelInterface getCustomerByID(int customerID) throws NotFoundException;
    
    public ArrayList<CustomerModelInterface> getCustomerByName(String customerName) throws NotFoundException;
    
    public ArrayList<CustomerModelInterface> getCustomerByUser(String username) throws NotFoundException;
    
    public ArrayList<CustomerModelInterface> getCustomerByDateCreated(LocalDateTime startDate, LocalDateTime endDate);
    
    public ArrayList<CustomerModelInterface> getCustomerByDateUpdated(LocalDateTime startDate, LocalDateTime endDate);
        
    public void updateCustomer(CustomerModelInterface customer) throws NotFoundException;
    
    public void createCustomer(CustomerModelInterface customer);
    
    public void deleteCustomer(CustomerModelInterface customer) throws NotFoundException;
    
    public void deleteCustomer(int customerID) throws NotFoundException;
    
}
