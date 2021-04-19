/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.businesslogic;

import sgp.ca.domain.Collaborator;


public interface ICollaboratorDAO {
    
    public Collaborator getCollaboratorByUVmail(String uvMail);
    public void addCollaborator(Collaborator newCollaborator);
    public void updateCollaborator(Collaborator collaborator, String oldRFC);
    public void deleteCollaboratorByEmailUV(String emailUV);
    
}
