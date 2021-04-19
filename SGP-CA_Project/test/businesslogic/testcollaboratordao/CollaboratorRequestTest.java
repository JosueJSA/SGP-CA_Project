/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.testcollaboratordao;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.domain.Collaborator;

/**
 *
 * @author Josue Alarcon
 */
public class CollaboratorRequestTest {
    
    private final CollaboratorInitializer INITIALIZER = new CollaboratorInitializer();
    
    @Test
    public void testGetExistCollaboratorByUVmail(){
        INITIALIZER.prepareRequestTestCase();
        CollaboratorDAO integrantDao = new CollaboratorDAO();
        Collaborator integrant = integrantDao.getCollaboratorByUVmail("arenas@uv.mx");
        String rfcExcpected = "AVFR8906245M7";
        String rfcRetrived = integrant.getRfc();
        INITIALIZER.cleanCollaboratorCaseTest();
        Assert.assertEquals("Get exist collaborator from database", rfcExcpected, rfcRetrived);
    }
    
    @Test
    public void testGetNotExistCollaboratorByUVmail(){
        CollaboratorDAO integrantDao = new CollaboratorDAO();
        Collaborator integrant = integrantDao.getCollaboratorByUVmail("arenasss@uv.mx");
        String rfcRetrived = integrant.getRfc();
        Assert.assertNull(rfcRetrived);
    }
}
