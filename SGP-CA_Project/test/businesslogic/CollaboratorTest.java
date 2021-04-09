/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.domain.Collaborator;

/**
 *
 * @author Josue Alarcon
 */
public class CollaboratorTest {
    
    @Test
    public void testGetExistCollaboratorByUVmail(){
        CollaboratorDAO integrantDao = new CollaboratorDAO();
        Collaborator integrant = integrantDao.getCollaboratorByUVmail("arenas@uv.mx");
        String rfcExcpected = "SAGA8906245M7";
        String rfcRetrived = integrant.getRfc();
        Assert.assertEquals("Get exist collaborator from database", rfcExcpected, rfcRetrived);
    }
}
