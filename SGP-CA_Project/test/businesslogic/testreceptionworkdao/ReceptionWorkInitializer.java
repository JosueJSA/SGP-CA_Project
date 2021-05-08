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
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.ReceptionWork;


public class ReceptionWorkInitializer {
    
    private final ReceptionWorkDAO ReceptionWorkDAO = new ReceptionWorkDAO();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final IntegrantInitializer INTEGRANT_INITIALIZER = new IntegrantInitializer();
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final CollaboratorInitializer COLLABORATOR_INITIALIZER = new CollaboratorInitializer();
 
    public void preparePrototypeInsertionForTest(){
        if(ReceptionWorkDAO.getReceptionWorkList().isEmpty()){
            COLLABORATOR_INITIALIZER.prepareRequestTestCase();
            INTEGRANT_INITIALIZER.prepareRequestTestCase();
            ReceptionWork receptionWork = new ReceptionWork(
                "trabajo_recepcional.pdf", "Nombre_trabajo", true, "UV-CA-127",
                "Investigacion docente", "Jorge Octavio Ocharan", "2020-05-5", "Licenciatura",
                "2020-12-12", "Mexico",  "Descripción_prueba",
                "Activo", 6, 11, "Tesina"
            );
            receptionWork.getIntegrants().add(INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx"));
            receptionWork.getCollaborators().add(COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx"));
            receptionWork.getStudents().add("Josué Sangabriel Alarcón");

            ReceptionWorkDAO.addReceptionWork(receptionWork);
        }
    }
    
    public void cleanInsertionsPrototypeForTest(String receptionWorkUrl){
        ReceptionWorkDAO.deleteReceptionWork(receptionWorkUrl);
    }
}
