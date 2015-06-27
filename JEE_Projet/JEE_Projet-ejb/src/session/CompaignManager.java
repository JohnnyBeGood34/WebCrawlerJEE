/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import conf.FaitReference;
import conf.Mail;
import conf.MailingCampaign;
import conf.Search;
import conf.Searchresults;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stef
 */
@Stateless
public class CompaignManager
  {

    @PersistenceContext(unitName = "JEE_Projet-ejbPU")
    private EntityManager em;

    public void persist(Object object)
      {
        em.persist(object);
      }

    public Mail getMailForCampaign(MailingCampaign campaign)
      {
        Query request = em.createNamedQuery("Mail.findByCampaignId");
        request.setParameter("campaignId", campaign);
        return (Mail) request.getSingleResult();
      }

    public void createCampaign(MailingCampaign campaign)
      {
        persist(campaign);
      }

    public MailingCampaign updateCampaign(MailingCampaign campaign)
      {
        return em.merge(campaign);
      }

    public MailingCampaign getCampaignById(Integer campaignId)
      {
        return em.find(MailingCampaign.class, campaignId);
      }

    /*
     Permet d'obtenir toutes les campagnes de mailing
     appartenant à un utilisateur
     @param idUser int L'id de l'utilisateur
     return List<MailingCampaign> La liste des campagnes
     */
    public List<MailingCampaign> getCampaignByUser(int idUser)
      {
        Query request = em.createNamedQuery("MailingCampaign.findByIdUser");
        request.setParameter("idUser", idUser);
        return request.getResultList();
      }

    public List<MailingCampaign> getAllCampaign()
      {
        Query request = em.createNamedQuery("MailingCampaign.findAll");
        return request.getResultList();
      }

    public List<Searchresults> getAllResultsForCampaign(MailingCampaign aCampaign)
      {
        Query request = em.createNamedQuery("Searchresults.findByIdSearch");
        request.setParameter("idSearch", aCampaign);
        return request.getResultList();
      }

    /*public List<FaitReference> getAllMailForCampaign(){
        
     }*/
  }
