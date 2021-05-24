///**
// *
// * @author johan
// */
//package businesslogic.testreceptionworkdao;
//
//import org.junit.Assert;
//import org.junit.Test;
//import sgp.ca.businesslogic.ReceptionWorkDAO;
//import sgp.ca.domain.ReceptionWork;
//
//
//public class ReceptionWorkRequestTest {
//    
//    public final ReceptionWorkInitializer INITIALIZER = new ReceptionWorkInitializer();
//    public final ReceptionWorkDAO RECEPTIONWORK_DAO = new ReceptionWorkDAO();
//    
//    @Test
//    public void testGetExistReceptionWorkByUrlFile(){
//        INITIALIZER.prepareReceptionWorkInsertionForTest();
//        ReceptionWork receptionWorkRetrieved = RECEPTIONWORK_DAO.getReceptionWork("trabajo_recepcional.pdf");
//        String supposedReceptionWork = "trabajo_recepcional.pdf";
//        Assert.assertEquals(supposedReceptionWork, receptionWorkRetrieved.getUrlFile());
//    }
//    
//    @Test
//    public void testGetNotExistReceptionWorKByUrlFile(){
//        ReceptionWork receptionWorkRetrieved = RECEPTIONWORK_DAO.getReceptionWork("trabajo.pdf");
//        Assert.assertNull(receptionWorkRetrieved.getUrlFile());
//    }
//}
