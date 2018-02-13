package tests.task4;

import org.apache.commons.net.ftp.FTPClient;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import task4.TestFTPMain;

/**
 * Created by skort on 30.10.2016.
 */
public class TestFTP {

    private String server;
    private String user;
    private String pass;

    /**
     * Передаем параметры из xml сервера, логина и пароля
     * @param server
     * @param user
     * @param pass
     */
    @BeforeTest
    @Parameters({"server", "user", "pass"})
    public void readParam(String server, String user, String pass) {
        this.server = server;
        this.user = user;
        this.pass = pass;
    }

    @Test
    public void testConnectionFTP() {
        TestFTPMain testFTPMain = new TestFTPMain();
        testFTPMain.connectToFTP(server, user, pass);
    }
}
