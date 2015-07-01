/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import conf.Mail;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import session.MailManager;

/**
 *
 * @author Kevin
 */
@WebService(serviceName = "WebServiceMail")
public class WebServiceMail {
    @EJB
    private MailManager mailManager;

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getAllMail")
    public List<Mail> getAllMail() {
        return mailManager.getAllMails();
    }
}
