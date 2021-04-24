
package TestReceptionWork;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businessLogic.ReceptionWorkDAO;
import sgp.ca.domain.ReceptionWork;

public class ReceptionWorkUpdateTest {
    
    @Test
    public void updateReceptionWorkTest(){
        System.out.println("updateReceptionWork");
        ReceptionWork receptionWork = new ReceptionWork("http...com","Crecimiento de lenguajes de programación", 
                "true", "Analisis de metodologias", "2021-05-07", "México", "Comprobar perspectivas...", "Activo", 1, 4, "Tesina");
        ReceptionWorkDAO instanceReceptionWork = new ReceptionWorkDAO();
        Assert.assertTrue(instanceReceptionWork.updateReceptionWork(receptionWork, "Z"));
    }
    
}
