/**
* @author Johann 
* Last modification date format: 26-03-2021
*/
package sgp.ca.domain;

import java.util.ArrayList;
import java.util.List;

public class ReceptionWork extends Evidence{
    
    private String description;
    private String status; 
    private String modality;
    private int actualDurationInMonths;
    private int estimatedDurationInMonths;
    private List<String> requirements;
    private List<Lgac> lgac;
    
    public ReceptionWork(String urlFile, String projectName, boolean impactAB, String bodyAcademyKey, String evidenceTitle, 
            String registrationResponsible, String registrationDate, String studyDegree,  String publicationDate, 
            String country, String description, String status, int actualDurationInMonths, 
            int estimatedDurationInMonths, String modality) {
        super(
            urlFile, projectName, evidenceTitle, country, 
            publicationDate, impactAB, registrationDate, 
            registrationResponsible, studyDegree, bodyAcademyKey
        );
        this.description = description;
        this.status = status;
        this.actualDurationInMonths = actualDurationInMonths;
        this.estimatedDurationInMonths = estimatedDurationInMonths;
        this.modality = modality;
        this.requirements = new ArrayList();
        this.lgac = new ArrayList();
        
    }
    
    public ReceptionWork(){
        this.requirements = new ArrayList();
        this.lgac = new ArrayList();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public int getActualDurationInMonths() {
        return actualDurationInMonths;
    }

    public void setActualDurationInMonths(int actualDurationInMonths) {
        this.actualDurationInMonths = actualDurationInMonths;
    }

    public int getEstimatedDurationInMonths() {
        return estimatedDurationInMonths;
    }

    public void setEstimatedDurationInMonths(int estimatedDurationInMonths) {
        this.estimatedDurationInMonths = estimatedDurationInMonths;
    }
    
    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<Lgac> getLgacs() {
        return lgac;
    }

    public void setLgac(List<Lgac> lgac) {
        this.lgac = lgac;
    }
}
