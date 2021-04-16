/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.domain;

/**
 *
 * @author estef
 */
public abstract class Evidence {
    private String urlFile, projectName, evidenceTitle, country, publicationDate, impactAB;


    public Evidence(String urlFile, String projectName, String evidenceTitle, String country, String publicationDate, String impactAB) {
        this.urlFile = urlFile;
        this.projectName = projectName;
        this.evidenceTitle = evidenceTitle;
        this.country = country;
        this.publicationDate = publicationDate;
        this.impactAB = impactAB;
    }
    
    public Evidence() {
        
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

    public String getImpactAB() {
        return impactAB;
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

    public void setImpactAB(String impactAB) {
        this.impactAB = impactAB;
    }
    
    

}

