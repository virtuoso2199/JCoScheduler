/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Controllers;

import JCoScheduling.Models.AddressModelInterface;
import JCoScheduling.Models.CustomerModelInterface;
import JCoScheduling.Models.UserModelInterface;
import javafx.collections.ObservableList;

/**
 *
 * @author virtu
 */
public interface CustomerControllerInterface {
    
    public void showCustomerEntryView();
    
    public void showCustomerListView();
    
    public void updateCustomer();
    
    public void createCustomer(CustomerModelInterface customer);
    
    public ObservableList<CustomerModelInterface> getAllCustomers();
    
    public AddressModelInterface buildAddress(String addr1, String addr2, String city, String state, String zip, String country);
    
    public UserModelInterface getUser();
    
}
