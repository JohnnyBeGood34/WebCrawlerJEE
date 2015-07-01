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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    @Inject
    SearchManagedSessionBean searchSessionbean;
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
        boolean isError = false;
        if (userManager.userExists(credentials.getUserName()))
          {
            if (userManager.hasUserGoodPass(credentials.getUserName(), credentials.getPassword()))
              {
                utilisateur = userManager.getUserByEmail(credentials.getUserName());
                if (utilisateur.getDenied())
                  {
                    utilisateur = null;
                    FacesMessage message = new FacesMessage("Votre compte n'est plus actif !");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                  } else
                  {
                    utilisateurAdresse = userManager.getUserAddress(utilisateur);
                  }
              } else
              {
                isError = true;
              }
          } else
          {
            isError = true;
          }
        if (isError)
          {
            FacesMessage message = new FacesMessage("Erreur combinaison Login / mot de passe !");
            FacesContext.getCurrentInstance().addMessage(null, message);
          }
        //return "index?faces-redirect=true";
      }

    public void logOut()
      {
        utilisateur = null;
        utilisateurAdresse = null;
        searchSessionbean.setSearch(null);
      }

    public boolean isLoggedIn()
      {
        return utilisateur != null;
      }

    public boolean isAdmin()
      {
        return utilisateur != null && "Admin".equals(utilisateur.getName()) && "admin@admin.fr".equals(utilisateur.getEmail());
      }

    public User getCurrentUser()
      {
        return utilisateur;
      }

    public void setCurrentUser(User user)
      {
        utilisateur = user;
      }

    public void setCurrentAddress(Address adresse)
      {
        utilisateurAdresse = adresse;
      }

    public Address getCurrentAddress()
      {
        return utilisateurAdresse;
      }
  }
