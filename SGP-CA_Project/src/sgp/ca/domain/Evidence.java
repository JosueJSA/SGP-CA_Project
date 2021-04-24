/**
 *
 * @author estef
 */

package sgp.ca.domain;

public abstract class Evidence {
    private String urlFile;
    private String projectName; 
    private String evidenceTitle;
    private String country;
    private String publicationDate;
    private String impactBA;

    public Evidence(String urlFile, String projectName, String evidenceTitle, String country, String publicationDate, String impactBA) {
        this.urlFile = urlFile;
        this.projectName = projectName;
        this.evidenceTitle = evidenceTitle;
        this.country = country;
        this.publicationDate = publicationDate;
        this.impactBA = impactBA;
    }
    
    public Evidence(){
        
    }

    public String getUrlFile() {
        return urlFile;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getEvidenceTitle() {
        return evidenceTitle;
    }

    public String getCountry() {
        return country;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String isImpactBA() {
        return impactBA;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setEvidenceTitle(String evidenceTitle) {
        this.evidenceTitle = evidenceTitle;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setImpactBA(String impactBA) {
        this.impactBA = impactBA;
    }
       
}
