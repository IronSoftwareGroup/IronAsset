/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc.is.services;

import com.bc.is.entity.GlobalProperties;
import com.bc.is.entity.Reminder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author bruno
 */
@Stateful
public class ReminderProcessor {

    @EJB
    ReminderFacade ejbReminders;
    @EJB
    GlobalPropertiesFacade ejbProperies;

    @Resource(name = "mail/Default")
    private Session session;

    public ReminderProcessor() {
    }

    public void processReminder() {
        List<Reminder> reminders = getActiveReminders();
        System.out.println("reminders:" + reminders.size());
        for (Iterator<Reminder> it = reminders.iterator(); it.hasNext();) {
            Reminder reminder = it.next();
            verifyReminder(reminder);

        }
    }

    private List<Reminder> getActiveReminders() {
        return ejbReminders.getActive();
    }

    private void verifyReminder(Reminder reminder) {
        boolean reminderSent = false;

        sendReminderMail(reminder);
            //setRemindersToSentStatus();

    }

    private void setRemindersToSentStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendReminderMail(Reminder reminder) {
        System.out.println("Send email");

        String smtpServer = "";
        String from = "";
        String to = "";
        String cc = "";
        String subject = "";
        String body = "";
        int smtpPort = 0;
        List<GlobalProperties> gp = ejbProperies.getPropertiesBySection("EMAIL");
        for (Iterator<GlobalProperties> it = gp.iterator(); it.hasNext();) {
            GlobalProperties globalProperties = it.next();
            if (globalProperties.getGlobalPropertiesPK().getEntry().equals("SMTP_SERVER")) {
                smtpServer = globalProperties.getValue();
            }
            if (globalProperties.getGlobalPropertiesPK().getEntry().equals("SMTP_PORT")) {
                smtpPort = Integer.parseInt(globalProperties.getValue());
            }
            if (globalProperties.getGlobalPropertiesPK().getEntry().equals("SENDER")) {
                from = globalProperties.getValue();
            }
            if (globalProperties.getGlobalPropertiesPK().getEntry().equals("RECEIVER")) {
                to = globalProperties.getValue();
            }
        }

        subject = "new reminder";
        body = "new reminder for you";
        System.out.println(smtpServer + smtpPort + from + to);

        send(smtpServer, smtpPort, to, from, cc, cc, subject, body, reminder);

    }

    private void send(String smtpServer, int smtpPort, String to, String from, String cc, String bcc, String subject, String body, Reminder reminder) {
        try {

            // -- Create a new message --
            Message msg = new MimeMessage(session);
            // -- Set the FROM and TO fields --

            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            // -- We could include CC recipients too --
            if (cc != null) {
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
            }

            if (bcc != null) {
                msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc, false));
            }
            // -- Set the subject and body text --
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
