/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public class UserDAOMySQL implements UserDAO{
    
    //Database server information
    private static Connection conn = null;
    
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109/U03lv6", "U03lv6", "53688016198");
        }catch(Exception ex){
            ex.printStackTrace();
        }
//        private String driver = "com.mysql.jdbc.Driver";
//        private String db = "U03lv6";
//        private String url = "jdbc:mysql://10.0.0.194/" + db;
//        private String user = "jbowley";
//        private String pass = "Paw52beh!";
    }
    
    
    public UserDAOMySQL(){
        //first, connect to the database
//        try {
//            Class.forName(driver);
//            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
//        } catch(Exception ex){
//            ex.printStackTrace();
//        } 
    }
    
//    public void finalize(){
//        try{
//            conn.close();
//        }catch(SQLException ex){
//            System.out.println("Unable to close database connection.\n");
//            ex.printStackTrace();
//        }
//    }

    @Override
    public ArrayList<User> getAllUsers() {
        //create ArrayList of User objects to return after querying the database
        ArrayList<User> users = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM user;";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                User user = new User();
                user.setUserID(rs.getInt("userId"));
                user.setActive(rs.getInt("active"));
                user.setUsername(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                AuditInfo ai = new AuditInfo(rs.getString("createBy"), rs.getTimestamp("createDate").toLocalDateTime(),rs.getString("lastUpdatedBy"), rs.getTimestamp("lastUpdate").toLocalDateTime());
                users.add(user);
            }
            
            stmt.closeOnCompletion();
            
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return users;
    }

    @Override
    public User getUserByID(int userID) throws NotFoundException {
        User fetchedUser = new User();
        String qryFetchUser = "SELECT * FROM user WHERE userId = "+userID;
        try{
            Statement stmtFetchUser = conn.createStatement();
            ResultSet rsFetchUser = stmtFetchUser.executeQuery(qryFetchUser);
            if(rsFetchUser.wasNull()){
                throw new NotFoundException("User not found in database!");
            } else {
                while(rsFetchUser.next()){
                    fetchedUser.setUserID(userID);
                    fetchedUser.setActive(rsFetchUser.getInt("active"));
                    fetchedUser.setPassword(rsFetchUser.getString("password"));
                    fetchedUser.setUsername(rsFetchUser.getString("userName"));
                    fetchedUser.setAuditInfo(new AuditInfo(
                                                    rsFetchUser.getString("createBy"),
                                                    rsFetchUser.getTimestamp("createDate").toLocalDateTime(),
                                                    rsFetchUser.getString("lastUpdatedBy"),
                                                    rsFetchUser.getTimestamp("lastUpdate").toLocalDateTime()));
                }
                
            }
            
            stmtFetchUser.closeOnCompletion();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return fetchedUser;
    }

    @Override
    public User getUserByName(String username) throws NotFoundException {
        User fetchedUser = new User();
        String qryFetchUser = "SELECT * FROM user WHERE userName = '"+username+"'";
        try{
            Statement stmtFetchUser = conn.createStatement();
            ResultSet rsFetchUser = stmtFetchUser.executeQuery(qryFetchUser);
            if(rsFetchUser.wasNull()){
                throw new NotFoundException("User not found in database!");
            } else {
                while(rsFetchUser.next()){
                    fetchedUser.setUsername(username);
                    fetchedUser.setUserID(rsFetchUser.getInt("userId"));
                    fetchedUser.setActive(rsFetchUser.getInt("active"));
                    fetchedUser.setPassword(rsFetchUser.getString("password"));
                    fetchedUser.setAuditInfo(new AuditInfo(
                                                    rsFetchUser.getString("createBy"),
                                                    rsFetchUser.getTimestamp("createDate").toLocalDateTime(),
                                                    rsFetchUser.getString("lastUpdatedBy"),
                                                    rsFetchUser.getTimestamp("lastUpdate").toLocalDateTime()));
                }
                
            }
            
            stmtFetchUser.closeOnCompletion();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return fetchedUser;
    }

    @Override
    public void createUser(User user, String createUser) {
        
        try {
            //since the primary key doesn't autoincrement, get the next available key
            int nextUserID=0;
            
            Statement stmtGetNextID = conn.createStatement();
            String qryGetNextID = "SELECT MAX(userId) AS userId FROM user;";
            ResultSet rsNextID = stmtGetNextID.executeQuery(qryGetNextID);
            
            //System.out.println("ResultSet was null: "+ rsNextID.wasNull()); //DEBUG ONLY
            
            if (rsNextID.wasNull()){
                nextUserID = 1;
            } else {
                while(rsNextID.next()){
                    nextUserID = rsNextID.getInt("userId")+1;
                    user.setUserID(nextUserID);
                }
            }
            
            stmtGetNextID.closeOnCompletion();
            
            String query = "INSERT INTO user (userId, userName, password,active,createBy,createDate,lastUpdate,lastUpdatedBy) VALUES ("+
                nextUserID+", '"+
                user.getUsername()+"', '"+
                user.getPassword()+"', "+
                user.isActive()+", '"+
                createUser+"', '"+
                LocalDateTime.now()+"', '"+
                LocalDateTime.now()+"', '"+
                createUser+"');";
            
            //System.out.println(query); //DEBUG ONLY
            Statement createStatement = conn.createStatement();
            createStatement.execute(query);
            createStatement.closeOnCompletion();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user, String updateUser) {
        
        try{
            Statement stmtUpdate = conn.createStatement();
            String qryUpdate = "UPDATE user "+
                    "SET username = '"+user.getUsername()+"', "+
                    "password = '"+user.getPassword()+"', "+
                    "active = "+user.isActive()+", "+
                    "lastUpdate ='"+ LocalDateTime.now()+"', "+
                    "lastUpdatedBy ='"+updateUser+"' "+
                    "WHERE userId="+user.getUserID();
            stmtUpdate.execute(qryUpdate);
            stmtUpdate.closeOnCompletion();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user, String deleteUser) {
        String qryDelUser = "UPDATE user SET active = 0 WHERE userId = "+user.getUserID();
        try{
            Statement stmtDelUser = conn.createStatement();
            stmtDelUser.execute(qryDelUser);
            stmtDelUser.closeOnCompletion();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
    
}
