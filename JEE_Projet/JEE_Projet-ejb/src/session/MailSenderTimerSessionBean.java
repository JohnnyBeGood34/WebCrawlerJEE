/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import conf.FaitReference;
import conf.FileMail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.mail.MessagingException;
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
    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/30")

    public void myTimer() throws MessagingException, EmailException, ClassNotFoundException, SQLException {
        System.out.println("Vous etes dans le timer, il est: " + new Date());

        if (count < DAILY_LIMIT)
          {

            List<FaitReference> listReferences = new ArrayList<>();
            listReferences = mailManager.getMailsNonDistributed();
            if (listReferences.size() > 0) {
                FaitReference faitReference = listReferences.get(0);
                
                //Pour envoyer des mails et mettre à jour 
                //sendMessage(faitReference);
                //updateMailSend(faitReference);
                count++;
            }

            System.out.println("Nombre de mail envoyé: " + count);
        } else {
            System.out.println("La limite de " + DAILY_LIMIT + " de mail a été ateinte");
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

        System.out.println("Le mail a ï¿½tï¿½ envoyï¿½ ï¿½ : " + recipient);
        System.out.println("avec le sujet : " + subject);
        System.out.println("et le corps du message est : " + message);
      }

    private void updateMailSend(FaitReference faitReference) throws ClassNotFoundException, SQLException {
        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";
        String DB_URL = "jdbc:mysql://localhost:3306/tuveuxquoi?useUnicode=true&characterEncoding=UTF-8";

        //  Database credentials
        String USER = "root";
        String PASS = "abcd4ABCD";

        Connection conn = null;
        Statement stmt = null;
        //STEP 2: Register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        //STEP 3: Open a connection
        System.out.println("Connecting to a selected database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");

        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql = "UPDATE fait_reference "
                + "SET DISTRIBUTED = 1 WHERE ID_ROW_RESULT =  "+faitReference.getIdRowResult();
        stmt.executeUpdate(sql);

    }

    //Tous les jours aï¿½ 00h on reinitialise le compteur aï¿½ 0
    //@Schedule(dayOfWeek = "*", month = "*", hour = "00", dayOfMonth = "*", year = "*", minute = "00", second = "00")
    public void resetCount()
      {
        System.out.println("RESET");
        count = 0;
      }
  }
