/**
 *
 * @author johan
 */

package businesslogic.testreceptionworkdao;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.ReceptionWorkDAO;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Lgac;
import sgp.ca.domain.ReceptionWork;

public class ReceptionWorkInsertTest {
    
    public final ReceptionWorkInitializer INITIALIZER = new ReceptionWorkInitializer();
    public final ReceptionWorkDAO RECEPTIONWORK_DAO = new ReceptionWorkDAO();
    private ReceptionWork receptionWork;
    
    @Test
    public void testCorrectReceptionWorkInsert(){
        receptionWork = new ReceptionWork(
                "trabajo_recepcional.pdf", "Crecimiento de lenguajes de programación", true, "Trabajo recepcional",
                "Investigacion docente", "Jorge Octavio Ocharan", "2020-05-5", "Licenciatura",
                "2020-12-12", "Mexico",  "Descripción_prueba",
                "Activo", 6, 11, "Tesina"
            );
        receptionWork.getRequirements().add("Requisitos de prueba");
        receptionWork.getStudents().add("Estefania Martinez");
        receptionWork.getStudents().add("Johann Alexis");
        receptionWork.getStudents().add("Josue SanGabriel");
        receptionWork.getIntegrants().add(new Integrant(
            "OGA8903456J", "Jorge Octavio Ocharán Hernpandez", "ocharan@uv.mx", "ocharan", "Activo", "OOOH890624HVZNRN09", "Mexicano", 
            "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721", "UV-CA-127",
            "PTC", "Integrante", "angelsg89@hotmail.com", "2288146210", "141912288421700"
        ));   
        receptionWork.getCollaborators().add(new Collaborator(
            "AVFR8906245M7", "María de los Ángeles Arenas Valdes", "arenas@uv.mx", "Activo", 
            "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
            41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
            "Informática", "Maestrpia"
        )); 
        INITIALIZER.cleanInsertionsReceptionWorkForTest("trabajo_recepcional.pdf");
        RECEPTIONWORK_DAO.addReceptionWork(receptionWork);
        ReceptionWork receptionWorkRetrieved = RECEPTIONWORK_DAO.getReceptionWork("trabajo_recepcional.pdf"); 
        Assert.assertEquals(receptionWork.getUrlFile(), receptionWorkRetrieved.getUrlFile());
    }
     
    
    @Test
    public void testIncorrectReceptionWorkInsertionDuplicated(){
        INITIALIZER.prepareReceptionWorkInsertionForTest();
        String oldReceptionWorkUrlFile = "trabajo_recepcional.pdf";
        receptionWork = new ReceptionWork(
            "trabajo_recepcional.pdf", "Crecimiento de lenguajes de programación", true, "Trabajo recepcional",
                "Investigacion docente", "Jorge Octavio Ocharan", "2020-05-5", "Licenciatura",
                "2020-12-12", "Mexico",  "Descripción_prueba",
                "Activo", 6, 11, "Tesina"
        );
        INITIALIZER.cleanInsertionsReceptionWorkForTest("trabajo_recepcional.pdf");
        RECEPTIONWORK_DAO.addReceptionWork(receptionWork);
        ReceptionWork receptionWorkRetrieved = RECEPTIONWORK_DAO.getReceptionWork("trabajo_recepcional.pdf");
        Assert.assertEquals(oldReceptionWorkUrlFile, receptionWorkRetrieved.getUrlFile());
    }
}
