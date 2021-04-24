/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.testintegrantdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Schooling;


public class IntegrantInitializer {
    
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    
    public void prepareRequestTestCase(){
        if(INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx").getRfc() == null){
            Integrant integrant = new Integrant(
                "SAGA8906245M7", "Angel Juan Sánchez García", "angelsanchez@uv.mx",
                "password", "Activo", "SAGA890624HVZNRN09", "Mexicano", "2012-08-12", 
                "Licenciatura en Ingeniería de Software", 41306, "2281394721",
                "UV-CA-127", "PTC", "Integrante", "angelsg89@hotmail.com", "2288146210", 
                "141912288421700"
            );     

            integrant.addSchooling(new Schooling(
                "Lic. en Ingeniería en Tecnologías Estratégicas de la Información",
                "Universidad Anáhuac de Xalapa", "Licenciatura", "Veracruz", "Computación",
                "Ingeniería", "2003-06-08", "08759567"
            ));

            integrant.addSchooling(new Schooling(
                "Lic. en Ingeniería en Tecnologías Estratégicas de la Información",
                "Universidad Anáhuac de Xalapa", "Maestría", "Veracruz", "Computación",
                "Ingeniería", "2003-06-08", "08759566"
            ));
            INTEGRANT_DAO.addIntegrant(integrant);
        }
    }
    
    public void prepareUpdateTestCase(){
        this.prepareRequestTestCase();
        Integrant integrantRetrieved = INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        integrantRetrieved.setRfc("SAGA8906245M7");
        INTEGRANT_DAO.updateIntegrant(integrantRetrieved, INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx").getRfc());
    }
    
    public void cleanIntegrantTest(String rfcIntegrant){
        Connection connection = CONNECTION.getConnectionDatabase();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Integrant WHERE rfc = ?;"
            );
            sentenceQuery.setString(1, rfcIntegrant);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
        }    
    }
    
}
