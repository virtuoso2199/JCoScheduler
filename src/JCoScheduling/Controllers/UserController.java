/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Controllers;

import JCoScheduling.DAO.UserDAO;
import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.User;
import JCoScheduling.Models.UserModelInterface;
import javafx.stage.Stage;

    
    
/**
 *
 * @author virtu
 */
public class UserController implements UserControllerInterface{
    
    private UserDAO userDAO;
    private Stage userView;
    private UserModelInterface user;
    
    public UserController(UserDAO userDAO,Stage userView, UserModelInterface user){
        this.userDAO = userDAO;
        this.userView=userView;
        this.user = user;
    }
    
    public boolean validateLogin(){
        
        try {
            String dbPassword = userDAO.getUserByName(user.getUsername()).getPassword();
            if(user.getPassword()!= null && user.getPassword().equals(dbPassword)){
                //passwords match! Let the user in
                return true;
            } else {
                //passwords don't match! Display error
                return false;
            }
        }catch(NotFoundException ex){
            //user not found, display user not found error
            ex.printStackTrace();
        }
        
        return false; //if we get this far, the user could not be authenticated
    }
    
    
    
    
}
