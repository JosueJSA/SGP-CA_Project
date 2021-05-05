/**
 * @author Josué
 * Last modification date format: 19-04-2021
 */

package sgp.ca.domain;

public class Prototype extends Evidence{
    private String features;

    public Prototype(String urlFile, String projectName, String evidenceTitle, 
    String country, String publicationDate, boolean impactAB, String registrationDate,
    String registrationResponsible, String studyDegree, String bodyAcademyKey, String features) {
        super(
            urlFile, projectName, evidenceTitle,country, 
            publicationDate, impactAB, registrationDate,
            registrationResponsible, studyDegree, bodyAcademyKey
        );
        this.features = features;
    }

    public Prototype(){
        
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
    
    
}
