/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.Mail;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import session.MailManager;

/**
 *
 * @author JOHN
 */
@Named(value = "mailManagedSessionBean")
@SessionScoped
public class MailManagedSessionBean implements Serializable
  {

    private Mail mail;

    public Mail getMail()
      {
        return mail;
      }

    public void setMail(Mail mail)
      {
        this.mail = mail;
      }
    @EJB
    MailManager mailManager;
    /**
     * Creates a new instance of MailManagedSessionBean
     */
    public MailManagedSessionBean()
      {
      }
    
  }
