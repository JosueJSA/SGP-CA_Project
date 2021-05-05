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
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.domain.GeneralResume;

/**
 * FXML Controller class
 *
 * @author Josue Alarcon
 */
public class GeneralResumeController implements Initializable {
    
    private final GeneralResumeDAO GENERAL_RESUME_DAO = new GeneralResumeDAO();

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
        GeneralResume general = GENERAL_RESUME_DAO.getGeneralResumeByKey("UV-CA-127");
        bodyAcademyNameField.setText(general.getBodyAcademyName());
        bdyAcademyKeyField.setText(general.getBodyAcademyKey());
        areaAscriptionFiel.setText(general.getAscriptionArea());
        ascriptionUnitField.setText(general.getAscriptionUnit());
        consolidationDegreeField.setText(general.getConsolidationDegree());
        generalTarjetField.setText(general.getGeneralTarjet());
        missionField.setText(general.getMission());
        visionField.setText(general.getVision());
    }    
    
}
