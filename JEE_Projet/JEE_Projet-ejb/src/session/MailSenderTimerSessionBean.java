/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 *
 * @author JOHN
 */
@Singleton
@LocalBean
public class MailSenderTimerSessionBean
  {

    //@PersistenceContext(unitName = "JEE_Projet-ejb")
    private EntityManager em;
    private String SMTP_HOST1;
    private String LOGIN_SMTP1;
    private String SMTP_PORT1;
    private String IMAP_ACCOUNT1;
    private Session session;
    private Transport transport;
    private String PASSWORD_SMTP1;
    private final int DAILY_LIMIT = 20;
    private int count = 0;

    //toute les 30 sec
    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/30")

    public void myTimer() throws MessagingException
      {
        System.out.println("Timer event: " + new Date());

        if (transport == null || session == null)
          {
            init();
          }

        if (count < DAILY_LIMIT)
          {

            sendMessage("coucou sa va ?", "email de test", "littlekev34@hotmail.fr");

            count++;
            System.out.println(count);
          }
        if (count > DAILY_LIMIT)
          {
            System.out.println("limite d'envoi atteinte ");
          }
      }

    public void init() throws javax.mail.NoSuchProviderException, MessagingException
      {
        try
          {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.setProperty("mail.smtp.host", SMTP_HOST1);
            properties.setProperty("mail.smtp.user", LOGIN_SMTP1);
            properties.setProperty("mail.smtp.port", SMTP_PORT1);
            properties.setProperty("mail.from", IMAP_ACCOUNT1);

            session = Session.getInstance(properties);
            transport = session.getTransport("smtp");

            transport.connect(LOGIN_SMTP1, PASSWORD_SMTP1);

          } catch (MessagingException ex)
          {
            Logger.getLogger(MailSenderTimerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

    //Tous les jours à 00h on reinitialise le compteur à 0
    @Schedule(dayOfWeek = "*", month = "*", hour = "00", dayOfMonth = "*", year = "*", minute = "00", second = "00")
    public void resetCount()
      {
        System.out.println("RESET");
        count = 0;
      }

    public void close() throws MessagingException
      {
        if (transport != null)
          {
            transport.close();
          }
      }

    @Asynchronous
    public Future<Boolean> sendMessage(String text, String subject, String destinataire) throws MessagingException
      {

        boolean resultat = true;

        MimeMessage message = new MimeMessage(session);
        message.setText(text);
        message.setSubject(subject);
        message.addRecipients(Message.RecipientType.TO, destinataire);

        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("message envoyé-----------------------------------");
        return new AsyncResult<Boolean>(resultat);
      }

    public void persist(Object object)
      {
        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
         * <persistence-context-ref>
         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
         * <persistence-unit-name>tuveuxquoi-ejbPU</persistence-unit-name>
         * </persistence-context-ref>
         * <resource-ref>
         * <res-ref-name>UserTransaction</res-ref-name>
         * <res-type>javax.transaction.UserTransaction</res-type>
         * <res-auth>Container</res-auth>
         * </resource-ref> */
        try
          {
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
            em.persist(object);
            utx.commit();
          } catch (Exception e)
          {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
          }
      }
  }
