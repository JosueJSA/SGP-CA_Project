/**
* @author Johann 
* Last modification date format: 26-03-2021
*/
package sgp.ca.domain;

public class ReceptionWork extends Evidence{
    
    private String description;
    private String status; 
    private String modality;
    private int actualDurationInMonths;
    private int estimatedDurationInMonths;
    

    public ReceptionWork(String urlFile, String projectName, String impactBA, String evidenceTitle, String publicationDate, 
            String country, String description, String status, int actualDurationInMonths, 
            int estimatedDurationInMonths, String modality) {
        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactBA);
        this.description = description;
        this.status = status;
        this.actualDurationInMonths = actualDurationInMonths;
        this.estimatedDurationInMonths = estimatedDurationInMonths;
        this.modality = modality;
    }

//    public ReceptionWork(String urlFile, String projectName, String impactAB, String evidenceTitle, String publicationDate , String country) {
//        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactAB);
//    }
    
    public ReceptionWork(){
        
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

}
