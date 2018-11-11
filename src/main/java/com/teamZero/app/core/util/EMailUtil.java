package com.teamZero.app.core.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EMailUtil {
    public static void sendMail(String messageDestination, String mailSubject, String mailMessage) throws MessagingException {
        // SMTP Server details
        String smtpServer = "smtp.gmail.com";
        String smtpPort = "587";

        // SMTP Server authentication
        final String username, password;
        username = "project1876798@gmail.com";
        password = "Random1234";

        // Set of persistent properties; used for storing nail server connection.
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", smtpServer);
        prop.put("mail.smtp.port", smtpPort);
        prop.put("mail.smtp.ssl.trust", smtpServer);

        // Create a mail session using the defined username and password
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a new MIME style email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(messageDestination));
        message.setSubject(mailSubject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(mailMessage, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
