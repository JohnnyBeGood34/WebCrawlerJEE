/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import conf.User;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.UserManager;

/**
 *
 * @author Kevin
 */
@WebService(serviceName = "WebServiceUser")
public class WebServiceUser {
    @EJB
    private UserManager userManager;

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getAllUsers")
    public List<User> getAllUsers() {
        return userManager.getAllUsers();
    }

    /**
     * Web service operation
     * @param iduser
     * @return 
     */
    @WebMethod(operationName = "getUserById")
    public User getUserById(@WebParam(name = "iduser") int iduser) {
        return userManager.getUserById(iduser);
    }

    /**
     * Web service operation
     * @param name
     * @param firstname
     * @param email
     * @param password
     * @param denied
     * @return 
     */
    @WebMethod(operationName = "createUser")
    public boolean createUser(@WebParam(name = "name") String name, @WebParam(name = "firstname") String firstname, @WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "denied") boolean denied) {
        User user = new User();
        user.setName(name);
        user.setFirstname(firstname);
        user.setPass(password);
        user.setEmail(email);
        user.setDenied(denied);
        userManager.createUser(user);
        return true;
    }

    /**
     * Web service operation
     * @param iduser
     * @param firstname
     * @param name
     * @param email
     * @param denied
     * @param password
     * @return 
     */
    @WebMethod(operationName = "updateUser")
    public User updateUser(@WebParam(name = "iduser") int iduser, @WebParam(name = "name") String name, @WebParam(name = "firstname") String firstname, @WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "denied") boolean denied) {
        User user = userManager.getUserById(iduser);
        user.setName(name);
        user.setFirstname(firstname);
        user.setPass(password);
        user.setEmail(email);
        user.setDenied(denied);
        userManager.updateUser(user);
        return user;
    }
    
    
    
    
}
