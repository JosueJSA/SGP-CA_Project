/**
 *
 * @author Johann
 */
package businesslogic.testreceptionworkdao;

import businesslogic.testcollaboratordao.CollaboratorInitializer;
import businesslogic.testintegrantdao.IntegrantInitializer;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.ReceptionWorkDAO;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.ReceptionWork;


public class ReceptionWorkInitializer {
    
    private final ReceptionWorkDAO ReceptionWorkDAO = new ReceptionWorkDAO();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final IntegrantInitializer INTEGRANT_INITIALIZER = new IntegrantInitializer();
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final CollaboratorInitializer COLLABORATOR_INITIALIZER = new CollaboratorInitializer();
 
    public void prepareReceptionWorkInsertionForTest(){
        if(ReceptionWorkDAO.getReceptionWork("trabajo_recepcional.pdf") == null){
            COLLABORATOR_INITIALIZER.prepareRequestTestCase();
            INTEGRANT_INITIALIZER.prepareRequestTestCase();
            ReceptionWork receptionWork = new ReceptionWork(
                "trabajo_recepcional.pdf", "Crecimiento de lenguajes de programación", true, "Trabajo recepcional",
                "Investigacion docente", "Jorge Octavio Ocharan", "2020-05-5", "Licenciatura",
                "2020-12-12", "Mexico",  "Descripción_prueba",
                "Activo", 6, 11, "Tesina"
            );
            receptionWork.getRequirements().add("Requisitos de prueba");
            receptionWork.getStudents().add("Josué Sangabriel Alarcón");
            receptionWork.getIntegrants().add(new Integrant(
                "OGA8903456J", "Jorge Octavio Ocharán Hernpandez", "ocharan@uv.mx", "ocharan", "Activo", "OOOH890624HVZNRN09", "Mexicano", 
                "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721", "UV-CA-127",
                "PTC", "Integrante", "angelsg89@hotmail.com", "2288146210", "141912288421700"
            ));
            ReceptionWorkDAO.addReceptionWork(receptionWork);
        }
    }
    
    public void cleanInsertionsReceptionWorkForTest(String receptionWorkUrl){
        ReceptionWorkDAO.deleteReceptionWork(receptionWorkUrl);
    }
}
