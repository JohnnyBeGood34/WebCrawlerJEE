/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.FileMail;
import conf.Mail;
import conf.MailingCampaign;
import conf.Searchresults;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import session.CompaignManager;
import session.MailManager;

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
    @Inject
    MailManager mailmanager;
    @EJB
    MailManager mailManager;
    @Inject
    MailManagedSessionBean mailManagedSessionBean;
    private MailingCampaign mailingCampaign;
    private Mail mailForCampaign;
    private FileMail mailFile;

    public void downloadFile()
      {
        File file = new File(getMailFile().getPath());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        //response.setHeader("Content-Disposition", "attachment;filename=file.txt");
        response.setContentLength((int) file.length());
        ServletOutputStream out = null;
        try
          {
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1)
              {
                out.write(buffer);
                out.flush();
              }
            FacesContext.getCurrentInstance().getResponseComplete();
          } catch (IOException err)
          {
            err.printStackTrace();
          } finally
          {
            try
              {
                if (out != null)
                  {
                    out.close();
                  }
              } catch (IOException err)
              {
                err.printStackTrace();
              }
          }
      }

    public FileMail getMailFile()
      {
        return mailmanager.getFileForMail(getMailForCampaign());
      }

    public void setMailFile(FileMail mailFile)
      {
        this.mailFile = mailFile;
      }

    public Mail getMailForCampaign()
      {
        return campaignManager.getMailForCampaign(campaignBean.getMailingCampaign());
      }

    public void setMailForCampaign(Mail mailForCampaign)
      {
        this.mailForCampaign = mailForCampaign;
      }

    @EJB
    CompaignManager campaignManager;

    public String creerCampagne()
      {
        String stringToResult = "mailingPersonnalization?faces-redirect=true";
        
        mailingCampaign.setIdUser(loginbean.getCurrentUser().getIdUser());
        mailingCampaign.setDateEnvoi(new Date());
        mailingCampaign.setIdSearch(searchManagedSessionBean.getSearch().getIdSearch());
        campaignManager.createCampaign(mailingCampaign);

        campaignBean.setMailingcampaign(mailingCampaign);
        
        if(!loginbean.isLoggedIn()){
            stringToResult = "index?faces-redirect=true";
        }
        return stringToResult;
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
                System.out.println("THE IS IN CAMPAIGN : " + sr.getIsInCampaign());
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
        Mail mail = campaignManager.getMailForCampaign(campaignBean.getMailingCampaign());
        if (mail != null)
          {
            mailManagedSessionBean.setMail(mail);
          }else{
            mailManagedSessionBean.setMail(null);
        }
        return "campaignDetails?faces-redirect=true";
      }

  }
