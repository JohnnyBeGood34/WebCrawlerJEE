/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.Address;
import conf.User;
import java.io.Serializable;
import java.util.List;
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
public class UserManagedBean implements Serializable
  {

    private static final long serialVersionUID = 1L;
    @Inject
    LoginManagedBean loginbean;
    @Inject
    SearchManagedBean searchmanagedbean;
    private User utilisateur;
    private Address adresseUtilisateur;

    private List<User> users;
    // Injection de notre EJB (Session Bean Stateless)

    @EJB
    private UserManager userManager;

    public List<User> getUsers()
      {
        return userManager.getAllUsers();
      }

    // Initialisation de l'entité utilisateur
    public UserManagedBean()
      {

        utilisateur = new User();
        adresseUtilisateur = new Address();
      }

    public void activateOrDeactivateUser(Integer iduser)
      {
        User aUser = userManager.getUserById(iduser);
        if (aUser.getDenied())
          {
            aUser.setDenied(false);
          } else
          {
            aUser.setDenied(true);
          }
        userManager.updateUser(aUser);
      }

    public String statisticsView()
      {
        return "statistics";
      }
    // Méthode d'action appelée lors du clic sur le bouton du formulaire

    // d'inscription
    public void inscrire()
      {
        utilisateur.setDenied(false);
        userManager.createUser(utilisateur);
        userManager.addUserAddress(utilisateur, adresseUtilisateur);
        FacesMessage message = new FacesMessage("Inscription réussie avec succes !");
        FacesContext.getCurrentInstance().addMessage(null, message);

      }

    public void updateUser()
      {
        loginbean.setCurrentUser(userManager.updateUser(loginbean.getCurrentUser()));
        loginbean.setCurrentAddress(userManager.updateUserAddress(loginbean.getCurrentAddress()));
        FacesMessage message = new FacesMessage("Mise à jour réussie avec succes !");
        FacesContext.getCurrentInstance().addMessage(null, message);
      }

    public String myCampaignView()
      {
        String renderedPage = "index";
        if (loginbean.getCurrentUser() != null)
          {
            renderedPage = "userCampaign";
          }
        return renderedPage;
      }

    public User getUtilisateur()
      {
        return utilisateur;
      }

    public Address getAdresseUtilisateur()
      {
        return adresseUtilisateur;
      }

    public String inscriptionView()
      {
        String renderedPage = "index";
        if (loginbean.getCurrentUser() == null)
          {
            renderedPage = "subscription";
          }
        return renderedPage;
      }

    public String myAccountView()
      {
        String renderedPage = "index";
        if (loginbean.getCurrentUser() != null)
          {
            renderedPage = "profile";
          }
        return renderedPage;
      }

    public String backofficeView()
      {
        String renderedPage = "index";
        if (loginbean.isAdmin() != false)
          {
            renderedPage = "backoffice";
          }
        return renderedPage;
      }

    public String indexView()
      {
        return "index";
      }

    public String campaignDetailView()
      {
        String renderedPage = "index";
        if (loginbean.getCurrentUser() != null)
          {
            renderedPage = "campaignDetails";
          }
        return renderedPage;
      }
  }
