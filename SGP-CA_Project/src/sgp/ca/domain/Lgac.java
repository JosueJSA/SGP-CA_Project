/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.domain;

import java.util.Objects;


public class Lgac {
    
    private String identifierLgac, description;
    private GeneralResume bodyAcademyRelated;

    public Lgac(String identifierLgac, String description) {
        this.identifierLgac = identifierLgac;
        this.description = description;
    }
    
    public Lgac(){
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

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if(this.description.equals(((Lgac)obj).getDescription())){
            isEqual = true;
        }
        return isEqual;
    }
    
    
}
