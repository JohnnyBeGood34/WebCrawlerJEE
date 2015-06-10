/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.Address;
import conf.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.UserManager;

/**
 *
 * @author JOHN
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable
  {

    @Inject
    Credential credentials;
    private User utilisateur;
    private Address utilisateurAdresse;
    // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private UserManager userManager;

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean()
      {
      }

    public void login()
      {
        if (userManager.hasUserGoodPass(credentials.getUserName(), credentials.getPassword()))
          {
            utilisateur = userManager.getUserByEmail(credentials.getUserName());
            utilisateurAdresse = userManager.getUserAddress(utilisateur);
          }
      }

    public void logOut()
      {
        utilisateur = null;
        utilisateurAdresse = null;
      }

    public boolean isLoggedIn()
      {
        return utilisateur != null;
      }

    public User getCurrentUser()
      {
        return utilisateur;
      }
    
    
    public void setCurrentUser(User user){
        utilisateur = user;
    }
    
    public void setCurrentAddress(Address adresse){
        utilisateurAdresse = adresse;
    }
    
    public Address getCurrentAddress(){
        return utilisateurAdresse;
    }
  }
