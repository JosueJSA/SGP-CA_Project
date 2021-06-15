/**
* @author 
* Last modification date format: 
*/

package sgp.ca.demodao;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sgp.ca.dataaccess.FtpClient;


public class DialogBox{
    
    private String fileSelectedPath = "";
    private String directorySelectedPath = "";
    private String fileNameSelected = "";
    private Stage stage;
    private final FtpClient FTPCLIENT = new FtpClient();

    public DialogBox(Stage stage){
        this.stage = stage;
    }
    
    public DialogBox(){
    }

    public String getFileSelectedPath(){
        this.openDialogFileSelector();
        return fileSelectedPath;
    }

    public String getFileNameSelected(){
        return fileNameSelected;
    }

    public void setFileNameSelected(String fileNameSelected) {
        this.fileNameSelected = fileNameSelected;
    }

    public void setFileSelectedPath(String fileSelectedPath){
        this.fileSelectedPath = fileSelectedPath;
    }

    public String getDirectorySelectedPath(){
        this.openDialogDirectorySelector();
        return directorySelectedPath;
    }

    public void setDirectorySelectedPath(String directorySelectedPath){
        this.openDialogDirectorySelector();
        this.directorySelectedPath = directorySelectedPath;
    }
    
    public String openDialogFileSelector(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select the file to save");
        System.out.println("HolaFile");
        File selectedFile = chooser.showOpenDialog(stage);
        System.out.println("HolaFile");
        this.fileSelectedPath = selectedFile.getAbsolutePath();
        this.fileNameSelected = selectedFile.getName();
        return this.fileNameSelected;
    }
    
     public void openDialogDirectorySelector(){
        System.out.println("HolaDirectory");
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select a destiny directory");;
        File selectedDirectory = chooser.showDialog(stage);
        this.directorySelectedPath = selectedDirectory.getAbsolutePath();
        FTPCLIENT.downloadFileFromFilesSystemByName(fileNameSelected, directorySelectedPath);
    }
    
}