/**
* @author 
* Last modification date format: 
*/

package sgp.ca.demodao;

import java.io.File;
import javax.swing.JFileChooser;


public class DialogBox{
    
    private String fileSelectedPath = "";
    private String directorySelectedPath = "";
    private String fileNameSelected = "";

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
    
    public void openDialogFileSelector(){
        JFileChooser chooser = new JFileChooser(); 
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
        int returnVal = chooser.showOpenDialog(chooser); 
        if(returnVal == JFileChooser.APPROVE_OPTION) { 
            this.fileSelectedPath = chooser.getSelectedFile().getAbsolutePath(); 
            this.fileNameSelected = chooser.getName(new File(fileSelectedPath));
        }
    }
    
     public void openDialogDirectorySelector(){
        JFileChooser chooser = new JFileChooser(); 
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        int returnVal = chooser.showOpenDialog(chooser); 
        if(returnVal == JFileChooser.APPROVE_OPTION) { 
            this.directorySelectedPath = chooser.getSelectedFile().getAbsolutePath();
        }
    }
    
}