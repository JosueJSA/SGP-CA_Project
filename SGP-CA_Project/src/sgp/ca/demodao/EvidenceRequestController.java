/**
 * @author estef
 * Last modification date format: 20-05-2021
 */

package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sgp.ca.businesslogic.ArticleDAO;
import sgp.ca.businesslogic.BookDAO;
import sgp.ca.businesslogic.ChapterBookDAO;
import sgp.ca.businesslogic.EvidenceDAO;
import sgp.ca.businesslogic.PrototypeDAO;
import sgp.ca.domain.Article;
import sgp.ca.domain.Book;
import sgp.ca.domain.ChapterBook;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Prototype;

/**
 * FXML Controller class
 *
 * @author estef
 */
public class EvidenceRequestController implements Initializable {

    @FXML
    private Button btnUpdateEvidence;

    @FXML
    private Button btnAddChapterBook;

    @FXML
    private Button btnRemoveEvidence;

    @FXML
    private Button btnCloseWindowEvidenceRequest;

    @FXML
    private CheckBox chBoxImpactAB;

    @FXML
    private Label lbTypeEvidence;
    
    @FXML
    private Label lbUsername;

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
    private TableView<Integrant> tvIntegrant;

    @FXML
    private TableColumn<Integrant, String> colIntegrantName;

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
    private TextField txtFieldNumberEdition;

    @FXML
    private Tab tabArticle;

    @FXML
    private TextField txtFieldMagazineName;

    @FXML
    private TextField txtFieldMagazineEditorial;

    @FXML
    private TextField txtFieldISNN;

    @FXML
    private TextArea txtAreaIndex;

    @FXML
    private Tab tabPrototype;

    @FXML
    private TextArea txtAreaFeatures;

    @FXML
    private ListView<String> lvStudent;
    
    @FXML
    private Button btnDownloadDocument;

    @FXML
    private ImageView imgViewPDFEvidence;

    @FXML
    private Label lbDocumentName;

    @FXML
    private VBox vbChapterBook;

    @FXML
    private TableView<ChapterBook> tvChapterBook;

    @FXML
    private TableColumn<ChapterBook, String> colTitleChapterBook;

    @FXML
    private TableColumn<ChapterBook, String> colRegistrationDateChapterBook;

    @FXML
    private TableColumn<ChapterBook, String> colRangePagesChapterBook;
    
    private final ChapterBookDAO CHAPTERBOOK_DAO = new ChapterBookDAO();
    private EvidenceDAO EVIDENCE_DAO;
    private Evidence evidence;
    private Integrant token;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void receiveToken(Integrant integrantToken){
        this.token = integrantToken;
        this.lbUsername.setText(token.getFullName());
        this.grantPermissions(token);
    }
    
    private void grantPermissions(Integrant token){
        if(token.getRfc().equals(evidence.getRegistrationResponsible())){
            this.btnRemoveEvidence.setVisible(true);
            this.btnUpdateEvidence.setVisible(true);
            
            if(evidence.getEvidenceType().equals("Libro")){
                this.btnAddChapterBook.setVisible(true);
            }
        }
    }
    
    public void receiveEvidence(Evidence evidence){
        if(evidence instanceof Book){
            this.evidence = new Book();
            this.EVIDENCE_DAO = new BookDAO();
            this.tabBook.setDisable(false);
            this.vbChapterBook.setVisible(true);
        }
        if(evidence instanceof Prototype){
            this.evidence = new Prototype();
            this.EVIDENCE_DAO = new PrototypeDAO();
            this.tabPrototype.setDisable(false);
            
        }
        if(evidence instanceof Article){
            this.evidence = new Article();
            this.EVIDENCE_DAO = new ArticleDAO();
            this.tabArticle.setDisable(false);
        }
        this.evidence = EVIDENCE_DAO.getEvidenceByUrl(evidence.getUrlFile());
        this.setInformationEvidence();
        this.setInformationEvidenceType();
    }
    
    private void setInformationEvidence(){
        if(evidence.getImpactAB()){
            this.chBoxImpactAB.setSelected(true);
        }
        this.lbTypeEvidence.setText(evidence.getEvidenceType());
        this.txtFieldEvidenceTittle.setText(evidence.getEvidenceTitle());
        this.txtFieldPublisherDate.setText(evidence.getPublicationDate());
        this.txtFieldPublicationCountry.setText(evidence.getCountry());
        this.txtFieldInvestigationProject.setText(evidence.getProjectName());
        this.txtFieldStudyDegree.setText(evidence.getStudyDegree());
        this.lbDocumentName.setText(evidence.getUrlFile());
        this.prepareIntegrantTable();
        this.prepareCollaboratorTable();
        this.prepareStudentView();
    }
    
    private void setInformationEvidenceType(){
        if(evidence instanceof Book){
            this.txtFieldPublisher.setText(((Book)evidence).getPublisher());
            this.txtFieldNumberEdition.setText(((Book)evidence).getEditionsNumber() + "");
            this.txtFieldISBN.setText(((Book)evidence).getIsbn() + "");
            this.prepareChapterBookTable();
        }
        if(evidence instanceof Prototype){
            this.txtAreaFeatures.setText(((Prototype)evidence).getFeatures());
        }
        if(evidence instanceof Article){
            this.txtFieldMagazineName.setText(((Article)evidence).getMagazineName());
            this.txtFieldMagazineEditorial.setText(((Article)evidence).getMagazineEditorial());
            this.txtFieldISNN.setText(((Article)evidence).getIsnn() + "");
            this.txtAreaIndex.setText(((Article)evidence).getIndex() + "");
        }
    }
    
    
    
    @FXML
    private void addChapterBook(ActionEvent event) {
        FXMLLoader loader = this.changeWindow("ChapterBookEdit.fxml", event);
        ChapterBookEditController controller = loader.getController();
        Book book = new Book();
        book.setUrlFile(this.evidence.getUrlFile());
        book.setEvidenceTitle(this.evidence.getEvidenceTitle());
        book.setEditionsNumber(((Book)evidence).getEditionsNumber());
        controller.receiveBook(book);
        controller.receiveToken(this.token);
    }

    @FXML
    private void closeWindowEvidenceRequest(ActionEvent event) {
        //FXMLLoader loader = this.changeWindow("Name.fxml", event); No sé a donde se va :c
    }
    
    @FXML
    private void downloadDocument(ActionEvent event) {
        DialogBox dialogBox = new DialogBox();
        dialogBox.setFileNameSelected(evidence.getUrlFile());
        dialogBox.openDialogDirectorySelector();
    }
    
    @FXML
    private void observeChapterBookInformation(MouseEvent event) {
        if(this.tvChapterBook.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = changeWindow("ChapterBookRequest.fxml", event);
            ChapterBookRequestController controller = loader.getController();
            Book book = new Book();
            book.setUrlFile(evidence.getUrlFile());
            book.setEvidenceTitle(evidence.getEvidenceTitle());
            book.setEditionsNumber(((Book)evidence).getEditionsNumber());
            controller.receiveBook(book);
            controller.receiveChapterBookURLFile(
                this.tvChapterBook.getSelectionModel().getSelectedItem().getUrlFile()
            );
            controller.receiveToken(token);
        }
    }

    @FXML
    private void removeEvidence(ActionEvent event) {
        Optional<ButtonType> action = AlertGenerator.showConfirmacionAlert(event,
            "¿Seguro que desea eliminar esta evidencia? Ya no se podrá recuperar.");
        if(action.get() == ButtonType.OK){

        }
        
    }

    @FXML
    private void updateEvidence(ActionEvent event) {
        FXMLLoader loader = this.changeWindow("EvidenceEdit.fxml", event);
        EvidenceEditController controller = loader.getController();
        controller.receiveEvidence(this.evidence);
        controller.receiveToken(this.token);
    }
    
    private void prepareIntegrantTable(){
        this.colIntegrantName.setCellValueFactory(new PropertyValueFactory<Integrant, String>("fullName"));
        this.tvIntegrant.setItems(makeitemsIntegrantsListForTable());
    }
    
    private void prepareCollaboratorTable(){
        this.colCollaboratorName.setCellValueFactory(new PropertyValueFactory<Collaborator, String>("fullName"));
        this.tvCollaborators.setItems(makeitemsCollaboratorsListForTable());
    }
    
    private void prepareStudentView(){
        this.lvStudent.setItems(makeitemsStudentsListForView());
    }
    
    private void prepareChapterBookTable(){
        this.colTitleChapterBook.setCellValueFactory(new PropertyValueFactory<ChapterBook, String>("chapterBookTitle"));
        this.colRegistrationDateChapterBook.setCellValueFactory(new PropertyValueFactory<ChapterBook, String>("registrationDate"));
        this.colRangePagesChapterBook.setCellValueFactory(new PropertyValueFactory<ChapterBook, String>("pageNumberRange"));
        this.tvChapterBook.setItems(makeitemsChapterBookListForTable());
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
    
    private ObservableList<String> makeitemsStudentsListForView(){
        ObservableList<String> itemsStudentNames = FXCollections.observableArrayList();
        List<String> studentList = evidence.getStudents();
        itemsStudentNames.addAll(studentList);
        return itemsStudentNames;
    }
    
    private ObservableList<ChapterBook> makeitemsChapterBookListForTable(){
        ObservableList<ChapterBook> itemsChapterBooks = FXCollections.observableArrayList();
        List<ChapterBook> chapterBookList = CHAPTERBOOK_DAO.getChapterBooksListByBook(evidence.getUrlFile());
        itemsChapterBooks.addAll(chapterBookList);
        return itemsChapterBooks;
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
