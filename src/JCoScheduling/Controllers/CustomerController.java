/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Controllers;

import JCoScheduling.DAO.CustomerDAO;
import JCoScheduling.DAO.CustomerDAOMySQL;
import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.CustomerModelInterface;
import JCoScheduling.Views.CustomerEditView;
import JCoScheduling.Views.CustomerViewInterface;

/**
 *
 * @author virtu
 */
public class CustomerController implements CustomerControllerInterface{
    private CustomerModelInterface customer;
    private CustomerViewInterface customerView;
    private CustomerDAO customerDAO;
    
    public CustomerController(CustomerModelInterface customer){
        this.customer = customer;
        this.customerDAO = new CustomerDAOMySQL();
    }

    @Override
    public void showCustomerEntryView() {
        this.customerView = new CustomerEditView(this, customer);
        customerView.show();
    }
    
    public void updateCustomer(){
        try{
            customerDAO.updateCustomer(customer);
        } catch (NotFoundException ex){
            
        }
        customerView.update();
    }
    
    
}
