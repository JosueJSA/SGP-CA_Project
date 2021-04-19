/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package businesslogic.testintegrantdao;

import org.junit.Test;
import org.junit.Assert;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Integrant;

public class IntegrantRquestTest {
    
    private final IntegrantInitializer INITIALIZER = new IntegrantInitializer();
    
    @Test
    public void testGetExistIntegrantByUVmail(){
        INITIALIZER.prepareRequestTestCase();
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
        INITIALIZER.prepareRequestTestCase();
        IntegrantDAO integrantDao = new IntegrantDAO();
        Integrant integrant = integrantDao.getIntegrantByUVmail("angelsanchez@uv.mx");
        int StudiesNumberExpected = 2;
        Assert.assertEquals("Get integrant studies, at least 1", StudiesNumberExpected, integrant.getSchooling().size());
    }
    
}
