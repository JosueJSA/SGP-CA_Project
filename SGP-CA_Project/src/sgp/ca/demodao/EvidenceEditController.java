/**
 * @author estef
 * Last modification date format: 20-05-2021
 */

package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.ArticleDAO;
import sgp.ca.businesslogic.BookDAO;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.EvidenceDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.businesslogic.PrototypeDAO;
import sgp.ca.domain.Article;
import sgp.ca.domain.Book;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Prototype;

public class EvidenceEditController implements Initializable {

    @FXML
    private Label lbWindowTitle;
    @FXML
    private Label lbUsername;
    @FXML
    private Button btnAddEvidence;
    @FXML
    private Button btnUpdateEvidence;
    @FXML
    private Button btnCloseWindow;
    @FXML
    private CheckBox chBoxImpactAB;
    @FXML
    private Label lbTypeEvidence;
    @FXML
    private TextField txtFieldEvidenceTittle;
    @FXML
    private DatePicker dtpPublicationDate;
    @FXML
    private TextField txtFieldPublicationCountry;
    @FXML
    private ComboBox<String> cboBoxInvestigationProject;
    @FXML
    private ComboBox<String> cboBoxStudyDegree;
    @FXML
    private Button btnAddRowIntegrantTable;
    @FXML
    private Button btnRemoveRowIntegrantTable;
    @FXML
    private ComboBox<Integrant> cboBoxIntegrantsName;
    @FXML
    private TableView<Integrant> tvIntegrant;
    @FXML
    private TableColumn<Integrant, String> colIntegrantName;
    @FXML
    private Button btnAddRowCollaboratorTable;
    @FXML
    private Button btnRemoveRowCollaboratorTable;
    @FXML
    private ComboBox<Collaborator> cboBoxCollaboratorsName;
    @FXML
    private TableView<Collaborator> tvCollaborators;
    @FXML
    private TableColumn<Collaborator, String> colCollaboratorName;
    @FXML
    private Tab tabBook;
    @FXML
    private TextField txtFieldPublisher;
    @FXML
    private TextField txtFieldISBN;
    @FXML
    private TextField txtFieldEditionsNumber;
    @FXML
    private Tab tabArticle;
    @FXML
    private TextField txtFieldMagazineName;
    @FXML
    private TextField txtFieldMagazineEditorial;
    @FXML
    private TextField txtFieldISNN;
    @FXML
    private TextField txtFieldIndex;
    @FXML
    private Tab tabPrototype;
    @FXML
    private TextArea txtAreaFeatures;
    @FXML
    private Button btnAddRowStudentsTable;
    @FXML
    private Button btnRemoveRowStudentsTable;
    @FXML
    private ListView<String> lvStudent;
    @FXML
    private TextField txtFieldStudentName;
    @FXML
    private Button btnAddDocument;
    @FXML
    private Button btnReplaceDocument;
    @FXML
    private ImageView imgViewPDFEvidence;
    @FXML
    private Label lbDocumentName;
    
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private EvidenceDAO EVIDENCE_DAO;
    private Evidence evidence;
    private Integrant token;
    private String urlFileEvidence;
    Calendar date = new GregorianCalendar();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setWindowInformationCamps();
        prepareIntegrantTable();
        prepareCollaboratorTable();
        prepareStudentTable();
        
    }    
    
    public void receiveToken(Integrant integrantToken){
        this.token = integrantToken;
        this.lbUsername.setText(token.getFullName());
    }
    
    public void receiveEvidence(Evidence evidence){
        this.urlFileEvidence = evidence.getUrlFile();
        determineTypeEvidence(evidence);
        determineTypeOperation(evidence);
        
    }
    
    private void setEvidenceInformationModification(){
        this.txtFieldEvidenceTittle.setText(evidence.getEvidenceTitle());
        LocalDate publicationDate = LocalDate.parse(evidence.getPublicationDate());
        this.dtpPublicationDate.setValue(publicationDate);
        this.txtFieldPublicationCountry.setText(evidence.getCountry());
        this.cboBoxInvestigationProject.setValue(evidence.getProjectName());
        this.cboBoxStudyDegree.setValue(evidence.getStudyDegree());
    }
    
    private void determineTypeEvidence(Evidence evidence){
        if(evidence instanceof Book){
            this.evidence = new Book();
            this.EVIDENCE_DAO = new BookDAO();
            this.lbTypeEvidence.setText("Libro");
            this.tabBook.setDisable(false);
        }
        if(evidence instanceof Prototype){
            this.evidence = new Prototype();
            this.EVIDENCE_DAO = new PrototypeDAO();
            this.lbTypeEvidence.setText("Prototipo");
            this.tabPrototype.setDisable(false);
            
        }
        if(evidence instanceof Article){
            this.evidence = new Article();
            this.EVIDENCE_DAO = new ArticleDAO();
            this.lbTypeEvidence.setText("Artículo");
            this.tabArticle.setDisable(false);
        }
    }
    
    private void determineTypeOperation(Evidence evidence){
        if(evidence.getUrlFile() == null){
            this.btnAddEvidence.setVisible(true);
        }else{
            this.evidence = evidence;
            this.btnUpdateEvidence.setVisible(true);
            modifyWindowForModification();
            setEvidenceInformationModification();
            evidenceTypeInformationModification();
            prepareIntegrantTable();
            prepareCollaboratorTable();
            prepareStudentTable();
        }
    }
    
    private void evidenceTypeInformationModification(){
        if(evidence instanceof Book){
            this.txtFieldPublisher.setText(((Book)evidence).getPublisher());
            this.txtFieldEditionsNumber.setText(((Book)evidence).getEditionsNumber() + "");
            this.txtFieldISBN.setText(((Book)evidence).getIsbn() + "");
        }
        if(evidence instanceof Prototype){
            this.txtAreaFeatures.setText(((Prototype) evidence).getFeatures());
        }
        if(evidence instanceof Article){
            this.txtFieldMagazineName.setText(((Article) evidence).getMagazineName());
            this.txtFieldMagazineEditorial.setText(((Article) evidence).getMagazineEditorial());
            this.txtFieldISNN.setText(((Article) evidence).getIsnn() + "");
            this.txtFieldIndex.setText(((Article) evidence).getIndex() + "");
        }
    }
    
    private void setWindowInformationCamps(){
        this.cboBoxCollaboratorsName.setItems(makeitemsCollaboratorsListForComboBox());
        this.cboBoxIntegrantsName.setItems(makeitemsIntegrantsListForComboBox());
        this.cboBoxInvestigationProject.setItems(makeitemsProjectListForComboBox());
        this.cboBoxStudyDegree.setItems(makeitemsStudiesDegreeListForComboBox());
    }
    
    private void modifyWindowForModification(){
        this.lbWindowTitle.setText("MODIFICAR PRODUCCIÓN");
        this.lbDocumentName.setText(evidence.getEvidenceTitle());
        this.lbDocumentName.setVisible(true);
        this.imgViewPDFEvidence.setVisible(true);
        this.btnReplaceDocument.setVisible(true);
        this.btnAddDocument.setVisible(false);
        this.btnUpdateEvidence.setVisible(true);
    }
    
    @FXML
    private void addDocument(ActionEvent event) {
        DialogBox dialogBox = new DialogBox();
        this.lbDocumentName.setText(dialogBox.openDialogFileSelector());
        this.lbDocumentName.setVisible(true);
        this.imgViewPDFEvidence.setVisible(true);
        this.btnAddDocument.setVisible(false);
        this.btnReplaceDocument.setVisible(true);
    }
    
    private void validateEvidenceInformation() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(this.txtFieldEvidenceTittle, 5, 80);
        ValidatorForm.checkNotEmptyDateField(dtpPublicationDate);
        ValidatorForm.chechkAlphabeticalField(this.txtFieldPublicationCountry, 3, 90);
        ValidatorForm.isComboBoxSelected(this.cboBoxInvestigationProject);
        ValidatorForm.isComboBoxSelected(this.cboBoxStudyDegree);
    }
    
    private void validateBookInformation() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(this.txtFieldPublisher, 2, 30);
        ValidatorForm.isIntegerNumberData(this.txtFieldEditionsNumber, 3);
        ValidatorForm.isNumberData(this.txtFieldISBN, 13);
    }
    
    private void validateArticleInformation() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(this.txtFieldMagazineName, 1, 100);
        ValidatorForm.chechkAlphabeticalField(this.txtFieldMagazineEditorial, 1, 100);
        ValidatorForm.isNumberData(this.txtFieldISNN, 13);
        ValidatorForm.isIntegerNumberData(this.txtFieldIndex, 11);
    }
    
    private void getOutEvidenceInformation(){
        this.evidence.setEvidenceTitle(this.txtFieldEvidenceTittle.getText());
        this.evidence.setCountry(this.txtFieldPublicationCountry.getText());
        this.evidence.setPublicationDate(this.dtpPublicationDate.toString());
        
        if(this.chBoxImpactAB.isSelected()){
            this.evidence.setImpactAB(true);
        }else{
            this.evidence.setImpactAB(false);
        }
        
        this.evidence.setRegistrationResponsible(token.getRfc());
        this.evidence.setStudyDegree(this.cboBoxStudyDegree.getSelectionModel().getSelectedItem());
        this.evidence.setProjectName(this.cboBoxInvestigationProject.getSelectionModel().getSelectedItem());
        this.evidence.setEvidenceType(this.lbTypeEvidence.getText());
    }
    
    private void getOutBookInformation(){
        ((Book)this.evidence).setPublisher(this.txtFieldPublisher.getText());
        
        int editionNumber= Integer.parseInt(this.txtFieldEditionsNumber.getText());
        ((Book)this.evidence).setEditionsNumber(editionNumber);
        
        double isbn = Double.parseDouble(this.txtFieldISBN.getText());
        ((Book)this.evidence).setIsbn(isbn);
    }
    
    private void getOurArticleInformation(){
        ((Article)this.evidence).setMagazineName(this.txtFieldMagazineName.getText());
        ((Article)this.evidence).setMagazineEditorial(this.txtFieldMagazineEditorial.getText());
        
        double isnn = Double.parseDouble(this.txtFieldISNN.getText());
        ((Article)this.evidence).setIsnn(isnn);
        
        int index = Integer.parseInt(this.txtFieldISNN.getText());
        ((Article)this.evidence).setIndex(index);
    }
    
    

    @FXML
    private void addEvidence(ActionEvent event) {
        try{
            this.validateEvidenceInformation();
            this.getOutEvidenceInformation();
            this.evidence.setRegistrationDate(
                date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DAY_OF_MONTH)
            );
            if(evidence instanceof Book){
                this.validateBookInformation();
                this.getOutBookInformation();
            }
            if(evidence instanceof Article){
                this.validateArticleInformation();
                this.getOurArticleInformation();
            }
            if(evidence instanceof Prototype){
                ValidatorForm.chechkAlphabeticalArea(this.txtAreaFeatures, 1, 1000);
                ((Prototype)this.evidence).setFeatures(this.txtAreaFeatures.getText());
            }
            if(EVIDENCE_DAO.addNewEvidence(this.evidence)){
                AlertGenerator.showInfoAlert(event, "Evidencia registrada con éxito");
            }else{
                AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de ponerse en contacto con sopoerte técnico");
            }
//            FXMLLoader loader = changeWindow("MeetingHistory.fxml", event); Informacion de la ventana lista de evidencias
//            MeetingHistoryController controller = loader.getController();
//            controller.receiveToken(token);
        }catch(InvalidFormException ex){
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }

    @FXML
    private void addRowCollaboratorTable(ActionEvent event) {
        try{
            ValidatorForm.isComboBoxSelectedCollaborator(cboBoxCollaboratorsName);
            Collaborator collaborator = this.cboBoxCollaboratorsName.getValue();
            this.evidence.getCollaborators().add(collaborator);
            this.tvCollaborators.setItems(makeitemsCollaboratorsListForTable());
            this.cboBoxCollaboratorsName.setValue(null);
        }catch(InvalidFormException ex){
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }

    @FXML
    private void addRowIntegrantTable(ActionEvent event) {
        try{
            ValidatorForm.isComboBoxSelectedIntegrant(cboBoxIntegrantsName);
            Integrant integrant = this.cboBoxIntegrantsName.getValue();
            this.evidence.getIntegrants().add(integrant);
            this.tvIntegrant.setItems(makeitemsIntegrantsListForTable());
            this.cboBoxIntegrantsName.setValue(null);
        }catch(InvalidFormException ex){
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }

    @FXML
    private void addRowStudentsTable(ActionEvent event) {
        try{
            ValidatorForm.chechkAlphabeticalField(this.txtFieldStudentName, 7, 50);
            String studentName = this.txtFieldStudentName.getText();
            this.evidence.getStudents().add(studentName);
            this.lvStudent.setItems(makeitemsStudentsListForTable());
            this.txtFieldStudentName.clear();
        }catch(InvalidFormException ex){
             AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Optional<ButtonType> action = AlertGenerator.showConfirmacionAlert(event,
            "¿Seguro que desea salir? No se guardará la información");
        if(action.get() == ButtonType.OK){
            if(this.urlFileEvidence == null){
    //            FXMLLoader loader = this.changeWindow("Name.fxml", event); Lista de evidencias
    //            ChapterBookEditController controller = loader.getController();
            }else{
               FXMLLoader loader = this.changeWindow("EvidenceRequest.fxml", event);
               EvidenceRequestController controller = loader.getController();
               controller.receiveEvidence(this.evidence);
               controller.receiveToken(token);
            }

        }
    }

    @FXML
    private void removeRowCollaboratorTable(ActionEvent event) {
        Collaborator collaboratorRemove = this.tvCollaborators.getSelectionModel().getSelectedItem();
        this.evidence.getCollaborators().remove(collaboratorRemove);
        this.tvCollaborators.setItems(makeitemsCollaboratorsListForTable());
    }

    @FXML
    private void removeRowIntegrantTable(ActionEvent event) {
        Integrant integrantRemove = this.tvIntegrant.getSelectionModel().getSelectedItem();
        this.evidence.getIntegrants().remove(integrantRemove);
        this.tvIntegrant.setItems(makeitemsIntegrantsListForTable());
    }

    @FXML
    private void removeRowStudentsTable(ActionEvent event) {
        String studentRemove = this.lvStudent.getSelectionModel().getSelectedItem();
        this.evidence.getStudents().remove(studentRemove);
        this.lvStudent.setItems(makeitemsStudentsListForTable());
    }

    @FXML
    private void replaceDocument(ActionEvent event) {
        DialogBox dialogBox = new DialogBox();
        String newURLFile = dialogBox.openDialogFileSelector();
    }

    @FXML
    private void updateEvidence(ActionEvent event) {
        try{
            this.validateEvidenceInformation();
            this.getOutEvidenceInformation();
            if(evidence instanceof Book){
                this.validateBookInformation();
                this.getOutBookInformation();
            }
            if(evidence instanceof Article){
                this.validateArticleInformation();
                this.getOurArticleInformation();
            }
            if(evidence instanceof Prototype){
                ValidatorForm.chechkAlphabeticalArea(this.txtAreaFeatures, 1, 1000);
                ((Prototype)this.evidence).setFeatures(this.txtAreaFeatures.getText());
            }
            if(EVIDENCE_DAO.updateEvidence(this.evidence, this.urlFileEvidence)){
                AlertGenerator.showInfoAlert(event, "Evidencia modificada con éxito");
            }else{
                AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de ponerse en contacto con sopoerte técnico");
            }
            
            FXMLLoader loader = changeWindow("EvidenceRequest.fxml", event);
            EvidenceRequestController controller = loader.getController();
            controller.receiveEvidence(this.evidence);
            controller.receiveToken(token);
        }catch(InvalidFormException ex){
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
        
    }
    
    private void prepareIntegrantTable(){
        this.colIntegrantName.setCellValueFactory(new PropertyValueFactory<Integrant, String>("fullName"));
        this.tvIntegrant.setItems(makeitemsIntegrantsListForTable());
    }
    
    private void prepareCollaboratorTable(){
        this.colCollaboratorName.setCellValueFactory(new PropertyValueFactory<Collaborator, String>("fullName"));
        this.tvCollaborators.setItems(makeitemsCollaboratorsListForTable());
    }
    
    private void prepareStudentTable(){
        this.lvStudent.setItems(makeitemsStudentsListForTable());
    }
    
    private ObservableList<Integrant> makeitemsIntegrantsListForTable(){
        ObservableList<Integrant> itemsIntegrants = FXCollections.observableArrayList();
        List<Integrant> integrantList = evidence.getIntegrants();
        itemsIntegrants.addAll(integrantList);
        return itemsIntegrants;
    }
    
    private ObservableList<Collaborator> makeitemsCollaboratorsListForTable(){
        ObservableList<Collaborator> itemsCollaborator = FXCollections.observableArrayList();
        List<Collaborator> collaboratorList = evidence.getCollaborators();
        itemsCollaborator.addAll(collaboratorList);
        return itemsCollaborator;
    }
    
    private ObservableList<String> makeitemsStudentsListForTable(){
        ObservableList<String> itemsStudentNames = FXCollections.observableArrayList();
        List<String> studentList = evidence.getStudents();
        itemsStudentNames.addAll(studentList);
        return itemsStudentNames;
    }
    
    private ObservableList<Integrant> makeitemsIntegrantsListForComboBox(){
        ObservableList<Integrant> itemsIntegrantNames = FXCollections.observableArrayList();
        List<Integrant> integrantsList = INTEGRANT_DAO.getIntegrantsForEvidence("UV-CA-127");
        itemsIntegrantNames.addAll(integrantsList);
        return itemsIntegrantNames;
    }
    
    private ObservableList<Collaborator> makeitemsCollaboratorsListForComboBox(){
        ObservableList<Collaborator> itemsCollaboratorNames = FXCollections.observableArrayList();
        List<Collaborator> collaboratorsList = COLLABORATOR_DAO.getCollaboratorsForEvidence("UV-CA-127");
        itemsCollaboratorNames.addAll(collaboratorsList);
        return itemsCollaboratorNames;
    }
    
    private ObservableList<String> makeitemsProjectListForComboBox(){
        ObservableList<String> itemsProject = FXCollections.observableArrayList();
        List<String> projectsList = PROJECT_DAO.getProjectNameListForEvidence();
        itemsProject.addAll(projectsList);
        return itemsProject;
    }
    
    private ObservableList<String> makeitemsStudiesDegreeListForComboBox(){
        ObservableList<String> itemsStudies = FXCollections.observableArrayList();
        List<String> studiesList = Arrays.asList("Licenciatura", "Maestría", "Doctorado", "Especialidad");
        itemsStudies.addAll(studiesList);
        return itemsStudies;
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
}
