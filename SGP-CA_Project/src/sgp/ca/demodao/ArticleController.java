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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class ArticleController implements Initializable, EvidenceWindow {

    @FXML
    private Label lbUsername;
    @FXML
    private Button btnUpdateEvidence;
    @FXML
    private Button btnRemoveEvidence;
    @FXML
    private Button btnCloseWindowEvidenceRequest;
    @FXML
    private CheckBox chBoxImpactAB;
    @FXML
    private Label lbTypeEvidence;
    @FXML
    private TextField txtFieldEvidenceTittle;
    @FXML
    private TextField txtFieldPublicationCountry;
    @FXML
    private TextField txtFieldPublisherDate;
    @FXML
    private TextField txtFieldInvestigationProject;
    @FXML
    private TextField txtFieldStudyDegree;
    @FXML
    private ListView<String> lvIntegrants;
    @FXML
    private ListView<String> lvCollaborators;
    @FXML
    private ListView<String> lvStudents;
    @FXML
    private TextField txtFieldMagazineName;
    @FXML
    private TextField txtFieldMagazineEditorial;
    @FXML
    private TextField txtFieldISnn;
    @FXML
    private TextArea txtAreaIndex;
    @FXML
    private Button btnDownloadDocument;
    @FXML
    private Label lbDocumentName;
    @FXML
    private VBox vbChapterBook;
    @FXML
    private TableView<?> tvChapterBook;
    @FXML
    private TableColumn<?, ?> colTitleChapterBook;
    @FXML
    private TableColumn<?, ?> colRegistrationDateChapterBook;
    @FXML
    private TableColumn<?, ?> colRangePagesChapterBook;
    @FXML
    private HBox hbOptions;

    private Integrant token;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    
    public void showArticleByUrl(String url, Integrant token){
        this.token = token;
    }

    @FXML
    private void updateEvidence(ActionEvent event) {
    }

    @FXML
    private void removeEvidence(ActionEvent event) {
    }

    @FXML
    private void closeWindowEvidenceRequest(ActionEvent event) {
    }


    @FXML
    private void downloadDocument(ActionEvent event) {
    }

    @FXML
    private void observeChapterBookInformation(MouseEvent event) {
    }
    
    @Override
    public String toString(){
        return "Artículo";
    }

    @Override
    public void createWindowAccordingEvidenceType(Evidence evidence, Node graphicElement, Integrant token) {
        if(this.toString().equalsIgnoreCase(evidence.getEvidenceType())){
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("Article.fxml", graphicElement);
            ArticleController controller = loader.getController();
            controller.showArticleByUrl(evidence.getUrlFile(), token);
        }
    }
    
}
