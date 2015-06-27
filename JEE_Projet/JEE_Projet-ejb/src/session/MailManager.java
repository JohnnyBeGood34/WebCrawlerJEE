/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import conf.Mail;
import conf.MailingCampaign;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.mail.*;

/**
 *
 * @author Stef
 */
@Stateless
@LocalBean
public class MailManager
  {

    @PersistenceContext(unitName = "JEE_Projet-ejbPU")
    private EntityManager em;

    public void persist(Object object)
      {
        em.persist(object);
      }

    public List<Mail> getAllMails()
      {
        Query request = em.createNamedQuery("Mail.findAll");
        return request.getResultList();
      }

    /*
     Permet d'obtenir tous les mails non distribués
     @return List<Mail> La liste de tous les mails non distribués
     */
    public List<Mail> getMailsNonDistributed()
      {
        Query request = em.createNamedQuery("Mail.findByDistributed");
        request.setParameter("distributed", false);
        return request.getResultList();
      }
    
    @Schedule(minute="*/10", hour="*")
    public void sendMail(MailingCampaign aCampaign)
      {
            
      }
  }
