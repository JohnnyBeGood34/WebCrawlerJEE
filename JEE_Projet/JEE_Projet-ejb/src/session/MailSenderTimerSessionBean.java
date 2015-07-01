/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import conf.FaitReference;
import conf.FileMail;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author JOHN
 */
@Singleton
@LocalBean
public class MailSenderTimerSessionBean
  {

    @Inject
    MailManager mailManager;
    private final int DAILY_LIMIT = 20;
    private int count = 0;

    //toute les 30 sec
    //@Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/30")

    public void myTimer() throws MessagingException, EmailException
      {
        System.out.println("Vous etes dans le timer, il est: " + new Date());

        if (count < DAILY_LIMIT)
          {

            List<FaitReference> listReferences = new ArrayList<>();
            listReferences = mailManager.getMailsNonDistributed();

            for (FaitReference faitReference : listReferences)
              {

                //Pour faire marcher les mail d�commenter les 3 lignes suivantes, Par defaut, notre mail est utilis� pr recevoir les mails
                //sendMessage(faitReference);
                mailManager.saveMailDelivered(faitReference);
                count++;

                System.out.println("Nombre de mail envoy�: " + count);
              }
          } else
          {
            System.out.println("La limite de " + DAILY_LIMIT + " de mail a �t� ateinte");
          }
      }

    public void sendMessage(FaitReference faitReference) throws EmailException
      {

        String recipient = faitReference.getIdSearchResult().getEmailResult();
        String subject = faitReference.getIdMail().getObjet();
        String message = faitReference.getIdMail().getMessage();

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("kevjosteph@gmail.com", "abcd4ABCD"));
        email.setSSLOnConnect(true);
        email.setFrom("kevjosteph@gmail.com");
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo("kevjosteph@gmail.com");

        if (mailManager.getAllFiles(faitReference) != null)
          {
            List<FileMail> listFile = new ArrayList<>();
            listFile = mailManager.getAllFiles(faitReference);
            for (FileMail fileMail : listFile)
              {
                EmailAttachment attachment = new EmailAttachment();

                attachment.setPath(fileMail.getPath());
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("Picture of John");
                attachment.setName("John");
                email.attach(attachment);
              }
          }
        email.send();

        System.out.println("Le mail a �t� envoy� � : " + recipient);
        System.out.println("avec le sujet : " + subject);
        System.out.println("et le corps du message est : " + message);
      }

    //Tous les jours a� 00h on reinitialise le compteur a� 0
    //@Schedule(dayOfWeek = "*", month = "*", hour = "00", dayOfMonth = "*", year = "*", minute = "00", second = "00")
    public void resetCount()
      {
        System.out.println("RESET");
        count = 0;
      }
  }
