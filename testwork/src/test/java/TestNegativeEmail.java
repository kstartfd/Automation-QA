import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import static org.testng.AssertJUnit.fail;

/**
 * Created by fd on 03.07.2016.
 */
public class TestNegativeEmail {
    private String userFrom;
    private String password;
    private String message;
    private String topic;
    private String wrongEmail;

    /**
     * Read parameters from testng.xml
     * @param userFrom theskortabeast@tut.by
     * @param password
     * @param message text for a message
     * @param topic  topic for a message
     * @param wrongEmail dasdagmail.com
     *
     */
    @BeforeTest
    @Parameters({"userFrom", "password", "wrongEmail", "message", "topic"})
    private void readParam(String userFrom, String password, String wrongEmail, String message,
                           String topic) {
        this.userFrom = userFrom;
        this.password = password;
        this.wrongEmail = wrongEmail;
        this.message = message;
        this.topic = topic;

    }

    /**
     * Read param from xml and send
     * message from 1 acc theskortabeast@tut.by
     * to dasdagmail.com
     *
     */
    @Test
    public void testSendTextMessage() {
        System.out.println("From acc 1 theskortabeast@tut.by to wrong email ");

        SendEmailMessages sender = new SendEmailMessages(userFrom, password);
        String[] users = {
                wrongEmail
        };
        try {
            sender.sendMessage(message, topic, userFrom, users );
        } catch (AddressException e) {
            fail();
        } catch (MessagingException e) {
            fail();
        }
    }


}
