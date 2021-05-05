/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package businesslogic.testcollaboratordao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Collaborator;


public class CollaboratorInitializer {
    
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    
    public void prepareRequestTestCase(){
        if(COLLABORATOR_DAO.getCollaboratorByUVmail("prueba@uv.mx").getRfc() == null){
            Collaborator colaborador = new Collaborator(
                "COLABORADORTEST", "María de los Ángeles Arenas Valdes", "prueba@uv.mx", "Activo", 
                "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 
                41306, "2281394721", "UV-CA-127", "Ingeniería y Tecnologías de software", 
                "Informática", "Maestrpia"
            ); 
            COLLABORATOR_DAO.addCollaborator(colaborador);
        }
    }
    
    public void cleanCollaboratorTest(String rfcCollaborator){
        Connection connection = CONNECTION.getConnectionDatabase();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Collaborator WHERE rfc = ?;"
            );
            sentenceQuery.setString(1, rfcCollaborator);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Collaborator.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
        }    
    }
    
}
