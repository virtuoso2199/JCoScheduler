/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Country;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public interface CountryDAO {
    
    public ArrayList<Country> getAllCountries();
    
    public Country getCountryByID(int countryID) throws NotFoundException;
    
    public ArrayList<Country> getCountryByName(String countryName) throws NotFoundException; //country names aren't necessarily unique so an ArrayList is returned
    
    public void createCountry(Country country);
    
    public void updateCountry(Country country);
    
    public void deleteCountry(Country country);
    
    public void deleteCountry(int countryID);
}
