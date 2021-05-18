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

public class IntegrantInsertTest{
    
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final IntegrantInitializer INITIALIZER = new IntegrantInitializer();
    
    @Test
    public void correctIntegrantInsertion(){
        Integrant integrant = new Integrant(
            "OGA8903456J", "Jorge Octavio Ocharán Hernpandez", "ocharan@uv.mx", "ocharan", "Activo", "OOOH890624HVZNRN09", "Mexicano", 
            "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721", "UV-CA-127",
            "PTC", "Integrante", "angelsg89@hotmail.com", "2288146210", "141912288421700"
        );     
        
        integrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información", "Universidad Anáhuac de Xalapa",
            "Maestría", "Veracruz", "Computación", "Ingeniería", "2003-06-08", "09759567"
        ));
        
        integrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información", "Universidad Veracruzana de Xalapa",
            "Doctorado", "Veracruz", "Computación", "Ingeniería", "2003-06-08", "01159566"
        ));
        
        INTEGRANT_DAO.addIntegrant(integrant);
        Integrant integrantRetrieved = INTEGRANT_DAO.getIntegrantByUVmail("ocharan@uv.mx");
        //INITIALIZER.cleanIntegrantTest(integrantRetrieved.getRfc());
        Assert.assertEquals(integrant, integrantRetrieved);
    }
    
    @Test
    public void incorrectDuplicatedSchoolingIntegrantInsertion(){
        INITIALIZER.prepareRequestTestCase();
        Integrant integrant = new Integrant(
            "COLABORADORTEST", "Karen Verdín Cortés", "karen@uv.mx", "karen", "Activo", "KDV890624HVZNRN09", "Mexicana", 
            "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721", "UV-CA-127",
            "PTC", "Integrante", "karen@hotmail.com", "2288146210", "141912288421700"
        );     
        
        integrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información", "Universidad Anáhuac de Xalapa",
            "Maestría", "Veracruz", "Computación", "Ingeniería", "2003-06-08", "08759566"
        ));
        
        INTEGRANT_DAO.addIntegrant(integrant);
        Integrant integrantRetireved = INTEGRANT_DAO.getIntegrantByUVmail("karen@uv.mx");
        INITIALIZER.cleanIntegrantTest("INTEGRANTETEST");
        Assert.assertNull(integrantRetireved.getBodyAcademyKey());
    }
    
}
