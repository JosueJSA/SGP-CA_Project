/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package businesslogic;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Schooling;


public class IntegrantInsertTest{
    
    @Test
    public void correctIntegrantInsertion(){
        Integrant integrant = new Integrant(
            "OGA8903456J", "Jorge Octavio Ocharán Hernpandez", "ocharan@uv.mx", "OOOH890624HVZNRN09", "Mexicano", 
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
        IntegrantDAO integrantDAO = new IntegrantDAO();
        integrantDAO.addIntegrant(integrant);
        Assert.assertEquals("Jorge Octavio Ocharán Hernpandez", integrantDAO.getIntegrantByUVmail("ocharan@uv.mx").getFullName());
        Assert.assertEquals(2, integrantDAO.getIntegrantByUVmail("ocharan@uv.mx").getSchooling().size());
    }
    
    @Test
    public void incorrectDuplicatedSchoolingIntegrantInsertion(){
        Integrant integrant = new Integrant(
            "KCV8903456J", "Karen Verdín Cortés", "karen@uv.mx", "KDV890624HVZNRN09", "Mexicana", 
            "2012-08-12", "Licenciatura en Ingeniería de Software", 41306, "2281394721", "UV-CA-127",
            "PTC", "Integrante", "karen@hotmail.com", "2288146210", "141912288421700"
        );     
        
        integrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información", "Universidad Anáhuac de Xalapa",
            "Maestría", "Veracruz", "Computación", "Ingeniería", "2003-06-08", "08759566"
        ));
        IntegrantDAO integrantDAO = new IntegrantDAO();
        integrantDAO.addIntegrant(integrant);
        Assert.assertNull(integrantDAO.getIntegrantByUVmail("karen@uv.mx").getFullName());
        Assert.assertEquals(0, integrantDAO.getIntegrantByUVmail("karen@uv.mx").getSchooling().size());
    }
    
}
