import au.com.bytecode.opencsv.CSVReader;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fd on 01.07.2016.
 */
public class TestSendEmailMessages {

    private String userFrom;
    private String password;
    private String userTo;
    private String message;
    private String topic;
    private String userToUnknownYahooCom;
    private String unknownUser;

    /**
     * Read parameters from testng.xml
     * @param userFrom theskortabeast@tut.by
     * @param password
     * @param userTo skortabeast@tut.by
     * @param message text for a message
     * @param topic  topic for a message
     * @param userToUnknownYahooCom for testSendTextMessageUnknownUsersFromXml method
     * @param unknownUser for testSendTextMessageUnknownUsersFromXml method
     */
    @BeforeTest
    @Parameters ({"userFrom", "password", "userTo", "message", "topic",
            "userToUnknownYahooCom", "unknownUser"})
    private void readParam(String userFrom, String password, String userTo,
                           String message, String topic,
                           String userToUnknownYahooCom, String unknownUser) {

        this.userFrom = userFrom;
        this.password = password;
        this.userTo = userTo;
        this.message = message;
        this.topic = topic;
        this.userToUnknownYahooCom = userToUnknownYahooCom;
        this.unknownUser = unknownUser;
    }

    /**
     * Read param from xml and send
     * message from 1 acc theskortabeast@tut.by
     * to 2 acc skortabeast@tut.by
     *
     */
    @Test
    public void testSendTextMessage() {

        System.out.println("From acc 1 theskortabeast@tut.by to acc 2 skortabeast@tut.by");

        SendEmailMessages sender = new SendEmailMessages(userFrom, password);
        String[] users = {
                 userTo
         };
        try {
            sender.sendMessage(message, topic, userFrom, users );
        } catch (AddressException e) {
            // handle error
        } catch (MessagingException e) {
            // handle error
        }
    }

    /**
     * Read param data from xml
     * for userToUnknownYahooCom,  unknownUser,
     * and send emails from theskortabeast@tut.by
     */
    @Test
    public void testSendTextMessageUnknownUsersFromXml() {

        System.out.println("From acc theskortabeast@tut.by to acc userToUnknownYahooCom, unknownUser");

        SendEmailMessages senderXml = new SendEmailMessages(userFrom, password);

        String[] users = {
                userToUnknownYahooCom,
                unknownUser
        };

        try {
            senderXml.sendMessage(message, topic, userFrom, users );
        } catch (AddressException e) {
            // handle error
        } catch (MessagingException e) {
            // handle error
        }

    }

    /**
     * Read data from cvs file
     * and send a messages from theskortabeast@tut.by
     * Using CVSReader library
     * @throws IOException
     */
    @Test
    public void testSendTextMessageCSV () throws IOException {

        System.out.println("From acc theskortabeast@tut.by to acc from CVS file");

        SendEmailMessages senderCVS = new SendEmailMessages(userFrom, password);

        CSVReader reader = new CSVReader(new FileReader("F:\\Need\\epamtest\\src\\test\\resources\\emails.csv"));
        //Read CSV line by line and use the string array as you want
        String[] users;

        while ((users = reader.readNext()) != null) {
            try {
                senderCVS.sendMessage(message, topic, userFrom, users);
            } catch (AddressException e) {
                // handle error
            } catch (MessagingException e) {
                // handle error
            }

        }
    }

    /**
     * Open MySql connection,
     * select email from emails table,
     * send a messages from theskortabeast@tut.by
     * Using MySQL, Open Server
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testSendTextMessageSQL() throws IOException, SQLException {

        System.out.println("From acc theskortabeast@tut.by to acc from MySQL db");

        SendEmailMessages senderSQL= new SendEmailMessages(userFrom, password);
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String userDB = "mysql";
        String passwordDB = "mysql";

        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName(driver);

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = (Connection) DriverManager.getConnection(url, userDB, passwordDB);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = (Statement) conn.createStatement();

            String sql = "SELECT email FROM emails";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            List<String> listResultFromBD = new ArrayList<String>();
            while(rs.next()){
                String email = rs.getString("email");
                listResultFromBD.add(email);
            }
            String[] users = listResultFromBD.toArray(new String[0]);
            try {
                senderSQL.sendMessage(message, topic, userFrom, users );
            } catch (AddressException e) {
                // handle error
            } catch (MessagingException e) {
                // handle error
            }

            rs.close();
        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        conn.close();
        stmt.close();
    }
}



