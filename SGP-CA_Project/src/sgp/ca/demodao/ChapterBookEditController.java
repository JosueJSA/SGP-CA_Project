/**
 * @author estef
 * Last modification date format: 03-06-2021
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.BookDAO;
import sgp.ca.businesslogic.ChapterBookDAO;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Book;
import sgp.ca.domain.ChapterBook;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;

public class ChapterBookEditController implements Initializable {

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
    private TextField txtFieldChapterBookTittle;

    @FXML
    private DatePicker dtpPublicationDate;

    @FXML
    private TextField txtFieldNumerPagesRange;

    @FXML
    private ComboBox<Book> cboBoxBook;

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
    private Button btnAddRowStudentsTable;

    @FXML
    private Button btnRemoveRowStudentsTable;

    @FXML
    private TextField txtFieldStudentName;

    @FXML
    private ListView<String> lvStudent;

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
    private final ChapterBookDAO CHAPTERBOOK_DAO = new ChapterBookDAO();
    private final BookDAO BOOK_DAO = new BookDAO();
    private final Calendar DATE = new GregorianCalendar();
    private ChapterBook chapterBook = new ChapterBook();
    private Integrant token;
    private Book book;
    boolean addNewChapterBook = false;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setWindowInformationCamps();
        prepareIntegrantTable();
        prepareCollaboratorTable();
        prepareStudentTable();
    }
    
    public void receiveToken(Integrant token){
        this.token = token;
        this.lbUsername.setText(this.token.getFullName());
    }
    
    public void receiveBook(Book book){
        this.book = book;
        this.chapterBook.setUrlFileBook(book.getUrlFile());
        this.addNewChapterBook = true;
    }
    
    public void receiveChapterBook(ChapterBook chapterBook){
        this.chapterBook = chapterBook;
        modifyWindowForModification();
        setChapterBookInformation();
    }
    

    private void setWindowInformationCamps(){
        this.cboBoxCollaboratorsName.setItems(makeitemsCollaboratorsListForComboBox());
        this.cboBoxIntegrantsName.setItems(makeitemsIntegrantsListForComboBox());
        this.cboBoxBook.setItems(makeitemsBooksListForComboBox());
    }
    
    private void modifyWindowForModification(){
        this.lbWindowTitle.setText("MODIFICAR CAPÍTULO DE LIBRO");
        this.btnUpdateEvidence.setDisable(false);
        this.btnUpdateEvidence.setVisible(true);
        this.btnAddEvidence.setDisable(true);
        this.btnAddEvidence.setVisible(false);
        prepareIntegrantTable();
        prepareCollaboratorTable();
        prepareStudentTable();
    }
    
    private void setChapterBookInformation(){
        this.txtFieldChapterBookTittle.setText(this.chapterBook.getChapterBookTitle());
        this.txtFieldNumerPagesRange.setText(this.chapterBook.getPageNumberRange());
        this.cboBoxBook.setValue(this.book);
        this.lbDocumentName.setText(this.chapterBook.getUrlFile());
    }
    
    
    private void validateChapterInformation() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(this.txtFieldChapterBookTittle, 3, 80);
        ValidatorForm.chechkAlphabeticalField(this.txtFieldNumerPagesRange, 3, 10);
        ValidatorForm.isComboBoxSelectedBook(this.cboBoxBook);
    }
    
    private void getOutChapterBookInformation(){
        this.chapterBook.setChapterBookTitle(this.txtFieldChapterBookTittle.getText());
        this.chapterBook.setPageNumberRange(this.txtFieldNumerPagesRange.getText());
        this.chapterBook.setUrlFileBook(this.cboBoxBook.getSelectionModel().getSelectedItem().getUrlFile());
        this.chapterBook.setRegistrationResponsible(token.getRfc());
    }
    
    @FXML
    private void addDocument(ActionEvent event) {
        DialogBox dialogBox = new DialogBox();
        this.lbDocumentName.setText(dialogBox.openDialogFileSelector());
        this.lbDocumentName.setVisible(true);
        this.imgViewPDFEvidence.setVisible(true);
        this.btnAddDocument.setVisible(false);
        this.btnReplaceDocument.setVisible(true);
        this.btnReplaceDocument.setDisable(false);
        
    }

    @FXML
    private void addEvidence(ActionEvent event) {
        try{
            this.validateChapterInformation();
            this.getOutChapterBookInformation();
            this.chapterBook.setRegistrationDate(
                DATE.get(Calendar.YEAR) + "-" + (DATE.get(Calendar.MONTH) + 1) + "-" + DATE.get(Calendar.DAY_OF_MONTH)
            );
            Book relatedBook = this.cboBoxBook.getSelectionModel().getSelectedItem();
            if(CHAPTERBOOK_DAO.addChapterBook(chapterBook, relatedBook)){
                AlertGenerator.showInfoAlert(event, "Capítulo de libro registrado con éxito");
            }else{
                AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de ponerse en contacto con sopoerte técnico");
            }
            FXMLLoader loader = changeWindow("EvidenceRequest.fxml", event);
            EvidenceRequestController controller = loader.getController();
            controller.receiveEvidence(relatedBook);
            controller.receiveToken(token);
        }catch(InvalidFormException ex){
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }

    @FXML
    private void addRowCollaboratorTable(ActionEvent event) {
        try{
            ValidatorForm.isComboBoxSelectedCollaborator(cboBoxCollaboratorsName);
            Collaborator collaborator = this.cboBoxCollaboratorsName.getValue();
            this.chapterBook.getCollaborators().add(collaborator);
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
            this.chapterBook.getIntegrants().add(integrant);
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
            this.chapterBook.getStudents().add(studentName);
            this.lvStudent.setItems(makeitemsStudentsListForView());
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
            if(this.addNewChapterBook){
                FXMLLoader loader = this.changeWindow("EvidenceRequest.fxml", event);
                EvidenceRequestController controller = loader.getController();
                Book book = new Book();
                book.setUrlFile(this.chapterBook.getUrlFileBook());
                controller.receiveEvidence(book);
                controller.receiveToken(token);
            }else{
               FXMLLoader loader = this.changeWindow("ChapterBookRequest.fxml", event);
               ChapterBookRequestController controller = loader.getController();
               controller.receiveChapterBookURLFile(this.chapterBook.getUrlFile());
               controller.receiveBook(this.book);
               controller.receiveToken(token);
            }

        }
    }

    @FXML
    private void removeRowCollaboratorTable(ActionEvent event) {
        Collaborator collaboratorRemove = this.tvCollaborators.getSelectionModel().getSelectedItem();
        this.chapterBook.getCollaborators().remove(collaboratorRemove);
        this.tvCollaborators.setItems(makeitemsCollaboratorsListForTable());
    }

    @FXML
    private void removeRowIntegrantTable(ActionEvent event) {
        Integrant integrantRemove = this.tvIntegrant.getSelectionModel().getSelectedItem();
        this.chapterBook.getIntegrants().remove(integrantRemove);
         this.tvIntegrant.setItems(makeitemsIntegrantsListForTable());
    }

    @FXML
    private void removeRowStudentsTable(ActionEvent event) {
        String studentRemove = this.lvStudent.getSelectionModel().getSelectedItem();
        this.chapterBook.getStudents().remove(studentRemove);
        this.lvStudent.setItems(makeitemsStudentsListForView());
    }

    @FXML
    private void replaceDocument(ActionEvent event) {

    }

    @FXML
    private void updateEvidence(ActionEvent event) {
        try{
            this.validateChapterInformation();
            this.getOutChapterBookInformation();
            Book relatedBook = this.cboBoxBook.getSelectionModel().getSelectedItem();
            if(CHAPTERBOOK_DAO.addChapterBook(chapterBook, relatedBook)){
                AlertGenerator.showInfoAlert(event, "Capítulo de libro modificado con éxito");
            }else{
                AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de ponerse en contacto con sopoerte técnico");
            }
            FXMLLoader loader = changeWindow("ChapterBookRequest.fxml", event);
            ChapterBookRequestController controller = loader.getController();
            controller.receiveChapterBookURLFile(this.chapterBook.getUrlFile());
            controller.receiveBook(relatedBook);
            controller.receiveToken(this.token);
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
        this.lvStudent.setItems(makeitemsStudentsListForView());
    }
    
    private ObservableList<Integrant> makeitemsIntegrantsListForTable(){
        ObservableList<Integrant> itemsIntegrants = FXCollections.observableArrayList();
        List<Integrant> integrantList = chapterBook.getIntegrants();
        itemsIntegrants.addAll(integrantList);
        return itemsIntegrants;
    }
    
    private ObservableList<Collaborator> makeitemsCollaboratorsListForTable(){
        ObservableList<Collaborator> itemsCollaborator = FXCollections.observableArrayList();
        List<Collaborator> collaboratorList = chapterBook.getCollaborators();
        itemsCollaborator.addAll(collaboratorList);
        return itemsCollaborator;
    }
    
    private ObservableList<String> makeitemsStudentsListForView(){
        ObservableList<String> itemsStudentNames = FXCollections.observableArrayList();
        List<String> studentList = chapterBook.getStudents();
        itemsStudentNames.addAll(studentList);
        return itemsStudentNames;
    }
    
    private ObservableList<Integrant> makeitemsIntegrantsListForComboBox(){
        ObservableList<Integrant> itemsIntegrantNames = FXCollections.observableArrayList();
        List<Integrant> integrantsList = INTEGRANT_DAO.getIntegrantsForEvidence(token.getBodyAcademyKey());
        itemsIntegrantNames.addAll(integrantsList);
        return itemsIntegrantNames;
    }
    
    private ObservableList<Collaborator> makeitemsCollaboratorsListForComboBox(){
        ObservableList<Collaborator> itemsCollaboratorNames = FXCollections.observableArrayList();
        List<Collaborator> collaboratorsList = COLLABORATOR_DAO.getCollaboratorsForEvidence(token.getBodyAcademyKey());
        itemsCollaboratorNames.addAll(collaboratorsList);
        return itemsCollaboratorNames;
    }
    
    private ObservableList<Book> makeitemsBooksListForComboBox(){
        ObservableList<Book> itemsBooks = FXCollections.observableArrayList();
        List<Book> booksList = BOOK_DAO.getBooksByIntegrant(token.getRfc());
        itemsBooks.addAll(booksList);
        return itemsBooks;
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