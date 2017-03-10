/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Address;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public interface AddressDAO {
    
    public ArrayList<Address> getAllAddresses();
    
    public ArrayList<Address> getAddressesByUser(String username);
    
    public ArrayList<Address> getAddressesByDateCreated(LocalDateTime startDate, LocalDateTime endDate);
    
    public ArrayList<Address> getAddressesByDateUpdated(LocalDateTime startDate, LocalDateTime endDate);
    
    public void createAddress(Address address);
    
    public Address getAddressByID(int addressID) throws NotFoundException;
    
    public void updateAddress(Address address);
    
    public void deleteAddress(Address address) throws NotFoundException;
    
    public void deleteAddress(int addressID) throws NotFoundException;
    
}
