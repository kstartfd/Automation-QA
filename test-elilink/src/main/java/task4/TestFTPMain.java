package task4;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;


/**
 * Created by skort on 30.10.2016.
 */
public class TestFTPMain {


    /**
     * Метод выводит список файлов в папке,
     * если не равно нулю
     * @param files
     */

    private void printNames(String[] files) {
        if (files != null && files.length > 0) {
            for (String aFile: files) {
                System.out.println(aFile);
            }
        }
    }

    /**
     * Метод пробует созадть новую папку на сервере,
     * если создается, то пишет успешно, удаляет папку
     * и выходит с сервера, иначе пишет,
     * что не может создать папку
     *
     * @param ftpClient
     */
    private void createNewFolder(FTPClient ftpClient) {
        String dirToCreate = "upload123";
        boolean created = false;
        try {
            created = ftpClient.makeDirectory(dirToCreate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (created) {
            try {
                System.out.println("Successfully created directory: " + dirToCreate);
                ftpClient.deleteFile(dirToCreate);
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cannot create new folder: " + dirToCreate);
        }
    }

    /**
     * Подключаемся к серверу, выводим список папок на сервере,
     * выводим файлы что находятся в каждой папке,
     * пробуем создать папку на сервере
     * @param server
     * @param user
     * @param pass
     */
    public  void connectToFTP(String server, String user, String pass) {

        int port = 21;
        FTPClient ftpClient = new FTPClient();

        try {

            ftpClient.connect(server, port);


            int replyCode = ftpClient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Connect failed" + replyCode);
                return;
            }

            boolean success = ftpClient.login(user, pass);

            if (!success) {
                System.out.println("Could not login to the server");
                return;
            } else {
                System.out.println("LOGGED IN SERVER");
            }


            String[] files = ftpClient.listNames();
            printNames(files);

            for (String f : files) {
                String[] a = ftpClient.listNames(f);
                System.out.printf("\n");
                printNames(a);
            }

            System.out.println("\n");
            createNewFolder(ftpClient);

        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
        } finally {
            // logs out and disconnects from server
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    System.out.println("LOGOUT FROM SERVER.");
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }



}
