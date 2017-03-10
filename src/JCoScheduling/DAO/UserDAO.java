/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.User;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public interface UserDAO {
    
    public ArrayList<User> getAllUsers();
    
    public User getUserByID(int userID) throws NotFoundException;
    
    public User getUserByName(String username) throws NotFoundException;
    
    public void createUser(User user, String createUser);
    
    public void updateUser(User user, String updateUser);
    
    public void deleteUser(User user, String deleteUser);
}
