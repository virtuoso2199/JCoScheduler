/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Controllers;

import JCoScheduling.DAO.AddressDAO;
import JCoScheduling.DAO.AddressDAOMySQL;
import JCoScheduling.DAO.CityDAO;
import JCoScheduling.DAO.CityDAOMySQL;
import JCoScheduling.DAO.CountryDAO;
import JCoScheduling.DAO.CountryDAOMySQL;
import JCoScheduling.DAO.CustomerDAO;
import JCoScheduling.DAO.CustomerDAOMySQL;
import JCoScheduling.Exceptions.FormatException;
import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Address;
import JCoScheduling.Models.AddressModelInterface;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.City;
import JCoScheduling.Models.CityModelInterface;
import JCoScheduling.Models.Country;
import JCoScheduling.Models.CountryModelInterface;
import JCoScheduling.Models.CustomerModelInterface;
import JCoScheduling.Models.UserModelInterface;
import JCoScheduling.Views.CustomerAddView;
import JCoScheduling.Views.CustomerEditView;
import JCoScheduling.Views.CustomerListView;
import JCoScheduling.Views.CustomerViewInterface;
import JCoScheduling.Views.MainWindow;
import java.time.LocalDateTime;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author virtu
 */
public class CustomerController implements CustomerControllerInterface{
    private CustomerModelInterface customer;
    private CustomerViewInterface customerView;
    private CustomerDAO customerDAO;
    private CountryDAO countryDAO;
    private CityDAO cityDAO;
    private AddressDAO addrDAO;
    private UserModelInterface user;
    
    public CustomerController(CustomerModelInterface customer){
        this.customer = customer;
        this.customerDAO = new CustomerDAOMySQL();
        this.addrDAO = new AddressDAOMySQL();
        this.cityDAO = new CityDAOMySQL();
        this.countryDAO = new CountryDAOMySQL();
        this.user = MainWindow.getUser();
        
    }

    @Override
    public void showCustomerEntryView() {
        this.customerView = new CustomerAddView(this, customer, this.user);
        customerView.show();
    }
    
    @Override
    public void showCustomerListView(){
        this.customerView = new CustomerListView(this);
        customerView.show();
    }
    
    @Override
    public void showCustomerEditView(CustomerModelInterface customer){
        this.customerView = new CustomerEditView(this,customer);
        this.customer = customer;
        customerView.show();
    }
    
    public void updateCustomer(CustomerModelInterface customer){
        this.customer = customer;
        try{
            customerDAO.updateCustomer(customer);
        } catch (NotFoundException ex){
            
        }
        customerView.update();
    }
    
    public ObservableList<CustomerModelInterface> getAllCustomers(){
        
        ObservableList<CustomerModelInterface> customers = FXCollections.observableArrayList();
        
        for(CustomerModelInterface customer: customerDAO.getAllCustomers()){
            customers.add(customer);
        }
        
        return customers;
    }

    @Override
    public AddressModelInterface buildAddress(String addr1, String addr2, String city, String state, String zip, String country, String phone) {
        AddressModelInterface customerAddress = new Address();
        CountryModelInterface customerCountry = new Country();
        CityModelInterface customerCity = new City();

        //build components of Address first, checking to see if they exist in the dataabase before creating new ones: Country, City, Address, then Customer
        
        
        try {
            //get first match for country by name. Add logic to handle multiple countries with same name?
            customerCountry = countryDAO.getCountryByName(country).get(0);
        } catch(NotFoundException ex){
            //country doesn't exist in database so create it
            customerCountry.setCountryName(country);
            customerCountry.setAuditInfo(new AuditInfo(this.user.getUsername(),LocalDateTime.now(),this.user.getUsername(),LocalDateTime.now()));
            countryDAO.createCountry((Country)customerCountry); //update DAO objects to use CountryModelInterface
        }
        
        try {
            //get first match for city by name. Add logic to handle multiple cities with same name?
            customerCity = cityDAO.getCityByName(city+", "+state).get(0);
            customerAddress.setCity(customerCity);
        } catch(NotFoundException ex){
            //country doesn't exist in database so create it
            customerCity.setCityName(city+", "+state);
            customerCity.setCountry(customerCountry);
            customerCity.setAuditInfo(new AuditInfo(this.user.getUsername(),LocalDateTime.now(),this.user.getUsername(),LocalDateTime.now()));
            cityDAO.createCity((City)customerCity); //update DAO objects to use CityModelInterface
            customerAddress.setCity(customerCity);
        }
        try{
            customerAddress.setAddrLine1(addr1);
            customerAddress.setAddrLine2(addr2);
            customerAddress.setPostCode(zip);
            customerAddress.setPhone(phone);
        } catch(FormatException ex){
            //handle bad formatting
        }
        //now that Address component objects have been created, add address to the database
        addrDAO.createAddress((Address)customerAddress); //update DAO object to use AddressModelInterface
        
        return customerAddress;
    }
    
    public void createCustomer(CustomerModelInterface customer){
        customer.setAuditInfo(new AuditInfo(user.getUsername(),LocalDateTime.now(),user.getUsername(),LocalDateTime.now()));
        //System.out.println("Customer in Controller: "+customer); //DEBUG ONLY
        customerDAO.createCustomer(customer);
    }
    
    public UserModelInterface getUser(){
        return this.user;
    }
    
    public void deleteCustomer(CustomerModelInterface customer){
        //first, prompt the user to see if they really meant to delete the customer
        Alert deleteConfirm = new Alert(AlertType.CONFIRMATION);
        deleteConfirm.setTitle("Confirm Customer Delete");
        deleteConfirm.setHeaderText("Confirm Customer Deletion");
        deleteConfirm.setContentText("Are you sure you want to delete this customer?");
        customerView.close();
        
        Optional<ButtonType> result = deleteConfirm.showAndWait();
        if (result.get()==ButtonType.OK){
            try{
                customerDAO.deleteCustomer(customer);
                customerView = new CustomerListView(this);
                customerView.show();
            }catch (NotFoundException ex){
                ex.printStackTrace();
            }
        }else {
            //return to edit view; the user clicked cancel
            customerView.show(); 
        }
        
        
    }
    
    
}
