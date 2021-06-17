/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sgp.ca.domain.Article;
import sgp.ca.domain.Book;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Prototype;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class EvidenceSelectionController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSelectReceptionWork;
    @FXML
    private Button btnSelectArticle;
    @FXML
    private Button btnSelectPrototype;
    @FXML
    private Button btnSelectBook;
    
    private Integrant token;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void receiveToken(Integrant token){
        this.token = token;
    }

    @FXML
    private void selectCancel(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("EvidenceList.fxml", btnCancel);
        EvidenceListController controller = loader.getController();
        controller.showGeneralResumeEvidences(token);
    }

    @FXML
    private void selectReceptionWork(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("ReceptionWorkForm.fxml", btnCancel);
        ReceptionWorkFormController controller = loader.getController();
        controller.receiveReceptionWorkSaveToken(token);
    }

    @FXML
    private void selectArticle(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("EvidenceEdit.fxml", btnCancel);
        EvidenceEditController controller = loader.getController();
        controller.receiveArticleAndToken(new Article(),token);
    }

    @FXML
    private void selectPrototype(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("EvidenceEdit.fxml", btnCancel);
        EvidenceEditController controller = loader.getController();
        controller.receivePrototypeAndToken(new Prototype(),token);
    }

    @FXML
    private void selectBook(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("EvidenceEdit.fxml", btnCancel);
        EvidenceEditController controller = loader.getController();
        controller.receiveBookAndToken(new Book(),token);
    }
    
}
