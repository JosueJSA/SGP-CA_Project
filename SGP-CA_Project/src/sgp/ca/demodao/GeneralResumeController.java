/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class GeneralResumeController implements Initializable {

    @FXML
    private TextField bodyAcademyNameField;
    @FXML
    private TextField bdyAcademyKeyField;
    @FXML
    private TextField areaAscriptionFiel;
    @FXML
    private TextField ascriptionUnitField;
    @FXML
    private TextField consolidationDegreeField;
    @FXML
    private DatePicker lastEvaluationDateField;
    @FXML
    private TextArea generalTarjetField;
    @FXML
    private TextArea missionField;
    @FXML
    private TextArea visionField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
