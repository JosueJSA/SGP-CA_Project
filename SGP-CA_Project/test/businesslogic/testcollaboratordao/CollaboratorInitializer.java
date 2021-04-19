/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.testcollaboratordao;

import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.domain.Collaborator;


public class CollaboratorInitializer {
    
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    
    public void prepareRequestTestCase(){
        if(COLLABORATOR_DAO.getCollaboratorByUVmail("arenas@uv.mx").getRfc() == null){
            Collaborator colaborador = new Collaborator(
                "AVFR8906245M7", "María de los Ángeles Arenas Valdes", "arenas@uv.mx", 
                "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
                41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
                "Informática", "Maestrpia"
            ); 
            COLLABORATOR_DAO.addCollaborator(colaborador);
        }
    }
    
    public void cleanCollaboratorCaseTest(){
        COLLABORATOR_DAO.deleteCollaboratorByEmailUV("arenas@uv.mx");
    }
    
}
