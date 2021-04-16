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
public class Prototype extends Evidence{
    private String features;

    public Prototype(String features, String urlFile, String projectName, String evidenceTitle, String country, String publicationDate, String impactAB) {
        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactAB);
        this.features = features;
    }
    
    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
    
    
    
}
