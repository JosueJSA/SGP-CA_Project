/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package businesslogic.testintegrantdao;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Schooling;


public class IntegrantUpdateTest{
    
    private final IntegrantInitializer INITIALIZER = new IntegrantInitializer();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    
    @Test
    public void testCorrecIntegrantUpdateWithAddSchooling(){
        INITIALIZER.prepareUpdateTestCase();
        Integrant oldIntegrant =  INTEGRANT_DAO.getIntegrantByUVmail("integrantTest@uv.mx");
        Integrant newIntegrant = new Integrant(
            "SAA8906245M8", "Angel Juan Sánchez García", "angelsanchez@uv.mx", "angel", "Activo", "SAGA890624HVZNRN09", 
            "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721",
            "UV-CA-127", "PTC", "Integrante", "angelsg89@hotmail.com", "2288146210", "141912288421700"
        );     
        
        newIntegrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información", "Universidad Anáhuac de Xalapa",
            "Licenciatura", "Veracruz", "Computación", "Ingeniería", "2003-06-08", "08759567"
        ));
        
        newIntegrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información", "Universidad Anáhuac de Xalapa",
            "Maestría", "Veracruz", "Inteligencia artificial", "Ingeniería", "2003-06-08", "08759566"
        ));
        
        newIntegrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información", "Universidad veracruzana",
            "Doctorado", "Veracruz", "Ingeniería de software", "Ingeniería", "2003-06-08", "02450244"
        ));
        
        INTEGRANT_DAO.updateIntegrant(newIntegrant, oldIntegrant.getRfc());
        Integrant newIntegrantRetrieved = INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        INITIALIZER.cleanIntegrantTest(newIntegrantRetrieved.getRfc());
        Assert.assertNotEquals(oldIntegrant, newIntegrantRetrieved);
    }
    
    @Test
    public void testIncorrectIntegrantDataUpdateNotAdded(){
        INITIALIZER.prepareUpdateTestCase();
        Integrant oldIntegrant =  INTEGRANT_DAO.getIntegrantByUVmail("integrantTest@uv.mx");
        
        Integrant newIntegrant = new Integrant(
            "AAS285R5EF", "Angel Juan Sánchez García", "integrantTest@uv.mx", "angel", "Activo", "SAGA890624HVZNRN09", 
            "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721",
            "UV-CA-127", "PTC", "Integrante", "angelsg89@hotmail.com", "2288146210", "141912288421700"
        );     
        
        newIntegrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información", "Universidad Anáhuac de Xalapa",
            "Licenciatura", "Veracruz", "Computación", "Ingeniería", "20000-06-08", "08759567"                                      /*Date format incorrect -> Insertion Error*/
        ));
        
        INTEGRANT_DAO.updateIntegrant(newIntegrant, oldIntegrant.getRfc());
        Integrant newIntegrantRetrieved = INTEGRANT_DAO.getIntegrantByUVmail("integrantTest@uv.mx");
        INITIALIZER.cleanIntegrantTest("INTEGRANTETEST");
        Assert.assertEquals(oldIntegrant, newIntegrantRetrieved);
    }
    
    @Test
    public void testCorrecIntegrantUpdateWithoutSchooling(){
        INITIALIZER.prepareUpdateTestCase();
        Integrant oldIntegrant =  INTEGRANT_DAO.getIntegrantByUVmail("integrantTest@uv.mx");
        
        Integrant newIntegrant = new Integrant(
            "JAJCUYEDF2", "Angel Juan Sánchez García", "angelsanchez@uv.mx", "angel", "Activo", "SAGA890624HVZNRN09", 
            "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721",
            "UV-CA-127", "PTC", "Integrante", "angelsg89@hotmail.com", "2288146210", "141912288421700"
        );     
        
        INTEGRANT_DAO.updateIntegrant(newIntegrant, oldIntegrant.getRfc());
        Integrant newIntegrantRetrieved = INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        INITIALIZER.cleanIntegrantTest(newIntegrantRetrieved.getRfc());
        Assert.assertNotEquals(oldIntegrant, newIntegrantRetrieved);
    }
    
    @Test
    public void correctUnsubscribeCollaborator(){
        INITIALIZER.prepareRequestTestCase();
        Integrant oldIntegrant = INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        INTEGRANT_DAO.unsubscribeIntegrantByEmailUV(oldIntegrant.getEmailUV());
        Integrant integrantRetrieved = INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        INITIALIZER.cleanIntegrantTest(integrantRetrieved.getRfc());
        Assert.assertNotEquals(oldIntegrant.getParticipationStatus(), integrantRetrieved.getParticipationStatus());
    }
    
    @Test
    public void correctSubscribeCollaborator(){
        INITIALIZER.prepareRequestTestCase();
        Integrant oldIntegrant = INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        INTEGRANT_DAO.unsubscribeIntegrantByEmailUV(oldIntegrant.getEmailUV());
        oldIntegrant = INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        INTEGRANT_DAO.subscribeIntegrantByEmailUV(oldIntegrant.getEmailUV());
        Integrant integrantRetrieved = INTEGRANT_DAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        INITIALIZER.cleanIntegrantTest(integrantRetrieved.getRfc());
        String stateExpected = "Activo";
        Assert.assertEquals(stateExpected, integrantRetrieved.getParticipationStatus());
    }
    
}
