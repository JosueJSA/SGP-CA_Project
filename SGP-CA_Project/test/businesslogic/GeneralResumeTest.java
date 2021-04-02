/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package businesslogic;

import org.junit.Test;
import org.junit.Assert;
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.domain.GeneralResume;

public class GeneralResumeTest {
    
    @Test
    public void testGetGeneralResumeByKeyNotNull(){
        GeneralResumeDAO generalResumeDAO = new GeneralResumeDAO();
        GeneralResume generalResume = generalResumeDAO.getGeneralResume("UV-CA-127");
        String bodyKeyExpected = "Ingenieria y Tecnología de Software";
        String bodyKeyRetrived = generalResume.getBodyAcademyName();
        Assert.assertEquals("Get exist body academy from database", bodyKeyExpected, bodyKeyRetrived);
    }
    
    @Test
    public void testGetGeneralResumeNotRegistered(){
        GeneralResumeDAO generalResumeDAO = new GeneralResumeDAO();
        GeneralResume generalResume = generalResumeDAO.getGeneralResume("UV-CA-3005247");
        String bodyKeyRetrived = generalResume.getBodyAcademyName();
        Assert.assertNull(bodyKeyRetrived);
    }
}
