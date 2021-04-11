/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package businesslogic;

import org.junit.Test;
import org.junit.Assert;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Integrant;

public class IntegrantRquestTest {
    
    @Test
    public void testGetExistIntegrantByUVmail(){
        IntegrantDAO integrantDao = new IntegrantDAO();
        Integrant integrant = integrantDao.getIntegrantByUVmail("angelsanchez@uv.mx");
        String rfcExpected = "SAGA8906245M7";
        String rfcRetrived = integrant.getRfc();
        Assert.assertEquals("Get exist integrant from database", rfcExpected, rfcRetrived);
    }
    
    @Test
    public void testGetIntegrantNotRegisteredByUVmail(){
        IntegrantDAO integrantDao = new IntegrantDAO();
        Integrant integrant = integrantDao.getIntegrantByUVmail("joijeoijd");
        String rfcRetrived = integrant.getRfc();
        Assert.assertNull(rfcRetrived);
    }
    
    @Test 
    public void testGetExistIntegrantStudies(){
        IntegrantDAO integrantDao = new IntegrantDAO();
        Integrant integrant = integrantDao.getIntegrantByUVmail("angelsanchez@uv.mx");
        int StudiesNumberExpected = 0;
        Assert.assertNotEquals("Get integrant studies, at least 1", StudiesNumberExpected, integrant.getSchooling().size());
    }
    
}
