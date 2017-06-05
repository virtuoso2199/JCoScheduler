
import JCoScheduling.DAO.UserDAO;
import JCoScheduling.DAO.UserDAOMySQL;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.User;
import JCoScheduling.Models.UserModelInterface;
import java.time.LocalDateTime;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author virtu
 */
public class TestUserDAO {
    public static void main(String args[]){
        UserModelInterface jonathan = new User();
        jonathan.setActive(1);
        jonathan.setUsername("jbowley");
        jonathan.setPassword("Paw52beh!");
        jonathan.setAuditInfo(new AuditInfo(jonathan.getUsername(),LocalDateTime.now(),jonathan.getUsername(),LocalDateTime.now()));
        
        UserDAO userDAO = new UserDAOMySQL();
        
        userDAO.createUser((User)jonathan,"admin"); //Create SUCCESS!
        userDAO.deleteUser((User)jonathan,"admin"); //Delete SUCCESS!
       try{ //Read by ID SUCCESS!!!!
           jonathan = userDAO.getUserByID(0);
           System.out.println(jonathan.toString());
       }catch(Exception ex){
           ex.printStackTrace();
       }
       
//       try{ Read by username SUCCESS!!
//           jonathan = userDAO.getUserByName("jbowley");
//           System.out.println(jonathan.toString());
//       }catch(Exception ex){
//           ex.printStackTrace();
//       }
       


       try{
//           jonathan = userDAO.getUserByID(0);
//           jonathan.setPassword("X22m3y2k!");
//           userDAO.updateUser(jonathan, "jbowley");
           User instructor = new User();
           instructor.setUsername("mmorgan");
           instructor.setPassword("WGUFTW2017");
           instructor.setActive(1);
           userDAO.createUser(instructor, "jbowley");
           ArrayList<User> userList = userDAO.getAllUsers();
           for(User user:userList){
               System.out.println(user.toString());
           }
           
       }catch(Exception ex){
           ex.printStackTrace();
       }
       
    }
}
