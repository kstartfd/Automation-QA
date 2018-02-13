import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by fd on 27.06.2016.
 */
public class SendEmailMessages {

    private String host = "smtp.yandex.ru";
    private String userEmail;
    private String password;

    /** default constructor
     * @param userEmail the email user
     * @param password the email password
     */
    public SendEmailMessages(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    /**
     *
     * @param text
     * @param topic
     * @param sender
     * @param recipients
     * @throws AddressException
     * @throws MessagingException
     */

    public void sendMessage(String text, String topic, String sender, String[] recipients)
            throws AddressException, MessagingException  {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.imap.ssl.enable", "true");


        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userEmail, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            if (sender != null) {
                message.setFrom(new InternetAddress(sender));
            } else {
                message.setFrom();
            }

            if (recipients.length > 0) {
                for (int i = 0; i < recipients.length; i++) {
                    message.addRecipient(MimeMessage.RecipientType.TO,
                            (Address) new InternetAddress(recipients[i]));
                }
            }

            //Topic
            message.setSubject(topic);

            //Message
            message.setText(text);

            Transport transport = session.getTransport("smtp");

            transport.connect(host, userEmail, password);

            Address[] addresses = new InternetAddress[recipients.length];
            if (recipients.length > 0) {
                for (int i = 0; i < recipients.length; i++) {
                    addresses[i] = new InternetAddress(recipients[i]);
                }
            }

            transport.sendMessage(message, addresses);

            System.out.println("Sent message successfully....");


            copyIntoSent(session, message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param session
     * @param msg
     * @throws MessagingException
     */

    private void copyIntoSent(Session session, Message msg) throws MessagingException {

        Store store = session.getStore("imap");
        store.connect("imap.yandex.ru", userEmail, password);

        Folder folder = (Folder) store.getFolder("Sent");
        if (!folder.exists()) {
            folder.create(Folder.HOLDS_MESSAGES);
        }
        folder.open(Folder.READ_WRITE);

        folder.appendMessages(new Message[]{msg});
    }

}
