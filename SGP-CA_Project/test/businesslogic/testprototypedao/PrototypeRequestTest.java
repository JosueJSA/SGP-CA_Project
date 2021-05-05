/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.testprototypedao;

import java.util.List;
import org.junit.Test;
import org.junit.Assert;
import sgp.ca.businesslogic.PrototypeDAO;
import sgp.ca.domain.Prototype;

public class PrototypeRequestTest{
    
    private final PrototypeDAO PROTOTYPE_DAO = new PrototypeDAO();
    private final PrototypeInitializer INITIALIZER = new PrototypeInitializer();
    
    @Test
    public void testCorrectExistPrototypeRequest(){
        INITIALIZER.preparePrototypeInsertionForTest();
        boolean comlpetePrototypeRetrieved = false;
        List<Prototype> prototypesRetrieved = PROTOTYPE_DAO.getPrototypesByTitle("PrototipoEjemplo", "2020-12-12");
        if(prototypesRetrieved.size() == 1){
            if(checkParticipantsNumberIntoPrototype(prototypesRetrieved.get(0))){
                comlpetePrototypeRetrieved = true;
            }
        }
        Assert.assertTrue(comlpetePrototypeRetrieved);
    }
    
    public boolean checkParticipantsNumberIntoPrototype(Prototype prototype){
        int collaboratorsSizeExpected = 1;
        int integrantsSizeExpected = 1;
        int studentsSizeExpected = 1;
        boolean correctParticipantSize = false;
        if(prototype.getCollaborators().size() == collaboratorsSizeExpected){
            if(prototype.getIntegrants().size() == integrantsSizeExpected && prototype.getStudents().size() == studentsSizeExpected){
                correctParticipantSize = true;
            }
        }
        return correctParticipantSize;
    }
    
}
