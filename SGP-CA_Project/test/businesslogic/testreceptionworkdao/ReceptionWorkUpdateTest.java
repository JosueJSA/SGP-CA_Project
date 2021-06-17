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
//public class ReceptionWorkUpdateTest {
//    
//    public final ReceptionWorkInitializer INITIALIZER = new ReceptionWorkInitializer();
//    public final ReceptionWorkDAO RECEPTIONWORK_DAO = new ReceptionWorkDAO();
//    private ReceptionWork receptionWork;
//    
//    @Test
//    public void testCorrectReceptionWorkUpdateNotChanges(){
//        ReceptionWork oldReceptionWork =RECEPTIONWORK_DAO.getReceptionWork("trabajo_recepcional.pdf");
//        RECEPTIONWORK_DAO.updateReceptionWork(oldReceptionWork, oldReceptionWork.getUrlFile());
//        ReceptionWork receptionWorkRetrieved = RECEPTIONWORK_DAO.getReceptionWork("trabajo_recepcional.pdf");
//        RECEPTIONWORK_DAO.deleteReceptionWork("trabajo_recepcional.pdf");
//        Assert.assertEquals(oldReceptionWork.getUrlFile(), receptionWorkRetrieved.getUrlFile());
//    }
//    
//    @Test
//    public void testCorrectUpdateReceptionWork(){
//        ReceptionWork oldReceptionWork = RECEPTIONWORK_DAO.getReceptionWork("trabajo_recepcional.pdf");
//        receptionWork = new ReceptionWork(
//            "trabajo_recepcional.pdf", "Crecimiento de lenguajes de programación", true, "Trabajo recepcional",
//                "Investigacion docente", "Jorge Octavio Ocharan", "2020-05-5", "Licenciatura",
//                "2020-12-12", "Mexico",  "Descripción_Cambio",
//                "Activo", 6, 11, "Tesina"
//        );
//        RECEPTIONWORK_DAO.updateReceptionWork(receptionWork, "trabajo_recepcional.pdf");
//        ReceptionWork receptionWorkRetrieved = RECEPTIONWORK_DAO.getReceptionWork("trabajo_recepcional.pdf");
//        RECEPTIONWORK_DAO.deleteReceptionWork("trabajo_recepcional.pdf");
//        Assert.assertNotEquals(oldReceptionWork, receptionWorkRetrieved);
//    }
//}