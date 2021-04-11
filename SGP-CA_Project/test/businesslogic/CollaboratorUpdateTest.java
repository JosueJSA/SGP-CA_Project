/**
* @author Josué Alarcón  
* Last modification date format: 05-04-2021
*/

package businesslogic;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.domain.Collaborator;


public class CollaboratorUpdateTest {
    
    @Test
    public void correctUpdatedCollaborator(){
        CollaboratorDAO colaboradorDAO = new CollaboratorDAO();
        Collaborator oldCollaborator = colaboradorDAO.getCollaboratorByUVmail("arenas@uv.mx");
        Collaborator newCollaborador = new Collaborator(
            "GRTCEVSFTJRB", "María de los Ángeles Arenas Valdes", "arenas@uv.mx", 
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestría"
        );
        colaboradorDAO.updateCollaborator(newCollaborador, oldCollaborator.getRfc());
        Assert.assertNotEquals("Rfc collaborator different", oldCollaborator.getRfc(), colaboradorDAO.getCollaboratorByUVmail("arenas@uv.mx").getRfc());
    }
    
    @Test
    public void incorrectUpdatedCollaboratorNotRegistered(){
        CollaboratorDAO colaboradorDAO = new CollaboratorDAO();
        Collaborator oldCollaborator = colaboradorDAO.getCollaboratorByUVmail("arenas@uv.mx");
        Collaborator newCollaborador = new Collaborator(
            "EFFYEVZPF", "Gerardo Contreras Vega", "contreras@uv.mx", 
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UVV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestría"
        );
        colaboradorDAO.updateCollaborator(newCollaborador, oldCollaborator.getRfc());
        Assert.assertEquals("Rfc collaborator equal", oldCollaborator.getRfc(), colaboradorDAO.getCollaboratorByUVmail("arenas@uv.mx").getRfc());
    }
    
}
