package enviarEmail.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailService {

    // Assuming you are sending email from through gmails smtp
    String host = "smtp.gmail.com";
    
    // Sender's email ID needs to be mentioned
    String from = "emailtestebicicleta@gmail.com";
    
	// Get system properties
    Properties properties = System.getProperties();

    // Setup mail server


	public SendEmailService() {
		
	    properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.port", "465");
	    properties.put("mail.smtp.ssl.enable", "true");
	    properties.put("mail.smtp.auth", "true");


	}
	
	public void send(String to, String emailSubject, String emailText) {
		
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("emailtestebicicleta@gmail.com", "Bicicleta123@");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);
		
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(emailSubject);

            // Now set the actual message
            message.setText(emailText);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
		
	}
	
	

}
