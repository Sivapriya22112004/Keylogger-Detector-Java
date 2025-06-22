import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    public static void sendEmail(String body) {
        try {
            // Load email credentials from config.properties
            Properties config = new Properties();
            FileInputStream input = new FileInputStream("config.properties");
            config.load(input); // ✅ LOAD properties from file

            final String username = config.getProperty("mail.from");
            final String password = config.getProperty("mail.password");
            final String to = config.getProperty("mail.to");

            // Set mail properties
            Properties sessionProps = new Properties();
            sessionProps.put("mail.smtp.auth", "true");
            sessionProps.put("mail.smtp.starttls.enable", "true");
            sessionProps.put("mail.smtp.host", "smtp.gmail.com");
            sessionProps.put("mail.smtp.port", "587");

            // Create a mail session with authenticator
            Session session = Session.getInstance(sessionProps, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // Compose email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Keylogger Detector Alert 🚨");
            message.setText(body);

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (IOException | MessagingException e) {
            System.out.println("Failed to send email.");
            e.printStackTrace();
        }
    }
}
