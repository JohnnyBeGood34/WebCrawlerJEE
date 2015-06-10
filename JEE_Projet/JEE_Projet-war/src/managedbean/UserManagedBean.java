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
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import session.UserManager;

/**
 *
 * @author JOHN
 */
@Named(value = "userManagedBean")
@RequestScoped
public class UserManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject LoginManagedBean loginbean;

    private User utilisateur;
    private Address adresseUtilisateur;

    // Injection de notre EJB (Session Bean Stateless)

    @EJB
    private UserManager userManager;


    // Initialisation de l'entité utilisateur

    public UserManagedBean() {

        utilisateur = new User();
        adresseUtilisateur = new Address();
    }


    // Méthode d'action appelée lors du clic sur le bouton du formulaire

    // d'inscription

    public void inscrire() {
        userManager.createUser( utilisateur );
        userManager.addUserAddress(utilisateur,adresseUtilisateur);
        FacesMessage message = new FacesMessage( "Inscription réussie avec succes !" );
        FacesContext.getCurrentInstance().addMessage( null, message );

    }

    public void updateUser(){
        loginbean.setCurrentUser(userManager.updateUser(loginbean.getCurrentUser()));
        loginbean.setCurrentAddress(userManager.updateUserAddress(loginbean.getCurrentAddress()));
        FacesMessage message = new FacesMessage( "Mise à jour réussie avec succes !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
    }
    
    public User getUtilisateur() {
        return utilisateur;
    }
    
    public Address getAdresseUtilisateur(){
        return adresseUtilisateur;
    }
    
    public String inscriptionView(){
        return "subscription";
    }
    
    public String myAccountView(){
        return "profile";
    }
}
