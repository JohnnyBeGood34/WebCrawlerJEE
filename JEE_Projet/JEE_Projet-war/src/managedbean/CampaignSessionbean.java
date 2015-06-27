/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.MailingCampaign;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author JOHN
 */
@Named(value = "campaignSessionbean")
@SessionScoped
public class CampaignSessionbean implements Serializable
  {
    private MailingCampaign mailingCampaign;
    
    /**
     * Creates a new instance of CampaignSessionbean
     */
    public CampaignSessionbean()
      {
      }
    
    
    
    public MailingCampaign getMailingCampaign(){
        return mailingCampaign;
    }

    void setMailingcampaign(MailingCampaign newMailingCampaign)
      {
        mailingCampaign = newMailingCampaign;
      }
  }
