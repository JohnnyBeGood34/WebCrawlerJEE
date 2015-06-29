/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import conf.FaitReference;
import conf.FileMail;
import conf.Mail;
import conf.MailingCampaign;
import conf.Searchresults;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    private MailingCampaign campaign;
    
    public void setCampaign(MailingCampaign aCampaign){
        this.campaign = aCampaign;
    }
    
    public void persist(Object object)
      {
        em.persist(object);
      }

   
    
    public List<Mail> getAllMails()
      {
        Query request = em.createNamedQuery("Mail.findAll");
        return request.getResultList();
      }

    public List<FaitReference> getAllReferences(Mail aMail){
        List<FaitReference> listToReturn = null;
        Query request = em.createNamedQuery("FaitReference.findByIdMail");
        request.setParameter("idMail", aMail);
        if(request.getResultList().size() > 0){
            listToReturn = request.getResultList();
        }
        return listToReturn;
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
   
    public void createMail(Mail mail){
        em.persist(mail);
    }
    
    public void createFile(FileMail file){
        em.persist(file);
    }
    
    public void createFaitReference(FaitReference reference){
        em.persist(reference);
    }

    public FileMail getFileForMail(Mail mailForCampaign)
      {
        FileMail fileToReturn = null;
        if(mailForCampaign != null){
            Query request = em.createNamedQuery("File.finByIdMail");
            request.setParameter("idMail", mailForCampaign);
            fileToReturn = (FileMail)request.getSingleResult();
        }
        return fileToReturn;
      }
  }
