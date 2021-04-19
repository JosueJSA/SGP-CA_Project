/**
* @author Josué Alarcón  
* Last modification date format: 05-04-2021
*/

package businesslogic.testcollaboratordao;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.domain.Collaborator;


public class CollaboratorUpdateTest{
    
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final CollaboratorInitializer INITIALIZER = new CollaboratorInitializer();
    
    @Test
    public void correctUpdatedCollaborator(){
        INITIALIZER.prepareRequestTestCase();
        Collaborator oldCollaborator = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        Collaborator newCollaborador = new Collaborator(
            "GRTCEVSFTJRB", "María de los Ángeles Arenas Valdes", "arenas@uv.mx", 
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestría"
        );
        COLLABORATOR_DAO.updateCollaborator(newCollaborador, oldCollaborator.getRfc());
        Collaborator collaboratorRetrieved = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        INITIALIZER.cleanCollaboratorCaseTest();
        Assert.assertNotEquals(oldCollaborator, collaboratorRetrieved);
    }
    
    @Test
    public void incorrectUpdatedCollaboratorNotRegistered(){
        Collaborator oldCollaborator = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        Collaborator newCollaborador = new Collaborator(
            "EFFYEVZPF", "Gerardo Contreras Vega", "contreras@uv.mx", 
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UVV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestría"
        );
        COLLABORATOR_DAO.updateCollaborator(newCollaborador, oldCollaborator.getRfc());
        Assert.assertNull(COLLABORATOR_DAO.getCollaboratorByUVmail("contreras@uv.mx").getRfc());
    }
    
}
