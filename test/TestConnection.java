/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author M219663
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
public class TestConnection {
    public static void main(String[] argv) throws ClassNotFoundException {
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String db = "U03lv6";
        String url = "jdbc:mysql://52.206.157.109/" + db;
        String user = "U03lv6";
        String pass = "53688016198";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connected to database : " + db);
            Statement statement = conn.createStatement();
            
            /*Table Data Dump*/
            
            System.out.println("Contents of City:");
            System.out.println("=================\n");
            String cityQuery = "SELECT * FROM city";
            ResultSet cityResults = statement.executeQuery(cityQuery);
            ResultSetMetaData rsmd = cityResults.getMetaData();
            int columnCount = rsmd.getColumnCount();
            //print columns
            for(int i=1;i<=columnCount;i++){
               System.out.print(rsmd.getColumnName(i)+"\t");
           }
            
            System.out.println();
            //print values
            while(cityResults.next()){
               for(int i=1;i<=columnCount;i++){
                    System.out.print(cityResults.getString(i)+"\t");
                }
               System.out.println();
            }
            
            
            Statement userStatement = conn.createStatement();
            System.out.println("\n\n");
            System.out.println("Contents of User:");
            System.out.println("=================\n");
            String userQuery = "SELECT * FROM user";
            ResultSet userResults = userStatement.executeQuery(userQuery);
            ResultSetMetaData rsmdUser = userResults.getMetaData();
            columnCount = rsmdUser.getColumnCount();
            //print columns
            for(int i=1;i<=columnCount;i++){
               System.out.print(rsmdUser.getColumnName(i)+"\t");
           }
            
            System.out.println();
            //print values
            while(userResults.next()){
               for(int i=1;i<=columnCount;i++){
                    System.out.print(userResults.getString(i)+"\t");
                }
               System.out.println();
            }
            
            
            Statement countryStatement = conn.createStatement();
            System.out.println("\n\n");
            System.out.println("Contents of Country:");
            System.out.println("====================\n");
            String countryQuery = "SELECT * FROM country";
            ResultSet countryResults = countryStatement.executeQuery(countryQuery);
            ResultSetMetaData rsmdCountry = countryResults.getMetaData();
            columnCount = rsmdCountry.getColumnCount();
            //print columns
            for(int i=1;i<=columnCount;i++){
               System.out.print(rsmdCountry.getColumnName(i)+"\t");
           }
            
            System.out.println();
            
            //print values
            while(userResults.next()){
               for(int i=1;i<=columnCount;i++){
                    System.out.print(countryResults.getString(i)+"\t");
                }
               System.out.println();
            }
            
            
            Statement addrStatement = conn.createStatement();
            System.out.println("\n\n");
            System.out.println("Contents of Address:");
            System.out.println("=====================\n");
            String addrQuery = "SELECT * FROM address";
            ResultSet addrResults = addrStatement.executeQuery(addrQuery);
            ResultSetMetaData rsmdAddr = addrResults.getMetaData();
            columnCount = rsmdAddr.getColumnCount();
            //print columns
            for(int i=1;i<=columnCount;i++){
               System.out.print(rsmdAddr.getColumnName(i)+"\t");
           }
            
            System.out.println();
            //print values
            while(userResults.next()){
               for(int i=1;i<=columnCount;i++){
                    System.out.print(addrResults.getString(i)+"\t");
                }
               System.out.println();
            }
           
            
            Statement custStatement = conn.createStatement();
            System.out.println("\n\n");
            System.out.println("Contents of Customer:");
            System.out.println("=====================\n");
            String custQuery = "SELECT * FROM customer";
            ResultSet custResults = custStatement.executeQuery(custQuery);
            ResultSetMetaData rsmdCust = custResults.getMetaData();
            columnCount = rsmdCust.getColumnCount();
            //print columns
            for(int i=1;i<=columnCount;i++){
               System.out.print(rsmdCust.getColumnName(i)+"\t");
           }
            
            System.out.println();
            
            //print values
            while(userResults.next()){
               for(int i=1;i<=columnCount;i++){
                    System.out.print(custResults.getString(i)+"\t");
                }
               
            }
           
            
            Statement apptStatement = conn.createStatement();
            System.out.println("\n\n");
            System.out.println("Contents of Appointment:");
            System.out.println("========================\n");
            String apptQuery = "SELECT * FROM appointment";
            ResultSet apptResults = apptStatement.executeQuery(apptQuery);
            ResultSetMetaData rsmdAppt = apptResults.getMetaData();
            columnCount = rsmdAppt.getColumnCount();
            //print columns
            for(int i=1;i<=columnCount;i++){
               System.out.print(rsmdAppt.getColumnName(i)+"\t");
           }
            
            System.out.println();
            
            //print values
            while(userResults.next()){
               for(int i=1;i<=columnCount;i++){
                    System.out.print(apptResults.getString(i)+"\t");
                }
               System.out.println();
            }
            
            
            Statement incStatement = conn.createStatement();
            System.out.println("\n\n");
            System.out.println("Contents of Increment Types:");
            System.out.println("============================\n");
            String incQuery = "SELECT * FROM incrementtypes";
            ResultSet incResults = incStatement.executeQuery(incQuery);
            ResultSetMetaData rsmdInc = incResults.getMetaData();
            columnCount = rsmdInc.getColumnCount();
            //print columns
            for(int i=1;i<=columnCount;i++){
               System.out.print(rsmdInc.getColumnName(i)+"\t");
           }
            
            System.out.println();
            //print values
            while(userResults.next()){
               for(int i=1;i<=columnCount;i++){
                    System.out.print(incResults.getString(i)+"\t");
                }
               System.out.println();
            }
            
            
            Statement remStatement = conn.createStatement();
            System.out.println("\n\n");
            System.out.println("Contents of Reminder:");
            System.out.println("=====================\n");
            String remQuery = "SELECT * FROM reminder";
            ResultSet remResults = incStatement.executeQuery(remQuery);
            ResultSetMetaData rsmdRem = remResults.getMetaData();
            columnCount = rsmdRem.getColumnCount();
            //print columns
            for(int i=1;i<=columnCount;i++){
               System.out.print(rsmdRem.getColumnName(i)+"\t");
           }
            
            System.out.println();
            
            //print values
            while(userResults.next()){
               for(int i=1;i<=columnCount;i++){
                    System.out.print(remResults.getString(i)+"\t");
                }
               System.out.println();
            }
            
        } catch (SQLException e) {
            System.out.println("SQLException: "+e.getMessage());
            System.out.println("SQLState: "+e.getSQLState());
            System.out.println("VendorError: "+e.getErrorCode());
        }
    }
}
