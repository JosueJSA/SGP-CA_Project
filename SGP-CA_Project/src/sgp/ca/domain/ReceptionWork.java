/**
* @author Johann 
* Last modification date format: 26-03-2021
*/
package domain;

public class ReceptionWork extends Evidence{
    
    private String urlFile;
    private String projectName;
    private String impactBA;
    private String evidenceTitle;
    private String country;
    private String description;
    private String status; 
    private String modality;
    private String publicationDate;
    private int actualDurationInMonths;
    private int estimatedDurationInMonths;
    

    public ReceptionWork(String urlFile, String projectName, String impactAB, String evidenceTitle, String publicationDate, 
            String country, String description, String status, int actualDurationInMonths, 
            int estimatedDurationInMonths, String modality) {
        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactAB);
        this.description = description;
        this.status = status;
        this.actualDurationInMonths = actualDurationInMonths;
        this.estimatedDurationInMonths = estimatedDurationInMonths;
        this.modality = modality;
    }

    public ReceptionWork(String urlFile, String projectName, String impactAB, String evidenceTitle, String publicationDate , String country) {
        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactAB);
    }
    
    @Override
    public String getUrlFile() {
        return urlFile;
    }

    @Override
    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    @Override
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getImpactBA() {
        return impactBA;
    }

    public void setImpactBA(String impactBA) {
        this.impactBA = impactBA;
    }

    @Override
    public String getEvidenceTitle() {
        return evidenceTitle;
    }

    @Override
    public void setEvidenceTitle(String evidenceTitle) {
        this.evidenceTitle = evidenceTitle;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public String getPublicationDate() {
        return publicationDate;
    }

    @Override
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }  
}
