/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.MailingCampaign;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JOHN
 */
@Local
public interface MailingCampaignFacadeLocal
  {

    void create(MailingCampaign mailingCampaign);

    void edit(MailingCampaign mailingCampaign);

    void remove(MailingCampaign mailingCampaign);

    MailingCampaign find(Object id);

    List<MailingCampaign> findAll();

    List<MailingCampaign> findRange(int[] range);

    int count();
    
  }
