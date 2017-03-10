/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.DAO;

import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.City;
import java.util.ArrayList;

/**
 *
 * @author M219663
 */
public interface CityDAO {
    
    public ArrayList<City> getAllCities();
    
    public City getCityByID(int ID) throws NotFoundException;
    
    public ArrayList<City> getCityByName(String cityName) throws NotFoundException; //ArraryList returned in case more than one city with the same name exists in db
    
    public void createCity(City city);
    
    public void updateCity(City city);
    
    public void deleteCity(City city);
    
    public void deleteCity(int cityId);
    
}
