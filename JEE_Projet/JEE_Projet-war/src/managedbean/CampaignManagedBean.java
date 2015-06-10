/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.MailingCampaign;
import java.util.ArrayList;
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
    @Inject LoginManagedBean loginbean;
    private MailingCampaign mailingCampaign;
    
    @EJB
    CompaignManager campaignManager;
    
    public String creerCampagne(){
        mailingCampaign.setIdUser(loginbean.getCurrentUser().getIdUser());
        campaignManager.createCampaign(mailingCampaign);
        return "mailingPersonnalization?faces-redirect=true";
    }
    /**
     * Get all campaigns fo main user
     * @return 
     */
    public List<MailingCampaign> getCampaigns(){
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
     * @return 
     */
    public String getCampaignView(){
        return "mailing";
    }
    
    public MailingCampaign getMailingCampaign(){
        return mailingCampaign;
    }
    
  }
