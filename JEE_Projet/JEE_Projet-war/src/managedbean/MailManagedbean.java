/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import conf.FaitReference;
import conf.File;
import conf.Mail;
import conf.Searchresults;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.servlet.http.Part;
import session.CompaignManager;
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
    private Part fileToUpload;
    private String path = "C:\\tmp\\img";
    @Inject
    LoginManagedBean loginbean;
    @Inject
    CampaignSessionbean campaignSessionbean;
    @EJB
    MailManager mailManager;
    
    private CompaignManager campaignManager;
    public Part getFileToUpload()
      {
        return fileToUpload;
      }

    public void setFileToUpload(Part file)
      {
        this.fileToUpload = file;
      }

    /**
     * Creates a new instance of MailManagedbean
     */
    public MailManagedbean()
      {
        mail = new Mail();
        file = new File();
      }

    public Mail getMail()
      {
        return mail;
      }

    public void setMail(Mail newMail)
      {
        mail = newMail;
      }

    public void setFile(File newFile)
      {
        file = newFile;
      }

    public File getFile()
      {
        return file;
      }

    public String createMail()
      {
        this.mail.setCampaignId(campaignSessionbean.getMailingCampaign());
        this.mail.setDistributed(false);
        this.mail.setStatut("undelivered");
        mailManager.createMail(this.mail);
        //Upload the file attached to the mail
        if(fileToUpload != null){
            try
              {
                //Copy file to filesystem
                this.copyFile(getFilename(fileToUpload), fileToUpload.getInputStream());
                this.file.setIdMail(mail);
                this.file.setIsInBody(false);
                this.file.setPath(path+"\\"+getFilename(fileToUpload));
                
                mailManager.createFile(file);
              }
            catch (IOException ex)
              {
                Logger.getLogger(MailManagedbean.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
        //Add all results and mail link
        //Get search results for campaign
        List<Searchresults> resultsForCamapaign = campaignManager.getAllResultsForCampaign(campaignSessionbean.getMailingCampaign());
        for(Searchresults sr : resultsForCamapaign){
            FaitReference reference = new FaitReference();
            reference.setDistributed(false);
            reference.setIdMail(mail);
            reference.setIdSearchResult(sr);
            mailManager.createFaitReference(reference);
        }
        
        //GOTO mailingresume
        return "mailingResume";
      }
    
    private static String getFilename(Part part) {  
        for (String cd : part.getHeader("content-disposition").split(";")) {  
            if (cd.trim().startsWith("filename")) {  
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");  
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }  
        }  
        return null;  
    }  

    
    private void copyFile(String fileName, InputStream input)
      {
        try
          {
            try (OutputStream output = new FileOutputStream(new java.io.File(this.path + "\\" + fileName)))
              {
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = input.read(bytes)) != -1)
                  {
                    output.write(bytes, 0, read);
                  }
                input.close();
                output.flush();
              }

            System.out.println("Nouveau fichier créé " + this.path + "\\" + fileName);
          } catch (IOException e)
          {
            System.out.println(e.getMessage());
          }
      }
    
    /**
     * Used to validate the size of file to upload
     * @param ctx
     * @param comp
     * @param value 
     */
    public void validateFile(FacesContext ctx,
            UIComponent comp,
            Object value)
      {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;
        if (file.getSize() > 1024)
          {
            msgs.add(new FacesMessage("File is too big"));
          }
        if (!msgs.isEmpty())
          {
            throw new ValidatorException(msgs);
          }
      }
  }
