/**
* @author 
* Last modification date format: 
*/

package sgp.ca.dataaccess;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;



public class FtpClient{
    
    private FTPClient client = new FTPClient();
    
    private final String HOSTNAME = "ftp-josuesa.alwaysdata.net";
    private final String USERNAME = "josuesa_admin";
    private final String PASSWORD = "sgp-caEJJ21teamA_full4k1080p";
    private final String DIRECTORY = "sgpcaFiles";

    public void stablishConnection(){
        try{
            client.connect(HOSTNAME);
            client.login(USERNAME,PASSWORD);
            client.changeWorkingDirectory(DIRECTORY);
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            client.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();
        }catch(IOException ioe){
            Logger.getLogger(FtpClient.class.getName()).log(Level.SEVERE, null, ioe);
        }catch(IllegalStateException ex){
            Logger.getLogger(FtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection(){
        try{
            client.logout();
            client.disconnect();
        }catch(IOException ex){
            Logger.getLogger(FtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String saveFileIntoFilesSystem(String fileSelectedPath, String destinationFileName){
        this.stablishConnection();
        BufferedInputStream fileSelected = null;
        try{
            fileSelected = new BufferedInputStream(new FileInputStream(fileSelectedPath));
            client.storeFile(destinationFileName, fileSelected);
            fileSelected.close();
        }catch(IOException ex){
            Logger.getLogger(FtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.closeConnection();
            return destinationFileName;
        }
    }
    
    public void downloadFileFromFilesSystemByName(String fileName, String directorySelectedPath){
        this.stablishConnection();
        try{
            File localfile = new File(directorySelectedPath + ((char)92) + fileName);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localfile));
            client.retrieveFile(fileName, outputStream);
            outputStream.close();
        }catch(IOException ex){
            Logger.getLogger(FtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.closeConnection();
        }
    }
    
    public void deleteFileFromFilesSystemByName(String fileName){
        this.stablishConnection();
        try{
            client.deleteFile(fileName);
        }catch(IOException ex){
            Logger.getLogger(FtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.closeConnection();
        }
    }
}
