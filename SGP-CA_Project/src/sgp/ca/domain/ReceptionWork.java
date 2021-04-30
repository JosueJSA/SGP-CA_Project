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
    private List<Integrant> integrant;
    private List<Collaborator> collaborator;
    private List<Lgac> lgac;
    

    public ReceptionWork(String urlFile, String projectName, String impactBA, String evidenceTitle, String publicationDate, 
            String country, String description, String status, int actualDurationInMonths, 
            int estimatedDurationInMonths, String modality) {
        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactBA);
        this.description = description;
        this.status = status;
        this.actualDurationInMonths = actualDurationInMonths;
        this.estimatedDurationInMonths = estimatedDurationInMonths;
        this.modality = modality;
        this.integrant = new ArrayList<>();
        this.collaborator = new ArrayList<>();
        this.lgac = new ArrayList<>();
    }
    
    public ReceptionWork(){
        this.integrant = new ArrayList<>();
        this.collaborator = new ArrayList<>();
        this.lgac = new ArrayList<>();
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

    public Integrant getIntegrantByRFC(String rfc){
        Integrant integrantReturn = null;
        for(Integrant integrant : this.integrant){
            if(integrant.getRfc() == rfc){
                integrantReturn = integrant;
            }
        }
        return integrantReturn;
    }
    
    public List<Integrant> getIntegrants() {
        return integrant;
    }
    
    public Collaborator getCollaboratorByRFC(String rfc){
        Collaborator collaboratorReturn = null;
        for(Collaborator collaborator : this.collaborator){
            if(collaborator.getRfc() == rfc){
                collaboratorReturn = collaborator;
            }
        }
        return collaboratorReturn;
    }
    
    public List<Collaborator> getCollaborators() {
        return collaborator;
    }
    
    public Lgac getLgacByIdentifier(String identifier){
        Lgac lgacReturn = null;
        for(Lgac lgac : this.lgac){
            if(lgac.getIdentifierLgac() == identifier){
                lgacReturn = lgac;
            }
        }
        return lgacReturn;
    }
    
    public List<Lgac> getLgacs() {
        return lgac;
    }
}
