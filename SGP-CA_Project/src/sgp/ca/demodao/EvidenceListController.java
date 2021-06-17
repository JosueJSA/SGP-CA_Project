/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sgp.ca.businesslogic.EvidenceDAOFactory;
import sgp.ca.businesslogic.ReceptionWorkDAO;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class EvidenceListController implements Initializable {

    @FXML
    private Label lbUserName;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtFieldEvicendeSearch;
    @FXML
    private Button btnAddNewEvidence;
    @FXML
    private TableView<Evidence> tvEvidences;
    @FXML
    private TableColumn<Evidence, String> colEvidenceType;
    @FXML
    private TableColumn<Evidence, String> colEvidenceTittle;
    @FXML
    private TableColumn<Evidence, String> colImpactAB;
    @FXML
    private TableColumn<Evidence, String> colRegistrationResponsible;
    @FXML
    private TableColumn<Evidence, String> colRegistrationDate;
    @FXML
    private TableColumn<Evidence, String> colUrl;

    private final ReceptionWorkDAO RECEPTION_WORK_DAO = new ReceptionWorkDAO();
    private Integrant token;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Evidence> listEvidences = RECEPTION_WORK_DAO.getAllEvidences();
        preprareSchoolingTable();
        tvEvidences.getItems().addAll(listEvidences);
    }
    
    public void showGeneralResumeEvidences(Integrant token){
        this.token = token;
        lbUserName.setText(this.token.getFullName());
    }

    @FXML
    private void searchEvidence(ActionEvent event) {
    }

    @FXML
    private void addEvidence(ActionEvent event) {
    }

    @FXML
    private void showEvidence(MouseEvent event) {
        if(tvEvidences.getSelectionModel().getSelectedItem() != null){
            Evidence evidence = tvEvidences.getSelectionModel().getSelectedItem();
            EvidenceWindowFctory.showSpecificEvidenceWindow(evidence, btnClose, token);
        }
    }
    
    private void preprareSchoolingTable(){
        colEvidenceTittle.setCellValueFactory(new PropertyValueFactory<>("evidenceTitle"));
        colEvidenceType.setCellValueFactory(new PropertyValueFactory<>("evidenceType"));
        colImpactAB.setCellValueFactory(new PropertyValueFactory<>("impactAB"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        colRegistrationResponsible.setCellValueFactory(new PropertyValueFactory<>("registrationResponsible"));
        colUrl.setCellValueFactory(new PropertyValueFactory<>("urlFile"));
    }
    
}
