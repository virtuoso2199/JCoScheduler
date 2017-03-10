
import JCoScheduling.DAO.AddressDAO;
import JCoScheduling.DAO.AddressDAOMySQL;
import JCoScheduling.DAO.CityDAO;
import JCoScheduling.DAO.CityDAOMySQL;
import JCoScheduling.DAO.CountryDAO;
import JCoScheduling.DAO.CountryDAOMySQL;
import JCoScheduling.Exceptions.FormatException;
import JCoScheduling.Exceptions.NotFoundException;
import JCoScheduling.Models.Address;
import JCoScheduling.Models.AuditInfo;
import JCoScheduling.Models.City;
import JCoScheduling.Models.Country;
import java.time.LocalDateTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author virtu
 */
public class TestAddrDAO {
    
    public static void main(String args[]){
        
        CountryDAO countryDAO = new CountryDAOMySQL();
        CityDAO cityDAO = new CityDAOMySQL();
        AddressDAO addressDAO = new AddressDAOMySQL();
//        Country USA = new Country("USA");
//        USA.setAuditInfo(new AuditInfo("jbowley",LocalDateTime.now(),"jbowley",LocalDateTime.now()));
//        countryDAO.createCountry(USA, "jbowley");
//        countryDAO.deleteCountry(2, "jbowley");
          try{
            Country USA = countryDAO.getCountryByID(1);
            City burlington = cityDAO.getCityByID(2);
//            burlington.setCityName("Burlington, VT");
//            cityDAO.updateCity(burlington);
//            cityDAO.deleteCity(0);
//            Address work = new Address("40 IDX Drive", "" , burlington, "05403", "8028478474", new AuditInfo("jbowley",LocalDateTime.now(),"jbowley",LocalDateTime.now()));
//            addressDAO.createAddress(work);
            Address home = addressDAO.getAddressByID(4);
//            try{
//                home.setAddrLine1("224 High Street");
//                home.setCity(new City("St Albans",USA,new AuditInfo("jbowley",LocalDateTime.now(),"jbowley",LocalDateTime.now())));
//                home.setPhone("802-343-8968");
//                home.setPostCode("05478");
//            } catch(FormatException ex){
//                ex.printStackTrace();
//            }
//            
//            addressDAO.updateAddress(home);
          } catch(NotFoundException ex){
              ex.printStackTrace();
          }
        for(Address address:addressDAO.getAllAddresses()){
            System.out.println(address.toString());
        }
        
//        AddressDAO addrDAO = new AddressDAOMySQL();
//        if(addrDAO.getAllAddresses().size()<1){
//            System.out.println("No addresses in database!");
//        } else {
//            for(Address address:addrDAO.getAllAddresses()){
//            System.out.println(address.toString());
//        }
//        }
        
    }
    
}
