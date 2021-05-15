/**
* @author Josué Alarcón  
* Last modification date format: 05-04-2021
*/

package businesslogic.testcollaboratordao;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.domain.Collaborator;


public class CollaboratorInsertTest {
    
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final CollaboratorInitializer INITIALIZER = new CollaboratorInitializer();
    
    @Test
    public void correctCollaboratorInsertion(){
        Collaborator collaborador = new Collaborator(
            "AVFR8906245M7", "María de los Ángeles Arenas Valdes", "arenas@uv.mx", "Activo", 
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestrpia"
        ); 
        COLLABORATOR_DAO.addCollaborator(collaborador);
        Collaborator collaboratorRetrieved = COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx");
        INITIALIZER.cleanCollaboratorTest(collaboratorRetrieved.getRfc());
        Assert.assertEquals(collaborador, collaboratorRetrieved);
    }
    
    @Test
    public void incorrectDuplicatedCollaboratorInsertion(){
        INITIALIZER.prepareRequestTestCase();
        Collaborator collaborador = new Collaborator(
            "COLABORADORTEST", "Adam López Martínez", "adam@uv.mx", "Activo",
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestría"
        );
        COLLABORATOR_DAO.addCollaborator(collaborador);
        Collaborator collaboratorRetrieved = COLLABORATOR_DAO.getCollaboratorByUVmail("adam@uv.mx");
        INITIALIZER.cleanCollaboratorTest("COLABORADORTEST");
        Assert.assertNull(collaboratorRetrieved.getRfc());
    }
    
}
