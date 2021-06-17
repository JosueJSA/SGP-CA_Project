///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package businesslogic.testevidences;
//
//import java.util.Map;
//import org.junit.Assert;
//import org.junit.Test;
//import sgp.ca.businesslogic.EvidenceDAO;
//import sgp.ca.businesslogic.PrototypeDAO;
//import sgp.ca.domain.Evidence;
//
//public class EvidencesRequesTest {
//    
//    private final EvidenceInitializer INITIALIZER = new EvidenceInitializer();
//    private final EvidenceDAO EVIDENCE_DAO = new PrototypeDAO();
//    
//    @Test
//    public void testCorrectAllEvidencesRequest(){
//        INITIALIZER.prepareEvidencesForTest();
//        boolean isCorrect = true;
//        if(EVIDENCE_DAO.getAllEvidences().size() > 0){
//            for(Evidence evidence : EVIDENCE_DAO.getAllEvidences()){
//                if(this.coincidencesNumber(evidence.getUrlFile().toString()) > 1){
//                    isCorrect = false;
//                }
//            }
//        }else{
//            isCorrect = false;
//        }
//        INITIALIZER.cleanEvidencesForTest();
//        Assert.assertEquals(true, isCorrect);
//    }
//    
//    @Test
//    public void testCorrectEvidencesRequestByDate(){
//        INITIALIZER.prepareEvidencesForTest();
//        boolean isCorrect = false;
//        if(EVIDENCE_DAO.getEvidencesByRegistrationDate("2010-01-11").size() > 0){
//            isCorrect = true;
//        }
//        INITIALIZER.cleanEvidencesForTest();
//        Assert.assertEquals(true, isCorrect);
//    }
//    
//    @Test
//    public void testCorrectEvidenceRequestByStudent(){
//        INITIALIZER.prepareEvidencesForTest();
//        boolean isCorrect = false;
//        if(EVIDENCE_DAO.getEvidencesByStudent("Bere Martínez").size() == 1){
//            isCorrect = true;
//        }
//        INITIALIZER.cleanEvidencesForTest();
//        Assert.assertEquals(true, isCorrect);
//    }
//    
//    @Test
//    public void testCorrectEvidenceRequestByEmailUv(){
//        INITIALIZER.prepareEvidencesForTest();
//        boolean isCorrect = false;
//        if(EVIDENCE_DAO.getAllEvidencesByIntegrantMailUv("integrantTest@uv.mx").size() == 2){
//            isCorrect = true;
//        }
//        INITIALIZER.cleanEvidencesForTest();
//        Assert.assertEquals(true, isCorrect);
//    }
//    
//    private int coincidencesNumber(String urlFile){
//        int coincidences = 0;
//        for(Map<String, Object> element : EVIDENCE_DAO.getAllBodyAcademyEvidences()){
//            if(element.get("urlFile").toString().equalsIgnoreCase(urlFile)){
//                coincidences++;
//            }
//        }
//        return coincidences;
//    }
//    
//}
