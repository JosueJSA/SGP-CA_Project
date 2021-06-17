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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import sgp.ca.businesslogic.BookDAO;
import sgp.ca.domain.Book;
import sgp.ca.domain.ChapterBook;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class BookController implements Initializable, EvidenceWindow {

    @FXML
    private Label lbUsername;
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
    private TextField txtFieldPublisher;
    @FXML
    private TextField txtFieldISBN;
    @FXML
    private TextField txtFieldNumberEdition;
    @FXML
    private Button btnDownloadDocument;
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
    @FXML
    private ListView<String> lvIntegrants;
    @FXML
    private ListView<String> lvCollaborators;
    @FXML
    private ListView<String> lvStudent;
    
    private Integrant token;
    private final BookDAO BOOK_DAO= new BookDAO();
    private Book book;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        book = new Book();
        this.prepareChapterBookTable();
    }
    
    public void showBook(String url, Integrant token){
        this.token = token;
        this.book = (Book) BOOK_DAO.getEvidenceByUrl(url);
        if(book == null){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(new ActionEvent(), "Lo sentimos, el sistema no está funcionando correctamente");
        }else{
            this.setDataIntoBookInterface();
        }
    }

    @FXML
    private void updateEvidence(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("EvidenceEdit.fxml", btnCloseWindowEvidenceRequest);
        EvidenceEditController controller = loader.getController();
        controller.receiveBookAndToken(this.book,token);
    }

    @FXML
    private void addChapterBook(ActionEvent event) {
    }

    @FXML
    private void removeEvidence(ActionEvent event) {
    }

    @FXML
    private void closeWindowEvidenceRequest(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("EvidenceList.fxml", btnCloseWindowEvidenceRequest);
        EvidenceListController controller = loader.getController();
        controller.showGeneralResumeEvidences(token);
    }

    @FXML
    private void downloadDocument(ActionEvent event) {
    }

    @FXML
    private void observeChapterBookInformation(MouseEvent event) {
        if(this.tvChapterBook.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("ChapterBookRequest.fxml", btnCloseWindowEvidenceRequest);
            ChapterBookRequestController controller = loader.getController();
            Book book = new Book();
            book.setUrlFile(this.book.getUrlFile());
            book.setEvidenceTitle(this.book.getEvidenceTitle());
            book.setEditionsNumber(this.book.getEditionsNumber());
            controller.receiveBook(book);
            controller.receiveChapterBookURLFile(
                this.tvChapterBook.getSelectionModel().getSelectedItem().getUrlFile()
            );
            controller.receiveToken(token);
        }
    }
    
    private void setDataIntoBookInterface(){
        if(this.book.getImpactAB()){
            this.chBoxImpactAB.setSelected(true);
        }
        lvStudent.getItems().addAll(this.book.getStudents());
        this.book.getIntegrants().forEach(integrant -> this.lvIntegrants.getItems().add(integrant.getFullName()));
        this.book.getCollaborators().forEach(collaborator -> this.lvCollaborators.getItems().add(collaborator.getFullName()));
        this.txtFieldPublisher.setText(this.book.getPublisher());
        this.txtFieldNumberEdition.setText(this.book.getEditionsNumber() + "");
        this.txtFieldISBN.setText(this.book.getIsbn() + "");
        this.lbTypeEvidence.setText(this.book.getEvidenceType());
        this.txtFieldEvidenceTittle.setText(this.book.getEvidenceTitle());
        this.txtFieldPublisherDate.setText(this.book.getPublicationDate());
        this.txtFieldPublicationCountry.setText(this.book.getCountry());
        this.txtFieldInvestigationProject.setText(this.book.getProjectName());
        this.txtFieldStudyDegree.setText(this.book.getStudyDegree());
        this.lbDocumentName.setText(this.book.getUrlFile());
    }

    @Override
    public String toString(){
        return "Libro";
    }

    @Override
    public void createWindowAccordingEvidenceType(Evidence evidence, Node graphicElement, Integrant token) {
        if(this.toString().equalsIgnoreCase(evidence.getEvidenceType())){
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("Book.fxml", graphicElement);
            BookController controller = loader.getController();
            controller.showBook(evidence.getUrlFile(), token);
        }
    }
    
    private void prepareChapterBookTable(){
        this.colTitleChapterBook.setCellValueFactory(new PropertyValueFactory<ChapterBook, String>("chapterBookTitle"));
        this.colRegistrationDateChapterBook.setCellValueFactory(new PropertyValueFactory<ChapterBook, String>("registrationDate"));
        this.colRangePagesChapterBook.setCellValueFactory(new PropertyValueFactory<ChapterBook, String>("pageNumberRange"));
    }
    
}
