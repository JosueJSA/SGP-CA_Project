/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;


public class Lgac {
    
    private String identifierLgac, description;
    private GeneralResume bodyAcademyRelated;

    public Lgac(String identifierLgac, String description) {
        this.identifierLgac = identifierLgac;
        this.description = description;
    }

    public String getIdentifierLgac() {
        return identifierLgac;
    }

    public void setIdentifierLgac(String identifierLgac) {
        this.identifierLgac = identifierLgac;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeneralResume getBodyAcademyRelated() {
        return bodyAcademyRelated;
    }

    public void setBodyAcademyRelated(GeneralResume bodyAcademyRelated) {
        this.bodyAcademyRelated = bodyAcademyRelated;
    }
    
    
}
