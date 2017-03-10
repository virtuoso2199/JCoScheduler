/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author M219663
 */
public class User implements UserModelInterface, AuditInfoObserver{
    
    private int userID;
    private String username;
    private String password;
    private int active;
    private AuditInfoModelInterface auditInfo;
    private ArrayList<UserObserver> observers;

    //no arg constructor needed for login screen
    public User() {
        this.username ="Undefined";
        this.userID =-1;
        this.active = 0;
        this.auditInfo = new AuditInfo("UserController",LocalDateTime.now(),"UserController",LocalDateTime.now());
        this.auditInfo.registerObserver(this);
        observers = new ArrayList<>();

    }
    
    //used when userID is not yet known
    public User(String username) {
        this.username =username;
        this.userID =-1;
        this.active = 1;
        this.auditInfo = new AuditInfo("UserController",LocalDateTime.now(),"UserController",LocalDateTime.now());
        this.auditInfo.registerObserver(this);
        observers = new ArrayList<>();

    }

    public User(int userID, String username, String password, int active) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.active = active;
        this.auditInfo = new AuditInfo("UserController",LocalDateTime.now(),"UserController",LocalDateTime.now());
        this.auditInfo.registerObserver(this);
        observers = new ArrayList<>();

        
    }
    
    public User(int userID, String username, String password, int active, AuditInfo auditInfo) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.active = active;
        this.auditInfo = auditInfo;
        this.auditInfo.registerObserver(this);
        observers = new ArrayList<>();

        
    }
        

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
        notifyObservers();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyObservers();

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyObservers();

    }

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
        notifyObservers();

    }

    public AuditInfoModelInterface getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfoModelInterface auditInfo) {
        this.auditInfo = auditInfo;
        notifyObservers();

    }
    
    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", username=" + username + ", password=" + password + ", active=" + active + ", auditInfo=" + auditInfo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.userID;
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userID != other.userID) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public void registerObserver(UserObserver o) {
        observers.add(o);

    }

    @Override
    public void removeObserver(UserObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {

        for(int i=0;i<observers.size();i++){
            observers.get(i).updateUser(this);
        }
    }

    @Override
    public void updateAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }



    
    

   

    
    
    
}
