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
    
    @Test
    public void testCorrecIntegrantUpdateWithAddSchooling(){
        INITIALIZER.prepareUpdateTestCase();
        IntegrantDAO integrantDAO = new IntegrantDAO();
        Integrant oldIntegrant =  integrantDAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        Integrant newIntegrant = new Integrant(
            "SAA8906245M8", "Angel Juan Sánchez García", "angelsanchez@uv.mx", "angel", "SAGA890624HVZNRN09", 
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
        
        integrantDAO.updateIntegrant(newIntegrant, oldIntegrant.getRfc());
        Integrant newIntegrantRetrieved = integrantDAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        Assert.assertNotEquals(oldIntegrant, newIntegrantRetrieved);
    }
    
    @Test
    public void testIncorrectIntegrantDataUpdateNotAdded(){
        INITIALIZER.prepareUpdateTestCase();
        IntegrantDAO integrantDAO = new IntegrantDAO();
        Integrant oldIntegrant =  integrantDAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        
        Integrant newIntegrant = new Integrant(
            "AAS285R5EF", "Angel Juan Sánchez García", "angelsanchez@uv.mx", "angel", "SAGA890624HVZNRN09", 
            "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721",
            "UV-CA-127", "PTC", "Integrante", "angelsg89@hotmail.com", "2288146210", "141912288421700"
        );     
        
        newIntegrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información", "Universidad Anáhuac de Xalapa",
            "Licenciatura", "Veracruz", "Computación", "Ingeniería", "20000-06-08", "08759567"                                      /*Date format incorrect -> Insertion Error*/
        ));
        
        integrantDAO.updateIntegrant(newIntegrant, oldIntegrant.getRfc());
        Integrant newIntegrantRetrieved = integrantDAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        
        Assert.assertEquals(oldIntegrant, newIntegrantRetrieved);
    }
    
    @Test
    public void testCorrecIntegrantUpdateWithoutSchooling(){
        INITIALIZER.prepareUpdateTestCase();
        IntegrantDAO integrantDAO = new IntegrantDAO();
        Integrant oldIntegrant =  integrantDAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        
        Integrant newIntegrant = new Integrant(
            "JAJCUYEDF2", "Angel Juan Sánchez García", "angelsanchez@uv.mx", "angel", "SAGA890624HVZNRN09", 
            "Mexicano", "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721",
            "UV-CA-127", "PTC", "Integrante", "angelsg89@hotmail.com", "2288146210", "141912288421700"
        );     
        
        integrantDAO.updateIntegrant(newIntegrant, oldIntegrant.getRfc());
        Integrant newIntegrantRetrieved = integrantDAO.getIntegrantByUVmail("angelsanchez@uv.mx");
        Assert.assertNotEquals(oldIntegrant, newIntegrantRetrieved);
    }
    
}
