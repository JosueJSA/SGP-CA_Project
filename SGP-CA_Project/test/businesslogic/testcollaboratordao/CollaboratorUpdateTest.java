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
            "GRTCEVSFTJRB", "María de los Ángeles Arenas Valdes", "arenas@uv.mx", "Activo",
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestría"
        );
        COLLABORATOR_DAO.updateCollaborator(newCollaborador, oldCollaborator.getRfc());
        Collaborator collaboratorRetrieved = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        INITIALIZER.cleanCollaboratorTest(collaboratorRetrieved.getRfc());
        Assert.assertNotEquals(oldCollaborator, collaboratorRetrieved);
    }
    
    @Test
    public void incorrectUpdatedCollaboratorNotRegistered(){
        Collaborator oldCollaborator = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        Collaborator newCollaborador = new Collaborator(
            "EFFYEVZPF", "Gerardo Contreras Vega", "contreras@uv.mx", "Activo",
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UVV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestría"
        );
        COLLABORATOR_DAO.updateCollaborator(newCollaborador, oldCollaborator.getRfc());
        Assert.assertNull(COLLABORATOR_DAO.getCollaboratorByUVmail("contreras@uv.mx").getRfc());
    }
    
    @Test
    public void correctUnsubscribeCollaborator(){
        INITIALIZER.prepareRequestTestCase();
        Collaborator oldCollaborator = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        COLLABORATOR_DAO.unsubscribeCollaboratorByEmailUV(oldCollaborator.getEmailUV());
        Collaborator collaboratorRetrieved = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        INITIALIZER.cleanCollaboratorTest(collaboratorRetrieved.getRfc());
        Assert.assertNotEquals(oldCollaborator.getParticipationStatus(), collaboratorRetrieved.getParticipationStatus());
    }
    
    @Test
    public void correctSubscribeCollaborator(){
        INITIALIZER.prepareRequestTestCase();
        Collaborator oldCollaborator = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        COLLABORATOR_DAO.unsubscribeCollaboratorByEmailUV(oldCollaborator.getEmailUV());
        oldCollaborator = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        COLLABORATOR_DAO.subscribeCollaboratorByEmailUV(oldCollaborator.getEmailUV());
        Collaborator collaboratorRetrieved = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        INITIALIZER.cleanCollaboratorTest(collaboratorRetrieved.getRfc());
        String stateExpected = "Activo";
        Assert.assertEquals(stateExpected, collaboratorRetrieved.getParticipationStatus());
    }
    
}
