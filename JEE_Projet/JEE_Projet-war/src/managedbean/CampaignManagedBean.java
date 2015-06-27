/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.Mail;
import conf.MailingCampaign;
import conf.Searchresults;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.CompaignManager;

/**
 *
 * @author JOHN
 */
@Named(value = "campaignManagedBean")
@RequestScoped
public class CampaignManagedBean
  {

    private List<MailingCampaign> campaigns;
    @Inject
    LoginManagedBean loginbean;
    @Inject
    CampaignSessionbean campaignBean;
    @Inject
    SearchResultsManagedBean searchResultManagedBean;
    @Inject
    SearchManagedSessionBean searchManagedSessionBean;
    private MailingCampaign mailingCampaign;

    @EJB
    CompaignManager campaignManager;

   
    
    public String creerCampagne()
      {
        mailingCampaign.setIdUser(loginbean.getCurrentUser().getIdUser());
        mailingCampaign.setDateEnvoi(new Date());
        mailingCampaign.setIdSearch(searchManagedSessionBean.getSearch().getIdSearch());
        campaignManager.createCampaign(mailingCampaign);
        
        campaignBean.setMailingcampaign(mailingCampaign);
        return "mailingPersonnalization?faces-redirect=true";
      }

    /**
     * Get all campaigns fo main user
     *
     * @return
     */
    public List<MailingCampaign> getCampaigns()
      {
        return campaignManager.getCampaignByUser(loginbean.getCurrentUser().getIdUser());
      }

    /**
     * Creates a new instance of CampaignManagedBean
     */
    public CampaignManagedBean()
      {
        mailingCampaign = new MailingCampaign();
      }

    /**
     * FOR TESTS ONLY
     *
     * @return
     */
    public String getCampaignView()
      {
        if (searchResultManagedBean.getLocalSearchResults().size() > 1)
          {
            for (Searchresults sr : searchResultManagedBean.getLocalSearchResults())
              {
                System.out.println("THE FUCKING IS IN CAMPAIGN : " + sr.getIsInCampaign());
              }
          }
        return "mailing";
      }

    public MailingCampaign getMailingCampaign()
      {
        return mailingCampaign;
      }

    public String getCampaignDetails(Integer campaignId)
      {
        campaignBean.setMailingcampaign(campaignManager.getCampaignById(campaignId));
        return "campaignDetails?faces-redirect=true";
      }

  }
