/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JCoScheduling.Views;

import JCoScheduling.Models.CustomerObserver;

/**
 *
 * @author virtu
 */
public interface CustomerViewInterface extends CustomerObserver{
 
    public void show();
    
    public void update();
    
    public void close();
}
