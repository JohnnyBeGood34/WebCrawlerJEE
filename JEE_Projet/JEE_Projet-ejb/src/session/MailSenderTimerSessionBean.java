/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.MessagingException;
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

    private final int DAILY_LIMIT = 20;
    private int count = 0;

    //toute les 30 sec
    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/30")

    public void myTimer() throws MessagingException, EmailException {
        System.out.println("Vous etes dans le timer, il est: " + new Date());


        if (count < DAILY_LIMIT) {

            //sendMessage();

            count++;
            System.out.println(count);
        }
        if (count > DAILY_LIMIT) {
            System.out.println("limite d'envoi atteinte ");
        }
    }

    public void sendMessage() throws EmailException {

        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("kevjosteph@gmail.com", "abcd4ABCD"));
        email.setSSLOnConnect(true);
        email.setFrom("kevjosteph@gmail.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("kevjosteph@gmail.com");
        email.send();
    }

    //Tous les jours a� 00h on reinitialise le compteur a� 0
    @Schedule(dayOfWeek = "*", month = "*", hour = "00", dayOfMonth = "*", year = "*", minute = "00", second = "00")
    public void resetCount() {
        System.out.println("RESET");
        count = 0;
    }
}
