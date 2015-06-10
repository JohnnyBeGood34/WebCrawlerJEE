/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.File;
import conf.Mail;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.MailManager;

/**
 *
 * @author JOHN
 */
@Named(value = "mailManagedbean")
@RequestScoped
public class MailManagedbean
  {
    private Mail mail;
    private File file;
    @Inject LoginManagedBean loginbean;
    @EJB
    MailManager mailManager;
    /**
     * Creates a new instance of MailManagedbean
     */
    public MailManagedbean()
      {
        mail = new Mail();
      }
    
    public Mail getMail(){
        return mail;
    }
    
    public void setMail(Mail newMail){
        mail = newMail;
    }
    
    public void setFile(File newFile){
        file = newFile;
    }
    
    public File getFile(){
        return file;
    }
    
    public void createMail(){
       
    }
  }
