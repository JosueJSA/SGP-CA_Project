/**
* @author Josué Alarcón  
* Last modification date format: 05-04-2021
*/

package businesslogic;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.domain.Collaborator;


public class CollaboratorInsertTest {
    
    @Test
    public void correctCollaboratorInsertion(){
        CollaboratorDAO colaboradorDAO = new CollaboratorDAO();
        Collaborator collaborador = new Collaborator(
            "GCVGR8906245M7", "Gerardo Contreras Vega", "contreras@uv.mx", 
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestría"
        );
        colaboradorDAO.addCollaborator(collaborador);
        Collaborator collaboratorRetrieved = colaboradorDAO.getCollaboratorByUVmail("contreras@uv.mx");
        Assert.assertEquals("Collaborator registered", collaborador.getRfc(), collaboratorRetrieved.getRfc());
    }
    
    @Test
    public void incorrectDuplicatedCollaboratorInsertion(){
        CollaboratorDAO colaboradorDAO = new CollaboratorDAO();
        Collaborator collaborador = new Collaborator(
            "AVFR8906245M7", "Adam López Martínez", "adam@uv.mx", 
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestría"
        );
        colaboradorDAO.addCollaborator(collaborador);
        Collaborator collaboratorRetrieved = colaboradorDAO.getCollaboratorByUVmail("adam@uv.mx");
        Assert.assertNull(collaboratorRetrieved.getRfc());
    }
    
}
