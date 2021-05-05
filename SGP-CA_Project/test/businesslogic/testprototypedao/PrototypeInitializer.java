/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.testprototypedao;

import businesslogic.testcollaboratordao.CollaboratorInitializer;
import businesslogic.testintegrantdao.IntegrantInitializer;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.PrototypeDAO;
import sgp.ca.domain.Prototype;

/**
 *
 * @author Josue Alarcon
 */
public class PrototypeInitializer {
    
    private final IntegrantInitializer INTEGRANT_INITIALIZER = new IntegrantInitializer();
    private final CollaboratorInitializer COLLABORATOR_INITIALIZER = new CollaboratorInitializer();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final PrototypeDAO PROTOTYPE_DAO = new PrototypeDAO();
    
    public void preparePrototypeInsertionForTest(){
        if(PROTOTYPE_DAO.getPrototypesByTitle("PrototipoEjemplo", "2020-12-12").isEmpty()){
            COLLABORATOR_INITIALIZER.prepareRequestTestCase();
            INTEGRANT_INITIALIZER.prepareRequestTestCase();
            Prototype prototype = new Prototype(
                "prototipo_prueba.pdf", "PrototipoEjemplo", 
                "Investigacion docente", "Mexico", "2020-12-12", true, "2020-03-12",
                "Jorge Octavio Ocharan", "Licenciatura", "UV-CA-127", "Ninguna"
            );
            prototype.getIntegrants().add(INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx"));
            prototype.getCollaborators().add(COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx"));
            prototype.getStudents().add("Josué Sangabriel Alarcón");

            PROTOTYPE_DAO.addPrototype(prototype);
        }
    }
    
    public void cleanInsertionsPrototypeForTest(String prototypeURL){
        PROTOTYPE_DAO.deletePrototypebyURL(prototypeURL);
    }
    
}
