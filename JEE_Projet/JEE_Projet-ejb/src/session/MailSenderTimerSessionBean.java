/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import conf.FaitReference;
import conf.Mail;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author JOHN
 */
@Singleton
@LocalBean
public class MailSenderTimerSessionBean {

    @EJB
    private MailManager mailManager;
    @PersistenceContext(unitName = "JEE_Projet-ejbPU")
    private EntityManager em;
    private final int DAILY_LIMIT = 20;
    private int count = 0;

    //toute les 30 sec
    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/30")

    public void myTimer() throws MessagingException, EmailException {
        System.out.println("Vous etes dans le timer, il est: " + new Date());

        if (count < DAILY_LIMIT) {

            List<FaitReference> listReferences = new ArrayList<>();
            listReferences = mailManager.getMailsNonDistributed();

            for (FaitReference faitReference : listReferences) {

                String recipient = faitReference.getIdMail().getMessage();
                String subject = faitReference.getIdMail().getObjet();
                String message = faitReference.getIdMail().getMessage();
                
                
                //Pour faire marcher les mail décommenter les 3 lignes suivantes, Par defaut, notre mail est utilisé pr recevoir les mails
                
                
                /*sendMessage("kevjosteph@gmail.com", subject, message);
                faitReference.setDistributed(true);
                updateFaitReference(faitReference);*/

                count++;
            }

            System.out.println(count);
        }
        if (count > DAILY_LIMIT) {
            System.out.println("limite d'envoi atteinte ");
        }
    }

    public void updateFaitReference(FaitReference faitReference) {
        em.merge(faitReference);
    }

    public void sendMessage(String recipient, String subject, String message) throws EmailException {

        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("kevjosteph@gmail.com", "abcd4ABCD"));
        email.setSSLOnConnect(true);
        email.setFrom("kevjosteph@gmail.com");
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(recipient);
        email.send();
    }

    //Tous les jours aï¿½ 00h on reinitialise le compteur aï¿½ 0
    @Schedule(dayOfWeek = "*", month = "*", hour = "00", dayOfMonth = "*", year = "*", minute = "00", second = "00")
    public void resetCount() {
        System.out.println("RESET");
        count = 0;
    }
}
