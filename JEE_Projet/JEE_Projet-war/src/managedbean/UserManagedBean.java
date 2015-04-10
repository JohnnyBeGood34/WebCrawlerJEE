/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.Address;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import session.UserManager;

/**
 *
 * @author Stef
 */
@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {
    @EJB
    private UserManager userManager;

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
    }
    
    public void createUser(String nom, String prenom, String password, String email,List<Address> adresses){
        userManager.createUser(nom,prenom,password,email,adresses);
    }
}
