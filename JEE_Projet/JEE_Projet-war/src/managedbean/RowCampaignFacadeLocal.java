/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import conf.RowCampaign;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JOHN
 */
@Local
public interface RowCampaignFacadeLocal
  {

    void create(RowCampaign rowCampaign);

    void edit(RowCampaign rowCampaign);

    void remove(RowCampaign rowCampaign);

    RowCampaign find(Object id);

    List<RowCampaign> findAll();

    List<RowCampaign> findRange(int[] range);

    int count();
    
  }
