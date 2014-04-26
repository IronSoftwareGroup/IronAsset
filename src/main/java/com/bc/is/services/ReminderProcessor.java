/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc.is.services;

import com.bc.is.entity.Asset;
import com.bc.is.entity.GlobalProperties;
import com.bc.is.entity.Reminder;
import java.text.SimpleDateFormat;
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
    AssetFacade ejbAsset;
    @EJB
    GlobalPropertiesFacade ejbProperies;

    @Resource(name = "mail/Default")
    private Session session;

    public ReminderProcessor() {
    }

    public void processReminder() {
        List<Reminder> reminders = getActiveReminders();
        
        for (Iterator<Reminder> it = reminders.iterator(); it.hasNext();) {
            Reminder reminder = it.next();
            verifyReminder(reminder);
        }
    }

    private List<Reminder> getActiveReminders() {
        return ejbReminders.getActive();
    }

    private void verifyReminder(Reminder reminder) {
        
        
        Asset a = ejbAsset.find(reminder.getReminderPK().getAssetId());
        Date endDate = a.getEndDate();
        Date toDay = new Date();
        long diff = endDate.getTime() - toDay.getTime();
        
        long dayDiff =  diff / (24 * 60 * 60 * 1000);
        
        System.out.println("diff="+dayDiff);
        if(dayDiff ==reminder.getDays()){
            sendReminderMail(reminder);
        }

        
        //
            //setRemindersToSentStatus();

    }

    private void setRemindersToSentStatus(Reminder reminder) {
        reminder.setSent("YES");
        ejbReminders.edit(reminder);
    }

    private void sendReminderMail(Reminder reminder) {
        Asset asset = ejbAsset.find(reminder.getReminderPK().getAssetId());


       
        String from = "";
        String to = "";
        String cc = "";
        String subject = "";
        String body = "";
       
        List<GlobalProperties> gp = ejbProperies.getPropertiesBySection("EMAIL");
        for (Iterator<GlobalProperties> it = gp.iterator(); it.hasNext();) {
            GlobalProperties globalProperties = it.next();
            
            if (globalProperties.getGlobalPropertiesPK().getEntry().equals("SENDER")) {
                from = globalProperties.getValue();
            }
            if (globalProperties.getGlobalPropertiesPK().getEntry().equals("RECEIVER")) {
                to = globalProperties.getValue();
            }
        }

        subject = "Reminder for "+asset.getName();
        body = "This is a reminder for your asset; "+asset.getName()+" "+asset.getDescription()+"/n";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	String date ;
        if(asset.getEndDate()!=null){
        date= sdf.format(asset.getEndDate());
        }
        body = body.concat("The asset exiparation date is :");
      
   
        send(to, from, cc, cc, subject, body);
        setRemindersToSentStatus(reminder);

    }

    private void send(String to, String from, String cc, String bcc, String subject, String body) {
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
