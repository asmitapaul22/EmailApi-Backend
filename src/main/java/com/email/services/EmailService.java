package com.email.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Service
public class EmailService {
    //this method is for sending message with attachment file
    public boolean sendEmailWithAttachment(String message, String subject, String to) {
      boolean flag=false;
       String from="testingemailapi1@gmail.com";
        //variable for gmail host
        String host="smtp.gmail.com";
//get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES"+properties);
//setting important information to properties object

        //host set
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        //ssl-secure socket layer
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");
//STEP-1: To get the session object....
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("testingemailapi1@gmail.com", "Testingemailapi@1");
            }
        });
        session.setDebug(true);
//STEP-2: Compose the message[text,multimedia]
        MimeMessage m = new MimeMessage(session);
        try {
            //from email
            m.setFrom(from);
            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //adding subject to message
            m.setSubject(subject);
            //adding content to message-attachment
            //attachment..file path
            String path="D:\\be 3rd sem notes\\c\\operatorquizupdated.pdf";
            MimeMultipart mimeMultipart = new MimeMultipart();
            //text //file
            MimeBodyPart textMime = new MimeBodyPart();
//            MimeBodyPart fileMime = new MimeBodyPart();
            try {
                textMime.setText(message);
//                File file = new File(path);
//                fileMime.attachFile(file);

                mimeMultipart.addBodyPart(textMime);
//                mimeMultipart.addBodyPart(fileMime);

            }catch (Exception e)
            {
                e.printStackTrace();
            }

            m.setContent(mimeMultipart);

            //send
//STEP-3: Send the message using transport class
            Transport.send(m);
            System.out.println("Sent successfulyy............");
        flag=true;
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Didnt sent successfuully....");
        }
        return flag;
    }

}
