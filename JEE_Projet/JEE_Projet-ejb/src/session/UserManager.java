/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import conf.Address;
import conf.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stef
 */
@Stateless
@LocalBean
public class UserManager {
    @PersistenceContext(unitName = "JEE_Projet-ejbPU")
    private EntityManager em;
    
    /*public void createUser(String nom, String prenom,String email, String password,List<Address> addresses) {
        User user=new User(nom,prenom,email,password,addresses);        
        persist(user);//To check, Not sure...
       
    }*/
    
    public void createUser(User user){
        persist(user);
    }

    
    public void addUserAddress(User user,Address address){
      
        address.setIdUser(user);
        persist(address);        
    }
    
    public User updateUser(User user){
        return em.merge(user);
    }
    
    public List<User> getAllUsers(){
        
        Query request = em.createNamedQuery("User.findAll");
        return request.getResultList();
    }
   
    /*
    Check si le mot de passe correspond à celui de l'utilisateur
    @param email String L'email du formulaire rentré pas le user
    @param password String Le mot de passe rentré par le user
    @return boolean True si le pass du user est bon sinon False
    */
    public boolean hasUserGoodPass(String email,String password){
        User user = getUserByEmail(email);
        return user.getPass().equals(password);
    }
    
    
    public User getUserByEmail(String email){
        Query request = em.createNamedQuery("User.findByEmail");
        request.setParameter("email", email);
        return (User) request.getSingleResult();
    }

    public Address getUserAddress(User idUser){
        Query request = em.createNamedQuery("Address.findByUserId");
        request.setParameter("idUser",idUser);
        return (Address)request.getResultList().get(0);
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
}
