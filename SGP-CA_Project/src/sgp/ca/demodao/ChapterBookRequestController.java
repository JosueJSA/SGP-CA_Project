/**
 * @author estef
 * Last modification date format: 03-06-2021
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.ChapterBookDAO;
import sgp.ca.domain.Book;
import sgp.ca.domain.ChapterBook;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;

public class ChapterBookRequestController implements Initializable {
    @FXML
    private Label lbUsername;

    @FXML
    private Button btnUpdateChapterBook;

    @FXML
    private Button btnRemoveChapterBook;

    @FXML
    private Button btnCloseWindow;

    @FXML
    private TextField txtFieldChapterBookTittle;

    @FXML
    private TextField txtFieldNumerPagesRange;

    @FXML
    private TextField txtFieldBook;

    @FXML
    private TableView<Integrant> tvIntegrant;

    @FXML
    private TableColumn<Integrant, String> colIntegrantName;

    @FXML
    private TableView<Collaborator> tvCollaborators;

    @FXML
    private TableColumn<Collaborator, String> colCollaboratorName;
    
    @FXML
    private ListView<String> lvStudent;

    @FXML
    private Button btnDownloadDocument;

    @FXML
    private Label lbDocumentName;
    
    private Integrant token;
    private ChapterBook chapterBook;
    private Book book;
    private final ChapterBookDAO CHAPTERBOOK_DAO = new ChapterBookDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void receiveToken(Integrant token){
        this.token = token;
        this.lbUsername.setText(token.getFullName());
        this.grantPermissions(token);
    }
    
    private void grantPermissions(Integrant token){
        if(token.getRfc().equals(chapterBook.getRegistrationResponsible())){
            this.btnRemoveChapterBook.setVisible(true);
            this.btnUpdateChapterBook.setVisible(true);
        }
    }
    
    public void receiveChapterBookURLFile(String urlFile){
        chapterBook = CHAPTERBOOK_DAO.getChapterBookByURLFile(urlFile);
        this.errorReturnChapterBook();
        this.setInformationChapterBook();
    }
    
    public void receiveBook(Book book){
        this.book = book;
    }
    
    private void setInformationChapterBook(){
        this.txtFieldChapterBookTittle.setText(chapterBook.getChapterBookTitle());
        this.txtFieldNumerPagesRange.setText(chapterBook.getPageNumberRange());
        this.txtFieldBook.setText(this.book.getEvidenceTitle());
        this.lbDocumentName.setText(chapterBook.getUrlFile());
        this.prepareIntegrantTable();
        this.prepareCollaboratorTable();
        this.prepareStudentView();
    }
    
    
    @FXML
    private void closeWindow(ActionEvent event) {
        FXMLLoader loader = this.changeWindow("EvidenceRequest.fxml", event);
        EvidenceRequestController controller = loader.getController();
        Book book = new Book();
        book.setUrlFile(chapterBook.getUrlFileBook());
        controller.receiveEvidence(book);
        controller.receiveToken(token);
    }

    @FXML
    private void downloadDocument(ActionEvent event) {
        DialogBox dialogBox = new DialogBox();
        dialogBox.setFileNameSelected(chapterBook.getUrlFile());
        dialogBox.openDialogDirectorySelector();
    }

    @FXML
    private void removeChapterBook(ActionEvent event) {
        Optional<ButtonType> action = AlertGenerator.showConfirmacionAlert(event,
            "¿Seguro que desea eliminar este capítulo? Ya no se podrá recuperar.");
        if(action.get() == ButtonType.OK){

        }
    }

    @FXML
    private void updateChapterBook(ActionEvent event) {
        FXMLLoader loader = this.changeWindow("ChapterBookEdit.fxml", event);
        ChapterBookEditController controller = loader.getController();
        controller.receiveChapterBook(this.chapterBook);
        controller.receiveBook(this.book);
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
    
    private void errorReturnChapterBook(){
        if(chapterBook == null){
            
        }
    }
}
