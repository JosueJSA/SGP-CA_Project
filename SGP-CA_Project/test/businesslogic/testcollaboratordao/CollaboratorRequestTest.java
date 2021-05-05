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
        Collaborator integrant = integrantDao.getCollaboratorByUVmail("prueba@uv.mx");
        System.out.println(integrant.getRfc());
        String rfcExcpected = "COLABORADORTEST";
        String rfcRetrived = integrant.getRfc(); 
        INITIALIZER.cleanCollaboratorTest(rfcRetrived); 
        Assert.assertEquals(rfcExcpected, rfcRetrived);
    }
    
    @Test
    public void testGetNotExistCollaboratorByUVmail(){
        CollaboratorDAO integrantDao = new CollaboratorDAO();
        Collaborator integrant = integrantDao.getCollaboratorByUVmail("arenasss@uv.mx");
        String rfcRetrived = integrant.getRfc();
        Assert.assertNull(rfcRetrived);
    }
}
