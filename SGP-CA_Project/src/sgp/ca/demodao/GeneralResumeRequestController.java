/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.GeneralResume;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Lgac;
import sgp.ca.domain.Member;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class GeneralResumeRequestController implements Initializable {

    @FXML
    private HBox hboxGeneralResumeOptions;
    @FXML
    private Button brtnProduction;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnExit;
    @FXML
    private Label lblBodyAcademyName;
    @FXML
    private Label lblBodyAcademyKey;
    @FXML
    private Label lblSubscriptionArea;
    @FXML
    private Label lblAdscriptionUnit;
    @FXML
    private Label lblConsolidationDegree;
    @FXML
    private Label lblRegistrationDate;
    @FXML
    private Label lblLastEvaluationDate;
    @FXML
    private Label lblUserName;
    @FXML
    private TextArea textAreGeneralTarget;
    @FXML
    private TextArea textAreaMission;
    @FXML
    private TextArea textAreaVision;
    @FXML
    private TableView<Lgac> textFieldLhac;
    @FXML
    private TableColumn<Lgac, String> lgacIdentified;
    @FXML
    private TableColumn<Lgac, String> lgacDescription;
    @FXML
    private Button btnAddNewMember;
    @FXML
    private RadioButton chckBoxAvailables;
    @FXML
    private RadioButton chckBoxUnavailables;
    @FXML
    private Button btnSearchMember;
    @FXML
    private TextField fieldFullNameMember;
    @FXML
    private TableView<Integrant> tebleViewIntegrants;
    @FXML
    private TableColumn<Integrant, String> columnParticipationTypeIntegrant;
    @FXML
    private TableColumn<Integrant, String> columnFullNameIntegrant;
    @FXML
    private TableColumn<Integrant, String> columnEmailUVIntegrant;
    @FXML
    private TableColumn<Integrant, String> columnCellPhoneIntegrant;
    @FXML
    private TableView<Collaborator> tableViewCollaborators;
    @FXML
    private TableColumn<Collaborator, String> columnParticipationTypeColllaborator;
    @FXML
    private TableColumn<Collaborator, String> columnFullNameCollaborator;
    @FXML
    private TableColumn<Collaborator, String> columnEmailUVCollaborator;
    @FXML
    private TableColumn<Collaborator, String> columnPhoneCollaborator;
    
    private  Integrant token;
    private final GeneralResumeDAO GENERAL_RESUME_DAO = new GeneralResumeDAO();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private List<TextArea> textAreas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.prepareCollaboratorTable();
        this.prepareIntegratsTable();
        textAreas = Arrays.asList(textAreGeneralTarget, textAreaMission, textAreaVision);
        textAreas.forEach(txtArea -> {txtArea.setWrapText(true);});
    }

    public void showGeneralResume(Integrant integrantToken){
        this.token = integrantToken;
        this.lblUserName.setText(this.token.getFullName());
        GeneralResume generalResume = GENERAL_RESUME_DAO.getGeneralResumeByKey(integrantToken.getBodyAcademyKey());
        this.setGeneralResumeDataIntoInterface(generalResume);
        this.tableViewCollaborators.getItems().addAll(COLLABORATOR_DAO.getMembers(this.token.getBodyAcademyKey()));
        this.tebleViewIntegrants.getItems().addAll(INTEGRANT_DAO.getMembers(this.token.getBodyAcademyKey()));
    }    
    
    private void setGeneralResumeDataIntoInterface(GeneralResume generalResume){
        if(generalResume != null){
            lblBodyAcademyKey.setText(generalResume.getBodyAcademyKey());
            lblBodyAcademyName.setText(generalResume.getBodyAcademyName());
            lblAdscriptionUnit.setText(generalResume.getAscriptionUnit());
            lblConsolidationDegree.setText(generalResume.getConsolidationDegree());
            lblRegistrationDate.setText(generalResume.getRegistrationDate());
            lblSubscriptionArea.setText(generalResume.getAscriptionArea());
            lblLastEvaluationDate.setText(generalResume.getLastEvaluation());
            textAreGeneralTarget.setText(generalResume.getGeneralTarjet());
            textAreaMission.setText(generalResume.getMission());
            textAreaVision.setText(generalResume.getVision());
        }
    }

    @FXML
    private void requestEvidencesList(ActionEvent event) {
    }

    @FXML
    private void editGeneralResume(ActionEvent event) {
        FXMLLoader loader = changeWindow("GeneralResumeEdit.fxml", event);
        GeneralResumeEditController controller = loader.getController();
        controller.showGeneralResumeUpdateForm(this.token);
    }

    @FXML
    private void exit(ActionEvent event) {
        FXMLLoader loader = changeWindow("Start.fxml", event);
        StartController controller = loader.getController();
        controller.receiveIntegrantToken(this.token);
    }

    @FXML
    private void addMember(ActionEvent event) {
        FXMLLoader loader = changeWindow("MemberSelection.fxml", event);
        MemberSelectionController controller = loader.getController();
        controller.receiveResponsibeleToken(token);
    }
    
    @FXML
    private void showIntegrant(MouseEvent event) {
        Integrant integrant = tebleViewIntegrants.getSelectionModel().getSelectedItem();
        if(integrant != null){
            FXMLLoader loader = changeWindow("IntegrantRequest.fxml", event);
            IntegrantRequestController controller = loader.getController();
            controller.showIntegrantByEmail(this.token, integrant.getEmailUV());
        }
    }

    @FXML
    private void showCollaborator(MouseEvent event) {
        Collaborator collaborator = tableViewCollaborators.getSelectionModel().getSelectedItem();
        if(collaborator != null){
            FXMLLoader loader = changeWindow("CollaboratorRequest.fxml", event);
            CollaboratorRequestController controller = loader.getController();
            controller.showCollaboratorByEmail(this.token, collaborator.getEmailUV());
        }
    }

    @FXML
    private void searchMember(ActionEvent event) {
    }
    
    private FXMLLoader changeWindow(String window, Event event){
        Stage stage = new Stage();
        FXMLLoader loader = null;
        try{
            loader = new FXMLLoader(getClass().getResource(window));
            stage.setScene(new Scene((Pane)loader.load()));
            stage.show(); 
            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch(IOException io){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, io);
        } finally {
            return loader;
        }
    }
    
    private void prepareIntegratsTable(){
        columnParticipationTypeIntegrant.setCellValueFactory(new PropertyValueFactory<>("participationType"));
        columnFullNameIntegrant.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        columnEmailUVIntegrant.setCellValueFactory(new PropertyValueFactory<>("emailUV"));
        columnCellPhoneIntegrant.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
    }
    
    private void prepareCollaboratorTable(){
        columnParticipationTypeColllaborator.setCellValueFactory(new PropertyValueFactory<>("participationType"));
        columnFullNameCollaborator.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        columnEmailUVCollaborator.setCellValueFactory(new PropertyValueFactory<>("emailUV"));
        columnPhoneCollaborator.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
    }
    
}
