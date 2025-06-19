import java.io.FileInputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void sendEmail(String messageBody) {
        try {
            Properties config = new Properties();
            config.load(new FileInputStream("../config.properties"));

            final String username = config.getProperty("gmail.username");
            final String password = config.getProperty("gmail.password");
            final String toEmail  = config.getProperty("gmail.to");

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Keylogger Alert: Suspicious Processes Detected");
            message.setText("The following suspicious processes were found:\n\n" + messageBody);

            Transport.send(message);
            System.out.println("Email sent successfully.");

        } catch (Exception e) {
            System.out.println("Failed to send email.");
            e.printStackTrace();
        }
    }
}
