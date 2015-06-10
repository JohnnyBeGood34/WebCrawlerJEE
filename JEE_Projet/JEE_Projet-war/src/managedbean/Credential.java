/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author JOHN
 */
@Named(value = "credential")
@RequestScoped
public class Credential
  {

    private String userName;
    private String password;
    /**
     * Creates a new instance of Credential
     */
    public Credential()
      {
      }
    
    @NotNull @Length(min=3,max=50)
    public String getUserName(){
        return userName;
    }
    public void setUserName(String name){
        userName = name;
    }
    
    @NotNull @Length(min=3,max=50)
    public String getPassword(){
        return password;
    }
    public void setPassword(String newPassword){
        password = newPassword;
    }
  }
